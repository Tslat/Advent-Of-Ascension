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
import net.tslat.aoa3.content.entity.projectile.cannon.TriDischargeShotEntity;
import net.tslat.aoa3.util.LocaleUtil;

import java.util.List;

public class AncientDischarger extends AoACannon {
	public AncientDischarger(Item.Properties properties) {
		super(properties);
	}

	@Override
	public WeaponProjectile createProjectileEntity(Level level, WeaponFiringContext context) {
		return new TriDischargeShotEntity(level, context);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		super.appendHoverText(stack, context, tooltip, flag);

		for (MutableComponent component : LocaleUtil.getExplosionInfoLocale(AoAExplosions.TRI_DISCHARGE_SHOT, true, flag.isAdvanced(), false)) {
			tooltip.add(1, component);
		}
	}
}
