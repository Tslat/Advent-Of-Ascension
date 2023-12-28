package net.tslat.aoa3.content.item.weapon.crossbow;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.content.entity.projectile.arrow.CustomArrowEntity;
import net.tslat.aoa3.util.LocaleUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PyroCrossbow extends BaseCrossbow {
	public PyroCrossbow(double damage, int durability) {
		super(damage, durability);
	}

	@Override
	public void doArrowMods(CustomArrowEntity arrow, Entity shooter, int useTicksRemaining) {
		arrow.setSecondsOnFire(100);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.BURNS_TARGETS, LocaleUtil.ItemDescriptionType.BENEFICIAL));
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
	}
}
