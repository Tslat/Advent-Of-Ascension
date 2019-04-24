package net.tslat.aoa3.entity.boss.crystocore;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.fx.audio.BossMusicSound;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoAFlyingMeleeMob;
import net.tslat.aoa3.entity.properties.BossEntity;
import net.tslat.aoa3.entity.properties.SpecialPropertyEntity;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.PredicateUtil;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.TreeSet;

public class EntityCrystocore extends AoAFlyingMeleeMob implements BossEntity, SpecialPropertyEntity {
	private static final ResourceLocation bossBarTexture = new ResourceLocation("aoa3", "textures/gui/bossbars/crystocore.png");
	public static final float entityWidth = 2.5f;
	private byte damageType = 0;
	private int changeCooldown = 220;
	private static final DataParameter<Byte> TYPE = EntityDataManager.<Byte>createKey(EntityCrystocore.class, DataSerializers.BYTE);

	@SideOnly(Side.CLIENT)
	protected BossMusicSound bossMusic;

	public EntityCrystocore(World world) {
		super(world, entityWidth, 4.875f);

		this.mobProperties.add(Enums.MobProperties.GUN_IMMUNE);
	}

	@Override
	public float getEyeHeight() {
		return 3.625f;
	}

	@Override
	protected void entityInit() {
		super.entityInit();

		this.dataManager.register(TYPE, (byte)0);
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.6;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 3000;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 15;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.1;
	}

	public int getType() {
		return this.dataManager.get(TYPE);
	}

	private void changeState() {
		damageType = (byte)rand.nextInt(6);

		this.dataManager.set(TYPE, damageType);
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobCrystalConstructLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobCrystalConstructDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobCrystalConstructHit;
	}

	@Override
	public boolean isNonBoss() {
		return false;
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		dropItem(Item.getItemFromBlock(BlockRegister.statueCrystocore), 1);

		switch (rand.nextInt(3)) {
			case 0:
				dropItem(WeaponRegister.staffCryston, 1);
				break;
			case 1:
				dropItem(WeaponRegister.staffCrystik, 1);
				break;
			case 2:
				dropItem(WeaponRegister.staffCrystal, 1);
				break;
		}
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (!world.isRemote) {
			if (changeCooldown > 0) {
				changeCooldown--;
			}
			else {
				changeCooldown = 220;
				changeState();

				PotionEffect effect;

				switch (rand.nextInt(6)) {
					case 0:
						effect = new PotionEffect(MobEffects.POISON, 180, 1, true, true);
						break;
					case 1:
						effect = new PotionEffect(MobEffects.BLINDNESS, 180, 2, true, true);
						break;
					case 2:
						effect = new PotionEffect(MobEffects.WEAKNESS, 180, 1, true, true);
						break;
					case 3:
						effect = new PotionEffect(MobEffects.NAUSEA, 180, 10, true, true);
						break;
					case 4:
						effect = new PotionEffect(MobEffects.WITHER, 180, 1, true, true);
						break;
					case 5:
					default:
						effect = new PotionEffect(MobEffects.SLOWNESS, 180, 1, true, true);
						break;
				}

				for (EntityPlayer pl : world.getEntitiesWithinAABB(EntityPlayer.class, getEntityBoundingBox().grow(10), PredicateUtil.IS_VULNERABLE_PLAYER)) {
					pl.addPotionEffect(effect);
				}
			}
		}
	}

	@Override
	protected boolean isSpecialImmuneTo(DamageSource source) {
		return EntityUtil.isGunDamage(source);
	}

	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);

		if (!world.isRemote && !isAIDisabled()) {
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
				StringUtil.sendMessageWithinRadius(StringUtil.getLocaleWithArguments("message.mob.crystocore.kill", killer.getDisplayNameString()), this, 50);
		}
	}

	@Override
	protected void doMeleeEffect(Entity target) {
		if (target instanceof EntityLivingBase) {
			switch (damageType) {
				case 0:
					((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.POISON, 180, 1, true, true));
					break;
				case 1:
					((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 180, 2, true, true));
					break;
				case 2:
					((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 180, 1, true, true));
					break;
				case 3:
					((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 180, 10, true, true));
					break;
				case 4:
					((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.WITHER, 180, 1, true, true));
					break;
				case 5:
				default:
					((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 180, 1, true, true));
					break;
			}
		}
	}

	@Override
	public void setAttackTarget(@Nullable EntityLivingBase target) {
		if (target instanceof BossEntity)
			return;

		super.setAttackTarget(target);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void checkMusicStatus() {
		SoundHandler soundHandler = Minecraft.getMinecraft().getSoundHandler();

		if (!this.isDead && getHealth() > 0) {
			if (BossMusicSound.isAvailable()) {
				if (bossMusic == null)
					bossMusic = new BossMusicSound(SoundsRegister.musicCrystocore, this);

				soundHandler.stopSounds();
				soundHandler.playSound(bossMusic);
			}
		}
		else {
			soundHandler.stopSound(bossMusic);
		}
	}

	@Override
	public ResourceLocation getBossBarTexture() {
		return bossBarTexture;
	}

	@Nonnull
	@Override
	public TreeSet<Enums.MobProperties> getMobProperties() {
		return mobProperties;
	}
}
