package com.kitp13.simplymagnet.event;

import com.kitp13.simplymagnet.SimplyMagnet;
import com.kitp13.simplymagnet.components.Components;
import com.kitp13.simplymagnet.content.items.PullerMagnet;
import com.kitp13.simplymagnet.keybinds.Keybinds;
import com.kitp13.simplymagnet.network.SetMagnetActivePayload;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;

public class ClientTick {
    @EventBusSubscriber(modid = SimplyMagnet.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.GAME)
    public static final class ClientTickInGame {
        @SubscribeEvent
        public static void onClientTick(ClientTickEvent.Post event){
            Minecraft mc = Minecraft.getInstance();
            if (mc.player == null) return;

            boolean keyDown = Keybinds.TOGGLE_MAGNET.get().consumeClick();
            if (keyDown) {
                for (ItemStack stack : mc.player.getInventory().items) {
                    if (stack.getItem() instanceof PullerMagnet) {
                        boolean enabled = stack.getOrDefault(Components.EnabledComp, false);
                        PacketDistributor.sendToServer(new SetMagnetActivePayload(!enabled));
                    }

                }
            }
        }
    }
}
