package net.tslat.aoa3.event.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.tslat.aoa3.event.custom.events.*;
import net.tslat.aoa3.content.entity.misc.HaulingFishingBobberEntity;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.player.skill.AoASkill;

import java.util.List;

public final class AoAEvents {
	public static void playerLevelChange(ServerPlayerDataManager playerDataManager, AoASkill.Instance skill, int oldLevel, boolean wasNaturallyChanged) {
		MinecraftForge.EVENT_BUS.post(new PlayerLevelChangeEvent(playerDataManager, skill, oldLevel, wasNaturallyChanged));
	}

	public static float playerChangeXp(ServerPlayerDataManager playerDataManager, AoASkill.Instance skill, float xpGained, float xpAfterModifiers, boolean wasNaturallyGained) {
		PlayerChangeXpEvent event = new PlayerChangeXpEvent(playerDataManager, skill, xpGained, xpAfterModifiers, wasNaturallyGained);

		boolean cancelled = MinecraftForge.EVENT_BUS.post(event);

		return cancelled ? 0 : event.getNewXpGain();
	}

	public static HaulingItemFishedEvent haulingItemFished(Entity hookedEntity, ItemStack rodStack, List<ItemStack> lootList, int baseXp, int rodDamage, HaulingFishingBobberEntity bobber) {
		HaulingItemFishedEvent event = new HaulingItemFishedEvent(hookedEntity, rodStack, lootList, baseXp, rodDamage, bobber);

		MinecraftForge.EVENT_BUS.post(event);

		return event;
	}

	public static HaulingRodPullEntityEvent haulingRodPullEntity(PlayerEntity player, ItemStack haulingRod, HaulingFishingBobberEntity bobber, Entity hookedEntity, int rodDamage, float pullStrength) {
		HaulingRodPullEntityEvent event = new HaulingRodPullEntityEvent(player, haulingRod, bobber, hookedEntity, rodDamage, pullStrength);

		MinecraftForge.EVENT_BUS.post(event);

		return event;
	}

	public static boolean firePlayerCraftingEvent(PlayerEntity player, ItemStack crafting, CraftingInventory craftingInventory, CraftResultInventory outputInventory) {
		return MinecraftForge.EVENT_BUS.post(new ItemCraftingEvent(player, crafting, craftingInventory, outputInventory));
	}

	public static boolean firePlayerSmeltingEvent(PlayerEntity player, ItemStack smelting, IInventory outputInventory) {
		return MinecraftForge.EVENT_BUS.post(new ItemSmeltingEvent(player, smelting, outputInventory));
	}
}
