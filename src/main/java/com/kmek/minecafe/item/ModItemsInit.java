package com.kmek.minecafe.item;

import com.kmek.minecafe.MineCafeMod;
import com.kmek.minecafe.fluid.ModFluids;
import com.kmek.minecafe.item.custom.*;
import com.kmek.minecafe.item.registery.FoodVariants;
import com.kmek.minecafe.registration.ItemDataLoader;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class ModItemsInit {
    /**
     * Registry
     */
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MineCafeMod.MODID);
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    public static final Map<RegistryObject<Item>, Float> compostableItems = new HashMap<>();
    public static RegistryObject<Item> compostable(float rate, RegistryObject<Item> reg) {
        compostableItems.put(reg, rate);
        return reg;
    }

    /**
     * Fluid Block Item
     */
    public static final RegistryObject<Item> COFFEE_BUCKET = ITEMS.register("coffee_bucket",
            () -> new BucketItem(ModFluids.SOURCE_COFFEE_FLUID, new Item.Properties().stacksTo(1).craftRemainder(Items.BUCKET)));

    /**
     * Crop Inspector Tool
     */
    public static final RegistryObject<Item> CROP_INSPECTOR_ITEM = ITEMS.register("crop_inspector",
            () -> new Item(new Item.Properties().stacksTo(1)));

    /**
     * Raw Crop Drops
     */
    public static final RegistryObject<Item> CASSAVA = compostable(0.65F, ITEMS.register("cassava", () -> new Item(new Item.Properties().food(
            new FoodProperties.Builder().nutrition(0).saturationMod(0f)
                    .effect(() -> new MobEffectInstance(MobEffects.POISON, 200, 0), 1.0f)
                    .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 400, 0), 1.0f)
                    .effect(() -> new MobEffectInstance(MobEffects.WEAKNESS, 600, 0), 1.0f)
                    .build())) {
        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
            components.add(new TextComponent("Poisonous when raw").withStyle(ChatFormatting.YELLOW));
            super.appendHoverText(stack, level, components, flag);
        }
    }));
    // Fruits
    public static final RegistryObject<Item> COFFEE_CHERRIES = compostable(0.6F, ITEMS.register("coffee_cherries", () -> new Item(new Item.Properties())));
    public static final List<RegistryObject<Item>> FRUIT_ITEMS = Arrays.stream(FoodVariants.fruits)
            .filter(fruit -> fruit != FoodVariants.APPLE)
            .map(fruit -> compostable(0.65F, ITEMS.register(fruit.toString(), () -> new Item(new Item.Properties().food(
                new FoodProperties.Builder().nutrition(3).saturationMod(1f).build())))))
            .toList();

    /**
     * Ingredients
     */
    public static final RegistryObject<Item> CHOCOLATE = ITEMS.register("chocolate", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> VANILLA = ITEMS.register("vanilla", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CARAMEL = ITEMS.register("caramel", () -> new Item(new Item.Properties().food(
            new FoodProperties.Builder().nutrition(0).saturationMod(0.2f).build())));
    public static final RegistryObject<Item> CINNAMON = ITEMS.register("cinnamon", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BUTTER = ITEMS.register("butter", () -> new Item(new Item.Properties().food(
            new FoodProperties.Builder().nutrition(0).saturationMod(0.2f).build())));
    public static final RegistryObject<Item> MONKFRUIT_SWEETENER = ITEMS.register("monkfruit_sweetener", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ICE_CUBES = ITEMS.register("ice_cubes", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PIE_CRUST = ITEMS.register("pie_crust", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GRAHAM_CRACKER = ITEMS.register("graham_cracker", () -> new Item(new Item.Properties().food(
            new FoodProperties.Builder().nutrition(1).saturationMod(0.5f).build())));
    public static final RegistryObject<Item> CUSTARD = ITEMS.register("custard", () -> new Item(new Item.Properties().food(
            new FoodProperties.Builder().nutrition(0).saturationMod(0.3f).build())));
    public static final RegistryObject<Item> RAISINS = ITEMS.register("raisins", () -> new Item(new Item.Properties().food(
            new FoodProperties.Builder().nutrition(2).saturationMod(2f).build())));
    // Creams
    public static final RegistryObject<Item> CREAM = ITEMS.register("cream", () -> new Item(new Item.Properties().food(
            new FoodProperties.Builder().nutrition(0).saturationMod(0.2f).build())));
    public static final RegistryObject<Item> WHIPPED_CREAM = ITEMS.register("whipped_cream", () -> new Item(new Item.Properties().food(
            new FoodProperties.Builder().nutrition(1).saturationMod(0.5f).build())));
    public static final List<RegistryObject<Item>> CREAMS =
            new ItemDataLoader("registration_data/creams.txt").read().stream()
                .map(args -> ITEMS.register(args.get(0) + "_cream", () -> new Item(new Item.Properties().food(
                        new FoodProperties.Builder().nutrition(0).saturationMod(0.3f).build())))
                ).toList();
    // Jams
    public static final List<RegistryObject<Item>> JAMS =
        new ItemDataLoader("registration_data/jams.txt").read().stream()
            .map(args -> ITEMS.register(args.get(0) + "_jam", () -> new Item(new Item.Properties().food(
                    new FoodProperties.Builder().nutrition(1).saturationMod(0.4f).build())))
            ).toList();
    public static final RegistryObject<Item> BOBA_PEARLS = ITEMS.register("boba_pearls", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MATCHA_POWDER = ITEMS.register("matcha_powder", () -> new Item(new Item.Properties()));

    /**
     * Dishes
     */
    public static final RegistryObject<Item> CLEAR_CUP = ITEMS.register("clear_cup", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MUG = ITEMS.register("mug", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ICE_TRAY = ITEMS.register("ice_tray", () -> new Item(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> ICE_TRAY_WATER = ITEMS.register("ice_tray_water", () -> new Item(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> ICE_TRAY_ICE = ITEMS.register("ice_tray_ice", () -> new Item(new Item.Properties().stacksTo(1).craftRemainder(ICE_TRAY.get())));

    /**
     * Food
     */
    public static final RegistryObject<Item> BATTER_MESS = ITEMS.register("batter_mess", () -> new Item(new Item.Properties().food(
            new FoodProperties.Builder().nutrition(0).saturationMod(0)
                    .effect(() -> new MobEffectInstance(MobEffects.POISON, 200, 0), 0.8f)
                    .build())) {
        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
            components.add(new TextComponent("Consume raw eggs at your own risk").withStyle(ChatFormatting.YELLOW));
            super.appendHoverText(stack, level, components, flag);
        }
    });
    public static final RegistryObject<Item> BURNT_CRISP = ITEMS.register("burnt_crisp", () -> new Item(new Item.Properties().food(
            new FoodProperties.Builder().nutrition(1).saturationMod(0.5f)
                    .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 200, 0), 1.0f)
                    .build())));
    // Breads
    public static final List<RegistryObject<Item>> BREADS =
            new ItemDataLoader("registration_data/breads.txt").read().stream()
                    .map(args -> ITEMS.register(args.get(0), () -> new Item(new Item.Properties().food(
                            new FoodProperties.Builder()
                                    .nutrition(Integer.parseInt(args.get(1)))
                                    .saturationMod(Float.parseFloat(args.get(2))).build())))
                    ).toList();
    // Waffle snacks
    public static final RegistryObject<Item> RAW_WAFFLE_BATTER = ITEMS.register("raw_waffle_batter", () -> new Item(new Item.Properties().food(
            new FoodProperties.Builder().nutrition(0).saturationMod(0)
                    .effect(() -> new MobEffectInstance(MobEffects.POISON, 300, 0), 0.6f)
                    .build())) {
        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
            components.add(new TextComponent("Consume raw eggs at your own risk").withStyle(ChatFormatting.YELLOW));
            super.appendHoverText(stack, level, components, flag);
        }
    });
    public static final RegistryObject<Item> TAIYAKI = ITEMS.register("taiyaki", () -> new WaffleItem(2, 0.8f));
    public static final RegistryObject<Item> TAIYAKI_MOLD = ITEMS.register("taiyaki_mold", () -> new WaffleMoldItem(TAIYAKI.get()));
    public static final RegistryObject<Item> EGG_WAFFLE = ITEMS.register("egg_waffle", () -> new WaffleItem(2, 0.8f));
    public static final RegistryObject<Item> EGG_WAFFLE_MOLD = ITEMS.register("egg_waffle_mold", () -> new WaffleMoldItem(EGG_WAFFLE.get()));
    public static final RegistryObject<Item> PAW_WAFFLE = ITEMS.register("paw_waffle", () -> new WaffleItem(2, 0.8f));
    public static final RegistryObject<Item> PAW_WAFFLE_MOLD = ITEMS.register("paw_waffle_mold", () -> new WaffleMoldItem(PAW_WAFFLE.get()));
    public static final RegistryObject<Item> CLASSIC_WAFFLE = ITEMS.register("classic_waffle", () -> new WaffleItem(2, 0.8f));
    public static final RegistryObject<Item> CLASSIC_WAFFLE_MOLD = ITEMS.register("classic_waffle_mold", () -> new WaffleMoldItem(CLASSIC_WAFFLE.get()));
    public static final RegistryObject<Item> CREEPER_WAFFLE = ITEMS.register("creeper_waffle", () -> new WaffleItem(2, 0.8f));
    public static final RegistryObject<Item> CREEPER_WAFFLE_MOLD = ITEMS.register("creeper_waffle_mold", () -> new WaffleMoldItem(CREEPER_WAFFLE.get()));
    public static final RegistryObject<Item> HEART_WAFFLE = ITEMS.register("heart_waffle", () -> new WaffleItem(2, 0.8f));
    public static final RegistryObject<Item> HEART_WAFFLE_MOLD = ITEMS.register("heart_waffle_mold", () -> new WaffleMoldItem(HEART_WAFFLE.get()));
    public static final RegistryObject<Item> PUMPKIN_WAFFLE = ITEMS.register("pumpkin_waffle", () -> new WaffleItem(2, 0.8f));
    public static final RegistryObject<Item> PUMPKIN_WAFFLE_MOLD = ITEMS.register("pumpkin_waffle_mold", () -> new WaffleMoldItem(PUMPKIN_WAFFLE.get()));
    public static final RegistryObject<Item> MUSHROOM_WAFFLE = ITEMS.register("mushroom_waffle", () -> new WaffleItem(2, 0.8f));
    public static final RegistryObject<Item> MUSHROOM_WAFFLE_MOLD = ITEMS.register("mushroom_waffle_mold", () -> new WaffleMoldItem(MUSHROOM_WAFFLE.get()));
    public static final RegistryObject<Item> FLOWER_WAFFLE = ITEMS.register("flower_waffle", () -> new WaffleItem(2, 0.8f));
    public static final RegistryObject<Item> FLOWER_WAFFLE_MOLD = ITEMS.register("flower_waffle_mold", () -> new WaffleMoldItem(FLOWER_WAFFLE.get()));
    public static final RegistryObject<Item> DIAMOND_WAFFLE = ITEMS.register("diamond_waffle", () -> new WaffleItem(2, 0.8f));
    public static final RegistryObject<Item> DIAMOND_WAFFLE_MOLD = ITEMS.register("diamond_waffle_mold", () -> new WaffleMoldItem(DIAMOND_WAFFLE.get()));
    public static final RegistryObject<Item> PICKAXE_WAFFLE = ITEMS.register("pickaxe_waffle", () -> new WaffleItem(2, 0.8f));
    public static final RegistryObject<Item> PICKAXE_WAFFLE_MOLD = ITEMS.register("pickaxe_waffle_mold", () -> new WaffleMoldItem(PICKAXE_WAFFLE.get()));
    public static final RegistryObject<Item> SWORD_WAFFLE = ITEMS.register("sword_waffle", () -> new WaffleItem(2, 0.8f));
    public static final RegistryObject<Item> SWORD_WAFFLE_MOLD = ITEMS.register("sword_waffle_mold", () -> new WaffleMoldItem(SWORD_WAFFLE.get()));
    // Cake
    public static final RegistryObject<Item> CASSAVA_BIBINGKA = ITEMS.register("cassava_bibingka", () -> new Item(new Item.Properties().food(
            new FoodProperties.Builder().nutrition(3).saturationMod(1.5f).build())));
    public static final RegistryObject<Item> COFFEE_CAKE = ITEMS.register("coffee_cake", () -> new Item(new Item.Properties().food(
            new FoodProperties.Builder().nutrition(3).saturationMod(1.5f).build())));
    public static final List<RegistryObject<Item>> CHEESECAKES =
            new ItemDataLoader("registration_data/cheesecakes.txt").read().stream()
                .map(args -> ITEMS.register(args.get(0), () -> new Item(new Item.Properties().food(
                        new FoodProperties.Builder()
                                .nutrition(Integer.parseInt(args.get(1)))
                                .saturationMod(Float.parseFloat(args.get(2))).build())))
                ).toList();
    // Pie
    public static final List<RegistryObject<Item>> PIES =
            new ItemDataLoader("registration_data/pies.txt").read().stream()
                .map(args -> ITEMS.register(args.get(0), () -> new Item(new Item.Properties().food(
                        new FoodProperties.Builder()
                                .nutrition(Integer.parseInt(args.get(1)))
                                .saturationMod(Float.parseFloat(args.get(2))).build())))
                ).toList();
    // Pudding
    public static final RegistryObject<Item> TAPIOCA_PUDDING = ITEMS.register("tapioca_pudding", () -> new Item(new Item.Properties().food(
            new FoodProperties.Builder().nutrition(3).saturationMod(1.2f).build())));
    public static final RegistryObject<Item> FIGGY_PUDDING = ITEMS.register("figgy_pudding", () -> new Item(new Item.Properties().food(
            new FoodProperties.Builder().nutrition(3).saturationMod(1.2f).build())));
    public static final RegistryObject<Item> FLAN = ITEMS.register("flan", () -> new Item(new Item.Properties().food(
            new FoodProperties.Builder().nutrition(4).saturationMod(2.5f).build())));
    // todo chocolate vanilla caramel
    // Candy
    public static final RegistryObject<Item> PEEPS = ITEMS.register("peeps", () -> new Item(new Item.Properties().food(
            new FoodProperties.Builder().nutrition(1).saturationMod(0.5f).build())));
    public static final RegistryObject<Item> CARAMEL_APPLE = ITEMS.register("caramel_apple", () -> new Item(new Item.Properties().food(
            new FoodProperties.Builder().nutrition(4).saturationMod(2.5f).build())));
    public static final RegistryObject<Item> STRAWBERRY_CHOCOLATE = ITEMS.register("strawberry_chocolate", () -> new Item(new Item.Properties().food(
            new FoodProperties.Builder().nutrition(4).saturationMod(2.5f).build())));
    public static final RegistryObject<Item> WHITE_STRAWBERRY_CHOCOLATE = ITEMS.register("white_strawberry_chocolate", () -> new Item(new Item.Properties().food(
            new FoodProperties.Builder().nutrition(4).saturationMod(2.5f).build())));
    public static final RegistryObject<Item> CHOCOLATE_BANANA = ITEMS.register("chocolate_banana", () -> new Item(new Item.Properties().food(
            new FoodProperties.Builder().nutrition(4).saturationMod(2.5f).build())));
    // Misc Foods
    public static final List<RegistryObject<Item>> BROWNIES =
            new ItemDataLoader("registration_data/brownies.txt").read().stream()
                .map(args -> ITEMS.register(args.get(0), () -> new Item(new Item.Properties().food(
                        new FoodProperties.Builder()
                                .nutrition(Integer.parseInt(args.get(1)))
                                .saturationMod(Float.parseFloat(args.get(2))).build())))
                ).toList();
    public static final RegistryObject<Item> LEMON_BAR = ITEMS.register("lemon_bar", () -> new Item(new Item.Properties().food(
            new FoodProperties.Builder().nutrition(3).saturationMod(1.5f).build())));
    public static final RegistryObject<Item> SOPAIPILLA_CHEESECAKE_BAR = ITEMS.register("sopaipilla_cheesecake_bar", () -> new Item(new Item.Properties().food(
            new FoodProperties.Builder().nutrition(3).saturationMod(1.5f).build())));
    public static final RegistryObject<Item> FIG_NEWTON = ITEMS.register("fig_newton", () -> new Item(new Item.Properties().food(
            new FoodProperties.Builder().nutrition(3).saturationMod(1.5f).build())));
    public static final RegistryObject<Item> MACAROON = ITEMS.register("macaroon", () -> new Item(new Item.Properties().food(
            new FoodProperties.Builder().nutrition(2).saturationMod(0.5f).build())));
    public static final RegistryObject<Item> TIRAMISU = ITEMS.register("tiramisu", () -> new Item(new Item.Properties().food(
            new FoodProperties.Builder().nutrition(3).saturationMod(1.5f).build())));
    // Smores Marshmallow toasting
    public static final RegistryObject<Item> MARSHMALLOW = ITEMS.register("marshmallow", () -> new Item(new Item.Properties().food(
            new FoodProperties.Builder().nutrition(1).saturationMod(0.5f).build())));
    public static final RegistryObject<Item> MARSHMALLOWS = ITEMS.register("marshmallows", () -> new Item(new Item.Properties().food(
            new FoodProperties.Builder().nutrition(1).saturationMod(0.5f).alwaysEat()
                    .effect(() -> new MobEffectInstance(MobEffects.LEVITATION, 60, 0), 1.0f)
                    .effect(() -> new MobEffectInstance(MobEffects.SLOW_FALLING, 100, 0), 1.0f)
                    .build())));
    public static final RegistryObject<Item> MARSHMALLOW_PUFF = ITEMS.register("marshmallow_puff", () -> new Item(new Item.Properties().food(
            new FoodProperties.Builder().nutrition(1).saturationMod(0.5f).build())));
    public static final RegistryObject<Item> MARSHMALLOW_ON_STICK_SCORCHED = ITEMS.register("marshmallow_on_stick_scorched",
            () -> new MarshmallowStickItem(5, BURNT_CRISP.get()));
    public static final RegistryObject<Item> MARSHMALLOW_ON_STICK_BURNT = ITEMS.register("marshmallow_on_stick_burnt",
            () -> new MarshmallowStickItem(5, MARSHMALLOW_ON_STICK_SCORCHED.get()));
    public static final RegistryObject<Item> MARSHMALLOW_ON_STICK_WELL_DONE = ITEMS.register("marshmallow_on_stick_well_done",
            () -> new MarshmallowStickItem(5, MARSHMALLOW_ON_STICK_BURNT.get()));
    public static final RegistryObject<Item> MARSHMALLOW_ON_STICK_GOLDEN = ITEMS.register("marshmallow_on_stick_golden",
            () -> new MarshmallowStickItem(10, MARSHMALLOW_ON_STICK_WELL_DONE.get()));
    public static final RegistryObject<Item> MARSHMALLOW_ON_STICK_TOASTED = ITEMS.register("marshmallow_on_stick_toasted",
            () -> new MarshmallowStickItem(10, MARSHMALLOW_ON_STICK_GOLDEN.get()));
    public static final RegistryObject<Item> MARSHMALLOW_ON_STICK_WARM = ITEMS.register("marshmallow_on_stick_warm",
            () -> new MarshmallowStickItem(10, MARSHMALLOW_ON_STICK_TOASTED.get()));
    public static final RegistryObject<Item> MARSHMALLOW_ON_STICK = ITEMS.register("marshmallow_on_stick",
            () -> new MarshmallowStickItem(5, MARSHMALLOW_ON_STICK_WARM.get()));
    public static final List<RegistryObject<Item>> SMORES = Arrays.stream(new String[]{"warm", "toasted", "golden", "well_done", "burnt", "scorched"})
            .map(str -> ITEMS.register("smore_" + str, () -> new Item(new Item.Properties().food(
                        new FoodProperties.Builder().nutrition(5).saturationMod(2f).build())))
            ).toList();
    // Cannolis
    public static final RegistryObject<Item> CANNOLI_SHELL = ITEMS.register("cannoli_shell", () -> new Item(new Item.Properties()));
    public static final List<RegistryObject<Item>> CANNOLIS =
            new ItemDataLoader("registration_data/creams.txt").read().stream()
                .map(args -> ITEMS.register(args.get(0) + "_cannoli", () -> new Item(new Item.Properties().food(
                        new FoodProperties.Builder().nutrition(2).saturationMod(0.5f).build())))
                ).toList();

    /**
     * Bubble Milk Teas
     */
    public static final List<RegistryObject<MilkTeaItem>> BOBA_MILK_TEAS =
        new ItemDataLoader("registration_data/boba_milk_teas.txt").read().stream().map(
            args -> ITEMS.register(args.get(0) + "_milk_tea",
                    () -> new MilkTeaItem(
                            Integer.parseInt(args.get(1)),
                            Float.parseFloat(args.get(2))))
        ).toList();
    // Funky tea flavors with effects
    public static final RegistryObject<Item> GLOW_BERRY_MILK_TEA = ITEMS.register("glow_berry_milk_tea",
            () -> new MilkTeaItem(new Item.Properties().food(
                    new FoodProperties.Builder().nutrition(2).saturationMod(0.5f)
                            .effect(() -> new MobEffectInstance(MobEffects.GLOWING, 600, 0), 1.0f)
                            .effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 300, 0), 1.0f)
                            .alwaysEat().build()),
                    new TextComponent("§6Glowing!§r")));
    public static final RegistryObject<Item> PHANTOM_MILK_TEA = ITEMS.register("phantom_milk_tea",
            () -> new MilkTeaItem(new Item.Properties().food(
                    new FoodProperties.Builder().nutrition(1).saturationMod(0.4f)
                            .effect(() -> new MobEffectInstance(MobEffects.LEVITATION, 700, 0), 1.0f)
                            .effect(() -> new MobEffectInstance(MobEffects.SLOW_FALLING, 1000, 0), 1.0f)
                            .alwaysEat().build()),
                    new TextComponent("§eA good pick-me-up in the morning§r")));
    public static final RegistryObject<Item> BLAZING_MILK_TEA = ITEMS.register("blazing_milk_tea",
            () -> new MilkTeaItem(new Item.Properties().food(
                    new FoodProperties.Builder().nutrition(0).saturationMod(0f)
                            .effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 600, 0), 1.0f)
                            .alwaysEat().build()),
                    new TextComponent("Now with authentic firey taste").withStyle(ChatFormatting.YELLOW)));
    public static final RegistryObject<Item> DARKSIDE_MILK_TEA = ITEMS.register("darkside_milk_tea",
            () -> new MilkTeaItem(new Item.Properties().food(
                    new FoodProperties.Builder().nutrition(0).saturationMod(0f)
                            .effect(() -> new MobEffectInstance(MobEffects.BLINDNESS, 800, 0), 1.0f)
                            .alwaysEat().build()),
                    new TextComponent("Join the dark side").withStyle(ChatFormatting.YELLOW)));
    public static final RegistryObject<Item> WET_MILK_TEA = ITEMS.register("wet_milk_tea",
            () -> new MilkTeaItem(new Item.Properties().food(
                    new FoodProperties.Builder().nutrition(0).saturationMod(0f)
                            .effect(() -> new MobEffectInstance(MobEffects.WATER_BREATHING, 800, 0), 1.0f)
                            .effect(() -> new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 800, 0), 1.0f)
                            .effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 300, 0), 1.0f)
                            .alwaysEat().build()),
                    new TextComponent("Underwater vibes").withStyle(ChatFormatting.YELLOW)));
    public static final RegistryObject<Item> IMAGINARY_MILK_TEA = ITEMS.register("imaginary_milk_tea",
        () -> new MilkTeaItem(new Item.Properties().food(
                new FoodProperties.Builder().nutrition(0).saturationMod(0f)
                        .effect(() -> new MobEffectInstance(MobEffects.INVISIBILITY, 800, 0), 1.0f)
                        .alwaysEat().build()),
                new TextComponent("It's there, I promise!").withStyle(ChatFormatting.YELLOW)));

    /**
     * Coffee Stuff
     */
    public static final RegistryObject<Item> COFFEE_BEANS_ROASTED = compostable(0.5F, ITEMS.register("coffee_beans_roasted", () -> new Item(new Item.Properties())));
    public static final RegistryObject<Item> COFFEE_GROUNDS = compostable(0.6F, ITEMS.register("coffee_grounds", () -> new Item(new Item.Properties())));
    public static final RegistryObject<Item> COFFEE_FILTER = ITEMS.register("coffee_filter", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> COFFEE_FILTER_USED = compostable(0.5F, ITEMS.register("coffee_filter_used", () -> new Item(new Item.Properties())));
    public static final RegistryObject<Item> COFFEE_POT = ITEMS.register("coffee_pot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> COFFEE_POT_FULL = ITEMS.register("coffee_pot_full",
            () -> new Item(new Item.Properties().craftRemainder(COFFEE_POT.get())));
    public static final RegistryObject<Item> ESPRESSO_SHOT = ITEMS.register("espresso_shot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STEAMED_MILK = ITEMS.register("steamed_milk", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MILK_FOAM = ITEMS.register("milk_foam", () -> new Item(new Item.Properties()));

    public static final List<RegistryObject<CoffeeItem>> COFFEES =
            new ItemDataLoader("registration_data/coffees.txt").read().stream().map(
                    args -> ITEMS.register(args.get(0),
                            () -> new CoffeeItem(
                                    Integer.parseInt(args.get(1)),
                                    Float.parseFloat(args.get(2)),
                                    Integer.parseInt(args.get(3)),
                                    Integer.parseInt(args.get(4)) == 1 ? CLEAR_CUP.get() : null))
            ).toList();
    public static final RegistryObject<Item> RED_EYE_COFFEE = ITEMS.register("red_eye_coffee",
            () -> new CoffeeItem(0, 0, 3, null,
                    new TextComponent("Careful there!").withStyle(ChatFormatting.DARK_RED)));
    public static final RegistryObject<Item> BLACK_EYE_COFFEE = ITEMS.register("black_eye_coffee",
            () -> new CoffeeItem(0, 0, 4, null,
                    new TextComponent("Has a lot of caffeine...").withStyle(ChatFormatting.DARK_RED)));
    public static final RegistryObject<Item> DEAD_EYE_COFFEE = ITEMS.register("dead_eye_coffee",
            () -> new CoffeeItem(0, 0, 5, null,
                    new TextComponent("Too much caffeine...").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.OBFUSCATED)));

    // Teas
    public static final RegistryObject<Item> MATCHA_TEA = ITEMS.register("matcha_tea",
            () -> new CoffeeItem(1, 0.5f, 1, null));

    // Juices & lemonades
    public static final List<RegistryObject<Item>> FRUIT_JUICES =
            new ItemDataLoader("registration_data/juices.txt").read().stream()
                .map(args -> ITEMS.register(args.get(0),
                        () -> (Item) new CustomDrinkItem(
                                Integer.parseInt(args.get(1)),
                                Float.parseFloat(args.get(2)), CLEAR_CUP.get())))
                .toList();
}
