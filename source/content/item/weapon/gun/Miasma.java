package net.tslat.aoa3.content.item.weapon.gun;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.content.entity.projectile.base.WeaponProjectile;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.object.builder.EffectBuilder;
import net.tslat.tme.api.object.RayTrace;

import java.util.List;

public class Miasma extends AoAGun {
	public Miasma(Item.Properties properties) {
		super(properties);
	}

	@Override
	protected void onDamageEntity(Level level, WeaponProjectile projectile, RayTrace<?> rayTrace, Entity hitEntity, float damage) {
		EntityUtil.applyPotions(hitEntity, projectile.getShooter(), new EffectBuilder(MobEffects.MOVEMENT_SLOWDOWN, 40));
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.SLOWS_TARGETS, LocaleUtil.ItemDescriptionType.BENEFICIAL));
		super.appendHoverText(stack, context, tooltip, flag);
	}
}
