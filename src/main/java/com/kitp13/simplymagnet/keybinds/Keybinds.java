package com.kitp13.simplymagnet.keybinds;

import com.kitp13.simplymagnet.SimplyMagnet;
import net.minecraft.client.KeyMapping;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.common.util.Lazy;
import org.lwjgl.glfw.GLFW;
@EventBusSubscriber(modid = SimplyMagnet.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class Keybinds {
    public static final Lazy<KeyMapping> TOGGLE_MAGNET = Lazy.of(()->new KeyMapping("Toggle Magnet", GLFW.GLFW_KEY_B, SimplyMagnet.MODID));

    @SubscribeEvent
    public static void registerKeyBindings(RegisterKeyMappingsEvent event) {
        event.register(TOGGLE_MAGNET.get());
    }
}
