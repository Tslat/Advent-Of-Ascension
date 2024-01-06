package net.tslat.aoa3.content.item.weapon.bow;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.tslat.aoa3.common.registration.AoAExplosions;
import net.tslat.aoa3.content.entity.projectile.arrow.CustomArrowEntity;
import net.tslat.aoa3.library.object.explosion.StandardExplosion;
import net.tslat.aoa3.util.LocaleUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ExplosiveBow extends BaseBow {
	public ExplosiveBow(double damage, float drawSpeedMultiplier, int durability) {
		super(damage, drawSpeedMultiplier, durability);
	}

	@Override
	public void onEntityHit(CustomArrowEntity arrow, Entity target, Entity shooter, double damage, float drawStrength) {
		if (arrow.isCritArrow() && arrow.level() instanceof ServerLevel serverLevel)
			new StandardExplosion(AoAExplosions.EXPLOSIVE_BOW, serverLevel, arrow, shooter).explode();
	}

	@Override
	public void onBlockHit(CustomArrowEntity arrow, BlockHitResult rayTrace, Entity shooter) {
		if (arrow.isCritArrow() && arrow.level() instanceof ServerLevel serverLevel)
			new StandardExplosion(AoAExplosions.EXPLOSIVE_BOW, serverLevel, arrow, shooter).explode();
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);

		for (MutableComponent component : LocaleUtil.getExplosionInfoLocale(AoAExplosions.EXPLOSIVE_BOW, flag.isAdvanced(), false)) {
			tooltip.add(2, component);
		}
	}
}
