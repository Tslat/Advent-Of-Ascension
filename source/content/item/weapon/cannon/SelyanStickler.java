package net.tslat.aoa3.content.item.weapon.cannon;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoAExplosions;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.content.entity.projectile.base.WeaponProjectile;
import net.tslat.aoa3.content.entity.projectile.cannon.SelyanSticklerShotEntity;
import net.tslat.aoa3.content.entity.projectile.misc.SelyanSticklerStuckEntity;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.object.RayTrace;

import java.util.List;

public class SelyanStickler extends AoACannon {
	public SelyanStickler(Item.Properties properties) {
		super(properties);
	}

	@Override
	public WeaponProjectile createProjectileEntity(Level level, WeaponFiringContext context) {
		return new SelyanSticklerShotEntity(level, context);
	}

	@Override
	protected void onDamageEntity(Level level, WeaponProjectile projectile, RayTrace<?> rayTrace, Entity hitEntity, float damage) {
		if (hitEntity instanceof LivingEntity target && target.isAlive())
			level.addFreshEntity(new SelyanSticklerStuckEntity(level, projectile.getShooter(), target, target.position().vectorTo(rayTrace.hitPos()), projectile.getShotContext()));
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		super.appendHoverText(stack, context, tooltip, flag);
		tooltip.add(2, LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		tooltip.add(2, LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.STICKLER_DESCRIPTION_1, LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(2, LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.STICKLER_DESCRIPTION_2, LocaleUtil.ItemDescriptionType.BENEFICIAL));

		for (MutableComponent component : LocaleUtil.getExplosionInfoLocale(AoAExplosions.STICKLER, true, flag.isAdvanced(), false)) {
			tooltip.add(2, component);
		}
	}
}
