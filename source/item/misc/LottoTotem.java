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
		super(new Item.Properties().group(AoAItemGroups.MISC_ITEMS));
	}

	@Override
	public ActionResultType onItemUse(ItemUseContext context) {
		PlayerEntity player = context.getPlayer();

		if (player == null)
			return ActionResultType.FAIL;

		World world = context.getWorld();
		BlockPos pos = context.getPos();
		BlockState targetBlockState = world.getBlockState(pos);

		if (context.getFace() != Direction.UP || !targetBlockState.isSolidSide(world, pos, Direction.UP))
			return ActionResultType.FAIL;

		if (!world.isRemote) {
			if (!world.getEntitiesWithinAABB(LottoTotemEntity.class, new AxisAlignedBB(pos).grow(4)).isEmpty()) {
				PlayerUtil.notifyPlayer((ServerPlayerEntity)player, "message.feedback.lottoTotem.nearby", TextFormatting.RED);

				return ActionResultType.FAIL;
			}

			ArrayList<BlockPos> spawnPositions = new ArrayList<BlockPos>(5);

			spawnPositions.add(pos);

			if (populateSpawnPositions(world, pos, spawnPositions)) {
				player.getHeldItem(context.getHand()).shrink(1);

				int selectedWinner = RandomUtil.randomNumberUpTo(5);
				UUID winningUUID = MathHelper.getRandomUUID();

				for (BlockPos spawnPos : spawnPositions) {
					LottoTotemEntity totem = new LottoTotemEntity(world, spawnPos, winningUUID, player.getUniqueID());

					if (selectedWinner == 0)
						totem.setUniqueId(winningUUID);

					world.addEntity(totem);
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
		BlockPos.Mutable checkPos = new BlockPos.Mutable(pos);

		for (int x = pos.getX() - 1; x <= pos.getX() + 1; x += 2) {
			for (int z = pos.getZ() - 1; z <= pos.getZ() + 1; z += 2) {
				checkPos.setPos(x, pos.getY(), z);

				if (world.getBlockState(checkPos.up()).getMaterial().isReplaceable()) {
					if (world.getBlockState(checkPos).isSolidSide(world, checkPos, Direction.UP)) {
						spawnPositions.add(checkPos.toImmutable());

						if (spawnPositions.size() >= 5)
							return true;
					}
				}
			}
		}

		return false;
	}
}
