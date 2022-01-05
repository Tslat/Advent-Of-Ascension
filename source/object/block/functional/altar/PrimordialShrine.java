package net.tslat.aoa3.object.block.functional.altar;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.tslat.aoa3.object.block.functional.misc.DustopianLamp;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.object.entity.boss.KajarosEntity;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.RandomUtil;
import net.tslat.aoa3.util.WorldUtil;
import net.tslat.aoa3.util.PlayerUtil;

public class PrimordialShrine extends BossAltarBlock {
	public PrimordialShrine() {
		super(MaterialColor.TERRACOTTA_BLACK);
	}

	@Override
	public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if (world.getDifficulty() == Difficulty.PEACEFUL && player instanceof ServerPlayerEntity) {
			PlayerUtil.notifyPlayer((ServerPlayerEntity)player, new TranslationTextComponent("message.feedback.spawnBoss.difficultyFail"));

			return ActionResultType.FAIL;
		}

		if (!world.isClientSide && checkActivationConditions(player, hand, state, pos))
			doActivationEffect(player, hand, state, pos);

		return ActionResultType.SUCCESS;
	}

	@Override
	protected boolean checkActivationConditions(PlayerEntity player, Hand hand, BlockState state, BlockPos pos) {
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

		world.setBlockAndUpdate(RandomUtil.getRandomSelection(
				blockPos.offset(5, 1, -3),
				blockPos.offset(4, 1, -1),
				blockPos.offset(3, 3, 1),
				blockPos.offset(1, 5, 1),
				blockPos.offset(-1, 5, 1),
				blockPos.offset(-3, 3, 1),
				blockPos.offset(-4, 1, -1),
				blockPos.offset(-5, 1, -3)), lampOff);

		KajarosEntity kajaros = new KajarosEntity(AoAEntities.Mobs.KAJAROS.get(), player.level);

		kajaros.moveTo(blockPos.getX(), blockPos.getY() + 3, blockPos.getZ(), 0, 0);
		player.level.addFreshEntity(kajaros);
		sendSpawnMessage(player, LocaleUtil.getLocaleMessage(AoAEntities.Mobs.OKAZOR.get().getDescriptionId() + ".spawn", player.getDisplayName()), blockPos);
	}

	@Override
	protected Item getActivationItem() {
		return null;
	}
}