package net.tslat.aoa3.content.item.weapon.staff;

import it.unimi.dsi.fastutil.objects.Object2BooleanArrayMap;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.level.block.MushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;
import net.tslat.tme.api.util.RandomUtil;

import java.util.List;
import java.util.Optional;

public class FungalStaff extends AoAStaff<Object2BooleanArrayMap<BlockPos>> {
	public FungalStaff(Item.Properties properties) {
		super(properties);
	}

	@Override
	public Optional<Object2BooleanArrayMap<BlockPos>> checkPreconditions(LivingEntity caster, ItemStack staff) {
		Object2BooleanArrayMap<BlockPos> workablePositions = new Object2BooleanArrayMap<>();

		for (int x = -2; x <= 2; x++) {
			for (int y = -2; y <= 2; y++) {
				for (int z = -2; z <= 2; z++) {
					BlockPos pos = BlockPos.containing(caster.getX() + x, caster.getY() + y, caster.getZ() + z);
					BlockState state = caster.level().getBlockState(pos);
					Block block = state.getBlock();

					if (block == Blocks.GRASS_BLOCK) {
						if (WorldUtil.canModifyBlock(caster.level(), pos, caster, staff))
							workablePositions.put(pos, true);
					}
					else if (block instanceof MushroomBlock mushroom) {
                        if (mushroom.isValidBonemealTarget(caster.level(), pos, state) && WorldUtil.canModifyBlock(caster.level(), pos, caster, staff))
							workablePositions.put(pos, false);
					}
				}
			}
		}

		return Optional.ofNullable(workablePositions.isEmpty() ? null : workablePositions);
	}

	@Override
	public void cast(ServerLevel level, LivingEntity caster, ItemStack staff, InteractionHand hand, Object2BooleanArrayMap<BlockPos> args) {
		if (level instanceof ServerLevel) {
			for (Object2BooleanArrayMap.Entry<BlockPos> entry : args.object2BooleanEntrySet()) {
				BlockPos pos = entry.getKey();

				if (entry.getBooleanValue()) {
					level.setBlockAndUpdate(pos, Blocks.MYCELIUM.defaultBlockState());
				}
				else {
					BlockState state = level.getBlockState(pos);
					MushroomBlock mushroom = (MushroomBlock)state.getBlock();

					if (mushroom.isBonemealSuccess(level, RandomUtil.RANDOM, pos, state))
						mushroom.performBonemeal(level, RandomUtil.RANDOM, pos, state);
				}

				level.levelEvent(LevelEvent.PARTICLES_AND_SOUND_PLANT_GROWTH, pos, 0);
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 2));
		super.appendHoverText(stack, context, tooltip, flag);
	}
}
