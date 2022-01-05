package net.tslat.aoa3.event.custom.events;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Cancelable;
import net.tslat.aoa3.object.entity.misc.HaulingFishingBobberEntity;

@Cancelable
public class HaulingRodPullEntityEvent extends PlayerEvent {
	private final ItemStack haulingRod;
	private final HaulingFishingBobberEntity bobber;
	private final Entity hookedEntity;

	private int rodDamage;
	private float pullStrength;

	public HaulingRodPullEntityEvent(PlayerEntity player, ItemStack haulingRod, HaulingFishingBobberEntity bobber, Entity hookedEntity, int rodDamage, float pullStrength) {
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
