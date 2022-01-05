package net.tslat.aoa3.object.entity.mob.nowhere;

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
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.object.entity.base.AoAMeleeMob;
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

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return null;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.IRON_GOLEM_HURT;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.IRON_GOLEM_DEATH;
	}

	@Nullable
	@Override
	protected ResourceLocation getDefaultLootTable() {
		return null;
	}

	@Override
	public void die(DamageSource cause) {
		super.die(cause);

		if (!level.isClientSide) {
			Entity attacker = cause.getEntity();

			if (attacker instanceof PlayerEntity || attacker instanceof TameableEntity) {
				PlayerEntity pl = null;

				if (attacker instanceof TameableEntity) {
					if (((TameableEntity)attacker).getOwner() instanceof PlayerEntity)
						pl = (PlayerEntity)((TameableEntity)attacker).getOwner();
				}
				else {
					pl = (PlayerEntity)attacker;
				}

				if (pl != null && !pl.inventory.contains(new ItemStack(AoAItems.PURE_GOLD.get()))) {
					ItemUtil.givePlayerItemOrDrop(pl, new ItemStack(AoAItems.PURE_GOLD.get()));
					pl.inventoryMenu.broadcastChanges();
				}
			}
		}
	}
}
