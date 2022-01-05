package net.tslat.aoa3.object.item.weapon.bow;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.object.entity.projectile.arrow.CustomArrowEntity;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;
import java.util.List;

public class SkydriverBow extends BaseBow {
	public SkydriverBow(double damage, float drawSpeedMultiplier, int durability) {
		super(damage, drawSpeedMultiplier, durability);
	}

	@Override
	public void onArrowTick(CustomArrowEntity arrow, Entity shooter) {
		if (!arrow.inGround && arrow.tickCount > 1) {
			BlockPos.Mutable testPos = new BlockPos.Mutable();

			testPos.set(arrow.blockPosition());

			while (testPos.getY() >= 0 && arrow.level.isEmptyBlock(testPos.move(Direction.DOWN))) {
				;
			}

			if (arrow.level.getBlockState(testPos).isFaceSturdy(arrow.level, testPos, Direction.UP) && arrow.level.getBlockState(testPos.above()).getMaterial().isReplaceable() && WorldUtil.canPlaceBlock(arrow.level, testPos.above(), shooter, null))
				arrow.level.setBlockAndUpdate(testPos.above(), AoABlocks.ORANGE_ACID.get().defaultBlockState());
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
