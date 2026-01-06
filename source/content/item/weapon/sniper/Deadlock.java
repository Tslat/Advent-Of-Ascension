package net.tslat.aoa3.content.item.weapon.sniper;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.tslat.aoa3.content.entity.projectile.base.WeaponProjectile;
import net.tslat.aoa3.library.object.extension.MutableFloat;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.object.RayTrace;

import java.util.List;

public class Deadlock extends AoASniper {
	public Deadlock(Item.Properties properties) {
		super(properties);
	}

	@Override
	protected void modifyImpactDamage(ServerLevel level, WeaponProjectile projectile, RayTrace<?> rayTrace, Entity hitEntity, DamageSource source, MutableFloat damage) {
		super.modifyImpactDamage(level, projectile, rayTrace, hitEntity, source, damage);

		damage.multiply(1 + (float)Math.min(0.5f, Math.abs(hitEntity.getDeltaMovement().x() * hitEntity.getDeltaMovement().z() * 50f)));
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, context, tooltip, flag);
	}
}
