package com.kitp13.simplymagnet.network;

import com.kitp13.simplymagnet.SimplyMagnet;
import com.kitp13.simplymagnet.components.Components;
import com.kitp13.simplymagnet.content.items.PullerMagnet;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jetbrains.annotations.NotNull;

public record SetMagnetActivePayload(boolean active) implements CustomPacketPayload {
    public static final Type<SetMagnetActivePayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(SimplyMagnet.MODID, "toggle_magnet"));

    public static final StreamCodec<RegistryFriendlyByteBuf, SetMagnetActivePayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL,
            SetMagnetActivePayload::active,
            SetMagnetActivePayload::new
    );

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void action(SetMagnetActivePayload payload, IPayloadContext ctx) {
        Player player = ctx.player();
        for (ItemStack stack : player.getInventory().items) {
            if (stack.getItem() instanceof PullerMagnet magnet) {
                stack.set(Components.EnabledComp, !stack.get(Components.EnabledComp));
                return;
            }
        }
    }

}
