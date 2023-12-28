package net.tslat.aoa3.content.item.weapon.bow;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.content.entity.projectile.arrow.CustomArrowEntity;
import net.tslat.aoa3.util.LocaleUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class InfernalBow extends BaseBow {
	public InfernalBow(double damage, float drawSpeedMultiplier, int durability) {
		super(damage, drawSpeedMultiplier, durability);
	}

	@Override
	public CustomArrowEntity doArrowMods(CustomArrowEntity arrow, LivingEntity shooter, ItemStack ammoStack, int useTicksRemaining) {
		arrow.setSecondsOnFire(100);

		return super.doArrowMods(arrow, shooter, ammoStack, useTicksRemaining);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.BURNS_TARGETS, LocaleUtil.ItemDescriptionType.BENEFICIAL));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
