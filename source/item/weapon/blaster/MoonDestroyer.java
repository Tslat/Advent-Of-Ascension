package net.tslat.aoa3.item.weapon.blaster;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.projectile.blaster.MoonDestroyerShotEntity;
import net.tslat.aoa3.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;
import java.util.List;

public class MoonDestroyer extends BaseBlaster {
	public MoonDestroyer(double dmg, int durability, int fireDelayTicks, float energyCost) {
		super(dmg, durability, fireDelayTicks, energyCost);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_DOOM_GUN_FIRE.get();
	}

	@Override
	public void fire(ItemStack blaster, LivingEntity shooter) {
		shooter.world.addEntity(new MoonDestroyerShotEntity(shooter, this, 60));
	}

	@Override
	public void doBlockImpact(BaseEnergyShot shot, Vec3d hitPos, LivingEntity shooter) {
		doExplosions(shot, shooter, shot.getPosX(), shot.getPosY(), shot.getPosZ());
	}

	@Override
	protected void doImpactEffect(BaseEnergyShot shot, Entity target, LivingEntity shooter) {
		doExplosions(shot, shooter, shot.getPosX(), shot.getPosY(), shot.getPosZ());
	}

	private void doExplosions(BaseEnergyShot shot, LivingEntity shooter, double posX, double posY, double posZ) {
		for (double x = posX - 5; x <= posX + 5; x += 2.5) {
			for (double y = posY - 5; y <= posY + 5; y += 2.5) {
				for (double z = posZ - 5; z <= posZ + 5; z += 2.5) {
					WorldUtil.createExplosion(shooter, shot.world, x, y, z, 2.5f);
				}
			}
		}
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.addInformation(stack, world, tooltip, flag);
	}
}
