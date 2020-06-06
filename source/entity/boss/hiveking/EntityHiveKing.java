package net.tslat.aoa3.entity.boss.hiveking;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.minions.EntityHiveSoldier;
import net.tslat.aoa3.entity.properties.BossEntity;
import net.tslat.aoa3.utils.ModUtil;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nullable;

public class EntityHiveKing extends AoAMeleeMob implements BossEntity {
	private static final DataParameter<Integer> GROWTH_PERCENT = EntityDataManager.<Integer>createKey(EntityHiveKing.class, DataSerializers.VARINT);

	private static final ResourceLocation bossBarTexture = new ResourceLocation("aoa3", "textures/gui/bossbars/hive_king.png");
	public static final float entityWidth = 1.2f;
	public static final float entityHeight = 1.5f;

	private int growthPercent = 0;

	public EntityHiveKing(World world) {
		super(world, entityWidth, entityHeight);

		if (!world.isRemote) {
			dataManager.set(GROWTH_PERCENT, 100);
			growthPercent = 100;
		}

		setSize(entityWidth / (100 / (float)growthPercent), entityHeight / (100 / (float)growthPercent));
	}

	public EntityHiveKing(World world, int growthPercent) {
		this(world);

		this.growthPercent = growthPercent;

		dataManager.set(GROWTH_PERCENT, growthPercent);
		setHealth(Math.max(1, getMaxHealth() / (100 / (float)growthPercent)));
		setNoAI(true);
	}

	@Override
	protected void entityInit() {
		super.entityInit();

		dataManager.register(GROWTH_PERCENT, 0);
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.8;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 2500;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 20;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.MOB_HIVE_KING_LIVING;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.MOB_HIVE_KING_DEATH;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.MOB_HIVE_KING_LIVING;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return growthPercent >= 100 ? LootSystemRegister.entityHiveKing : null;
	}

	@Override
	public boolean isNonBoss() {
		return false;
	}

	public int getGrowthPercent() {
		return growthPercent;
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

		if (growthPercent < 100) {
			if (!world.isRemote) {
				incrementGrowth();
			}
			else {
				growthPercent = dataManager.get(GROWTH_PERCENT);
			}

			setSize(entityWidth / (100 / (float)growthPercent), entityHeight / (100 / (float)growthPercent));

			if (growthPercent == 100)
				setNoAI(false);

			return;
		}

		if (!world.isRemote && rand.nextInt(500) == 0) {
			EntityHiveWorker worker = new EntityHiveWorker(this);

			world.spawnEntity(worker);
		}
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (growthPercent >= 100)
			return super.attackEntityFrom(source, amount);

		if (!world.isRemote)
			setDead();

		return true;
	}

	private void incrementGrowth() {
		growthPercent++;

		dataManager.set(GROWTH_PERCENT, growthPercent);
		setHealth(getMaxHealth() / (100 / (float)growthPercent));
	}

	@Override
	public void onDeath(DamageSource cause) {
		if (growthPercent < 100)
			return;

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

					if (killer instanceof EntityPlayerMP && source instanceof EntityHiveSoldier)
						ModUtil.completeAdvancement((EntityPlayerMP)killer, "barathos/daddy_issues", "hive_soldier_kill");
				}
			}

			if (killer != null)
				StringUtil.sendMessageWithinRadius(StringUtil.getLocaleWithArguments("message.mob.hiveKing.kill", killer.getDisplayNameString()), this, 50);
		}
	}

	@Override
	public ResourceLocation getBossBarTexture() {
		return bossBarTexture;
	}

	@Nullable
	@Override
	public SoundEvent getBossMusic() {
		return SoundsRegister.HIVE_KING_MUSIC;
	}

	@Override
	public void setAttackTarget(@Nullable EntityLivingBase target) {
		if (target instanceof BossEntity)
			return;

		super.setAttackTarget(target);
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}
}
