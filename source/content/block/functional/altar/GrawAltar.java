package net.tslat.aoa3.content.block.functional.altar;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.content.entity.mob.lelyetia.FlyeEntity;
import net.tslat.aoa3.util.WorldUtil;

public class GrawAltar extends BossAltarBlock {
	public GrawAltar(BlockBehaviour.Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		ItemStack heldItem = player.getItemInHand(hand);

		if (heldItem.getItem() == AoAItems.ORANGE_SPORES.get() || heldItem.getItem() == AoAItems.YELLOW_SPORES.get()) {
			if (!world.isClientSide) {
				world.addFreshEntity(new FlyeEntity(world, pos));

				if (!player.isCreative())
					heldItem.shrink(1);
			}

			return InteractionResult.SUCCESS;
		}
		else {
			return super.use(state, world, pos, player, hand, hit);
		}
	}

	@Override
	protected void doActivationEffect(Player player, InteractionHand hand, BlockState state, BlockPos blockPos) {
		/*GrawEntity graw = new GrawEntity(AoAMobs.GRAW.get(), player.level);

		graw.moveTo(blockPos.getX(), blockPos.getY() + 3, blockPos.getZ(), 0, 0);
		player.level.addFreshEntity(graw);
		sendSpawnMessage(player, LocaleUtil.getLocaleMessage(AoAMobs.GRAW.get().getDescriptionId() + ".spawn", player.getDisplayName()), blockPos);*/
	}

	@Override
	protected boolean checkActivationConditions(Player player, InteractionHand hand, BlockState state, BlockPos pos) {
		return WorldUtil.isWorld(player.level(), AoADimensions.LELYETIA.key);
	}

	@Override
	protected Item getActivationItem() {
		return AoAItems.GUARDIANS_EYE.get();
	}
}
