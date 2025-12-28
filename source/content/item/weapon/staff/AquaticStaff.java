package net.tslat.aoa3.content.item.weapon.staff;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.content.entity.projectile.base.WeaponProjectile;
import net.tslat.aoa3.library.object.extension.MutableFloat;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.object.RayTrace;

import java.util.List;

public class AquaticStaff extends AoAStaff<Object> {
	public AquaticStaff(Item.Properties properties) {
		super(properties);
	}

	@Override
	protected void modifyImpactDamage(ServerLevel level, WeaponProjectile projectile, WeaponFiringContext context, RayTrace<?> rayTrace, Entity hitEntity, DamageSource source, MutableFloat damage) {
		super.modifyImpactDamage(level, projectile, context, rayTrace, hitEntity, source, damage);

		if (context.getShooter() != null && context.getShooter().isInWater())
			damage.multiply(1.4f);
	}

	@Override
	public void cast(ServerLevel level, LivingEntity caster, ItemStack staff, InteractionHand hand, Object args) {
		fireProjectile(level, caster, staff, hand, AoAProjectiles.AQUATIC_SHOT);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 2));
		super.appendHoverText(stack, context, tooltip, flag);
	}
}
