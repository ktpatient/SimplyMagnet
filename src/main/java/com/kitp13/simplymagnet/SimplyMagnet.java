package com.kitp13.simplymagnet;

import com.kitp13.simplymagnet.components.Components;
import com.kitp13.simplymagnet.content.items.PullerMagnet;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(SimplyMagnet.MODID)
public class SimplyMagnet
{
    public static final String MODID = "simplymagnet";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    public static final DeferredItem<Item> PULLER_MAGNET = ITEMS.register("magnet",
            () -> new PullerMagnet(new Item.Properties()
                    .component(Components.EnabledComp, true)
                    .component(Components.MagnetSpeedComp, 1)
                    .component(Components.MagnetRangeComp, 1)
                    .component(Components.UpgradeSlotsComp,  8)));


    public SimplyMagnet(IEventBus modEventBus, ModContainer modContainer) {
        ITEMS.register(modEventBus);
        Components.REGISTRAR.register(modEventBus);
        // modEventBus.addListener(PullerMagnet::registerCapabilities);
    }
}