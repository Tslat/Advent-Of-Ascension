package net.tslat.aoa3.library.scheduling.sync;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.tslat.aoa3.event.GlobalEvents;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.ModUtil;

public class UltimatumStaffTask implements Runnable {
	private final int startingTick;
	private final EntityLivingBase shooter;
	private final EntityLivingBase target;
	private final BlockPos shooterPos;
	private final BlockPos targetPos;
	private final float shooterStartHealth;
	private final float targetStartHealth;

	private boolean targetTurn = true;

	public UltimatumStaffTask(EntityLivingBase shooter, EntityLivingBase target) {
		this.startingTick = GlobalEvents.tick;
		this.shooter = shooter;
		this.target = target;
		this.shooterPos = shooter.getPosition();
		this.targetPos = target.getPosition();
		this.shooterStartHealth = shooter.getHealth();
		this.targetStartHealth = target.getHealth();

		target.motionX = 0;
		target.motionY = 0;
		target.motionZ = 0;
		shooter.motionX = 0;
		shooter.motionY = 0;
		shooter.motionZ = 0;

		EntityUtil.safelyRemovePotionEffects(target, MobEffects.REGENERATION);
		target.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 210, 100, true, false));
		target.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 210, 50, true, false));
		target.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 210, 5, true, false));
		target.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 210, 0, true, false));
		target.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 510, 0, true, false));
		target.addPotionEffect(new PotionEffect(MobEffects.LEVITATION, 210, -1, true, false));
		EntityUtil.safelyRemovePotionEffects(target, MobEffects.REGENERATION);
		shooter.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 210, 100, true, false));
		shooter.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 210, 50, true, false));
		shooter.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 210, 5, true, false));
		shooter.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 510, 5, true, false));
		shooter.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 210, 0, true, false));
		shooter.addPotionEffect(new PotionEffect(MobEffects.LEVITATION, 210, -1, true, false));
	}

	@Override
	public void run() {
		if (shooter == null || target == null || shooter.world.isRemote || !shooter.getPosition().equals(shooterPos) || !target.getPosition().equals(targetPos)) {
			resetStates();

			return;
		}

		float healthPercent = 1 - (GlobalEvents.tick - startingTick) / 200f;
		float targetPostHealth = targetStartHealth * healthPercent;
		float shooterPostHealth = shooterStartHealth * healthPercent;

		if (targetTurn) {
			if (target.getHealth() == 0) {
				resetStates();

				return;
			}

			if (targetPostHealth <= 0) {
				resetStates();
				EntityUtil.dealMagicDamage(null, shooter, target, target.getHealth() - targetPostHealth, true);

				target.motionX = 0;
				target.motionY = 0;
				target.motionZ = 0;
			}
			else {
				target.setHealth(targetPostHealth);
			}

			((WorldServer)shooter.world).spawnParticle(EnumParticleTypes.DAMAGE_INDICATOR, target.posX, target.posY + target.height, target.posZ, (int)Math.ceil(10 * healthPercent), 0, 0, 0, (double)0);
		}
		else {
			if (!(shooter instanceof EntityPlayer) || !((EntityPlayer)shooter).capabilities.isCreativeMode) {
				if (shooterPostHealth <= 0) {
					resetStates();
					EntityUtil.dealSelfHarmDamage(shooter, shooter.getHealth() - shooterPostHealth);
				}
				else {
					shooter.setHealth(shooterPostHealth);
				}

				((WorldServer)shooter.world).spawnParticle(EnumParticleTypes.DAMAGE_INDICATOR, shooter.posX, shooter.posY + shooter.height, shooter.posZ, (int)Math.ceil(10 * healthPercent), 0, 0, 0, (double)0);
			}
		}

		targetTurn = !targetTurn;

		ModUtil.scheduleSyncronisedTask(this, 1);
	}

	private void resetStates() {
		if (target.getHealth() > 0)
			EntityUtil.safelyRemovePotionEffects(target, MobEffects.BLINDNESS, MobEffects.RESISTANCE, MobEffects.WEAKNESS, MobEffects.SLOWNESS, MobEffects.LEVITATION, MobEffects.NIGHT_VISION);

		if (shooter.getHealth() > 0)
			EntityUtil.safelyRemovePotionEffects(shooter, MobEffects.BLINDNESS, MobEffects.RESISTANCE, MobEffects.WEAKNESS, MobEffects.SLOWNESS, MobEffects.LEVITATION, MobEffects.NIGHT_VISION);
	}
}
