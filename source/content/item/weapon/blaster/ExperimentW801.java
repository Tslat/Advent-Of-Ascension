package net.tslat.aoa3.content.item.weapon.blaster;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoASounds;

import javax.annotation.Nullable;

public class ExperimentW801 extends BaseBlaster {
	public ExperimentW801(double dmg, int durability, int fireDelayTicks, float energyCost) {
		super(new Item.Properties().durability(durability).rarity(Rarity.EPIC), dmg, fireDelayTicks, energyCost);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ENTITY_ARCWORM_HURT.get();
	}

	@Override
	public void fire(ItemStack blaster, LivingEntity shooter) {
		/*shooter.level.addFreshEntity(new ArcwormShotEntity(shooter, this, 120));*/
	}

	@Override
	public void inventoryTick(ItemStack stack, Level worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (!verifyStack(stack)) {
			stack.setCount(0);
			((Player)entityIn).getInventory().setItem(itemSlot, ItemStack.EMPTY);
		}
	}

	@Override
	public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
		if (!verifyStack(entity.getItem())) {
			entity.setItem(ItemStack.EMPTY);
			entity.discard();
		}

		return false;
	}

	private boolean verifyStack(ItemStack stack) {
		if (stack.isEmpty())
			return false;

		if (!stack.hasTag())
			return false;

		CompoundTag tag = stack.getTag();

		if (!tag.contains("alien_orb"))
			return false;

		return tag.getBoolean("alien_orb");
	}

	public ItemStack newValidStack() {
		ItemStack stack = new ItemStack(this);
		CompoundTag tag = stack.getOrCreateTag();

		tag.putBoolean("alien_orb", true);

		return stack;
	}
}
