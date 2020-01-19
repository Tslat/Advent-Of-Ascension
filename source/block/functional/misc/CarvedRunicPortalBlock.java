package net.tslat.aoa3.block.functional.misc;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.block.functional.portal.PortalBlock;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.item.misc.BlankRealmstone;
import net.tslat.aoa3.item.misc.Realmstone;
import net.tslat.aoa3.library.misc.AoAPortalFrame;
import net.tslat.aoa3.utils.StringUtil;

public class CarvedRunicPortalBlock extends CarvedRunicBlock {
	public CarvedRunicPortalBlock(String name, String registryName) {
		super(name, registryName);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (state.getBlock() == BlockRegister.carvedRunePower) {
			if (!world.isRemote) {
				if (player.getHeldItem(hand).getItem() instanceof Realmstone) {
					Realmstone realmstone = (Realmstone)player.getHeldItem(hand).getItem();
					PortalBlock portalBlock = realmstone.getPortalBlock();
					AoAPortalFrame.PortalDirection direction = AoAPortalFrame.testFrameForActivation(world, pos, facing, portalBlock);

					if (direction == AoAPortalFrame.PortalDirection.EXISTING) {
						player.sendMessage(StringUtil.getLocale("message.feedback.teleporterFrame.existing"));
					}
					else if (direction == AoAPortalFrame.PortalDirection.INVALID) {
						player.sendMessage(StringUtil.getLocale("message.feedback.teleporterFrame.fail"));
					}
					else {
						AoAPortalFrame.lightPortalFrame(world, pos, direction, portalBlock);
						player.sendMessage(StringUtil.getLocale("message.feedback.teleporterFrame." + realmstone.getMsgSuffix()));
						world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), realmstone.getPortalSound(), SoundCategory.MASTER, 1.0f, 1.0f);
					}
				}
				else if (player.getHeldItem(hand).getItem() instanceof BlankRealmstone) {
					if (world.getBlockState(pos.offset(EnumFacing.UP)).getBlock() instanceof PortalBlock)
						world.setBlockToAir(pos.offset(EnumFacing.UP));
				}
			}

			return true;
		}

		return false;
	}
}