package net.tslat.aoa3.item.weapon.bow;

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
import net.tslat.aoa3.entity.projectile.arrow.CustomArrowEntity;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.aoa3.util.PotionUtil;

import javax.annotation.Nullable;
import java.util.List;

public class SunshineBow extends BaseBow {
	public SunshineBow(double damage, float drawSpeedMultiplier, int durability) {
		super(damage, drawSpeedMultiplier, durability);
	}

	@Override
	public void onEntityHit(CustomArrowEntity arrow, Entity target, Entity shooter, double damage, float drawStrength) {
		if (arrow.getIsCritical()) {
			AreaEffectCloudEntity cloud = new AreaEffectCloudEntity(target.world, arrow.getPosX(), arrow.getPosY(), arrow.getPosZ());

			cloud.addEffect(new EffectInstance(Effects.GLOWING, 200, 0, true, false));
			cloud.setRadius(0.5f);
			cloud.setRadiusPerTick(30);
			cloud.setDuration(2);
			cloud.setWaitTime(0);
			cloud.setColor(NumberUtil.RGB(255, 255, 255));
			cloud.setParticleData(ParticleTypes.ENTITY_EFFECT);

			if (shooter instanceof LivingEntity)
				cloud.setOwner((LivingEntity)shooter);

			target.world.addEntity(cloud);

			for (LivingEntity entity : arrow.world.getEntitiesWithinAABB(LivingEntity.class, arrow.getBoundingBox().grow(30, 1, 30), EntityUtil.Predicates.HOSTILE_MOB)) {
				EntityUtil.applyPotions(entity, new PotionUtil.EffectBuilder(Effects.GLOWING, 200));
			}
		}
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.addInformation(stack, world, tooltip, flag);
	}
}