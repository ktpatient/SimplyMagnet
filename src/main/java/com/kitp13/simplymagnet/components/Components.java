package com.kitp13.simplymagnet.components;

import com.kitp13.simplymagnet.SimplyMagnet;
import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class Components {
    public static final DeferredRegister.DataComponents REGISTRAR = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, SimplyMagnet.MODID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> EnabledComp = REGISTRAR.registerComponentType(
            "enabled",
            builder -> builder
                    .persistent(Codec.BOOL)
                    .networkSynchronized(ByteBufCodecs.BOOL)
    );
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> MagnetSpeedComp = REGISTRAR.registerComponentType(
            "magnet_speed",
            builder -> builder
                    .persistent(Codec.INT)
                    .networkSynchronized(ByteBufCodecs.INT)
    );
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> MagnetRangeComp = REGISTRAR.registerComponentType(
            "magnet_range",
            builder -> builder
                    .persistent(Codec.INT)
                    .networkSynchronized(ByteBufCodecs.INT)
    );
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> UpgradeSlotsComp = REGISTRAR.registerComponentType(
            "upgrade_slots",
            builder -> builder
                    .persistent(Codec.INT)
                    .networkSynchronized(ByteBufCodecs.INT)
    );


}
