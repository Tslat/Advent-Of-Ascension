package net.tslat.aoa3.content.block.generation.misc;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.tslat.aoa3.content.item.misc.RuneSource;
import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.aoa3.scheduling.sync.RuneCreationTask;

import java.util.function.Supplier;

public class RunePostBlock extends Block {
	private final Supplier<Item> rune;

	public RunePostBlock(BlockBehaviour.Properties properties, Supplier<Item> runeItem) {
		super(properties);

		this.rune = runeItem;
	}

	public Item getRune() {
		return rune.get();
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		ItemStack heldItem = player.getItemInHand(hand);

		if (heldItem.getItem() instanceof RuneSource) {
			if (level.getBlockState(pos.above()).blocksMotion()) {
				if (!level.isClientSide())
					player.sendSystemMessage(Component.translatable("message.feedback.runeShrine.blocked"));

				return InteractionResult.FAIL;
			}

			if (!level.isClientSide()) {
				AoAScheduler.scheduleSyncronisedTask(new RuneCreationTask((ServerLevel)level, pos, getRune(), heldItem.getCount() * ((RuneSource)heldItem.getItem()).getRuneGenFactor(), player.getUUID()), 1);

				if (!player.isCreative())
					heldItem.shrink(heldItem.getCount());
			}

			return InteractionResult.SUCCESS;
		}

		return InteractionResult.PASS;
	}
}
