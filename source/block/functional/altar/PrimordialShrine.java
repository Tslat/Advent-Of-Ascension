package net.tslat.aoa3.block.functional.altar;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.tslat.aoa3.block.functional.misc.DustopianLamp;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.boss.KajarosEntity;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.player.PlayerUtil;

public class PrimordialShrine extends BossAltarBlock {
	public PrimordialShrine() {
		super(MaterialColor.TERRACOTTA_BLACK);
	}

	@Override
	public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if (world.getDifficulty() == Difficulty.PEACEFUL && player instanceof ServerPlayerEntity) {
			PlayerUtil.getAdventPlayer((ServerPlayerEntity)player).sendThrottledChatMessage("message.feedback.spawnBoss.difficultyFail");

			return ActionResultType.FAIL;
		}

		if (!world.isClientSide && checkActivationConditions(player, hand, state, pos))
			doActivationEffect(player, hand, state, pos);

		return ActionResultType.SUCCESS;
	}

	@Override
	protected boolean checkActivationConditions(PlayerEntity player, Hand hand, BlockState state, BlockPos pos) {
		BlockPos pos2 = pos.above(1).west(3).north(5);
		BlockPos pos3 = pos.above(1).west(1).north(4);
		BlockPos pos4 = pos.above(3).north(3).east(1);
		BlockPos pos5 = pos.above(5).north(1).east(1);

		return checkLamps(player.level,
				pos.above(1).west(3).north(5),
				pos2.south(10),
				pos.above(1).west(1).north(4),
				pos3.south(8),
				pos.above(3).north(3).east(1),
				pos4.south(6),
				pos.above(5).north(1).east(1),
				pos5.south(2));
	}

	private boolean checkLamps(World world, BlockPos... positions) {
		for (BlockPos pos : positions) {
			BlockState state = world.getBlockState(pos);

			if (state.getBlock() != AoABlocks.DUSTOPIAN_LAMP.get() || !state.getValue(DustopianLamp.LIT))
				return false;
		}

		return true;
	}

	@Override
	protected void doActivationEffect(PlayerEntity player, Hand hand, BlockState state, BlockPos blockPos) {
		World world = player.level;
		BlockState lampOff = AoABlocks.DUSTOPIAN_LAMP.get().defaultBlockState().setValue(DustopianLamp.LIT, false);

		switch (player.getRandom().nextInt(8)) {
			case 0:
				world.setBlockAndUpdate(blockPos.above().west(3).north(5), lampOff);
				break;
			case 1:
				world.setBlockAndUpdate(blockPos.above().west(3).south(5), lampOff);
				break;
			case 2:
				world.setBlockAndUpdate(blockPos.above().west().north(4), lampOff);
				break;
			case 3:
				world.setBlockAndUpdate(blockPos.above().west().south(4), lampOff);
				break;
			case 4:
				world.setBlockAndUpdate(blockPos.above(3).north(3).east(), lampOff);
				break;
			case 5:
				world.setBlockAndUpdate(blockPos.above(3).south(3).east(), lampOff);
				break;
			case 6:
				world.setBlockAndUpdate(blockPos.above(5).north().east(), lampOff);
				break;
			case 7:
				world.setBlockAndUpdate(blockPos.above(5).south().east(), lampOff);
				break;
		}

		KajarosEntity kajaros = new KajarosEntity(AoAEntities.Mobs.KAJAROS.get(), player.level);

		kajaros.moveTo(blockPos.getX(), blockPos.getY() + 3, blockPos.getZ(), 0, 0);
		player.level.addFreshEntity(kajaros);
		sendSpawnMessage(player, LocaleUtil.getLocaleMessage("message.mob.primordialFive.spawn", player.getDisplayName()), blockPos);
	}

	@Override
	protected Item getActivationItem() {
		return null;
	}
}