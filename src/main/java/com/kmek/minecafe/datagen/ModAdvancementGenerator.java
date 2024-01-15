package com.kmek.minecafe.datagen;

import com.kmek.minecafe.MineCafeMod;
import com.kmek.minecafe.item.ModItemsInit;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Consumer;

public class ModAdvancementGenerator implements ForgeAdvancementProvider.AdvancementGenerator {
    @Override
    public void generate(HolderLookup.Provider registries, Consumer<Advancement> writer, ExistingFileHelper existingFileHelper) {

        /**
         * DRINKS
         */
        Advancement every_coffee = everyAchievementFromArrayReg(ModItemsInit.COFFEES, 10,
                "Every Coffee", "Craft every type of coffee",
                "root_drinks", "every_coffee", writer, existingFileHelper);

        Advancement every_juice = everyAchievementFromArrayReg(ModItemsInit.FRUIT_JUICES, 17,
                "Every Juice", "Craft every type of juice",
                "root_drinks", "every_juice", writer, existingFileHelper);

        Advancement every_boba_milk_tea = everyAchievementFromArrayReg(ModItemsInit.BOBA_MILK_TEAS, 32,
                "Every Boba Milk Tea", "Craft every type of boba milk tea",
                "root_drinks", "every_boba_milk_tea", writer, existingFileHelper);

        /**
         * FOOD
         */
        Advancement every_cannoli = everyAchievementFromArrayReg(ModItemsInit.CANNOLIS, 17,
                "Every Cannoli", "Craft every type of cannoli",
                "food/cannoli/any_cannoli", "every_cannoli", writer, existingFileHelper);

        Advancement every_pudding = everyAchievementFromArrayReg(ModItemsInit.PUDDINGS, 17,
                "Every Pudding", "Craft every type of pudding",
                "root_food", "every_pudding", writer, existingFileHelper);

        Advancement every_yogurt = everyAchievementFromArrayReg(ModItemsInit.YOGURTS, 17,
                "Every Yogurt", "Craft every type of yogurt",
                "root_food", "every_yogurt", writer, existingFileHelper);

        Advancement every_candy = everyAchievementFromArrayReg(ModItemsInit.CANDY, 7,
                "Every Candy", "Craft every type of candy",
                "root_food", "every_candy", writer, existingFileHelper);

        Advancement every_muffin = everyAchievementFromArrayReg(ModItemsInit.MUFFINS, 17,
                "Every Muffin", "Craft every type of muffin",
                "root_food", "every_muffin", writer, existingFileHelper);

        Advancement every_brownie = everyAchievementFromArrayReg(ModItemsInit.BROWNIES, 3,
                "Every Brownie", "Craft every type of brownie",
                "root_food", "every_brownie", writer, existingFileHelper);

        Advancement every_cookie = everyAchievementFromArrayReg(ModItemsInit.COOKIES, 13,
                "Every Cookie", "Craft every type of cookie",
                "root_food", "every_cookie", writer, existingFileHelper);

        Advancement every_bread = everyAchievementFromArrayReg(ModItemsInit.BREADS, 16,
                "Every Bread", "Craft every type of bread loaf",
                "root_food", "every_bread", writer, existingFileHelper);

        Advancement every_toast = everyAchievementFromArrayReg(ModItemsInit.BREADS_TOASTS, 8,
                "Every Toast", "Craft every type of toast",
                "root_food", "every_toast", writer, existingFileHelper);

        /**
         * INGREDIENTS
         */
        Advancement every_cream = everyAchievementFromArrayReg(ModItemsInit.CREAMS, 17,
                "Every Flavored Cream", "Craft every type of flavored cream",
                "ingredients/any_flavored_cream", "every_flavored_cream", writer, existingFileHelper);

        Advancement every_jam = everyAchievementFromArrayReg(ModItemsInit.JAMS, 17,
                "Every Jam", "Craft every type of jam",
                "ingredients/jams/any_jam", "every_jam", writer, existingFileHelper);

        Advancement every_fruit = everyAchievementFromArrayReg(ModItemsInit.CROP_ITEMS, 7,
                "Every Fruit", "Craft every type of fruit",
                "root_ingredients", "every_fruit", writer, existingFileHelper);

        Advancement every_nut = everyAchievementFromArrayReg(ModItemsInit.NUTS, 2,
                "Aw Nuts!", "Craft every type of nut",
                "root_ingredients", "every_nut", writer, existingFileHelper);

    }

    private Advancement everyAchievementFromArrayReg(List<RegistryObject<Item>> itemList, int iconIndex, String title, String description, String parentAdv, String fileName, Consumer<Advancement> writer, ExistingFileHelper existingFileHelper) {
        return criterionFromArrayRegs(itemList, Advancement.Builder.advancement()
                .parent(new ResourceLocation(MineCafeMod.MODID, parentAdv))
                .display(itemList.get(iconIndex).get(),
                        Component.literal(title),
                        Component.literal(description),
                        null, FrameType.CHALLENGE, true, true, false))
                .save(writer, new ResourceLocation(MineCafeMod.MODID, fileName), existingFileHelper);
    }

    private Advancement.Builder criterionFromArrayRegs(List<RegistryObject<Item>> itemList, Advancement.Builder builder) {
        itemList.forEach(reg -> {
//            System.out.println("TEST: " + reg.get().toString());
            builder.addCriterion("trigger_" + reg.get().toString(), triggerForItem(reg));
        });

        return builder;
    }

    private CriterionTriggerInstance triggerForItem(ItemLike item) {
        return new InventoryChangeTrigger.TriggerInstance(EntityPredicate.Composite.ANY, MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY,
                new ItemPredicate[]{
                        ItemPredicate.Builder.item().of(item).build()
                });
    }

    private CriterionTriggerInstance triggerForItem(RegistryObject<Item> item) {
        return triggerForItem(item.get());
    }
}
