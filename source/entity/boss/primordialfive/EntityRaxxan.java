package net.tslat.aoa3.entity.boss.primordialfive;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.properties.BossEntity;

import javax.annotation.Nullable;

public class EntityRaxxan extends AoAMeleeMob implements BossEntity {
	private static final ResourceLocation bossBarTexture = new ResourceLocation("aoa3", "textures/gui/bossbars/raxxan.png");
	public static final float entityWidth = 0.7f;

	public EntityRaxxan(EntityHarkos harkos) {
		this(harkos.world);

		setLocationAndAngles(harkos.posX, harkos.posY, harkos.posZ, harkos.rotationYaw, harkos.rotationPitch);
	}

	public EntityRaxxan(World world) {
		super(world, entityWidth, 2.375f);

		this.setSlipperyMovement();
		this.setAIMoveSpeed(1.4f);
	}

	@Override
	public float getEyeHeight() {
		return 2.009375f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 1;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 1000;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 15;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.329;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.MOB_PRIMORDIAL_LIVING;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.MOB_PRIMORDIAL_DEATH;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityRaxxan;
	}

	@Override
	public boolean isNonBoss() {
		return false;
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

		if (!world.isRemote) {
			if (rand.nextInt(100) == 0) {
				EntityPlayer nearestTarget = world.getClosestPlayer(posX, posY, posZ, 30, false);

				if (nearestTarget != null && !nearestTarget.capabilities.isCreativeMode) {
					addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 100, 4, true, true));
					world.playSound(null, posX, posY, posZ, SoundsRegister.MOB_VOXXULON_LIVING, SoundCategory.HOSTILE, 1.0f, 1.0f);
				}
			}
			else if (rand.nextInt(100) == 0) {
				addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, 25, 5, true, true));
			}
			else if (rand.nextInt(200) == 0) {
				heal(100);
			}
		}
	}

	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);

		if (!world.isRemote) {
			EntityOkazor okazor = new EntityOkazor(this);

			world.spawnEntity(okazor);
			setDead();
		}
	}

	@Override
	public ResourceLocation getBossBarTexture() {
		return bossBarTexture;
	}

	@Nullable
	@Override
	public SoundEvent getBossMusic() {
		return SoundsRegister.PRIMORDIAL_FIVE_MUSIC;
	}

	@Override
	public void setAttackTarget(@Nullable EntityLivingBase target) {
		if (target instanceof BossEntity)
			return;

		super.setAttackTarget(target);
	}
}
