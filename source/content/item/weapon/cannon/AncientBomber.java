package net.tslat.aoa3.content.item.weapon.cannon;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoAExplosions;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.content.entity.projectile.base.WeaponProjectile;
import net.tslat.aoa3.content.entity.projectile.cannon.CannonGrenade;
import net.tslat.aoa3.util.LocaleUtil;

import java.util.List;

public class AncientBomber extends AoACannon {
	public AncientBomber(Item.Properties properties) {
		super(properties);
	}

	@Override
	public WeaponProjectile createProjectileEntity(Level level, WeaponFiringContext context) {
		return new CannonGrenade(level, context);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		super.appendHoverText(stack, context, tooltip, flag);

		for (MutableComponent component : LocaleUtil.getExplosionInfoLocale(AoAExplosions.ancientBomber(entity -> 1), true, flag.isAdvanced(), false)) {
			tooltip.add(2, component);
		}
	}
}
