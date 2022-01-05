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
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.scheduling.async.KrorSpawnTask;
import net.tslat.aoa3.util.WorldUtil;
import net.tslat.aoa3.util.PlayerUtil;

import java.util.concurrent.TimeUnit;

public class KrorAltar extends BossAltarBlock {
	public KrorAltar() {
		super(MaterialColor.COLOR_GRAY);
	}

	@Override
	public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if (player.getItemInHand(hand).getItem() == Item.byBlock(AoABlocks.CHARGING_TABLE.get()))
			return ActionResultType.FAIL;

		return super.use(state, world, pos, player, hand, hit);
	}

	@Override
	protected void doActivationEffect(PlayerEntity player, Hand hand, BlockState state, BlockPos blockPos) {
		if (player instanceof ServerPlayerEntity) {
			new KrorSpawnTask((ServerPlayerEntity)player, blockPos.above()).schedule(1, TimeUnit.SECONDS);

			PlayerUtil.notifyPlayer((ServerPlayerEntity)player, new TranslationTextComponent(AoAEntities.Mobs.KROR.get().getDescriptionId() + ".start"));
		}
	}

	@Override
	protected boolean checkActivationConditions(PlayerEntity player, Hand hand, BlockState state, BlockPos pos) {
		if (player.level.getBlockState(pos.above()).getBlock() != AoABlocks.CHARGING_TABLE.get() && player instanceof ServerPlayerEntity) {
			PlayerUtil.notifyPlayer((ServerPlayerEntity)player, new TranslationTextComponent("message.feedback.krorAltar.chargingTable"));

			return false;
		}

		return WorldUtil.isWorld(player.level, AoADimensions.DEEPLANDS.key);
	}

	@Override
	protected Item getActivationItem() {
		return AoAItems.BOULDER_DASH.get();
	}
}
