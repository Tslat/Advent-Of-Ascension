package net.tslat.aoa3.content.item.weapon.bow;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.content.entity.projectile.arrow.CustomArrowEntity;
import net.tslat.aoa3.library.builder.EffectBuilder;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.List;

public class SunshineBow extends BaseBow {
	public SunshineBow(double damage, float drawSpeedMultiplier, int durability) {
		super(damage, drawSpeedMultiplier, durability);
	}

	@Override
	public void onEntityHit(CustomArrowEntity arrow, Entity target, Entity shooter, double damage, float drawStrength) {
		if (arrow.isCritArrow()) {
			AreaEffectCloudEntity cloud = new AreaEffectCloudEntity(target.level, arrow.getX(), arrow.getY(), arrow.getZ());

			cloud.addEffect(new EffectInstance(Effects.GLOWING, 200, 0, true, false));
			cloud.setRadius(0.5f);
			cloud.setRadiusPerTick(30);
			cloud.setDuration(2);
			cloud.setWaitTime(0);
			cloud.setFixedColor(ColourUtil.WHITE);
			cloud.setParticle(ParticleTypes.ENTITY_EFFECT);

			if (shooter instanceof LivingEntity)
				cloud.setOwner((LivingEntity)shooter);

			target.level.addFreshEntity(cloud);

			for (LivingEntity entity : arrow.level.getEntitiesOfClass(LivingEntity.class, arrow.getBoundingBox().inflate(30, 1, 30), EntityUtil.Predicates.HOSTILE_MOB)) {
				EntityUtil.applyPotions(entity, new EffectBuilder(Effects.GLOWING, 200));
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}