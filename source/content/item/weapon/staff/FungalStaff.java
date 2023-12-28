package net.tslat.aoa3.content.item.weapon.staff;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.MushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;
import net.tslat.smartbrainlib.util.RandomUtil;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FungalStaff extends BaseStaff<HashMap<BlockPos, Boolean>> {
	public FungalStaff(int durability) {
		super(durability);
	}

	@Nullable
	@Override
	public SoundEvent getCastingSound() {
		return AoASounds.ITEM_FUNGAL_STAFF_CAST.get();
	}

	@Override
	protected void populateRunes(HashMap<Item, Integer> runes) {
		runes.put(AoAItems.DISTORTION_RUNE.get(), 5);
		runes.put(AoAItems.LIFE_RUNE.get(), 2);
	}

	@Nullable
	@Override
	public HashMap<BlockPos, Boolean> checkPreconditions(LivingEntity caster, ItemStack staff) {
		BlockPos.MutableBlockPos checkPos = new BlockPos.MutableBlockPos();
		HashMap<BlockPos, Boolean> workablePositions = new HashMap<BlockPos, Boolean>();

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
					else if (block instanceof MushroomBlock) {
						MushroomBlock mushroom = (MushroomBlock)block;

						if (mushroom.isValidBonemealTarget(caster.level(), pos, state, caster.level().isClientSide()) && WorldUtil.canModifyBlock(caster.level(), pos, caster, staff))
							workablePositions.put(pos, false);
					}
				}
			}
		}

		return workablePositions.isEmpty() ? null : workablePositions;
	}

	@Override
	public void cast(Level world, ItemStack staff, LivingEntity caster, HashMap<BlockPos, Boolean> args) {
		if (world instanceof ServerLevel) {
			for (Map.Entry<BlockPos, Boolean> entry : args.entrySet()) {
				BlockPos pos = entry.getKey();

				if (entry.getValue()) {
					world.setBlockAndUpdate(pos, Blocks.MYCELIUM.defaultBlockState());
				}
				else {
					BlockState state = world.getBlockState(pos);
					MushroomBlock mushroom = (MushroomBlock)state.getBlock();

					if (mushroom.isBonemealSuccess(world, RandomUtil.RANDOM.getSource(), pos, state))
						mushroom.performBonemeal((ServerLevel)world, RandomUtil.RANDOM.getSource(), pos, state);
				}

				world.levelEvent(2005, pos, 0);
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 2));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
