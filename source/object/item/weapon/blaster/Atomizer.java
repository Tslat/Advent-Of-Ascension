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
import net.tslat.aoa3.object.entity.projectile.blaster.AtomizerBounceEntity;
import net.tslat.aoa3.object.entity.projectile.blaster.AtomizerShotEntity;
import net.tslat.aoa3.object.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;
import java.util.List;

public class Atomizer extends BaseBlaster {
	public Atomizer(double dmg, int durability, int fireDelayTicks, float energyCost) {
		super(dmg, durability, fireDelayTicks, energyCost);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_ATOMIZER_FIRE.get();
	}

	@Override
	public void fire(ItemStack blaster, LivingEntity shooter) {
		shooter.level.addFreshEntity(new AtomizerShotEntity(shooter, this, 60));
	}

	@Override
	public void doBlockImpact(BaseEnergyShot shot, Vector3d hitPos, LivingEntity shooter) {
		if (shot instanceof AtomizerShotEntity) {
			shot.level.addFreshEntity(new AtomizerBounceEntity(shooter, this, (AtomizerShotEntity)shot, random.nextGaussian() * 0.5, 1.3, random.nextGaussian() * 0.5));
		}
		else {
			WorldUtil.createExplosion(shooter, shot.level, shot, 1.5f);
		}
	}

	@Override
	public boolean doEntityImpact(BaseEnergyShot shot, Entity target, LivingEntity shooter) {
		if (super.doEntityImpact(shot, target, shooter)) {
			WorldUtil.createExplosion(shooter, shot.level, shot, 1.5f);

			if (shot instanceof AtomizerShotEntity)
				shot.level.addFreshEntity(new AtomizerBounceEntity(shooter, this, (AtomizerShotEntity)shot, random.nextGaussian() * 0.5, 1.3, random.nextGaussian() * 0.5));

			return true;
		}

		return false;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
