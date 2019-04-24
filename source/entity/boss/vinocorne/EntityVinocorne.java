package net.tslat.aoa3.entity.boss.vinocorne;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
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

import javax.annotation.Nullable;

public class EntityVinocorne extends AoAMeleeMob implements BossEntity {
	private static final ResourceLocation bossBarTexture = new ResourceLocation("aoa3", "textures/gui/bossbars/vinocorne.png");
	public static final float entityWidth = 1.2f;
	private int minionCountdown = 70;

	@SideOnly(Side.CLIENT)
	protected BossMusicSound bossMusic;

	public EntityVinocorne(World world) {
		super(world, entityWidth, 3f);
	}

	@Override
	public float getEyeHeight() {
		return 2.28125f;
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
		return 10;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobTreeSpiritLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobTreeSpiritDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobTreeSpiritHit;
	}

	@Override
	public boolean isNonBoss() {
		return false;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (isDead)
			return;

		if (!world.isRemote) {
			if (minionCountdown > 0)
				minionCountdown--;

			if (minionCountdown == 0) {
				minionCountdown = 70;

				switch (rand.nextInt(5)) {
					case 0:
						world.spawnEntity(new EntityBlueFlower(this));
						break;
					case 1:
						world.spawnEntity(new EntityYellowFlower(this));
						break;
					case 2:
						world.spawnEntity(new EntityOrangeFlower(this));
						break;
					case 3:
						world.spawnEntity(new EntityGreenFlower(this));
						break;
					case 4:
						world.spawnEntity(new EntityPurpleFlower(this));
						break;
				}
			}
		}
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		dropItem(Item.getItemFromBlock(BlockRegister.statueVinocorne), 1);

		switch (rand.nextInt(5)) {
			case 0:
				dropItem(WeaponRegister.bowRosidian, 1);
				break;
			case 1:
				dropItem(WeaponRegister.swordRosidian, 1);
				break;
			case 2:
				dropItem(WeaponRegister.greatbladeRosidian, 1);
				break;
			case 3:
				dropItem(WeaponRegister.sniperRosidRifle, 1);
				break;
			case 4:
				dropItem(WeaponRegister.staffRosidian, 1);
				break;
		}
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
				StringUtil.sendMessageWithinRadius(StringUtil.getLocaleWithArguments("message.mob.vinocorne.kill", killer.getDisplayNameString()), this, 50);
		}
	}

	@Override
	public boolean startRiding(Entity entity, boolean force) {
		return false;
	}

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
					bossMusic = new BossMusicSound(SoundsRegister.musicVinocorne, this);

				soundHandler.stopSounds();
				soundHandler.playSound(bossMusic);
			}
		}
		else {
			soundHandler.stopSound(bossMusic);
		}
	}
}
