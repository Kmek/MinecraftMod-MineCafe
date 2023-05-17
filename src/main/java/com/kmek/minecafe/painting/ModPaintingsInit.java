package com.kmek.minecafe.painting;

import com.kmek.minecafe.MineCafeMod;
import net.minecraft.world.entity.decoration.Motive;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModPaintingsInit {
    public static final DeferredRegister<Motive> PAINTING_VARIANTS = DeferredRegister.create(ForgeRegistries.PAINTING_TYPES, MineCafeMod.MODID);

    public static final RegistryObject<Motive> TAIYAKI = PAINTING_VARIANTS.register("taiyaki", () -> new Motive(16, 16));
    public static final RegistryObject<Motive> TAIYAKI_MOLD = PAINTING_VARIANTS.register("taiyaki_mold", () -> new Motive(16, 16));
    public static final RegistryObject<Motive> BOBA_SIGN = PAINTING_VARIANTS.register("boba_sign", () -> new Motive(48, 16));
    public static final RegistryObject<Motive> BROWN_SUGAR_MILK_TEA = PAINTING_VARIANTS.register("brown_sugar_milk_tea", () -> new Motive(16, 32));
    public static final RegistryObject<Motive> MILK_TEAS_TRIO = PAINTING_VARIANTS.register("milk_teas_trio", () -> new Motive(32, 32));

    public static void register(IEventBus eventBus) {
        PAINTING_VARIANTS.register(eventBus);
    }
}
