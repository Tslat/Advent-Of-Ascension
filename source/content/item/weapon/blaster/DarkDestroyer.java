package net.tslat.aoa3.content.item.weapon.blaster;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.projectile.blaster.DestroyerShotEntity;
import net.tslat.aoa3.content.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DarkDestroyer extends BaseBlaster {
	public DarkDestroyer(double dmg, int durability, int fireDelayTicks, float energyCost) {
		super(dmg, durability, fireDelayTicks, energyCost);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_DOOM_GUN_FIRE.get();
	}

	@Override
	public void fireBlaster(ServerLevel level, LivingEntity shooter, ItemStack blaster) {
		shooter.level().addFreshEntity(new DestroyerShotEntity(shooter, this, 60));
	}

	@Override
	public void doBlockImpact(BaseEnergyShot shot, Vec3 hitPos, LivingEntity shooter) {
		doExplosions(shot, shooter, shot.getX(), shot.getY(), shot.getZ());
	}

	@Override
	protected void doImpactEffect(BaseEnergyShot shot, Entity target, LivingEntity shooter) {
		doExplosions(shot, shooter, shot.getX(), shot.getY(), shot.getZ());
	}

	private void doExplosions(BaseEnergyShot shot, LivingEntity shooter, double posX, double posY, double posZ) {
		for (double x = posX - 5; x <= posX + 5; x += 2.5) {
			for (double y = posY - 5; y <= posY + 5; y += 2.5) {
				for (double z = posZ - 5; z <= posZ + 5; z += 2.5) {
					WorldUtil.createExplosion(shooter, shot.level(), x, y, z, 2f);
				}
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
