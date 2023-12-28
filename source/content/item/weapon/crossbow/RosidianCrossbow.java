package net.tslat.aoa3.content.item.weapon.crossbow;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.content.entity.projectile.arrow.CustomArrowEntity;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.smartbrainlib.util.RandomUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RosidianCrossbow extends BaseCrossbow {
	public RosidianCrossbow(double damage, int durability) {
		super(damage, durability);
	}

	@Override
	public void onEntityHit(CustomArrowEntity arrow, Entity target, Entity shooter, double damage, float drawStrength) {
		if (RandomUtil.oneInNChance(8) && shooter instanceof LivingEntity)
			((LivingEntity)shooter).heal(1);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
	}
}
