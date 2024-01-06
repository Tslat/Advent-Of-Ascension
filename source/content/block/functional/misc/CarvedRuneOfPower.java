package net.tslat.aoa3.content.block.functional.misc;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.block.functional.portal.PortalBlock;
import net.tslat.aoa3.content.item.misc.BlankRealmstone;
import net.tslat.aoa3.content.item.misc.Realmstone;
import net.tslat.aoa3.content.world.teleporter.AoAPortalFrame;
import net.tslat.aoa3.util.LocaleUtil;
import org.jetbrains.annotations.Nullable;


public class CarvedRuneOfPower extends Block {
	public CarvedRuneOfPower(BlockBehaviour.Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		ItemStack heldItem = player.getItemInHand(hand);

		if (heldItem.getItem() instanceof Realmstone)
			return fillPortal(world, pos, hit.getDirection(), heldItem, player) ? InteractionResult.SUCCESS : InteractionResult.FAIL;

		if (heldItem.getItem() instanceof BlankRealmstone)
			return clearPortal(world, pos, hit.getDirection(), heldItem, player) ? InteractionResult.SUCCESS : InteractionResult.FAIL;

		return InteractionResult.PASS;
	}

	public static boolean fillPortal(Level level, BlockPos pos, Direction direction, ItemStack stack, @Nullable Player player) {
		Realmstone realmstone = (Realmstone)stack.getItem();

		if (realmstone.getPortalBlock() == null) {
			player.sendSystemMessage(LocaleUtil.getLocaleMessage(LocaleUtil.createFeedbackLocaleKey("portal.tba")));

			return false;
		}

		PortalBlock portalBlock = (PortalBlock)realmstone.getPortalBlock().get();
		AoAPortalFrame.PortalDirection facing = AoAPortalFrame.testFrameForActivation(level, pos, direction, portalBlock);

		if (facing == AoAPortalFrame.PortalDirection.EXISTING) {
			if (player instanceof ServerPlayer)
				player.sendSystemMessage(LocaleUtil.getLocaleMessage(LocaleUtil.createFeedbackLocaleKey("teleporterFrame.existing")));

			return false;
		}

		if (facing == AoAPortalFrame.PortalDirection.INVALID) {
			if (player instanceof ServerPlayer)
				player.sendSystemMessage(LocaleUtil.getLocaleMessage(LocaleUtil.createFeedbackLocaleKey("teleporterFrame.fail")));

			return false;
		}

		if (level instanceof ServerLevel serverLevel) {
			AoAPortalFrame.lightPortalFrame(serverLevel, pos, facing, portalBlock);
			level.playSound(null, pos.getX(), pos.getY() + 1, pos.getZ(), AoASounds.PORTAL_ACTIVATE.get(), SoundSource.AMBIENT, 1, 1);
		}

		return true;
	}

	public static boolean clearPortal(Level world, BlockPos pos, Direction direction, ItemStack stack, @Nullable Player player) {
		if (world.getBlockState(pos.relative(Direction.UP)).getBlock() instanceof PortalBlock) {
			world.setBlockAndUpdate(pos.relative(Direction.UP), Blocks.AIR.defaultBlockState());

			return true;
		}

		return false;
	}
}