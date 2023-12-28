package net.tslat.aoa3.content.item.weapon.bow;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.content.entity.projectile.arrow.CustomArrowEntity;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.effectslib.api.util.EffectBuilder;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SunshineBow extends BaseBow {
	public SunshineBow(double damage, float drawSpeedMultiplier, int durability) {
		super(damage, drawSpeedMultiplier, durability);
	}

	@Override
	public void onEntityHit(CustomArrowEntity arrow, Entity target, Entity shooter, double damage, float drawStrength) {
		if (arrow.isCritArrow()) {
			AreaEffectCloud cloud = new AreaEffectCloud(target.level(), arrow.getX(), arrow.getY(), arrow.getZ());

			cloud.addEffect(new MobEffectInstance(MobEffects.GLOWING, 200, 0, true, false));
			cloud.setRadius(0.5f);
			cloud.setRadiusPerTick(30);
			cloud.setDuration(2);
			cloud.setWaitTime(0);
			cloud.setFixedColor(ColourUtil.WHITE);
			cloud.setParticle(ParticleTypes.ENTITY_EFFECT);

			if (shooter instanceof LivingEntity)
				cloud.setOwner((LivingEntity)shooter);

			target.level().addFreshEntity(cloud);

			for (LivingEntity entity : arrow.level().getEntitiesOfClass(LivingEntity.class, arrow.getBoundingBox().inflate(30, 1, 30), EntityUtil.Predicates.HOSTILE_MOB)) {
				EntityUtil.applyPotions(entity, new EffectBuilder(MobEffects.GLOWING, 200));
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}