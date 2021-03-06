package net.tslat.aoa3.item.weapon.blaster;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.projectile.blaster.HeavyShowerShotEntity;
import net.tslat.aoa3.entity.projectile.blaster.ShowerShotEntity;
import net.tslat.aoa3.entity.projectile.blaster.WeightedShowerShotEntity;
import net.tslat.aoa3.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;
import java.util.List;

public class SpiritShower extends BaseBlaster {
	public SpiritShower(double dmg, int durability, int fireDelayTicks, float energyCost) {
		super(dmg, durability, fireDelayTicks, energyCost);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_SPIRIT_SHOWER_FIRE.get();
	}

	@Override
	public void fire(ItemStack blaster, LivingEntity shooter) {
		shooter.level.addFreshEntity(new ShowerShotEntity(shooter, this, 60));
		shooter.level.addFreshEntity(new WeightedShowerShotEntity(shooter, this, 60));
		shooter.level.addFreshEntity(new HeavyShowerShotEntity(shooter, this, 60));
	}

	@Override
	public void doBlockImpact(BaseEnergyShot shot, Vector3d hitPos, LivingEntity shooter) {
		WorldUtil.createExplosion(shooter, shot.level, shot, 2.5f);
	}

	@Override
	protected void doImpactEffect(BaseEnergyShot shot, Entity target, LivingEntity shooter) {
		WorldUtil.createExplosion(shooter, shot.level, shot, 2.5f);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
