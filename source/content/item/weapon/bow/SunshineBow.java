package net.tslat.aoa3.content.item.weapon.bow;

import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.phys.EntityHitResult;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.object.builder.EffectBuilder;
import net.tslat.tme.api.util.EntityRetrievalUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class SunshineBow extends AoABow {
	public SunshineBow(Item.Properties properties) {
		super(properties);
	}

	@Override
	public void onEntityImpact(Projectile projectile, @Nullable Entity shooter, EntityHitResult hitResult, ItemStack stack, float velocity) {
		if (projectile instanceof AbstractArrow arrow && arrow.isCritArrow() && !arrow.level().isClientSide) {
			AreaEffectCloud cloud = new AreaEffectCloud(arrow.level(), arrow.getX(), arrow.getY(), arrow.getZ());

			cloud.addEffect(new MobEffectInstance(MobEffects.GLOWING, 200, 0, true, false));
			cloud.setRadius(0.5f);
			cloud.setRadiusPerTick(30);
			cloud.setDuration(2);
			cloud.setWaitTime(0);
			cloud.setPotionContents(new PotionContents(Optional.empty(), Optional.of(ColourUtil.WHITE), List.of()));
			cloud.setParticle(ColorParticleOption.create(ParticleTypes.ENTITY_EFFECT, ColourUtil.WHITE));

			if (shooter instanceof LivingEntity)
				cloud.setOwner((LivingEntity)shooter);

			arrow.level().addFreshEntity(cloud);

			EntityUtil.applyPotions(EntityRetrievalUtil.getEntities(arrow, 30, 1, 30, LivingEntity.class, target -> EntityUtil.areProbablyEnemies(target, shooter)), shooter,
									new EffectBuilder(MobEffects.GLOWING, 200));
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, context, tooltip, flag);
	}
}