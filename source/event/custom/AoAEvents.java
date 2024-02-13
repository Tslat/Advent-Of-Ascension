package net.tslat.aoa3.event.custom;

import net.minecraft.world.Container;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.NeoForge;
import net.tslat.aoa3.content.entity.misc.HaulingFishingBobberEntity;
import net.tslat.aoa3.event.custom.events.*;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.player.skill.AoASkill;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public final class AoAEvents {
	public static void playerLevelChange(ServerPlayerDataManager playerDataManager, AoASkill.Instance skill, int oldLevel, boolean wasNaturallyChanged) {
		NeoForge.EVENT_BUS.post(new PlayerLevelChangeEvent(playerDataManager, skill, oldLevel, wasNaturallyChanged));
	}

	public static float playerChangeXp(ServerPlayerDataManager playerDataManager, AoASkill.Instance skill, float xpGained, float xpAfterModifiers, boolean wasNaturallyGained) {
		PlayerChangeXpEvent event = NeoForge.EVENT_BUS.post(new PlayerChangeXpEvent(playerDataManager, skill, xpGained, xpAfterModifiers, wasNaturallyGained));

		return event.isCanceled() ? 0 : event.getNewXpGain();
	}

	public static HaulingItemFishedEvent haulingItemFished(Entity hookedEntity, ItemStack rodStack, List<ItemStack> lootList, int baseXp, int rodDamage, HaulingFishingBobberEntity bobber) {
		HaulingItemFishedEvent event = new HaulingItemFishedEvent(hookedEntity, rodStack, lootList, baseXp, rodDamage, bobber);

		NeoForge.EVENT_BUS.post(event);

		return event;
	}

	public static HaulingRodPullEntityEvent haulingRodPullEntity(Player player, ItemStack haulingRod, HaulingFishingBobberEntity bobber, Entity hookedEntity, int rodDamage, float pullStrength) {
		HaulingRodPullEntityEvent event = new HaulingRodPullEntityEvent(player, haulingRod, bobber, hookedEntity, rodDamage, pullStrength);

		NeoForge.EVENT_BUS.post(event);

		return event;
	}

	/**
	 * Returns true if cancelled
	 */
	public static boolean firePlayerCraftingEvent(Player player, ItemStack crafting, Container craftingInventory, ResultContainer outputInventory) {
		return NeoForge.EVENT_BUS.post(new ItemCraftingEvent(player, crafting, craftingInventory, outputInventory)).isCanceled();
	}

	/**
	 * Returns true if cancelled
	 */
	public static boolean firePlayerGrindstoneEvent(Player player, ItemStack result, Container inputSlots) {
		return NeoForge.EVENT_BUS.post(new GrindstoneResultEvent(player, result, inputSlots)).isCanceled();
	}

	public static void firePlayerSmeltingEvent(Player player, ItemStack smelting, Container outputInventory) {
		NeoForge.EVENT_BUS.post(new ItemSmeltingEvent(player, smelting, outputInventory));
	}

	public static MagicTeleportEvent magicalTeleport(Entity entity, @Nullable Entity teleportSource, @Nullable Entity indirectTeleportSource, Vec3 teleportPosition) {
		MagicTeleportEvent event = new MagicTeleportEvent(entity, teleportSource, indirectTeleportSource, teleportPosition);

		NeoForge.EVENT_BUS.post(event);

		return event;
	}
}
