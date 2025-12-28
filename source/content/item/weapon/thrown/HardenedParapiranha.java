package net.tslat.aoa3.content.item.weapon.thrown;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.content.entity.projectile.base.WeaponProjectile;
import net.tslat.aoa3.content.entity.projectile.thrown.HardenedParapiranhaEntity;
import net.tslat.aoa3.util.LocaleUtil;

import java.util.List;

public class HardenedParapiranha extends AoAThrowableWeapon {
	public HardenedParapiranha(Item.Properties properties) {
		super(properties);
	}

	@Override
	public WeaponProjectile createProjectileEntity(Level level, WeaponFiringContext context) {
		return new HardenedParapiranhaEntity(level, context);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.WITHERS_TARGETS, LocaleUtil.ItemDescriptionType.BENEFICIAL));

		super.appendHoverText(stack, context, tooltip, flag);
	}
}
