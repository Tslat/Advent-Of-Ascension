package net.tslat.aoa3.entity.boss.bane;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
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
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.properties.BossEntity;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashSet;

public class EntityBane extends AoAMeleeMob implements BossEntity {
	private static final ResourceLocation bossBarTexture = new ResourceLocation("aoa3", "textures/gui/bossbars/bane.png");
	public static final float entityWidth = 0.75f;

	@SideOnly(Side.CLIENT)
	protected BossMusicSound bossMusic;

	private final HashSet<AoAMeleeMob> summons = new HashSet<AoAMeleeMob>();

	public EntityBane(World world) {
		super(world, entityWidth, 2f);
	}

	@Override
	public float getEyeHeight() {
		return 1.1875f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.8;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 1750;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 10;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobBaneLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobBaneDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobBaneLiving;
	}

	@Override
	public boolean isNonBoss() {
		return false;
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		dropItem(Item.getItemFromBlock(BlockRegister.statueBane), 1);

		switch(rand.nextInt(4)) {
			case 0:
				dropItem(WeaponRegister.greatbladeHaunted, 1);
				break;
			case 1:
				dropItem(WeaponRegister.staffHaunters, 1);
				break;
			case 2:
				dropItem(WeaponRegister.bowHaunted, 1);
				break;
			case 3:
				dropItem(WeaponRegister.blasterGhoulGasser,1);
				break;
		}
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (world.isRemote)
			return;

		if (rand.nextInt(400) == 0) {
			EntityPlayer closestPlayer = world.getClosestPlayer(posX, posY, posZ, 60, player -> !((EntityPlayer)player).capabilities.isCreativeMode);

			if (closestPlayer != null)
				setPositionAndUpdate(closestPlayer.posX, closestPlayer.posY, closestPlayer.posZ);
		}
		else if (rand.nextInt(200) == 0) {
			for (int i = 0; i < 6; i++) {
				EntityBaneClone clone = new EntityBaneClone(this);

				summons.add(clone);
				world.spawnEntity(clone);
			}
		}
		else if (rand.nextInt(300) == 0) {
			EntityBaneBig bigClone = new EntityBaneBig(this);

			summons.add(bigClone);
			world.spawnEntity(bigClone);
		}
	}

	@Override
	protected void doMeleeEffect(Entity target) {
		addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, 40, 2, true, true));

		if (target instanceof EntityLivingBase)
			((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 80, 1, true, false));
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
				StringUtil.sendMessageWithinRadius(StringUtil.getLocaleWithArguments("message.mob.bane.kill", killer.getDisplayNameString()), this, 50);

			for (AoAMeleeMob summon : summons) {
				if (summon != null)
					summon.setDead();
			}
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
					bossMusic = new BossMusicSound(SoundsRegister.musicBane, this);

				soundHandler.stopSounds();
				soundHandler.playSound(bossMusic);
			}
		}
		else {
			soundHandler.stopSound(bossMusic);
		}
	}
}
