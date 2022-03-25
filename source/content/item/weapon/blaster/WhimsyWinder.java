package net.tslat.aoa3.content.item.weapon.blaster;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.projectile.blaster.WinderShotEntity;
import net.tslat.aoa3.content.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.List;

public class WhimsyWinder extends BaseBlaster {
	public WhimsyWinder(double dmg, int durability, int fireDelayTicks, float energyCost) {
		super(dmg, durability, fireDelayTicks, energyCost);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_WHIMSY_WINDER_FIRE.get();
	}

	@Override
	public void fire(ItemStack blaster, LivingEntity shooter) {
		shooter.level.addFreshEntity(new WinderShotEntity(shooter, this, 60));
	}

	@Override
	public boolean doEntityImpact(BaseEnergyShot shot, Entity target, LivingEntity shooter) {
		List<Entity> nearbyTargets = shot.level.getEntities(target, target.getBoundingBox().inflate(3, 1, 3));

		nearbyTargets.removeIf(entity -> !(entity instanceof LivingEntity) || !EntityUtil.Predicates.HOSTILE_MOB.test((LivingEntity)entity));
		nearbyTargets.add(target);

		float splitDmg = (float)(getDamage() / nearbyTargets.size() * (Math.pow(1.05, nearbyTargets.size())));
		boolean success = false;

		for (Entity entity : nearbyTargets) {
			success |= DamageUtil.dealBlasterDamage(shooter, entity, shot, splitDmg, false);
		}

		return success;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
