package net.tslat.aoa3.block.functional.misc;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.tslat.aoa3.block.functional.portal.PortalBlock;
import net.tslat.aoa3.item.misc.BlankRealmstone;
import net.tslat.aoa3.item.misc.Realmstone;
import net.tslat.aoa3.world.teleporter.AoAPortalFrame;
import net.tslat.aoa3.util.BlockUtil;
import net.tslat.aoa3.util.LocaleUtil;

public class CarvedRuneOfPower extends Block {
	public CarvedRuneOfPower() {
		super(new BlockUtil.CompactProperties(Material.STONE, MaterialColor.COLOR_BLACK).stats(3f, 10f).get());
	}

	@Override
	public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if (!world.isClientSide) {
			if (player.getItemInHand(hand).getItem() instanceof Realmstone) {
				Realmstone realmstone = (Realmstone)player.getItemInHand(hand).getItem();
				PortalBlock portalBlock = (PortalBlock)realmstone.getPortalBlock().get();
				AoAPortalFrame.PortalDirection direction = AoAPortalFrame.testFrameForActivation(world, pos, hit.getDirection(), portalBlock);

				if (direction == AoAPortalFrame.PortalDirection.EXISTING) {
					player.sendMessage(LocaleUtil.getLocaleMessage("message.feedback.teleporterFrame.existing"), Util.NIL_UUID);
				}
				else if (direction == AoAPortalFrame.PortalDirection.INVALID) {
					player.sendMessage(LocaleUtil.getLocaleMessage("message.feedback.teleporterFrame.fail"), Util.NIL_UUID);
				}
				else {
					AoAPortalFrame.lightPortalFrame(world, pos, direction, portalBlock);

					if (realmstone.getActivationSound() != null)
						world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), realmstone.getActivationSound().get(), SoundCategory.MASTER, 1.0f, 1.0f);
				}

				return ActionResultType.SUCCESS;
			}
			else if (player.getItemInHand(hand).getItem() instanceof BlankRealmstone) {
				if (world.getBlockState(pos.relative(Direction.UP)).getBlock() instanceof PortalBlock) {
					world.setBlockAndUpdate(pos.relative(Direction.UP), Blocks.AIR.defaultBlockState());

					return ActionResultType.SUCCESS;
				}

				return ActionResultType.FAIL;
			}
			else {
				return ActionResultType.FAIL;
			}
		}

		return ActionResultType.SUCCESS;
	}
}