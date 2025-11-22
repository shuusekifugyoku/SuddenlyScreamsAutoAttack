package com.suddenlyscream.autoattack;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(AutoAttack.MODID)
public class AutoAttack {
    public static final String MODID = "autoattack";

    public AutoAttack() {
        // Regisster event bus
        var modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    }
}