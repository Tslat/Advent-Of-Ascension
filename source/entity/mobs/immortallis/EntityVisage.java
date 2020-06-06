package net.tslat.aoa3.entity.mobs.immortallis;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.utils.ConfigurationUtil;
import net.tslat.aoa3.utils.player.PlayerDataManager;
import net.tslat.aoa3.utils.player.PlayerUtil;

import javax.annotation.Nullable;

import static net.tslat.aoa3.library.Enums.Deities.EREBON;

public class EntityVisage extends AoAMeleeMob {
	public static final float entityWidth = 0.7f;

	private EntityVisage mirageHost = null;

	public EntityVisage(World world) {
		super(world, entityWidth, 1.5f);
	}

	public EntityVisage(World world, EntityVisage mirageHost) {
		super(world, entityWidth, 1.5f);

		this.mirageHost = mirageHost;
	}

	@Override
	public float getEyeHeight() {
		return 1.46875f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0d;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 60d;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 11d;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.MOB_PENUMBRA_LIVING;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.MOB_PENUMBRA_DEATH;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.MOB_PENUMBRA_HIT;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return null;
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEAD;
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (mirageHost != null) {
			setDead();

			return false;
		}
		else {
			return super.attackEntityFrom(source, amount);
		}
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (world.isRemote) {
			if (mirageHost != null) {
				if (ticksExisted >= 600 || mirageHost.isDead)
					setDead();
			}
			else if (rand.nextInt(200) == 0) {
				EntityVisage visage = new EntityVisage(world, this);
				double xPos = posX + (int)(rand.nextFloat() * 10 - 5);
				double zPos = posZ + (int)(rand.nextFloat() * 10 - 5);
				double yPos = world.getHeight((int)xPos, (int)zPos);

				visage.setPosition(xPos, yPos, zPos);
				world.spawnEntity(visage);
			}
		}
	}

	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);

		if (!world.isRemote && world.provider.getDimension() == ConfigurationUtil.MainConfig.dimensionIds.immortallis) {
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

				if (pl != null) {
					PlayerDataManager plData = PlayerUtil.getAdventPlayer(pl);

					plData.stats().addTribute(EREBON, 4);

					if (plData.stats().getTribute(EREBON) == 200)
						plData.sendThrottledChatMessage("message.feedback.immortallisProgression.evilSpiritsEnd");
				}
			}
		}
	}
}
