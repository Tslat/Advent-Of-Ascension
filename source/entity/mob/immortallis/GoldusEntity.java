package net.tslat.aoa3.entity.mob.immortallis;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.ItemUtil;

import javax.annotation.Nullable;

public class GoldusEntity extends AoAMeleeMob {
	public GoldusEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
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
		return AoASounds.ENTITY_AUTOMATON_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_AUTOMATON_HURT.get();
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

			if (attacker instanceof PlayerEntity || attacker instanceof TameableEntity) {
				PlayerEntity pl = null;

				if (attacker instanceof TameableEntity) {
					if (((TameableEntity)attacker).getOwner() instanceof PlayerEntity)
						pl = (PlayerEntity)((TameableEntity)attacker).getOwner();
				}
				else {
					pl = (PlayerEntity)attacker;
				}

				if (pl != null && !pl.inventory.hasItemStack(new ItemStack(AoAItems.PURE_GOLD.get()))) {
					ItemUtil.givePlayerItemOrDrop(pl, new ItemStack(AoAItems.PURE_GOLD.get()));
					pl.container.detectAndSendChanges();
				}
			}
		}
	}
}
