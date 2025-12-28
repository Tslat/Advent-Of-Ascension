package net.tslat.aoa3.content.item.weapon.gun;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.content.entity.projectile.base.WeaponProjectile;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.object.RayTrace;

import java.util.List;

public class IonRevolver extends AoAGun {
	public IonRevolver(Item.Properties properties) {
		super(properties);
	}

	@Override
	protected void onDamageEntity(Level level, WeaponProjectile projectile, RayTrace<?> rayTrace, Entity hitEntity, float damage) {
		if (hitEntity instanceof LivingEntity target && projectile.getShooter() instanceof LivingEntity shooter)
			DamageUtil.doScaledKnockback(target, shooter, 0.9f, 1, 1, 1);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.KNOCKBACK, LocaleUtil.ItemDescriptionType.BENEFICIAL));
		super.appendHoverText(stack, context, tooltip, flag);
	}
}
