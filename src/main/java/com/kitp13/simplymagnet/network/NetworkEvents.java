package com.kitp13.simplymagnet.network;

import com.kitp13.simplymagnet.SimplyMagnet;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = SimplyMagnet.MODID, bus = EventBusSubscriber.Bus.MOD)
public class NetworkEvents {
    @SubscribeEvent
    public static void registerPayloads(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar(SimplyMagnet.MODID);
        registrar.playBidirectional(
                SetMagnetActivePayload.TYPE,
                SetMagnetActivePayload.STREAM_CODEC,
                SetMagnetActivePayload::action);
    }
}
