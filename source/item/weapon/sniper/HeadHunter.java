package net.tslat.aoa3.item.weapon.sniper;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.tslat.aoa3.capabilities.handlers.AdventGunCapability;
import net.tslat.aoa3.capabilities.providers.AdventGunProvider;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.projectiles.gun.BaseBullet;
import net.tslat.aoa3.event.GlobalEvents;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.player.PlayerUtil;

import javax.annotation.Nullable;
import java.util.List;

public class HeadHunter extends BaseSniper implements AdventWeapon {
	public HeadHunter(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(dmg, durability, firingDelayTicks, recoil);
		setTranslationKey("HeadHunter");
		setRegistryName("aoa3:head_hunter");
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return SoundsRegister.gunSniper;
	}

	@Override
	public Enums.ScopeScreens getScreen() {
		return Enums.ScopeScreens.DOTTED;
	}

	@Override
	protected void doImpactEffect(Entity target, EntityLivingBase shooter, BaseBullet bullet, float bulletDmgMultiplier) {
		if (target instanceof EntityLivingBase) {
			Vec3d preciseImpactSpot = EntityUtil.preciseEntityInterceptCalculation(target, bullet, 20);

			if (preciseImpactSpot != null) {
				double headMinRange = (target.getEntityBoundingBox().minY + target.getEyeHeight()) - target.height * 0.105f;
				double headMaxRange = headMinRange + target.height * 0.225f;

				if (preciseImpactSpot.y > headMinRange && preciseImpactSpot.y < headMaxRange) {
					for (int i = 0; i < 5; i++) {
						((WorldServer)target.world).spawnParticle(EnumParticleTypes.DAMAGE_INDICATOR, true, preciseImpactSpot.x + itemRand.nextDouble() - 0.5, preciseImpactSpot.y + itemRand.nextDouble() - 0.5, preciseImpactSpot.z + itemRand.nextDouble() - 0.5, 3, 0, 0, 0, (double)0);
					}

					ItemStack gunStack = shooter.getHeldItem(EnumHand.MAIN_HAND);

					if (gunStack.getItem() != this)
						gunStack = shooter.getHeldItem(EnumHand.OFF_HAND);

					if (gunStack.getItem() != this)
						return;

					AdventGunCapability cap = (AdventGunCapability)gunStack.getCapability(AdventGunProvider.ADVENT_GUN, null);

					if (cap != null) {
						cap.setNextShotDelay(((cap.getNextFireTime() - GlobalEvents.tick) / 2));
						PlayerUtil.playSoundForPlayer((EntityPlayerMP)shooter, SoundsRegister.foragingLoot, SoundCategory.PLAYERS, shooter.posX, shooter.posY, shooter.posZ, 0.3f, 1.0f);
					}
				}
			}
		}
	}

	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.HeadHunter.desc.1", Enums.ItemDescriptionType.POSITIVE));
		super.addInformation(stack, world, tooltip, flag);
	}
}
