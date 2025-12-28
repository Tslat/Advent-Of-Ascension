package net.tslat.aoa3.content.item.weapon.bow;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.tslat.aoa3.common.registration.AoAExplosions;
import net.tslat.aoa3.library.builder.AoAExplosionBuilder;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.explosion.StandardExplosion;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ExplosiveBow extends AoABow {
	public ExplosiveBow(Item.Properties properties) {
		super(properties);
	}

	@Override
	public void onEntityImpact(Projectile projectile, @Nullable Entity shooter, EntityHitResult hitResult, ItemStack stack, float velocity) {
		explode(projectile);
	}

	@Override
	public void onBlockImpact(Projectile projectile, @Nullable Entity shooter, BlockHitResult hitResult, ItemStack stack) {
		explode(projectile);
	}

	protected void explode(Projectile projectile) {
		if (projectile instanceof AbstractArrow arrow && arrow.isCritArrow() && !projectile.level().isClientSide)
			AoAExplosionBuilder.at(projectile, AoAExplosions.EXPLOSIVE_BOW, StandardExplosion::new).explode();
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		super.appendHoverText(stack, context, tooltip, flag);

		for (MutableComponent component : LocaleUtil.getExplosionInfoLocale(AoAExplosions.EXPLOSIVE_BOW, true, flag.isAdvanced(), false)) {
			tooltip.add(2, component);
		}
	}
}
