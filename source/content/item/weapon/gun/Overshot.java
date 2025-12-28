package net.tslat.aoa3.content.item.weapon.gun;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.content.entity.projectile.base.WeaponProjectile;
import net.tslat.aoa3.util.LocaleUtil;

import java.util.List;

public class Overshot extends AoAGun {
	public Overshot(Item.Properties properties) {
		super(properties);
	}

	@Override
	protected void onGunFire(ServerLevel level, WeaponFiringContext context, WeaponProjectile projectile) {
		WeaponProjectile projectile2 = findAndConsumeAmmo(level, context);

		if (projectile2 == null || !(projectile2.asEntity() instanceof Entity bullet))
			return;

		projectile2.fromArmPosWithOffset(0, 0.1f).shootingAtTarget(context.velocity(), context.inaccuracy());
		level.addFreshEntity(bullet);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, context, tooltip, flag);
	}
}
