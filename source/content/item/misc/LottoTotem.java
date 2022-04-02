package net.tslat.aoa3.content.item.misc;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.content.entity.misc.LottoTotemEntity;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.aoa3.util.RandomUtil;

import java.util.ArrayList;
import java.util.UUID;

public class LottoTotem extends Item {
	public LottoTotem() {
		super(new Item.Properties().tab(AoAItemGroups.MISC_ITEMS));
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		Player player = context.getPlayer();

		if (player == null)
			return InteractionResult.FAIL;

		Level world = context.getLevel();
		BlockPos pos = context.getClickedPos();
		BlockState targetBlockState = world.getBlockState(pos);

		if (context.getClickedFace() != Direction.UP || !targetBlockState.isFaceSturdy(world, pos, Direction.UP))
			return InteractionResult.FAIL;

		if (!world.isClientSide) {
			if (!world.getEntitiesOfClass(LottoTotemEntity.class, new AABB(pos).inflate(4)).isEmpty()) {
				PlayerUtil.notifyPlayer(player, new TranslatableComponent("message.feedback.lottoTotem.nearby").withStyle(ChatFormatting.RED));

				return InteractionResult.FAIL;
			}

			ArrayList<BlockPos> spawnPositions = new ArrayList<>(5);

			spawnPositions.add(pos);

			if (populateSpawnPositions(world, pos, spawnPositions)) {
				player.getItemInHand(context.getHand()).shrink(1);

				int selectedWinner = RandomUtil.randomNumberUpTo(5);
				UUID winningUUID = Mth.createInsecureUUID();

				for (BlockPos spawnPos : spawnPositions) {
					LottoTotemEntity totem = new LottoTotemEntity(world, spawnPos, winningUUID, player.getUUID());

					if (selectedWinner == 0)
						totem.setUUID(winningUUID);

					world.addFreshEntity(totem);
					selectedWinner--;
				}

				PlayerUtil.notifyPlayer(player, new TranslatableComponent("message.feedback.lottoTotem.spawn").withStyle(ChatFormatting.GOLD));
			}
			else {
				PlayerUtil.notifyPlayer(player, new TranslatableComponent("message.feedback.lottoTotem.noSpace").withStyle(ChatFormatting.RED));

				return InteractionResult.FAIL;
			}
		}

		return InteractionResult.PASS;
	}

	private boolean populateSpawnPositions(Level world, BlockPos pos, ArrayList<BlockPos> spawnPositions) {
		BlockPos.MutableBlockPos checkPos = pos.mutable();

		for (int x = pos.getX() - 1; x <= pos.getX() + 1; x += 2) {
			for (int z = pos.getZ() - 1; z <= pos.getZ() + 1; z += 2) {
				checkPos.set(x, pos.getY(), z);

				if (world.getBlockState(checkPos.above()).getMaterial().isReplaceable()) {
					if (world.getBlockState(checkPos).isFaceSturdy(world, checkPos, Direction.UP)) {
						spawnPositions.add(checkPos.immutable());

						if (spawnPositions.size() >= 5)
							return true;
					}
				}
			}
		}

		return false;
	}
}
