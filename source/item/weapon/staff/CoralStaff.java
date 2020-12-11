package net.tslat.aoa3.item.weapon.staff;

import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.item.misc.RuneItem;
import net.tslat.aoa3.library.scheduling.async.CoralStaffTask;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CoralStaff extends BaseStaff<ArrayList<BlockPos>> {
	public CoralStaff(int durability) {
		super(durability);
	}

	@Nullable
	@Override
	public SoundEvent getCastingSound() {
		return AoASounds.ITEM_CORAL_STAFF_CAST.get();
	}

	@Override
	protected void populateRunes(HashMap<RuneItem, Integer> runes) {
		runes.put(AoAItems.WATER_RUNE.get(), 2);
		runes.put(AoAItems.KINETIC_RUNE.get(), 8);
	}

	@Override
	@Nullable
	public ArrayList<BlockPos> checkPreconditions(LivingEntity caster, ItemStack staff) {
		ArrayList<BlockPos> coralPositions = new ArrayList<BlockPos>();
		final BlockPos pos = caster.getPosition();
		BlockPos.Mutable checkPos = new BlockPos.Mutable();

		for (int x = -2; x <= 1; x++) {
			for (int z = -2; z <= 1; z++) {
				checkPos.setPos(pos.getX() + x, pos.getY() - 2, pos.getZ() + z);

				if (safeBlockPos(caster, checkPos))
					coralPositions.add(checkPos.toImmutable());

				if (safeBlockPos(caster, checkPos.move(Direction.UP, 4)))
					coralPositions.add(checkPos.toImmutable());
			}

			for (int y = -2; y <= 2; y++) {
				checkPos.setPos(pos.getX() + x, pos.getY() + y, pos.getZ() - 2);

				if (safeBlockPos(caster, checkPos))
					coralPositions.add(checkPos.toImmutable());

				if (safeBlockPos(caster, checkPos.move(Direction.SOUTH, 4)))
					coralPositions.add(checkPos.toImmutable());
			}
		}

		for (int z = -2; z <= 2; z++) {
			for (int y = -2; y <= 2; y++) {
				checkPos.setPos(pos.getX() - 2, pos.getY() + y, pos.getZ() + z);

				if (safeBlockPos(caster, checkPos))
					coralPositions.add(checkPos.toImmutable());

				if (safeBlockPos(caster, checkPos.move(Direction.EAST, 4)))
					coralPositions.add(checkPos.toImmutable());
			}
		}

		return coralPositions.isEmpty() ? null : coralPositions;
	}

	@Override
	public void cast(World world, ItemStack staff, LivingEntity caster, ArrayList<BlockPos> args) {
		if (!world.isRemote && caster instanceof PlayerEntity) {
			for (BlockPos pos : args) {
				world.setBlockState(pos, AoABlocks.PINK_CORAL.get().getDefaultState(), 2);
			}

			world.playSound(null, caster.getPosX(), caster.getPosY(), caster.getPosZ(), AoASounds.ITEM_REEF_STAFF_CAST.get(), SoundCategory.PLAYERS, 1.0f, 1.0f);
			new CoralStaffTask(world, args).schedule(30, TimeUnit.SECONDS);
		}
	}

	private boolean safeBlockPos(LivingEntity caster, BlockPos pos) {
		return caster.world.getBlockState(pos).getBlock() == Blocks.AIR && (!(caster instanceof PlayerEntity) || caster.world.canMineBlockBody((PlayerEntity)caster, pos));
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.addInformation(stack, world, tooltip, flag);
	}
}
