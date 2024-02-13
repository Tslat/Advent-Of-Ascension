package net.tslat.aoa3.content.item.weapon.staff;

import it.unimi.dsi.fastutil.objects.Object2BooleanArrayMap;
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
import net.minecraft.world.level.block.LevelEvent;
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
import java.util.Optional;

public class FungalStaff extends BaseStaff<Object2BooleanArrayMap<BlockPos>> {
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
	public void cast(Level level, ItemStack staff, LivingEntity caster, Object2BooleanArrayMap<BlockPos> args) {
		if (level instanceof ServerLevel) {
			for (Object2BooleanArrayMap.Entry<BlockPos> entry : args.object2BooleanEntrySet()) {
				BlockPos pos = entry.getKey();

				if (entry.getBooleanValue()) {
					level.setBlockAndUpdate(pos, Blocks.MYCELIUM.defaultBlockState());
				}
				else {
					BlockState state = level.getBlockState(pos);
					MushroomBlock mushroom = (MushroomBlock)state.getBlock();

					if (mushroom.isBonemealSuccess(level, RandomUtil.RANDOM.getSource(), pos, state))
						mushroom.performBonemeal((ServerLevel)level, RandomUtil.RANDOM.getSource(), pos, state);
				}

				level.levelEvent(LevelEvent.PARTICLES_PLANT_GROWTH, pos, 0);
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
