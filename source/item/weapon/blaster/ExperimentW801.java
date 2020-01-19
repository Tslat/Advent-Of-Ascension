package net.tslat.aoa3.item.weapon.blaster;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.projectiles.blaster.EntityArcwormShot;

import javax.annotation.Nullable;

public class ExperimentW801 extends BaseBlaster {
	public ExperimentW801(double dmg, int durability, int fireDelayTicks, float energyCost) {
		super(dmg, durability, fireDelayTicks, energyCost);
		setTranslationKey("ExperimentW801");
		setRegistryName("aoa3:experiment_w801");

		setCreativeTab(null);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return SoundsRegister.mobArcwormHit;
	}

	@Override
	public void fire(ItemStack blaster, EntityLivingBase shooter) {
		shooter.world.spawnEntity(new EntityArcwormShot(shooter, this, 120));
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (!verifyStack(stack)) {
			stack.setCount(0);
			entityIn.replaceItemInInventory(itemSlot, ItemStack.EMPTY);
		}
	}

	@Override
	public boolean onEntityItemUpdate(EntityItem entity) {
		if (!verifyStack(entity.getItem())) {
			entity.setItem(ItemStack.EMPTY);
			entity.setDead();
		}

		return false;
	}

	private boolean verifyStack(ItemStack stack) {
		if (stack.isEmpty())
			return false;

		if (!stack.hasTagCompound())
			return false;

		NBTTagCompound tag = stack.getTagCompound();

		if (!tag.hasKey("alien_orb"))
			return false;

		return tag.getBoolean("alien_orb");
	}

	public ItemStack newValidStack() {
		ItemStack stack = new ItemStack(this);
		NBTTagCompound tag = new NBTTagCompound();

		tag.setBoolean("alien_orb", true);
		stack.setTagCompound(tag);

		return stack;
	}
}
