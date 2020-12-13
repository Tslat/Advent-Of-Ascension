package net.tslat.aoa3.block.functional.utility;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.util.BlockUtil;
import net.tslat.aoa3.util.ItemUtil;

import java.util.function.Supplier;

public class CrystalCreator extends Block {
	private final Supplier<Item> gemstone;
	private final Supplier<Item> crystal;

	public CrystalCreator(MaterialColor mapColour, Supplier<Item> gemstone, Supplier<Item> crystal) {
		super(BlockUtil.generateBlockProperties(Material.ROCK, mapColour, BlockUtil.UNBREAKABLE_HARDNESS, BlockUtil.UNBREAKABLE_RESISTANCE, SoundType.GLASS));

		this.gemstone = gemstone;
		this.crystal = crystal;
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		ItemStack stack = player.getHeldItem(hand);

		if (stack.getItem() == gemstone.get()) {
			if (!world.isRemote()) {
				player.setHeldItem(hand, ItemStack.EMPTY);

				ItemUtil.givePlayerItemOrDrop(player, new ItemStack(crystal.get(), stack.getCount()));
				world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), AoASounds.BLOCK_CRYSTAL_CREATOR_CONVERT.get(), SoundCategory.BLOCKS, 1.0f, 1.0f);
			}

			return ActionResultType.SUCCESS;
		}

		return ActionResultType.FAIL;
	}
}
