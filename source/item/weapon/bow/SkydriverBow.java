package net.tslat.aoa3.item.weapon.bow;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.entity.projectile.arrow.CustomArrowEntity;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.List;

public class SkydriverBow extends BaseBow {
	public SkydriverBow(double damage, float drawSpeedMultiplier, int durability) {
		super(damage, drawSpeedMultiplier, durability);
	}

	@Override
	public void onArrowTick(CustomArrowEntity arrow, Entity shooter) {
		if (!arrow.inGround && arrow.ticksExisted > 1) {
			BlockPos.Mutable testPos = new BlockPos.Mutable(arrow.getPosition());

			while (testPos.getY() >= 0 && arrow.world.isAirBlock(testPos.move(Direction.DOWN))) {
				;
			}

			if (arrow.world.getBlockState(testPos).isSolidSide(arrow.world, testPos, Direction.UP) && arrow.world.getBlockState(testPos.up()).getMaterial().isReplaceable())
				arrow.world.setBlockState(testPos.up(), AoABlocks.ORANGE_ACID.get().getDefaultState());
		}
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.addInformation(stack, world, tooltip, flag);
	}
}
