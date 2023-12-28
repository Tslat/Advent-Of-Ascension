package net.tslat.aoa3.content.item.weapon.crossbow;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.content.entity.projectile.arrow.CustomArrowEntity;
import net.tslat.aoa3.util.LocaleUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LunarCrossbow extends BaseCrossbow {
	public LunarCrossbow(double damage, int durability) {
		super(damage, durability);
	}

	@Override
	protected CustomArrowEntity createArrow(LivingEntity shooter, ItemStack crossbowStack, ItemStack ammoStack) {
		CustomArrowEntity arrow = super.createArrow(shooter, crossbowStack, ammoStack);

		arrow.setNoGravity(true);

		return arrow;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
	}
}
