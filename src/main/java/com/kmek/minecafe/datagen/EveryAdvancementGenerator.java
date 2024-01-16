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

public class EveryAdvancementGenerator implements ForgeAdvancementProvider.AdvancementGenerator {
    @Override
    public void generate(HolderLookup.Provider registries, Consumer<Advancement> writer, ExistingFileHelper existingFileHelper) {

        /**
         * DRINKS
         */
        final String rootDrinks = "root_drinks";

        anyAndEveryAchievementFromArrayReg(ModItemsInit.COFFEES,
                2, "Any Coffee", "Craft a coffee",
                10, "Every Coffee", "Craft every type of coffee",
                rootDrinks, "coffee", writer, existingFileHelper);

        anyAndEveryAchievementFromArrayReg(ModItemsInit.FRUIT_JUICES,
                20, "Any Juice", "Craft a Juice",
                26, "Every Juice", "Craft every type of juice",
                rootDrinks, "juice", writer, existingFileHelper);

        anyAndEveryAchievementFromArrayReg(ModItemsInit.BOBA_MILK_TEAS,
                13, "Any Boba Milk Tea", "Craft a boba milk tea",
                32, "Every Boba Milk Tea", "Craft every type of boba milk tea",
                rootDrinks, "boba_milk_tea", writer, existingFileHelper);

        /**
         * FOOD
         */
        final String rootFood = "root_food";

        // Todo: convert any
        Advancement every_cannoli = everyAchievementFromArrayReg(ModItemsInit.CANNOLIS, 17,
                "Every Cannoli", "Craft every type of cannoli",
                "food/cannoli/any_cannoli", "every_cannoli", writer, existingFileHelper);

        anyAndEveryAchievementFromArrayReg(ModItemsInit.PUDDINGS,
                37, "Any Pudding", "Craft a pudding",
                17, "Every Pudding", "Craft every type of pudding",
                rootFood, "pudding", writer, existingFileHelper);

        anyAndEveryAchievementFromArrayReg(ModItemsInit.YOGURTS,
                40, "Any Yogurt", "Craft a yogurt",
                2, "Every Yogurt", "Craft every type of yogurt",
                rootFood, "yogurt", writer, existingFileHelper);

        anyAndEveryAchievementFromArrayReg(ModItemsInit.CANDY,
                0, "Any Candy", "Craft a candy",
                7, "Every Candy", "Craft every type of candy",
                rootFood, "candy", writer, existingFileHelper);

        anyAndEveryAchievementFromArrayReg(ModItemsInit.MUFFINS,
                0, "Any Muffin", "Craft a muffin",
                17, "Every Muffin", "Craft every type of muffin",
                rootFood, "muffin", writer, existingFileHelper);

        anyAndEveryAchievementFromArrayReg(ModItemsInit.BROWNIES,
                0, "Any Brownie", "Craft a brownie",
                3, "Every Brownie", "Craft every type of brownie",
                rootFood, "brownie", writer, existingFileHelper);

        anyAndEveryAchievementFromArrayReg(ModItemsInit.COOKIES,
                9, "Any Cookie", "Craft a cookie",
                13, "Every Cookie", "Craft every type of cookie",
                rootFood, "cookie", writer, existingFileHelper);

        anyAndEveryAchievementFromArrayReg(ModItemsInit.BREADS,
                6, "Aloaf (Of Bread)", "Craft a bread loaf",
                16, "Every Bread Loaf", "Craft every type of bread loaf",
                rootFood, "bread", writer, existingFileHelper);

        anyAndEveryAchievementFromArrayReg(ModItemsInit.BREADS_TOASTS,
                0, "A Toast", "Craft toast",
                8, "Every Toast", "Craft every type of toast",
                rootFood, "toast", writer, existingFileHelper);

        /**
         * INGREDIENTS
         */
        final String rootIngredients = "root_ingredients";

        // Todo: convert any
        Advancement every_cream = everyAchievementFromArrayReg(ModItemsInit.CREAMS, 17,
                "Every Flavored Cream", "Craft every type of flavored cream",
                "ingredients/any_flavored_cream", "every_flavored_cream", writer, existingFileHelper);

        // Todo: convert any
        Advancement every_jam = everyAchievementFromArrayReg(ModItemsInit.JAMS, 17,
                "Every Jam", "Craft every type of jam",
                "ingredients/jams/any_jam", "every_jam", writer, existingFileHelper);

        anyAndEveryAchievementFromArrayReg(ModItemsInit.CROP_ITEMS,
                1, "Any Fruit", "Find a fruit",
                7, "Every Fruit", "Find every type of fruit",
                rootIngredients, "fruit", writer, existingFileHelper);

        anyAndEveryAchievementFromArrayReg(ModItemsInit.NUTS,
                0, "A Nut!", "Find a nut",
                2, "Aw Nuts!", "Find every type of nut",
                rootIngredients, "nuts", writer, existingFileHelper);
    }

    private void anyAndEveryAchievementFromArrayReg(List<RegistryObject<Item>> itemList,
                                       int iconIndexAny, String titleAny, String descriptionAny,
                                       int iconIndexEvery, String titleEvery, String descriptionEvery,
                                       String parentOfAny, String fileName, Consumer<Advancement> writer, ExistingFileHelper existingFileHelper) {
        Advancement any = anyAchievementFromArrayReg(itemList, iconIndexAny, titleAny, descriptionAny,
                parentOfAny, "any_" + fileName, writer, existingFileHelper);
        Advancement every = everyAchievementFromArrayReg(itemList, iconIndexEvery, titleEvery, descriptionEvery,
                any, "every_" + fileName, writer, existingFileHelper);
    }

    private Advancement anyAchievementFromArrayReg(List<RegistryObject<Item>> itemList,
                                       int iconIndex, String title, String description, String parentAdv,
                                       String fileName, Consumer<Advancement> writer, ExistingFileHelper existingFileHelper) {
        return criterionFromArrayRegs(itemList, Advancement.Builder.advancement()
                .parent(new ResourceLocation(MineCafeMod.MODID, parentAdv))
                .display(itemList.get(iconIndex).get(),
                        Component.literal(title),
                        Component.literal(description),
                        null, FrameType.GOAL, true, true, false))
                .requirements(new String [][]{itemList.stream().map(reg -> "trigger_" + reg.get().toString()).toArray(String[]::new)})
                .save(writer, new ResourceLocation(MineCafeMod.MODID, fileName), existingFileHelper);
    }

    private Advancement everyAchievementFromArrayReg(List<RegistryObject<Item>> itemList,
                                       int iconIndex, String title, String description, Advancement parentAdv,
                                       String fileName, Consumer<Advancement> writer, ExistingFileHelper existingFileHelper) {
        return criterionFromArrayRegs(itemList, Advancement.Builder.advancement()
                .parent(parentAdv)
                .display(itemList.get(iconIndex).get(),
                        Component.literal(title),
                        Component.literal(description),
                        null, FrameType.CHALLENGE, true, true, false))
                .save(writer, new ResourceLocation(MineCafeMod.MODID, fileName), existingFileHelper);
    }

    private Advancement everyAchievementFromArrayReg(List<RegistryObject<Item>> itemList,
                                       int iconIndex, String title, String description, String parentAdv,
                                       String fileName, Consumer<Advancement> writer, ExistingFileHelper existingFileHelper) {
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
