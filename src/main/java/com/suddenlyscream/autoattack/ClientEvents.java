package com.suddenlyscream.autoattack;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = AutoAttack.MODID, value = Dist.CLIENT)
public class ClientEvents {
    private static final Minecraft mc = Minecraft.getInstance();

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        // only execute at the end of the tick
        if (event.phase != TickEvent.Phase.END) {
            return;
        }

        // Ensure player exists
        if (mc.player == null) {
            return;
        }

        // checking if left mouse button is held down
        if (!mc.options.keyAttack.isDown()) {
            return;
        }

        // check if attack cooldown is complete (100%)
        if (mc.player.getAttackStrengthScale(0.1F) < 1.0F) {
            return;
        }

        // get the target under the crosshair
        HitResult hitResult = mc.hitResult;
        if (hitResult == null || hitResult.getType() != HitResult.Type.ENTITY) {
            return;
        }

        Entity target = ((EntityHitResult) hitResult).getEntity();

        // filter out paintings and item frames
        if (target instanceof net.minecraft.world.entity.decoration.ItemFrame
                || target instanceof net.minecraft.world.entity.decoration.Painting) {
            return;
        }

        // execute attack
        mc.gameMode.attack(mc.player, target);
    }
}