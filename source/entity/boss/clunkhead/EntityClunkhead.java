package net.tslat.aoa3.entity.boss.clunkhead;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoARangedMob;
import net.tslat.aoa3.entity.projectiles.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.projectiles.mob.EntityCyanShot;
import net.tslat.aoa3.entity.properties.BossEntity;
import net.tslat.aoa3.entity.properties.SpecialPropertyEntity;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.TreeSet;

public class EntityClunkhead extends AoARangedMob implements SpecialPropertyEntity, BossEntity {
	private static final ResourceLocation bossBarTexture = new ResourceLocation("aoa3", "textures/gui/bossbars/clunkhead.png");
	public static final float entityWidth = 1f;
	private int stasisCountdown = 500;

	public EntityClunkhead(World world) {
		super(world, entityWidth, 2.0625f);

		this.mobProperties.add(Enums.MobProperties.RANGED_IMMUNE);
	}

	@Override
	public float getEyeHeight() {
		return 1.8125f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 1;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 2200;
	}

	@Override
	public double getBaseProjectileDamage() {
		return 1 + rand.nextInt(35);
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.207;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.MOB_CLUNKHEAD_DEATH;
	}

	@Nullable
	@Override
	protected SoundEvent getShootSound() {
		return SoundsRegister.CLUNKHEAD_SHOOT;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityClunkhead;
	}

	@Override
	public boolean isNonBoss() {
		return false;
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return new EntityCyanShot(this, Enums.MobProjectileType.MAGIC);
	}

	@Nonnull
	@Override
	public TreeSet<Enums.MobProperties> getMobProperties() {
		return mobProperties;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (world.isRemote && ticksExisted == 1)
			playMusic(this);
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (EntityUtil.getCurrentHealthPercent(this) < 0.5) {
			stasisCountdown--;

			if (stasisCountdown < 100) {
				heal(1);
				motionX = 0;
				motionY = 0;
				motionZ = 0;

				if (stasisCountdown == 0)
					stasisCountdown = 500;
			}
		}
	}

	@Override
	protected boolean isSpecialImmuneTo(DamageSource source, int damage) {
		return EntityUtil.isRangedDamage(source, this,1);
	}

	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);

		if (!world.isRemote) {
			Entity source = cause.getTrueSource();
			EntityPlayer killer = null;

			if (source != null) {
				if (source instanceof EntityPlayer) {
					killer = (EntityPlayer)source;
				}
				else if (source instanceof EntityTameable && ((EntityTameable)source).getOwner() instanceof EntityPlayer) {
					killer = (EntityPlayer)((EntityTameable)source).getOwner();
				}
			}

			if (killer != null)
				StringUtil.sendMessageWithinRadius(StringUtil.getLocaleWithArguments("message.mob.clunkhead.kill", killer.getDisplayNameString()), this, 50);
		}
	}

	@Override
	public boolean startRiding(Entity entity, boolean force) {
		return false;
	}

	@Nonnull
	@Override
	public ResourceLocation getBossBarTexture() {
		return bossBarTexture;
	}

	@Nullable
	@Override
	public SoundEvent getBossMusic() {
		return SoundsRegister.CLUNKHEAD_MUSIC;
	}

	@Override
	public void setAttackTarget(@Nullable EntityLivingBase target) {
		if (target instanceof BossEntity)
			return;

		super.setAttackTarget(target);
	}
}
