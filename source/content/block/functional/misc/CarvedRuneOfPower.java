package net.tslat.aoa3.content.block.functional.misc;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.BlockHitResult;
import net.tslat.aoa3.content.block.functional.portal.PortalBlock;
import net.tslat.aoa3.content.item.misc.BlankRealmstone;
import net.tslat.aoa3.content.item.misc.Realmstone;
import net.tslat.aoa3.content.world.teleporter.AoAPortalFrame;
import net.tslat.aoa3.util.BlockUtil;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;

public class CarvedRuneOfPower extends Block {
	public CarvedRuneOfPower() {
		super(new BlockUtil.CompactProperties(Material.STONE, MaterialColor.COLOR_BLACK).stats(3f, 10f).get());
	}

	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		if (!world.isClientSide)
			return testAndActivate(world, pos, hit.getDirection(), player.getItemInHand(hand).getItem(), player) ? InteractionResult.SUCCESS : InteractionResult.FAIL;

		return InteractionResult.SUCCESS;
	}

	public static boolean testAndActivate(Level world, BlockPos pos, Direction direction, Item item, @Nullable Player player) {
		if (item instanceof Realmstone realmstone) {
			PortalBlock portalBlock = (PortalBlock)realmstone.getPortalBlock().get();
			AoAPortalFrame.PortalDirection facing = AoAPortalFrame.testFrameForActivation(world, pos, direction, portalBlock);

			if (facing == AoAPortalFrame.PortalDirection.EXISTING) {
				if (player != null)
					player.sendSystemMessage(LocaleUtil.getLocaleMessage("message.feedback.teleporterFrame.existing"));
			}
			else if (facing == AoAPortalFrame.PortalDirection.INVALID) {
				if (player != null)
					player.sendSystemMessage(LocaleUtil.getLocaleMessage("message.feedback.teleporterFrame.fail"));
			}
			else {
				AoAPortalFrame.lightPortalFrame(world, pos, facing, portalBlock);

				if (realmstone.getActivationSound() != null)
					world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), realmstone.getActivationSound().get(), SoundSource.MASTER, 1.0f, 1.0f);
			}

			return true;
		}
		else if (item instanceof BlankRealmstone) {
			if (world.getBlockState(pos.relative(Direction.UP)).getBlock() instanceof PortalBlock) {
				world.setBlockAndUpdate(pos.relative(Direction.UP), Blocks.AIR.defaultBlockState());

				return true;
			}

			return false;
		}
		else {
			return false;
		}
	}
}