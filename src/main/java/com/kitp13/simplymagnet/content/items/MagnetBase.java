package com.kitp13.simplymagnet.content.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class MagnetBase extends Item {


    public MagnetBase(Properties properties) {
        super(properties);
    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        return 1;
    }
}
