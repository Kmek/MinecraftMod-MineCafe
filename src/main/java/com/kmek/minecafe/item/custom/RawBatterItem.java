package com.kmek.minecafe.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RawBatterItem extends Item {

    private final String warningText;
    private final boolean returnBowl;

    public RawBatterItem(String warningText, boolean returnBowl) {
        super(new Item.Properties()
                .craftRemainder(Items.BOWL)
                .food(new FoodProperties.Builder()
                        .nutrition(0)
                        .saturationMod(0)
                        .effect(() -> new MobEffectInstance(MobEffects.POISON, 300, 0), 0.6f)
                        .build()));
        this.warningText = warningText;
        this.returnBowl = returnBowl;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        components.add(Component.literal(this.warningText).withStyle(ChatFormatting.YELLOW));
        super.appendHoverText(stack, level, components, flag);
    }

    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack itemStack, @NotNull Level level, @NotNull LivingEntity livingEntity) {
        if (livingEntity instanceof Player player) {
            if (returnBowl) {
                player.addItem(new ItemStack(Items.BOWL));
            }
        }

        return livingEntity.eat(level, itemStack);
    }
}
