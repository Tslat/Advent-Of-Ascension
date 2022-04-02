package net.tslat.aoa3.event.custom.events;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.minecraftforge.eventbus.api.Cancelable;
import net.tslat.aoa3.content.entity.misc.HaulingFishingBobberEntity;

import java.util.List;

@Cancelable
public class HaulingItemFishedEvent extends ItemFishedEvent {
	private final Entity reeledInEntity;
	private final ItemStack haulingRod;
	private int xp;

	public HaulingItemFishedEvent(Entity reeledIn, ItemStack haulingRod, List<ItemStack> stacks, int xp, int rodDamage, HaulingFishingBobberEntity hook) {
		super(stacks, rodDamage, hook);

		this.reeledInEntity = reeledIn;
		this.haulingRod = haulingRod;
		this.xp = xp;
	}

	@Override
	public HaulingFishingBobberEntity getHookEntity() {
		return (HaulingFishingBobberEntity)super.getHookEntity();
	}

	public Entity getReeledInEntity() {
		return reeledInEntity;
	}

	public ItemStack getHaulingRod() {
		return haulingRod;
	}

	public float getLuck() {
		return getHookEntity().getLuck();
	}

	public int getXp() {
		return xp;
	}

	public void setXp(int xp) {
		this.xp = xp;
	}
}
