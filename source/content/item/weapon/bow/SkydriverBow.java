package net.tslat.aoa3.content.item.weapon.bow;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.content.entity.projectile.arrow.CustomArrowEntity;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SkydriverBow extends BaseBow {
	public SkydriverBow(double damage, float drawSpeedMultiplier, int durability) {
		super(damage, drawSpeedMultiplier, durability);
	}

	@Override
	public void onArrowTick(CustomArrowEntity arrow, Entity shooter) {
		if (!arrow.inGround && arrow.tickCount > 1) {
			BlockPos.MutableBlockPos testPos = new BlockPos.MutableBlockPos();

			testPos.set(arrow.blockPosition());

			while (testPos.getY() >= 0 && arrow.level().isEmptyBlock(testPos.move(Direction.DOWN))) {
				;
			}

			if (arrow.level().getBlockState(testPos).isFaceSturdy(arrow.level(), testPos, Direction.UP) && arrow.level().getBlockState(testPos.above()).canBeReplaced() && WorldUtil.canPlaceBlock(arrow.level(), testPos.above(), shooter, null))
				arrow.level().setBlockAndUpdate(testPos.above(), AoABlocks.ORANGE_ACID.get().defaultBlockState());
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
