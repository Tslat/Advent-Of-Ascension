package net.tslat.aoa3.item.misc;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.entity.misc.LottoTotemEntity;
import net.tslat.aoa3.util.RandomUtil;
import net.tslat.aoa3.util.player.PlayerUtil;

import java.util.ArrayList;
import java.util.UUID;

public class LottoTotem extends Item {
	public LottoTotem() {
		super(new Item.Properties().tab(AoAItemGroups.MISC_ITEMS));
	}

	@Override
	public ActionResultType useOn(ItemUseContext context) {
		PlayerEntity player = context.getPlayer();

		if (player == null)
			return ActionResultType.FAIL;

		World world = context.getLevel();
		BlockPos pos = context.getClickedPos();
		BlockState targetBlockState = world.getBlockState(pos);

		if (context.getClickedFace() != Direction.UP || !targetBlockState.isFaceSturdy(world, pos, Direction.UP))
			return ActionResultType.FAIL;

		if (!world.isClientSide) {
			if (!world.getEntitiesOfClass(LottoTotemEntity.class, new AxisAlignedBB(pos).inflate(4)).isEmpty()) {
				PlayerUtil.notifyPlayer((ServerPlayerEntity)player, "message.feedback.lottoTotem.nearby", TextFormatting.RED);

				return ActionResultType.FAIL;
			}

			ArrayList<BlockPos> spawnPositions = new ArrayList<BlockPos>(5);

			spawnPositions.add(pos);

			if (populateSpawnPositions(world, pos, spawnPositions)) {
				player.getItemInHand(context.getHand()).shrink(1);

				int selectedWinner = RandomUtil.randomNumberUpTo(5);
				UUID winningUUID = MathHelper.createInsecureUUID();

				for (BlockPos spawnPos : spawnPositions) {
					LottoTotemEntity totem = new LottoTotemEntity(world, spawnPos, winningUUID, player.getUUID());

					if (selectedWinner == 0)
						totem.setUUID(winningUUID);

					world.addFreshEntity(totem);
					selectedWinner--;
				}

				PlayerUtil.notifyPlayer((ServerPlayerEntity)player, "message.feedback.lottoTotem.spawn", TextFormatting.GOLD);
			}
			else {
				PlayerUtil.notifyPlayer((ServerPlayerEntity)player, "message.feedback.lottoTotem.noSpace", TextFormatting.RED);

				return ActionResultType.FAIL;
			}
		}

		return ActionResultType.PASS;
	}

	private boolean populateSpawnPositions(World world, BlockPos pos, ArrayList<BlockPos> spawnPositions) {
		BlockPos.Mutable checkPos = pos.mutable();

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
