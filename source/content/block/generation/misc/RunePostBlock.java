package net.tslat.aoa3.content.block.generation.misc;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
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
import net.tslat.aoa3.util.LocaleUtil;

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
	protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
		if (stack.getItem() instanceof RuneSource) {
			if (level.getBlockState(pos.above()).blocksMotion()) {
				if (!level.isClientSide())
					player.sendSystemMessage(Component.translatable(LocaleUtil.createFeedbackLocaleKey("runeShrine.blocked")));

				return ItemInteractionResult.SKIP_DEFAULT_BLOCK_INTERACTION;
			}

			if (!level.isClientSide()) {
				AoAScheduler.schedule(1, new RuneCreationTask((ServerLevel)level, pos, getRune(), stack.getCount() * ((RuneSource)stack.getItem()).getRuneGenFactor(), player));

				if (!player.isCreative())
					stack.shrink(stack.getCount());
			}

			return ItemInteractionResult.sidedSuccess(level.isClientSide());
		}

		return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
	}
}
