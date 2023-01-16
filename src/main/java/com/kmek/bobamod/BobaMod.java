package com.kmek.bobamod;

import com.kmek.bobamod.block.ModBlocksInit;
import com.kmek.bobamod.block.entity.ModBlockEntities;
import com.kmek.bobamod.item.ModItemsInit;
import com.kmek.bobamod.loot.ModLootModifiers;
import com.kmek.bobamod.networking.ModMessages;
import com.kmek.bobamod.painting.ModPaintingsInit;
import com.kmek.bobamod.screen.CakeStandScreen;
import com.kmek.bobamod.screen.DisplayCaseScreen;
import com.kmek.bobamod.screen.ModMenuTypes;
import com.kmek.bobamod.screen.WaffleIronScreen;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(BobaMod.MODID)
public class BobaMod {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "bobamod";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    CreativeModeTab bobaTab;

    public BobaMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItemsInit.register(modEventBus);
        ModBlocksInit.register(modEventBus);

        ModPaintingsInit.register(modEventBus);

        ModLootModifiers.register(modEventBus);

        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);
        modEventBus.addListener(this::registerTabs);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");
        LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));

        ModMessages.register();
    }

    // todo bug: this tab has no title
    private void registerTabs(CreativeModeTabEvent.Register event) {
         bobaTab = event.registerCreativeModeTab(new ResourceLocation(MODID, "bobamod"), builder -> builder
                .icon(() -> new ItemStack(ModItemsInit.BROWN_SUGAR_MILK_TEA.get()))
                .displayItems((featureFlags, output, hasOp) -> {
                    output.accept(ModItemsInit.CAKE_STAND_ITEM.get());
                    output.accept(ModItemsInit.DISPLAY_CASE_CURVED_ITEM.get());
                    output.accept(ModItemsInit.WAFFLE_IRON_ITEM.get());
                    output.accept(ModItemsInit.ESPRESSO_MACHINE_ITEM.get());

                    output.accept(ModItemsInit.CASSAVA_CUTTING.get());
                    output.accept(ModItemsInit.CASSAVA.get());
                    output.accept(ModItemsInit.CASSAVA_BIBINGKA.get());
                    output.accept(ModItemsInit.TAPIOCA_BALLS.get());
                    output.accept(ModItemsInit.TAPIOCA_PUDDING.get());

                    output.accept(ModItemsInit.MILK_TEA_CUP.get());
                    output.accept(ModItemsInit.BROWN_SUGAR_MILK_TEA.get());
                    output.accept(ModItemsInit.TIGER_MILK_TEA.get());
                    output.accept(ModItemsInit.HONEY_MILK_TEA.get());
                    output.accept(ModItemsInit.CHOCOLATE_MILK_TEA.get());
                    output.accept(ModItemsInit.VANILLA_MILK_TEA.get());
                    output.accept(ModItemsInit.EGG_CUSTARD_MILK_TEA.get());
                    output.accept(ModItemsInit.COOKIE_MILK_TEA.get());
                    output.accept(ModItemsInit.CAKE_MILK_TEA.get());
                    output.accept(ModItemsInit.PUMPKIN_SPICE_MILK_TEA.get());
                    output.accept(ModItemsInit.APPLE_MILK_TEA.get());
                    output.accept(ModItemsInit.SWEET_BERRY_MILK_TEA.get());
                    output.accept(ModItemsInit.WATERMELON_MILK_TEA.get());
                    output.accept(ModItemsInit.GLOW_BERRY_MILK_TEA.get());
                    output.accept(ModItemsInit.CHORUS_FRUIT_MILK_TEA.get());
                    output.accept(ModItemsInit.ROSE_MILK_TEA.get());
                    output.accept(ModItemsInit.BUTTERFLY_PEA_FLOWER_MILK_TEA.get());
                    output.accept(ModItemsInit.MATCHA_MILK_TEA.get());
                    output.accept(ModItemsInit.LAVENDER_MILK_TEA.get());
                    output.accept(ModItemsInit.THAI_MILK_TEA.get());
                    output.accept(ModItemsInit.BEETROOT_MILK_TEA.get());
                    output.accept(ModItemsInit.CARROT_MILK_TEA.get());
                    output.accept(ModItemsInit.KELP_MILK_TEA.get());
                    output.accept(ModItemsInit.PHANTOM_MILK_TEA.get());
                    output.accept(ModItemsInit.BLAZING_MILK_TEA.get());
                    output.accept(ModItemsInit.DARKSIDE_MILK_TEA.get());
                    output.accept(ModItemsInit.WET_MILK_TEA.get());
                    output.accept(ModItemsInit.IMAGINARY_MILK_TEA.get());

                    output.accept(ModItemsInit.RAW_WAFFLE_BATTER.get());
                    output.accept(ModItemsInit.TAIYAKI_MOLD.get());
                    output.accept(ModItemsInit.EGG_WAFFLE_MOLD.get());
                    output.accept(ModItemsInit.PAW_WAFFLE_MOLD.get());
                    output.accept(ModItemsInit.HEART_WAFFLE_MOLD.get());
                    output.accept(ModItemsInit.CLASSIC_WAFFLE_MOLD.get());
                    output.accept(ModItemsInit.PUMPKIN_WAFFLE_MOLD.get());
                    output.accept(ModItemsInit.MUSHROOM_WAFFLE_MOLD.get());
                    output.accept(ModItemsInit.FLOWER_WAFFLE_MOLD.get());
                    output.accept(ModItemsInit.CREEPER_WAFFLE_MOLD.get());
                    output.accept(ModItemsInit.DIAMOND_WAFFLE_MOLD.get());
                    output.accept(ModItemsInit.PICKAXE_WAFFLE_MOLD.get());
                    output.accept(ModItemsInit.SWORD_WAFFLE_MOLD.get());
                    output.accept(ModItemsInit.TAIYAKI.get());
                    output.accept(ModItemsInit.EGG_WAFFLE.get());
                    output.accept(ModItemsInit.PAW_WAFFLE.get());
                    output.accept(ModItemsInit.HEART_WAFFLE.get());
                    output.accept(ModItemsInit.CLASSIC_WAFFLE.get());
                    output.accept(ModItemsInit.PUMPKIN_WAFFLE.get());
                    output.accept(ModItemsInit.MUSHROOM_WAFFLE.get());
                    output.accept(ModItemsInit.FLOWER_WAFFLE.get());
                    output.accept(ModItemsInit.CREEPER_WAFFLE.get());
                    output.accept(ModItemsInit.DIAMOND_WAFFLE.get());
                    output.accept(ModItemsInit.PICKAXE_WAFFLE.get());
                    output.accept(ModItemsInit.SWORD_WAFFLE.get());

                    output.accept(ModItemsInit.BATTER_MESS.get());
                    output.accept(ModItemsInit.BURNT_CRISP.get());
                })
        );
    }

    private void addCreative(CreativeModeTabEvent.BuildContents event) {
        if (event.getTab() == CreativeModeTabs.FOOD_AND_DRINKS) {
            event.accept(ModItemsInit.CASSAVA);
            event.accept(ModItemsInit.CASSAVA_BIBINGKA);
            event.accept(ModItemsInit.TAPIOCA_PUDDING);

            event.accept(ModItemsInit.BROWN_SUGAR_MILK_TEA);
            event.accept(ModItemsInit.TIGER_MILK_TEA);
            event.accept(ModItemsInit.HONEY_MILK_TEA);
            event.accept(ModItemsInit.CHOCOLATE_MILK_TEA);
            event.accept(ModItemsInit.VANILLA_MILK_TEA);
            event.accept(ModItemsInit.EGG_CUSTARD_MILK_TEA);
            event.accept(ModItemsInit.COOKIE_MILK_TEA);
            event.accept(ModItemsInit.CAKE_MILK_TEA);
            event.accept(ModItemsInit.PUMPKIN_SPICE_MILK_TEA);
            event.accept(ModItemsInit.APPLE_MILK_TEA);
            event.accept(ModItemsInit.SWEET_BERRY_MILK_TEA);
            event.accept(ModItemsInit.WATERMELON_MILK_TEA);
            event.accept(ModItemsInit.GLOW_BERRY_MILK_TEA);
            event.accept(ModItemsInit.CHORUS_FRUIT_MILK_TEA);
            event.accept(ModItemsInit.ROSE_MILK_TEA);
            event.accept(ModItemsInit.BUTTERFLY_PEA_FLOWER_MILK_TEA);
            event.accept(ModItemsInit.MATCHA_MILK_TEA);
            event.accept(ModItemsInit.LAVENDER_MILK_TEA);
            event.accept(ModItemsInit.THAI_MILK_TEA);
            event.accept(ModItemsInit.BEETROOT_MILK_TEA);
            event.accept(ModItemsInit.CARROT_MILK_TEA);
            event.accept(ModItemsInit.KELP_MILK_TEA);
            event.accept(ModItemsInit.PHANTOM_MILK_TEA);
            event.accept(ModItemsInit.BLAZING_MILK_TEA);
            event.accept(ModItemsInit.DARKSIDE_MILK_TEA);
            event.accept(ModItemsInit.WET_MILK_TEA);
            event.accept(ModItemsInit.IMAGINARY_MILK_TEA);

            event.accept(ModItemsInit.RAW_WAFFLE_BATTER);
            event.accept(ModItemsInit.TAIYAKI);
            event.accept(ModItemsInit.EGG_WAFFLE);
            event.accept(ModItemsInit.PAW_WAFFLE);
            event.accept(ModItemsInit.HEART_WAFFLE);
            event.accept(ModItemsInit.CLASSIC_WAFFLE);
            event.accept(ModItemsInit.PUMPKIN_WAFFLE);
            event.accept(ModItemsInit.MUSHROOM_WAFFLE);
            event.accept(ModItemsInit.FLOWER_WAFFLE);
            event.accept(ModItemsInit.CREEPER_WAFFLE);
            event.accept(ModItemsInit.DIAMOND_WAFFLE);
            event.accept(ModItemsInit.PICKAXE_WAFFLE);
            event.accept(ModItemsInit.SWORD_WAFFLE);

            event.accept(ModItemsInit.BATTER_MESS);
            event.accept(ModItemsInit.BURNT_CRISP);
        }

        if (event.getTab() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            event.accept(ModItemsInit.CAKE_STAND_ITEM);
            event.accept(ModItemsInit.DISPLAY_CASE_CURVED_ITEM);
            event.accept(ModItemsInit.WAFFLE_IRON_ITEM);
            event.accept(ModItemsInit.ESPRESSO_MACHINE_ITEM);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
            ItemBlockRenderTypes.setRenderLayer(ModBlocksInit.CASSAVA_CROP.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocksInit.DISPLAY_CASE_CURVED.get(), RenderType.translucent());

            MenuScreens.register(ModMenuTypes.WAFFLE_IRON_MENU.get(), WaffleIronScreen::new);
            MenuScreens.register(ModMenuTypes.DISPLAY_CASE_MENU.get(), DisplayCaseScreen::new);
            MenuScreens.register(ModMenuTypes.CAKE_STAND_MENU.get(), CakeStandScreen::new);
        }
    }

    /**
     * Helper Methods
     */

    public static VoxelShape calculateShapes(Direction to, VoxelShape shape) {
        final VoxelShape[] buffer = { shape, Shapes.empty() };

        final int times = (to.get2DDataValue() - Direction.NORTH.get2DDataValue() + 4) % 4;
        for (int i = 0; i < times; i++) {
            buffer[0].forAllBoxes((minX, minY, minZ, maxX, maxY, maxZ) -> buffer[1] = Shapes.or(buffer[1],
                    Shapes.create(1 - maxZ, minY, minX, 1 - minZ, maxY, maxX)));
            buffer[0] = buffer[1];
            buffer[1] = Shapes.empty();
        }

        return buffer[0];
    }
}
