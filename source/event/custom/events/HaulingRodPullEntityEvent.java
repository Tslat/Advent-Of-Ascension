package net.tslat.aoa3.event.custom.events;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.ICancellableEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.tslat.aoa3.content.entity.misc.HaulingFishingBobberEntity;

public class HaulingRodPullEntityEvent extends PlayerEvent implements ICancellableEvent {
	private final ItemStack haulingRod;
	private final HaulingFishingBobberEntity bobber;
	private final Entity hookedEntity;

	private int rodDamage;
	private float pullStrength;

	public HaulingRodPullEntityEvent(Player player, ItemStack haulingRod, HaulingFishingBobberEntity bobber, Entity hookedEntity, int rodDamage, float pullStrength) {
		super(player);

		this.haulingRod = haulingRod;
		this.bobber = bobber;
		this.hookedEntity = hookedEntity;
		this.rodDamage = rodDamage;
		this.pullStrength = pullStrength;
	}

	public ItemStack getHaulingRod() {
		return this.haulingRod;
	}

	public HaulingFishingBobberEntity getBobber() {
		return this.bobber;
	}

	public Entity getHookedEntity() {
		return this.hookedEntity;
	}

	public int getAdditionalRodDamage() {
		return this.rodDamage;
	}

	public void setAdditionalRodDamage(int damage) {
		this.rodDamage = damage;
	}

	public float getPullStrength() {
		return this.pullStrength;
	}

	public void setPullStrength(float pullStrength) {
		this.pullStrength = pullStrength;
	}
}
