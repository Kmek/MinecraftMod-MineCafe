package com.kmek.minecafe.item.registery;

public enum FoodVariants {
    // Vanilla variants
    CARROT, POTATO, BEETROOT,
    SWEET_BERRY, GLOW_BERRY, WATERMELON, CHORUS_FRUIT,

    // Sweet variants
    VANILLA, CHOCOLATE, CARAMEL, PUMPKIN_SPICE, HONEY, BROWN_SUGAR,

    // Flowery
    ROSE, BUTTERFLY_PEA, LAVENDER, MATCHA,

    // Fruit tree crops
    APPLE,
    ORANGE,
    LEMON,
    LIME,
    AVOCADO,
    LYCHEE,
    BANANA,
    MANGO,
    CHERRY,
    PEACH,
    PEAR,
    PLUM,
    FIG,

    // To be implemented as growable crops eventually
    GRAPE,
    KIWI,
    PINEAPPLE,
    DRAGON_FRUIT,
    PASSION_FRUIT,
    POMEGRANATE,
    COCONUT,
    DATE,
    HONEYDEW_MELON,
    MANGOSTEEN,
    MONKFRUIT,
    STARFRUIT,
    PAPAYA,
    GUAVA,
    SOURSOP,
    CUSTARD_APPLE,
    SALAK,
    EGG_FRUIT,

    STRAWBERRY,
    WHITE_STRAWBERRY,
    RASPBERRY,
    BLUEBERRY,
    CRANBERRY,
    ACAI,
    GOOSEBERRY,
    KIWIBERRY,

    VANILLA_BEAN_RAW,
    ALOE_VERA;

    public static final FoodVariants[] fruits = { APPLE, ORANGE, LEMON, LIME, AVOCADO, LYCHEE, BANANA, MANGO, CHERRY,
            PEACH, PEAR, PLUM, FIG, GRAPE, KIWI, PINEAPPLE, DRAGON_FRUIT, PASSION_FRUIT,
            POMEGRANATE, COCONUT, DATE, HONEYDEW_MELON, MANGOSTEEN, MONKFRUIT,
            STARFRUIT, PAPAYA, GUAVA, SOURSOP, CUSTARD_APPLE, SALAK, EGG_FRUIT,
            STRAWBERRY, WHITE_STRAWBERRY, RASPBERRY, BLUEBERRY, CRANBERRY, ACAI, GOOSEBERRY, KIWIBERRY,
            VANILLA_BEAN_RAW, ALOE_VERA };

    public static final FoodVariants[] treeCrops = {APPLE, ORANGE, LEMON, LIME, AVOCADO, LYCHEE, BANANA, MANGO, CHERRY, PEACH,
            PEAR, PLUM, FIG/*, DRAGON_FRUIT, EGG_FRUIT, GRAPE, KIWI, MANGOSTEEN, PAPAYA, PASSION_FRUIT, POMEGRANATE*/};

    public static final FoodVariants[] bushCrops = {STRAWBERRY, WHITE_STRAWBERRY, RASPBERRY, BLUEBERRY, CRANBERRY, PINEAPPLE/*, GRAPE?*/};

    public static final FoodVariants[] juices = {APPLE, ORANGE, LEMON, LIME, LYCHEE, MANGO, CHERRY, PEACH,
            PEAR, PLUM, FIG, SWEET_BERRY, WATERMELON, GLOW_BERRY, CHORUS_FRUIT, CARROT, POTATO, BEETROOT/*,
            STRAWBERRY, RASPBERRY, BLUEBERRY, CRANBERRY, DRAGON_FRUIT, GRAPE, KIWI, MANGOSTEEN, PAPAYA,
            PASSION_FRUIT, PINEAPPLE, POMEGRANATE*/};

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
