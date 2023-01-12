package com.kmek.bobamod.block.entity;

import com.kmek.bobamod.item.ModItemsInit;
import com.kmek.bobamod.item.WaffleMoldItem;
import com.kmek.bobamod.screen.WaffleIronMenu;
import com.kmek.bobamod.tags.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class WaffleIronBlockEntity extends BlockEntity implements MenuProvider {
    public static final int menuSlotCount = 4;
    public static final int dataFieldsCount = 2;

    private static final int SLOT_FILLING = 3;
    private static final int SLOT_BATTER = 0;
    private static final int SLOT_MOLD = 2;
    private static final int SLOT_OUTPUT = 1;

    private final ItemStackHandler itemHandler = new ItemStackHandler(menuSlotCount) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;

    private int progress = 0;
    private static final int batteredMessTime = 24; // 120
    private static final int cookedTime = 48; // 240
    private static final int maxProgress = 60; // 300

    public WaffleIronBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.WAFFLE_IRON.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> WaffleIronBlockEntity.this.progress;
                    case 1 -> WaffleIronBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch(pIndex) {
                    case 0 -> WaffleIronBlockEntity.this.progress = pValue;
//                    case 1 -> WaffleIronBlockEntity.this.maxProgress = pValue;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Waffle Iron");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new WaffleIronMenu(pContainerId, pPlayerInventory, this, this.data);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemHandler.serializeNBT());

        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
    }

    // Spills the inventory of the block
    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public static void tick(Level level, BlockPos blockPos, BlockState blockState, WaffleIronBlockEntity entity) {
        if (level.isClientSide) {
            return;
        }

        // todo eventually if "open" blockstate is true then return to not increment progress

        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        if (entity.progress == 0) {
            if (canMoveItem(inventory)) {
                moveItem(entity);
                entity.progress++;
                setChanged(level, blockPos, blockState);
            }
//            else if (isCooking(inventory)) {
//                // todo set entity progress
//                entity.progress = getResumeProgress(inventory);
//                cook(entity);
//                setChanged(level, blockPos, blockState);
//            }
        } else if (isCooking(inventory)) {
            entity.progress++;
            cook(entity);
            setChanged(level, blockPos, blockState);
        } else {
            entity.resetProgress();
            setChanged(level, blockPos, blockState);
        }
    }

    private static boolean itemIsRawWaffleBatter(Item item) {
        return item == ModItemsInit.RAW_WAFFLE_BATTER.get();
    }

    private static boolean itemIsBatterMess(Item item) {
        return item == ModItemsInit.BATTER_MESS.get();
    }

    private static boolean itemIsWaffle(ItemStack itemStack) {
        return itemStack.is(ModTags.Items.WAFFLE);
    }

    private static boolean hasWaffleMold(SimpleContainer inventory) {
        return !inventory.getItem(SLOT_MOLD).isEmpty()
                && inventory.getItem(SLOT_MOLD).is(ModTags.Items.WAFFLE_MOLD);
    }

    private static boolean canMoveItem(SimpleContainer inventory) {
        return inventory.getItem(SLOT_OUTPUT).isEmpty()
                && itemIsRawWaffleBatter(inventory.getItem(SLOT_BATTER).getItem())
                && hasWaffleMold(inventory);
    }

    private static void moveItem(WaffleIronBlockEntity entity) {
        entity.itemHandler.setStackInSlot(SLOT_OUTPUT, entity.itemHandler.extractItem(SLOT_BATTER, 1, false));
    }

//    private static int getResumeProgress(SimpleContainer inventory) {
//        ItemStack itemStack = inventory.getItem(SLOT_OUTPUT);
//        if (itemIsRawWaffleBatter(itemStack.getItem())) {
//            return 0;
//        } else if (itemIsBatterMess(itemStack.getItem())) {
//            return batteredMessTime;
//        } else if (itemIsWaffle(itemStack)) {
//            return cookedTime;
//        }
//        return maxProgress;
//    }

    private static boolean isCooking(SimpleContainer inventory) {
        ItemStack itemStack = inventory.getItem(SLOT_OUTPUT);

        return inventory.getItem(SLOT_OUTPUT).getCount() == 1
                && hasWaffleMold(inventory)
                && (itemIsRawWaffleBatter(itemStack.getItem()) || itemIsBatterMess(itemStack.getItem()) || itemIsWaffle(itemStack));
    }

    private static void cook(WaffleIronBlockEntity entity) {
        if (entity.progress == batteredMessTime) { // must be raw batter, turns to batter mess
            entity.itemHandler.setStackInSlot(SLOT_OUTPUT, new ItemStack(ModItemsInit.BATTER_MESS.get(), 1));
        } else if (entity.progress == cookedTime) { // must be batter mess, turns to waffle
            entity.itemHandler.setStackInSlot(SLOT_OUTPUT,
                    new ItemStack(((WaffleMoldItem) entity.itemHandler.getStackInSlot(SLOT_MOLD).getItem()).getWaffle(), 1));
        } else if (entity.progress >= maxProgress) { // must be waffle, burns
            entity.itemHandler.setStackInSlot(SLOT_OUTPUT, new ItemStack(ModItemsInit.BURNT_CRISP.get(), 1));
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }
}