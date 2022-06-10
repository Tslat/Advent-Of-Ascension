package net.tslat.aoa3.content.block.functional.altar;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.BlockHitResult;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.content.block.functional.misc.DustopianLamp;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.aoa3.util.RandomUtil;
import net.tslat.aoa3.util.WorldUtil;

public class PrimordialShrine extends BossAltarBlock {
	public PrimordialShrine() {
		super(MaterialColor.TERRACOTTA_BLACK);
	}

	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		if (world.getDifficulty() == Difficulty.PEACEFUL && player instanceof ServerPlayer) {
			PlayerUtil.notifyPlayer(player, Component.translatable("message.feedback.spawnBoss.difficultyFail"));

			return InteractionResult.FAIL;
		}

		if (!world.isClientSide && checkActivationConditions(player, hand, state, pos))
			doActivationEffect(player, hand, state, pos);

		return InteractionResult.SUCCESS;
	}

	@Override
	protected boolean checkActivationConditions(Player player, InteractionHand hand, BlockState state, BlockPos pos) {
		if (!WorldUtil.isWorld(player.level, AoADimensions.DUSTOPIA.key))
			return false;

		return checkLamps(player.level,
				pos.offset(5, 1, -3),
				pos.offset(4, 1, -1),
				pos.offset(3, 3, 1),
				pos.offset(1, 5, 1),
				pos.offset(-1, 5, 1),
				pos.offset(-3, 3, 1),
				pos.offset(-4, 1, -1),
				pos.offset(-5, 1, -3));
	}

	private boolean checkLamps(Level world, BlockPos... positions) {
		for (BlockPos pos : positions) {
			BlockState state = world.getBlockState(pos);

			if (state.getBlock() != AoABlocks.DUSTOPIAN_LAMP.get() || !state.getValue(DustopianLamp.LIT))
				return false;
		}

		return true;
	}

	@Override
	protected void doActivationEffect(Player player, InteractionHand hand, BlockState state, BlockPos blockPos) {
		Level world = player.level;
		BlockState lampOff = AoABlocks.DUSTOPIAN_LAMP.get().defaultBlockState().setValue(DustopianLamp.LIT, false);

		world.setBlockAndUpdate(RandomUtil.getRandomSelection(
				blockPos.offset(5, 1, -3),
				blockPos.offset(4, 1, -1),
				blockPos.offset(3, 3, 1),
				blockPos.offset(1, 5, 1),
				blockPos.offset(-1, 5, 1),
				blockPos.offset(-3, 3, 1),
				blockPos.offset(-4, 1, -1),
				blockPos.offset(-5, 1, -3)), lampOff);

		/*KajarosEntity kajaros = new KajarosEntity(AoAMobs.KAJAROS.get(), player.level);

		kajaros.moveTo(blockPos.getX(), blockPos.getY() + 3, blockPos.getZ(), 0, 0);
		player.level.addFreshEntity(kajaros);
		sendSpawnMessage(player, LocaleUtil.getLocaleMessage(AoAMobs.OKAZOR.get().getDescriptionId() + ".spawn", player.getDisplayName()), blockPos);*/
	}

	@Override
	protected Item getActivationItem() {
		return null;
	}
}