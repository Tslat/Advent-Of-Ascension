package net.tslat.aoa3.content.item.weapon.bow;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.phys.EntityHitResult;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.object.builder.EffectBuilder;
import net.tslat.tme.api.particle.ParticleBuilder;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DeepBow extends AoABow {
	public DeepBow(Item.Properties properties) {
		super(properties);
	}

	@Override
	public void tickArrow(Projectile projectile, @Nullable Entity shooter, ItemStack stack) {
		if (!projectile.level().isClientSide)
			ParticleBuilder.forPositions(ParticleTypes.FIREWORK, projectile.position().add(0, 0.1f, 0)).sendToAllPlayersTrackingEntity(projectile);
	}

	@Override
	public void onEntityImpact(Projectile projectile, @Nullable Entity shooter, EntityHitResult hitResult, ItemStack stack, float velocity) {
		if (hitResult.getEntity() instanceof LivingEntity target)
			EntityUtil.applyPotions(target, shooter, new EffectBuilder(MobEffects.GLOWING, 200));
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, context, tooltip, flag);
	}
}
