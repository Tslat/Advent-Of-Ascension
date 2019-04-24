package net.tslat.aoa3.entity.minions;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFishFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.item.food.HealingFishFood;
import net.tslat.aoa3.utils.EntityUtil;

import javax.annotation.Nullable;

public class EntityGnawer extends AoAMinion {
	public static final float entityWidth = 1.0f;

	public EntityGnawer(final World world){
		super(world, -1, entityWidth, 2.5f);
	}

	@Override
	public float getEyeHeight() {
		return 1.93f;
	}

	@Override
	protected double getBaseMoveSpeed() {
		return 0.3d;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 430.0d;
	}

	@Override
	protected boolean isHostile() {
		return true;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 9.0d;
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		Item item = stack.getItem();

		if (item != Items.AIR && getOwner() != null && getOwnerId().equals(player.getUniqueID())) {
			if (item instanceof HealingFishFood) {
				if (!player.capabilities.isCreativeMode && player.getHealth() < player.getMaxHealth()) {
					stack.shrink(1);
					EntityUtil.healEntity(player, ((HealingFishFood)item).healAmount);
					this.setHealth(this.getHealth() - ((HealingFishFood)item).healAmount);

					return true;
				}
			}
			else if (item instanceof ItemFishFood) {
				if (!player.capabilities.isCreativeMode && player.getHealth() < player.getMaxHealth()) {
					stack.shrink(1);
					EntityUtil.healEntity(player, ((ItemFishFood)item).getHealAmount(stack));
					this.setHealth(this.getHealth() - ((ItemFishFood)item).getHealAmount(stack));

					return true;
				}
			}
		}

		return super.processInteract(player, hand);
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobChomperLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobChomperHit;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return getHurtSound(DamageSource.FALL);
	}
}
