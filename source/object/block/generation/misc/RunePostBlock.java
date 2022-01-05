package net.tslat.aoa3.object.block.generation.misc;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.tslat.aoa3.object.item.misc.RuneSource;
import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.aoa3.scheduling.sync.RuneCreationTask;
import net.tslat.aoa3.util.BlockUtil;

import java.util.function.Supplier;

public class RunePostBlock extends Block {
	private Supplier<Item> rune;

	public RunePostBlock(Supplier<Item> runeItem) {
		super(new BlockUtil.CompactProperties(Material.STONE, MaterialColor.COLOR_BLACK).stats(10f, 15f).get());

		this.rune = runeItem;
	}

	public Item getRune() {
		return rune.get();
	}

	@Override
	public ActionResultType use(BlockState state, World level, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		ItemStack heldItem = player.getItemInHand(hand);

		if (heldItem.getItem() instanceof RuneSource) {
			if (level.getBlockState(pos.above()).getMaterial().blocksMotion()) {
				if (!level.isClientSide())
					player.sendMessage(new TranslationTextComponent("message.feedback.runeShrine.blocked"), Util.NIL_UUID);

				return ActionResultType.FAIL;
			}

			if (!level.isClientSide()) {
				AoAScheduler.scheduleSyncronisedTask(new RuneCreationTask((ServerWorld)level, pos, getRune(), heldItem.getCount() * ((RuneSource)heldItem.getItem()).getRuneGenFactor(), player.getUUID()), 1);

				if (!player.isCreative())
					heldItem.shrink(heldItem.getCount());
			}

			return ActionResultType.SUCCESS;
		}

		return ActionResultType.PASS;
	}
}
