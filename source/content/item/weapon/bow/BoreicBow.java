package net.tslat.aoa3.content.item.weapon.bow;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.tslat.aoa3.common.registration.AoAExplosions;
import net.tslat.aoa3.library.builder.AoAExplosionBuilder;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.explosion.StandardExplosion;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BoreicBow extends AoABow {
	public BoreicBow(Item.Properties properties) {
		super(properties);
	}

	@Override
	public void tickArrow(Projectile projectile, @Nullable Entity shooter, ItemStack stack) {
		if (projectile.isInWater() && !projectile.level().isClientSide) {
			AoAExplosionBuilder.at(projectile, AoAExplosions.boreicBow(projectile instanceof AbstractArrow arrow && arrow.isCritArrow()), StandardExplosion::new).explode();
			projectile.discard();
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, context, tooltip, flag);

		for (MutableComponent component : LocaleUtil.getExplosionInfoLocale(AoAExplosions.boreicBow(true), true, flag.isAdvanced(), false)) {
			tooltip.add(2, component);
		}
	}
}
