package com.kitp13.simplymagnet.content.items;

import com.kitp13.simplymagnet.components.Components;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.AnvilUpdateEvent;

import java.util.List;

@EventBusSubscriber
public class PullerMagnet extends MagnetBase {

    //public static void registerCapabilities(final RegisterCapabilitiesEvent event){
    //    event.registerItem(
    //            CuriosCapability.ITEM,
    //            (stack, ctx) -> new ICurio() {
    //                @Override
    //                public ItemStack getStack() {
    //                    return SimpleMagnet.PULLER_MAGNET.toStack();
    //                }
    //
    //                @Override
    //                public void curioTick(SlotContext slotContext) {
    //                    // ICurio.super.curioTick(slotContext);
    //                    performAction(slotContext.entity().level(), (Player) slotContext.entity(), getStack());
    //                }
    //            }, SimpleMagnet.PULLER_MAGNET.get()
    //    );
    //}


    public PullerMagnet(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        boolean isEnabled = stack.getComponents().getOrDefault(Components.EnabledComp.get(), false);
        tooltipComponents.add(Component.literal(isEnabled? ChatFormatting.DARK_GREEN + "Enabled" : ChatFormatting.DARK_RED + "Disabled"));
        if (InputConstants.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), InputConstants.KEY_LSHIFT)){
            int magnetSpeed = stack.getComponents().getOrDefault(Components.MagnetSpeedComp.get(), -1);
            int magnetRange = stack.getComponents().getOrDefault(Components.MagnetRangeComp.get(), -1);
            int upgradeSlots = stack.getComponents().getOrDefault(Components.UpgradeSlotsComp.get(), -1);
            tooltipComponents.add(Component.literal("Speed: "  + magnetSpeed));
            tooltipComponents.add(Component.literal("Range: "  + magnetRange));
            tooltipComponents.add(Component.literal("Upgrade Slots Left: "  + upgradeSlots));
        } else {
            tooltipComponents.add(Component.literal("Hold "+"["+ChatFormatting.DARK_GRAY + "Shift"+ChatFormatting.WHITE+"]"+" to view stats"));
            tooltipComponents.add(Component.literal("Combine with 8 Redstone to "+ChatFormatting.GREEN + "increase"+ChatFormatting.WHITE+" the speed"));
            tooltipComponents.add(Component.literal("Combine with a Diamond to "+ChatFormatting.GREEN + "increase"+ChatFormatting.WHITE+" the range"));
        }

    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        super.inventoryTick(stack, level, entity, slotId, isSelected);
        performAction(level, (Player) entity, stack);
    }

    public static void performAction(Level level, Player player, ItemStack stack) {
        boolean enabled = stack.getOrDefault(Components.EnabledComp, false);

        if (!enabled) {return;}

        int range = stack.getOrDefault(Components.MagnetRangeComp, 0);
        float speed = (float) stack.getOrDefault(Components.MagnetSpeedComp, 0) /10;

        List<ItemEntity> items = level.getEntitiesOfClass(ItemEntity.class, player.getBoundingBox().inflate(range));

        for (ItemEntity item : items) {
            if (!(item.getItem().getItem() instanceof PullerMagnet)) {
                Vec3 pullVec = player.position().subtract(item.position()).normalize().scale(speed);
                item.setDeltaMovement(pullVec);
                item.setPickUpDelay(0);
            }
        }
    }

    @SubscribeEvent
    public static void onAnvilUpdate(AnvilUpdateEvent event) {
        ItemStack left = event.getLeft();
        ItemStack right = event.getRight();

        if (left.getItem() instanceof PullerMagnet && left.getOrDefault(Components.UpgradeSlotsComp,-1) >= 1) {
            ItemStack output = left.copy();
            if (right.getItem() == Items.REDSTONE) {
                output.set(Components.UpgradeSlotsComp, output.getOrDefault(Components.UpgradeSlotsComp,-1) - 1);
                output.set(Components.MagnetSpeedComp, output.getOrDefault(Components.MagnetSpeedComp,-1) + 1);
                event.setOutput(output);
                event.setCost(8);
                event.setMaterialCost(1);
            } else if (right.getItem() == Items.DIAMOND) {
                output.set(Components.UpgradeSlotsComp, output.getOrDefault(Components.UpgradeSlotsComp,-1) - 1);
                output.set(Components.MagnetRangeComp, output.getOrDefault(Components.MagnetRangeComp,-1) + 1);
                event.setOutput(output);
                event.setCost(1);
                event.setMaterialCost(1);
            }
        }
    }
}
