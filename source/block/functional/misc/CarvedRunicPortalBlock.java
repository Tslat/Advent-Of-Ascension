package net.tslat.aoa3.block.functional.misc;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.block.functional.portal.PortalBlock;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.item.misc.Realmstone;
import net.tslat.aoa3.library.AoAPortalFrame;
import net.tslat.aoa3.utils.StringUtil;

public class CarvedRunicPortalBlock extends CarvedRunicBlock {
	public CarvedRunicPortalBlock(String name, String registryName) {
		super(name, registryName);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (state.getBlock() == BlockRegister.carvedRunePower) {
			if (!world.isRemote && player.getHeldItem(hand).getItem() instanceof Realmstone) {
				ItemStack realmstoneStack = player.getHeldItem(hand);
				PortalBlock portalBlock = ((Realmstone)realmstoneStack.getItem()).getPortalBlock();
				AoAPortalFrame.PortalDirection direction = AoAPortalFrame.testFrameForActivation(world, pos, facing, portalBlock);

				if (direction == AoAPortalFrame.PortalDirection.EXISTING) {
					player.sendMessage(StringUtil.getLocale("message.feedback.teleporterFrame.existing"));
				}
				else if (direction == AoAPortalFrame.PortalDirection.INVALID) {
					player.sendMessage(StringUtil.getLocale("message.feedback.teleporterFrame.fail"));
				}
				else {
					AoAPortalFrame.lightPortalFrame(world, pos, direction, portalBlock);
					player.sendMessage(StringUtil.getLocale("message.feedback.teleporterFrame." + ((Realmstone)realmstoneStack.getItem()).getMsgSuffix()));
					world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), ((Realmstone)realmstoneStack.getItem()).getPortalSound(), SoundCategory.MASTER, 1.0f, 1.0f);

					if (!player.capabilities.isCreativeMode)
						player.getHeldItem(hand).shrink(1);
				}
			}

			return true;
		}

		return false;
	}
}