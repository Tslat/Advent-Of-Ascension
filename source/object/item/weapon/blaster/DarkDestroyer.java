package net.tslat.aoa3.object.item.weapon.blaster;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.object.entity.projectile.blaster.DestroyerShotEntity;
import net.tslat.aoa3.object.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;
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
	public void fire(ItemStack blaster, LivingEntity shooter) {
		shooter.level.addFreshEntity(new DestroyerShotEntity(shooter, this, 60));
	}

	@Override
	public void doBlockImpact(BaseEnergyShot shot, Vector3d hitPos, LivingEntity shooter) {
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
					WorldUtil.createExplosion(shooter, shot.level, x, y, z, 2f);
				}
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
