package net.tslat.aoa3.content.item.weapon.staff;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.BonemealableBlock;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;

import java.util.List;
import java.util.Optional;

public class NatureStaff extends AoAStaff<List<BlockPos>> {
	public NatureStaff(Item.Properties properties) {
		super(properties);
	}

	@Override
	public Optional<List<BlockPos>> checkPreconditions(LivingEntity caster, ItemStack staff) {
		List<BlockPos> blocks = WorldUtil.getBlocksWithinAABB(caster.level(), caster.getBoundingBox().inflate(10), (state, pos) -> {
			if (!(state.getBlock() instanceof BonemealableBlock bonemealable))
				return false;

			if (!bonemealable.isValidBonemealTarget(caster.level(), pos.immutable(), state))
				return false;

			return WorldUtil.canModifyBlock(caster.level(), pos, caster, staff);
		});

		return Optional.ofNullable(blocks.isEmpty() ? null : blocks);
	}

	@Override
	public void cast(ServerLevel level, LivingEntity caster, ItemStack staff, InteractionHand hand, List<BlockPos> args) {
		for (BlockPos pos : args) {
			BoneMealItem.growCrop(new ItemStack(Items.BONE_MEAL), caster.level(), pos);
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, context, tooltip, flag);
	}
}
