package net.tslat.aoa3.content.item.weapon.crossbow;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.phys.EntityHitResult;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.util.RandomUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RosidianCrossbow extends AoACrossbow {
	public RosidianCrossbow(Item.Properties properties) {
		super(properties);
	}

	@Override
	public void onEntityImpact(Projectile projectile, @Nullable Entity shooter, EntityHitResult hitResult, ItemStack stack, float velocity) {
		if (RandomUtil.oneInNChance(8) && shooter instanceof LivingEntity livingShooter)
			livingShooter.heal(1);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, context, tooltip, tooltipFlag);
	}
}
