package net.tslat.aoa3.item.misc;

import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.entity.misc.EntityLottoTotem;
import net.tslat.aoa3.utils.player.PlayerUtil;

import java.util.ArrayList;
import java.util.UUID;

public class ItemLottoTotem extends Item {
	public ItemLottoTotem() {
		setTranslationKey("LottoTotem");
		setRegistryName("aoa3:lotto_totem");
		setCreativeTab(CreativeTabsRegister.miscTab);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		IBlockState targetBlockState = world.getBlockState(pos).getActualState(world, pos);

		if (facing != EnumFacing.UP || targetBlockState.getBlockFaceShape(world, pos, EnumFacing.UP) != BlockFaceShape.SOLID)
			return EnumActionResult.FAIL;

		if (!world.isRemote) {
			if (!world.getEntitiesWithinAABB(EntityLottoTotem.class, new AxisAlignedBB(pos).grow(4)).isEmpty()) {
				PlayerUtil.notifyPlayer((EntityPlayerMP)player, "message.feedback.lottoTotem.nearby", TextFormatting.RED);

				return EnumActionResult.FAIL;
			}

			ArrayList<BlockPos> spawnPositions = new ArrayList<BlockPos>(5);

			spawnPositions.add(pos);

			if (populateSpawnPositions(world, pos, spawnPositions)) {
				player.getHeldItem(hand).shrink(1);

				int selectedWinner = itemRand.nextInt(5);
				UUID winningUUID = MathHelper.getRandomUUID();

				for (BlockPos spawnPos : spawnPositions) {
					EntityLottoTotem totem = new EntityLottoTotem(world, spawnPos, winningUUID, player.getUniqueID());

					if (selectedWinner == 0)
						totem.setUniqueId(winningUUID);

					world.spawnEntity(totem);
					selectedWinner--;
				}

				PlayerUtil.notifyPlayer((EntityPlayerMP)player, "message.feedback.lottoTotem.spawn", TextFormatting.GOLD);
			}
			else {
				PlayerUtil.notifyPlayer((EntityPlayerMP)player, "message.feedback.lottoTotem.space", TextFormatting.RED);

				return EnumActionResult.FAIL;
			}
		}

		return EnumActionResult.PASS;
	}

	private boolean populateSpawnPositions(World world, BlockPos pos, ArrayList<BlockPos> spawnPositions) {
		BlockPos.MutableBlockPos checkPos = new BlockPos.MutableBlockPos(pos);

		for (int x = pos.getX() - 1; x <= pos.getX() + 1; x += 2) {
			for (int z = pos.getZ() - 1; z <= pos.getZ() + 1; z += 2) {
				checkPos.setPos(x, pos.getY(), z);

				if (world.getBlockState(checkPos.up()).getMaterial().isReplaceable()) {
					if (world.getBlockState(checkPos).getActualState(world, checkPos).getBlockFaceShape(world, checkPos, EnumFacing.UP) == BlockFaceShape.SOLID) {
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
