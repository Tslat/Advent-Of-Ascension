package net.tslat.aoa3.content.item.weapon.bow;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.tslat.aoa3.content.entity.projectile.arrow.CustomArrowEntity;
import net.tslat.aoa3.util.LocaleUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PrimordialBow extends BaseBow {
	public PrimordialBow(double damage, float drawSpeedMultiplier, int durability) {
		super(damage, drawSpeedMultiplier, durability);
	}

	@Override
	public void onBlockHit(CustomArrowEntity arrow, BlockHitResult rayTrace, Entity shooter) {
		AreaEffectCloud cloud = new AreaEffectCloud(arrow.level(), arrow.getX(), arrow.getY(), arrow.getZ());

		cloud.addEffect(new MobEffectInstance(MobEffects.WITHER, 40, 0, false, true));
		cloud.setParticle(ParticleTypes.SMOKE);
		cloud.setRadius(2);
		cloud.setDuration(200);

		arrow.level().addFreshEntity(cloud);
	}

	@Override
	public void onEntityHit(CustomArrowEntity arrow, Entity target, Entity shooter, double damage, float drawStrength) {
		AreaEffectCloud cloud = new AreaEffectCloud(arrow.level(), arrow.getX(), arrow.getY(), arrow.getZ());

		cloud.addEffect(new MobEffectInstance(MobEffects.WITHER, 40, 0, false, true));
		cloud.setParticle(ParticleTypes.SMOKE);
		cloud.setRadius(2);
		cloud.setDuration(200);

		arrow.level().addFreshEntity(cloud);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
