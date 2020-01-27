package net.tslat.aoa3.entity.minions;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;

import javax.annotation.Nullable;

public class EntityAlluricorn extends AoAMinion {
	public static final float entityWidth = 1.1f;

	public EntityAlluricorn(final World world){
		super(world, -1, entityWidth, 2.0f);
	}

	@Override
	public float getEyeHeight() {
		return 1.65625f;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityAlluricorn;
	}

	@Override
	protected double getBaseMoveSpeed() {
		return 0.3d;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 200;
	}

	@Override
	protected boolean isHostile() {
		return true;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 13.0d;
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);

		if (stack != ItemStack.EMPTY && getOwner() != null && getOwnerId().equals(player.getUniqueID())) {
			if (stack.getItem() == ItemRegister.runeKinetic && !player.isPotionActive(MobEffects.SPEED)) {
				if (!player.capabilities.isCreativeMode)
					stack.shrink(1);

				player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 150, 3));

				return true;
			}
		}

		return super.processInteract(player, hand);
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobRainicornLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobRainicornHit;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobRainicornDeath;
	}
}
