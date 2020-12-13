package net.tslat.aoa3.block.functional.misc;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.tslat.aoa3.block.functional.portal.PortalBlock;
import net.tslat.aoa3.item.misc.BlankRealmstone;
import net.tslat.aoa3.item.misc.Realmstone;
import net.tslat.aoa3.library.misc.AoAPortalFrame;
import net.tslat.aoa3.util.BlockUtil;
import net.tslat.aoa3.util.LocaleUtil;

public class CarvedRuneOfPower extends Block {
	public CarvedRuneOfPower() {
		super(BlockUtil.generateBlockProperties(Material.ROCK, MaterialColor.BLACK, 3f, 10f, SoundType.STONE));
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if (!world.isRemote) {
			if (player.getHeldItem(hand).getItem() instanceof Realmstone) {
				Realmstone realmstone = (Realmstone)player.getHeldItem(hand).getItem();
				PortalBlock portalBlock = (PortalBlock)realmstone.getPortalBlock().get();
				AoAPortalFrame.PortalDirection direction = AoAPortalFrame.testFrameForActivation(world, pos, hit.getFace(), portalBlock);

				if (direction == AoAPortalFrame.PortalDirection.EXISTING) {
					player.sendMessage(LocaleUtil.getLocaleMessage("message.feedback.teleporterFrame.existing"));
				}
				else if (direction == AoAPortalFrame.PortalDirection.INVALID) {
					player.sendMessage(LocaleUtil.getLocaleMessage("message.feedback.teleporterFrame.fail"));
				}
				else {
					AoAPortalFrame.lightPortalFrame(world, pos, direction, portalBlock);
					player.sendMessage(LocaleUtil.getLocaleMessage("message.feedback.teleporterFrame." + realmstone.getPortalMessageSuffix()));

					if (realmstone.getActivationSound() != null)
						world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), realmstone.getActivationSound().get(), SoundCategory.MASTER, 1.0f, 1.0f);
				}

				return ActionResultType.SUCCESS;
			}
			else if (player.getHeldItem(hand).getItem() instanceof BlankRealmstone) {
				if (world.getBlockState(pos.offset(Direction.UP)).getBlock() instanceof PortalBlock) {
					world.setBlockState(pos.offset(Direction.UP), Blocks.AIR.getDefaultState());

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