package net.tslat.aoa3.content.item.weapon.staff;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.content.entity.projectile.base.WeaponProjectile;
import net.tslat.aoa3.util.AttributeUtil;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.object.RayTrace;
import net.tslat.tme.api.particle.ParticleBuilder;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MechaStaff extends AoAStaff<Object> {
	private static final AttributeModifier DEBUFF = new AttributeModifier(AdventOfAscension.id("mecha_staff_debuff"), -0.5, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);

	public MechaStaff(Item.Properties properties) {
		super(properties);
	}

	@Override
	public void cast(ServerLevel level, LivingEntity caster, ItemStack staff, InteractionHand hand, Object args) {
		fireProjectile(level, caster, staff, hand, AoAProjectiles.LYONIC_SHOT);
	}

	@Override
	protected void onDamageEntity(ServerLevel level, @Nullable WeaponProjectile projectile, @Nullable WeaponFiringContext context, @Nullable RayTrace<?> rayTrace, Entity hitEntity, float damage) {
		if (level instanceof ServerLevel serverLevel && hitEntity instanceof LivingEntity target && EntityUtil.areProbablyEnemies(hitEntity, projectile.getShooter())) {
			if (AttributeUtil.getAttribute(target, Attributes.ARMOR).filter(instance -> instance.getValue() > 0 && !instance.hasModifier(DEBUFF.id())).isPresent()) {
				AttributeUtil.applyTransientModifier(target, Attributes.ARMOR, DEBUFF);

				ParticleBuilder.forRandomPosInCircleRadius(ParticleTypes.TOTEM_OF_UNDYING, target.position().add(0, target.getBbHeight() + 0.1f, 0), target.getBbWidth())
						.spawnNTimes(8)
						.sendToAllPlayersTrackingEntity(target);
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, context, tooltip, flag);
	}
}
