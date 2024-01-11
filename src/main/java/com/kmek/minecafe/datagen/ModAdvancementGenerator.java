package com.kmek.minecafe.datagen;

import com.kmek.minecafe.MineCafeMod;
import com.kmek.minecafe.item.ModItemsInit;
import com.kmek.minecafe.tags.ModTags;
import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.tags.ITag;

import java.util.List;
import java.util.function.Consumer;

public class ModAdvancementGenerator implements ForgeAdvancementProvider.AdvancementGenerator {
    @Override
    public void generate(HolderLookup.Provider registries, Consumer<Advancement> writer, ExistingFileHelper existingFileHelper) {
//        Advancement example = Advancement.Builder.advancement()
//                .parent(new ResourceLocation(MineCafeMod.MODID, "root"))
//                .display(ModItemsInit.CANDY.get(0).get(),
//                        Component.literal("Example Title"),
//                        Component.literal("Example description"),
//                        null, FrameType.TASK, true, true, false)
////                .addCriterion("example_criterion",
////                        new InventoryChangeTrigger.TriggerInstance(EntityPredicate.Composite.ANY, MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY,
////                            new ItemPredicate[]{
////                                    ItemPredicate.Builder.item().of(ModItemsInit.CANDY.get(0).get()).build(),
////                                    ItemPredicate.Builder.item().of(ModItemsInit.CANDY.get(2).get()).build()
////                        })) // How the advancement is unlocked
//                .addCriterion("example_criterion", triggerForItem(ModItemsInit.CANDY.get(0))) // How the advancement is unlocked
//                .addCriterion("example_criterion2",triggerForItem(ModItemsInit.CANDY.get(1))) // How the advancement is unlocked
//                .save(writer, new ResourceLocation(MineCafeMod.MODID, "example"), existingFileHelper); // Add data to builder

//        Advancement meat_pies = criterionFromTag(ModTags.Items.MEAT_PIES, Advancement.Builder.advancement()
//                .parent(new ResourceLocation(MineCafeMod.MODID, "root"))
//                .display(ModItemsInit.CANDY.get(0).get(),
//                        Component.literal("Every Meat Pie"),
//                        Component.literal("Craft every type of meat pie"),
//                        null, FrameType.CHALLENGE, true, true, false))
//                .save(writer, new ResourceLocation(MineCafeMod.MODID, "every_meat_pie"), existingFileHelper);

//        Advancement meat_pies = criterionFromArrayRegs(ModItemsInit.PIES, Advancement.Builder.advancement()
//                .parent(new ResourceLocation(MineCafeMod.MODID, "root"))
//                .display(ModItemsInit.CANDY.get(0).get(),
//                        Component.literal("Every Meat Pie"),
//                        Component.literal("Craft every type of meat pie"),
//                        null, FrameType.CHALLENGE, true, true, false))
//                .save(writer, new ResourceLocation(MineCafeMod.MODID, "every_meat_pie"), existingFileHelper);

        Advancement every_cream = criterionFromArrayRegs(ModItemsInit.CREAMS, Advancement.Builder.advancement()
                .parent(new ResourceLocation(MineCafeMod.MODID, "ingredients/any_flavored_cream"))
                .display(ModItemsInit.CREAMS.get(17).get(),
                        Component.literal("Every Flavored Cream"),
                        Component.literal("Craft every flavored cream"),
                        null, FrameType.CHALLENGE, true, true, false))
                .save(writer, new ResourceLocation(MineCafeMod.MODID, "every_cream"), existingFileHelper);

        Advancement every_cannoli = criterionFromArrayRegs(ModItemsInit.CANNOLIS, Advancement.Builder.advancement()
                .parent(new ResourceLocation(MineCafeMod.MODID, "cannoli/any_cannoli"))
                .display(ModItemsInit.CANNOLIS.get(17).get(),
                        Component.literal("Every Cannoli"),
                        Component.literal("Craft every type of cannoli"),
                        null, FrameType.CHALLENGE, true, true, false))
                .save(writer, new ResourceLocation(MineCafeMod.MODID, "every_cannoli"), existingFileHelper);

        Advancement every_jam = criterionFromArrayRegs(ModItemsInit.JAMS, Advancement.Builder.advancement()
                .parent(new ResourceLocation(MineCafeMod.MODID, "jams/any_jam"))
                .display(ModItemsInit.JAMS.get(17).get(),
                        Component.literal("Every Jam"),
                        Component.literal("Craft every type of jam"),
                        null, FrameType.CHALLENGE, true, true, false))
                .save(writer, new ResourceLocation(MineCafeMod.MODID, "every_jam"), existingFileHelper);
    }

    private Advancement.Builder criterionFromArrayRegs(List<RegistryObject<Item>> itemList, Advancement.Builder builder) {
        itemList.forEach(reg -> {
            System.out.println("TEST: " + reg.get().toString());
            builder.addCriterion("trigger_" + reg.get().toString(), triggerForItem(reg));
        });

        return builder;
    }

//    private Advancement.Builder criterionFromTag(TagKey<Item> tagKey, Advancement.Builder builder) {
//////        TagKey<Item> tkey = ItemTags.create(new ResourceLocation(MineCafeMod.MODID, "meat_pies"));
//////        ITag<Item> itag = ModItemsInit.ITEMS.get.tags().getTag(tagKey);
////        ITag<Item> itag = ForgeRegistries.ITEMS.tags().getTag(tagKey);
////        if (itag.size() > 0) {
////            itag.forEach(item -> {
////                System.out.println("PRINTING TAG ITEMS: " + item.toString());
////            });
////        } else {
////            System.err.println("Tag for Advancement is empty!\n\t" + tagKey.toString());
////        }
//
//        return builder;
//    }

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
