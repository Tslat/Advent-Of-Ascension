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
import net.tslat.aoa3.content.entity.projectile.blaster.AtomizerBounceEntity;
import net.tslat.aoa3.content.entity.projectile.blaster.AtomizerShotEntity;
import net.tslat.aoa3.content.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;
import net.tslat.smartbrainlib.util.RandomUtil;
import org.jetbrains.annotations.Nullable;

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
	public void fireBlaster(ServerLevel level, LivingEntity shooter, ItemStack blaster) {
		shooter.level().addFreshEntity(new AtomizerShotEntity(shooter, this, 60));
	}

	@Override
	public void doBlockImpact(BaseEnergyShot shot, Vec3 hitPos, LivingEntity shooter) {
		if (shot instanceof AtomizerShotEntity) {
			shot.level().addFreshEntity(new AtomizerBounceEntity(shooter, this, (AtomizerShotEntity)shot, RandomUtil.randomScaledGaussianValue(0.5f), 1.3, RandomUtil.randomScaledGaussianValue(0.5f)));
		}
		else {
			WorldUtil.createExplosion(shooter, shot.level(), shot, 1.5f);
		}
	}

	@Override
	public boolean doEntityImpact(BaseEnergyShot shot, Entity target, LivingEntity shooter) {
		if (super.doEntityImpact(shot, target, shooter)) {
			WorldUtil.createExplosion(shooter, shot.level(), shot, 1.5f);

			if (shot instanceof AtomizerShotEntity)
				shot.level().addFreshEntity(new AtomizerBounceEntity(shooter, this, (AtomizerShotEntity)shot, RandomUtil.randomScaledGaussianValue(0.5f), 1.3, RandomUtil.randomScaledGaussianValue(0.5f)));

			return true;
		}

		return false;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
