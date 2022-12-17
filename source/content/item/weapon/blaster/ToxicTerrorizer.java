package net.tslat.aoa3.content.item.weapon.blaster;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.projectile.blaster.ToxicShotEntity;
import net.tslat.aoa3.content.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.effectslib.api.util.EffectBuilder;

import javax.annotation.Nullable;
import java.util.List;

public class ToxicTerrorizer extends BaseBlaster {
	public ToxicTerrorizer(double dmg, int durability, int fireDelayTicks, float energyCost) {
		super(dmg, durability, fireDelayTicks, energyCost);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_MAGIC_GUN_FIRE.get();
	}

	@Override
	public void fire(ItemStack blaster, LivingEntity shooter) {
		shooter.level.addFreshEntity(new ToxicShotEntity(shooter, this, 60));
		shooter.level.addFreshEntity(new ToxicShotEntity(shooter, this, 60, -0.05f, -0.05f, 0f));
		shooter.level.addFreshEntity(new ToxicShotEntity(shooter, this, 60, 0.05f, -0.05f, 0f));
		shooter.level.addFreshEntity(new ToxicShotEntity(shooter, this, 60, 0, -0.05f, -0.05f));
		shooter.level.addFreshEntity(new ToxicShotEntity(shooter, this, 60, 0, -0.05f, 0.05f));
	}

	@Override
	public boolean doEntityImpact(BaseEnergyShot shot, Entity target, LivingEntity shooter) {
		if (target instanceof LivingEntity)
			EntityUtil.applyPotions(target, new EffectBuilder(MobEffects.POISON, 185).level(2));

		return false;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Constants.POISONS_TARGETS, LocaleUtil.ItemDescriptionType.BENEFICIAL));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
