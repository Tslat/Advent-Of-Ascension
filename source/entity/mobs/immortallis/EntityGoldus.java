package net.tslat.aoa3.entity.mobs.immortallis;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.utils.ItemUtil;

import javax.annotation.Nullable;

public class EntityGoldus extends AoAMeleeMob {
	public static final float entityWidth = 0.6f;

	public EntityGoldus(World world) {
		super(world, entityWidth, 1.5f);
	}

	@Override
	public float getEyeHeight() {
		return 1.5f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 1;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 1;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 0;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return null;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.MOB_AUTOMATON_DEATH;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.MOB_AUTOMATON_HIT;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return null;
	}

	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);

		if (!world.isRemote) {
			Entity attacker = cause.getTrueSource();

			if (attacker instanceof EntityPlayer || attacker instanceof EntityTameable) {
				EntityPlayer pl = null;

				if (attacker instanceof EntityTameable) {
					if (((EntityTameable)attacker).getOwner() instanceof EntityPlayer)
						pl = (EntityPlayer)((EntityTameable)attacker).getOwner();
				}
				else {
					pl = (EntityPlayer)attacker;
				}

				if (pl != null && !pl.inventory.hasItemStack(new ItemStack(ItemRegister.PURE_GOLD))) {
					ItemUtil.givePlayerItemOrDrop(pl, new ItemStack(ItemRegister.PURE_GOLD));
					pl.inventoryContainer.detectAndSendChanges();
				}
			}
		}
	}
}
