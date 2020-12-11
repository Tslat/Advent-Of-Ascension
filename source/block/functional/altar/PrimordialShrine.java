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
		super(MaterialColor.BLACK_TERRACOTTA);
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if (world.getDifficulty() == Difficulty.PEACEFUL && player instanceof ServerPlayerEntity) {
			PlayerUtil.getAdventPlayer((ServerPlayerEntity)player).sendThrottledChatMessage("message.feedback.spawnBoss.difficultyFail");

			return ActionResultType.FAIL;
		}

		if (!world.isRemote && checkActivationConditions(player, hand, state, pos))
			doActivationEffect(player, hand, state, pos);

		return ActionResultType.SUCCESS;
	}

	@Override
	protected boolean checkActivationConditions(PlayerEntity player, Hand hand, BlockState state, BlockPos pos) {
		BlockPos pos2 = pos.up(1).west(3).north(5);
		BlockPos pos3 = pos.up(1).west(1).north(4);
		BlockPos pos4 = pos.up(3).north(3).east(1);
		BlockPos pos5 = pos.up(5).north(1).east(1);

		return checkLamps(player.world,
				pos.up(1).west(3).north(5),
				pos2.south(10),
				pos.up(1).west(1).north(4),
				pos3.south(8),
				pos.up(3).north(3).east(1),
				pos4.south(6),
				pos.up(5).north(1).east(1),
				pos5.south(2));
	}

	private boolean checkLamps(World world, BlockPos... positions) {
		for (BlockPos pos : positions) {
			BlockState state = world.getBlockState(pos);

			if (state.getBlock() != AoABlocks.DUSTOPIAN_LAMP.get() || !state.get(DustopianLamp.LIT))
				return false;
		}

		return true;
	}

	@Override
	protected void doActivationEffect(PlayerEntity player, Hand hand, BlockState state, BlockPos blockPos) {
		World world = player.world;
		BlockState lampOff = AoABlocks.DUSTOPIAN_LAMP.get().getDefaultState().with(DustopianLamp.LIT, false);

		switch (player.getRNG().nextInt(8)) {
			case 0:
				world.setBlockState(blockPos.up().west(3).north(5), lampOff);
				break;
			case 1:
				world.setBlockState(blockPos.up().west(3).south(5), lampOff);
				break;
			case 2:
				world.setBlockState(blockPos.up().west().north(4), lampOff);
				break;
			case 3:
				world.setBlockState(blockPos.up().west().south(4), lampOff);
				break;
			case 4:
				world.setBlockState(blockPos.up(3).north(3).east(), lampOff);
				break;
			case 5:
				world.setBlockState(blockPos.up(3).south(3).east(), lampOff);
				break;
			case 6:
				world.setBlockState(blockPos.up(5).north().east(), lampOff);
				break;
			case 7:
				world.setBlockState(blockPos.up(5).south().east(), lampOff);
				break;
		}

		KajarosEntity kajaros = new KajarosEntity(AoAEntities.Mobs.KAJAROS.get(), player.world);

		kajaros.setLocationAndAngles(blockPos.getX(), blockPos.getY() + 3, blockPos.getZ(), 0, 0);
		player.world.addEntity(kajaros);
		sendSpawnMessage(player, LocaleUtil.getLocaleMessage("message.mob.primordialFive.spawn", player.getDisplayName().getFormattedText()), blockPos);
	}

	@Override
	protected Item getActivationItem() {
		return null;
	}
}