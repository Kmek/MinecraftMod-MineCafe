package com.kmek.minecafe.datagen;

import com.kmek.minecafe.MineCafeMod;
import com.kmek.minecafe.block.ModBlocksInit;
import com.kmek.minecafe.item.ModItemsInit;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput packOutput, ExistingFileHelper existingFileHelper) {
        super(packOutput, MineCafeMod.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        /* ********* ITEMS ********* */
        simpleItem(ModItemsInit.CROP_INSPECTOR_ITEM, "tools/");
        handheldItem(ModItemsInit.GOLDEN_FORTUNE_COOKIE_PICKAXE, "tools/");

        ModItemsInit.CROP_ITEMS.forEach(e -> simpleItem(e, "crops/"));
        ModItemsInit.NUTS.forEach(e -> simpleItem(e, "nuts/"));
        ModItemsInit.CREAMS.forEach(e -> simpleItem(e, "creams/"));
        ModItemsInit.JAMS.forEach(e -> simpleItem(e, "jams/"));
        ModItemsInit.BREADS.forEach(e -> simpleItem(e, "breads/"));
        ModItemsInit.CAKES.forEach(e -> simpleItem(e, "cakes/"));
        ModItemsInit.PIES.forEach(e -> simpleItem(e, "pies/"));
        ModItemsInit.PUDDINGS.forEach(e -> simpleItem(e, "puddings/"));
        ModItemsInit.CANDY.forEach(e -> simpleItem(e, "candy/"));
        ModItemsInit.BROWNIES.forEach(e -> simpleItem(e, "baked_goods/"));
        ModItemsInit.SMORES.forEach(e -> simpleItem(e, "marshmallows/"));
        ModItemsInit.CANNOLIS.forEach(e -> simpleItem(e, "cannolis/"));
        ModItemsInit.MUFFINS.forEach(e -> simpleItem(e, "baked_goods/"));
        ModItemsInit.COOKIES.forEach(e -> simpleItem(e, "cookies/"));
        ModItemsInit.YOGURTS.forEach(e -> simpleItem(e, "yogurts/"));
        ModItemsInit.BOBA_MILK_TEAS.forEach(e -> simpleItem(e, "milk_teas/"));
        ModItemsInit.SPECIAL_BOBA_MILK_TEAS.forEach(e -> simpleItem(e, "milk_teas/"));
        ModItemsInit.COFFEES.forEach(e -> simpleItem(e, "coffees/"));
        ModItemsInit.SPECIAL_COFFEES.forEach(e -> simpleItem(e, "coffees/"));
        ModItemsInit.FRUIT_JUICES.forEach(e -> simpleItem(e, "juices/"));
        ModItemsInit.INGREDIENTS.forEach(e -> simpleItem(e, "ingredients/"));
        ModItemsInit.WAFFLE_MOLDS.forEach(e -> simpleItem(e, "waffles/"));
        ModItemsInit.WAFFLES.forEach(e -> simpleItem(e, "waffles/"));
        ModItemsInit.MISC_DRINKS.forEach(e -> simpleItem(e, "misc_drinks/"));

        // Band-aid solution to straggler items
        simpleItem(ModItemsInit.FORTUNE_COOKIE, "cookies/");
        simpleItem(ModItemsInit.GOLDEN_FORTUNE_COOKIE, "cookies/");
        simpleItem(ModItemsInit.MATCHA_TEA, "teas/");
        List.of("marshmallow", "marshmallows", "marshmallow_puff")
                .forEach(x -> simpleItemFromPath(x, "marshmallows/"));
        List.of("coffee_beans_roasted", "coffee_grounds", "coffee_pot", "coffee_pot_full", "coffee_filter",
                        "coffee_filter_used")
                .forEach(x -> simpleItemFromPath(x, "coffees/"));
        List.of("espresso_shot", "steamed_milk", "milk_foam")
                .forEach(x -> simpleItemFromPath(x, "ingredients/"));
        simpleItem(ModItemsInit.MUG, "ingredients/");
        simpleItem(ModItemsInit.CLEAR_CUP, "ingredients/");
        simpleItem(ModItemsInit.BURNT_CRISP, "ingredients/");
        simpleItem(ModItemsInit.BATTER_MESS, "ingredients/");
        simpleItem(ModItemsInit.RAW_WAFFLE_BATTER, "waffles/");

        /* ********* ITEMS for BLOCKS ********* */

        simpleItemFromPath("coffee_bucket", "coffees/");
        itemFromExplicitPath("vanilla_flower", "block/flower/", "item/generated");

        // Seed items
        ModBlocksInit.FLOWER_CROPS.stream()
                .map(e -> e.getId().getPath().replace("_crop", "_seeds"))
                .forEach(path -> simpleItemFromPath(path, "seeds/"));
        ModBlocksInit.TILLED_CROPS.stream()
                .map(e -> e.getId().getPath().replace("_crop", "_seeds"))
                .forEach(path -> simpleItemFromPath(path, "seeds/"));
        ModBlocksInit.FRUIT_BUSH_CROPS.stream()
                .map(e -> e.getId().getPath().replace("_bush_crop", "_seeds"))
                .forEach(path -> simpleItemFromPath(path, "seeds/"));
        ModBlocksInit.FRUIT_TREE_CROPS.stream()
                .map(e -> e.getId().getPath().replace("_crop_bottom", "_seeds"))
                .forEach(path -> simpleItemFromPath(path, "seeds/"));
        List.of("vanilla_seeds", "cassava_cutting", "coffee_beans_unroasted", "apple_seeds")
                .forEach(x -> simpleItemFromPath(x, "seeds/"));

        // Block items through parent block model
        ModBlocksInit.CRAFTING_BLOCKS.forEach(w -> blockItemFromBlockParent(w, "crafting/"));
        ModBlocksInit.DECORATIVE_BLOCKS.forEach(w -> blockItemFromBlockParent(w, "decorative/"));
        ModBlocksInit.WALL_SHELVES.forEach(w -> blockItemFromBlockParent(w, "shelf/"));
    }

    /**
     * Helper Functions
     */

    private ItemModelBuilder itemFromExplicitPath(String path, String textureFolder, String parent) {
        return withExistingParent(path, new ResourceLocation(parent))
                .texture("layer0", new ResourceLocation(MineCafeMod.MODID, textureFolder + path));
    }

    private ItemModelBuilder itemFromItemPath(String path, String textureFolder, String parent) {
        return withExistingParent(path, new ResourceLocation(parent))
                .texture("layer0", new ResourceLocation(MineCafeMod.MODID, "item/" + textureFolder + path));
    }

    private ItemModelBuilder simpleItemFromPath(String path, String textureFolder) {
        return itemFromItemPath(path, textureFolder, "item/generated");
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item, String textureFolder) {
        return simpleItemFromPath(item.getId().getPath(), textureFolder);
    }

    private ItemModelBuilder simpleItemFromBlock(RegistryObject<Block> block, String textureFolder) {
        return simpleItemFromPath(block.getId().getPath(), textureFolder);
    }

    private ItemModelBuilder handheldItem(RegistryObject<Item> item, String textureFolder) {
        return itemFromItemPath(item.getId().getPath(), textureFolder, "item/handheld");
    }

     private ItemModelBuilder blockItemFromBlockParent(RegistryObject<Block> block, String parentBlockModelFolder) {
         String path = block.getId().getPath();
         return withExistingParent(path, new ResourceLocation(MineCafeMod.MODID, "block/" + parentBlockModelFolder + path));
     }
}
