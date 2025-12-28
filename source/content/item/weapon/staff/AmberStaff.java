package net.tslat.aoa3.content.item.weapon.staff;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.object.builder.EffectBuilder;
import net.tslat.tme.api.particle.ParticleBuilder;
import net.tslat.tme.internal.particle.transition.FollowEntityParticleTransition;

import java.util.List;

public class AmberStaff extends AoAStaff<Object> {
	public AmberStaff(Item.Properties properties) {
		super(properties);
	}

	@Override
	public void cast(ServerLevel level, LivingEntity caster, ItemStack staff, InteractionHand hand, Object args) {
		EntityUtil.applyPotions(caster, caster, new EffectBuilder(MobEffects.ABSORPTION, 600).level(2));
	}

	@Override
	public void doCastFx(ServerLevel level, ItemStack staff, LivingEntity caster, Object args) {
		super.doCastFx(level, staff, caster, args);

		final int rings = Mth.ceil(caster.getBbHeight() * 4);

		for (int i = 0; i < rings; i++) {
			final int step = i;

			AoAScheduler.schedule(i + 1, tick -> {
				ParticleBuilder.forPositionsInCircle(ParticleTypes.EXPLOSION, caster.position().add(0, step * 0.25f, 0), Mth.sin(step / (float)rings + 0.5f) * 0.25f, 32)
						.scaleMod(0.15f)
						.lifespan((rings + 3) - step)
						.colourTint(0xF79400)
						.addTransition(FollowEntityParticleTransition.create(caster.getId()))
						.sendToAllPlayersTrackingEntity(caster);
				ParticleBuilder.forPositionsInCircle(ParticleTypes.SPORE_BLOSSOM_AIR, caster.position().add(0, step * 0.25f, 0), Mth.sin(step / (float)rings + 0.5f) * 0.25f, 32)
						.scaleMod(0.25f)
						.lifespan(caster.getRandom().nextInt(10, 30))
						.colourTint(0xFA9400)
						.sendToAllPlayersTrackingEntity(caster);
			});
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, context, tooltip, flag);
	}
}
