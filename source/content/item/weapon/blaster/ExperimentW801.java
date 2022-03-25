package net.tslat.aoa3.content.item.weapon.blaster;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.projectile.blaster.ArcwormShotEntity;

import javax.annotation.Nullable;

public class ExperimentW801 extends BaseBlaster {
	public ExperimentW801(double dmg, int durability, int fireDelayTicks, float energyCost) {
		super(new Item.Properties().tab(null).durability(durability).rarity(Rarity.EPIC), dmg, fireDelayTicks, energyCost);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ENTITY_ARCWORM_HURT.get();
	}

	@Override
	public void fire(ItemStack blaster, LivingEntity shooter) {
		shooter.level.addFreshEntity(new ArcwormShotEntity(shooter, this, 120));
	}

	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (!verifyStack(stack)) {
			stack.setCount(0);
			entityIn.setSlot(itemSlot, ItemStack.EMPTY);
		}
	}

	@Override
	public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
		if (!verifyStack(entity.getItem())) {
			entity.setItem(ItemStack.EMPTY);
			entity.remove();
		}

		return false;
	}

	private boolean verifyStack(ItemStack stack) {
		if (stack.isEmpty())
			return false;

		if (!stack.hasTag())
			return false;

		CompoundNBT tag = stack.getTag();

		if (!tag.contains("alien_orb"))
			return false;

		return tag.getBoolean("alien_orb");
	}

	public ItemStack newValidStack() {
		ItemStack stack = new ItemStack(this);
		CompoundNBT tag = stack.getOrCreateTag();

		tag.putBoolean("alien_orb", true);

		return stack;
	}
}
