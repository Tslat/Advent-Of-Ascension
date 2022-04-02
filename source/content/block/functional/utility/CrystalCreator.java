package net.tslat.aoa3.content.block.functional.utility;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.BlockHitResult;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.util.BlockUtil;
import net.tslat.aoa3.util.ItemUtil;

import java.util.function.Supplier;

public class CrystalCreator extends Block {
	private final Supplier<Item> gemstone;
	private final Supplier<Item> gemtrap;
	private final Supplier<Item> crystal;

	public CrystalCreator(MaterialColor mapColour, Supplier<Item> gemstone, Supplier<Item> gemtrap, Supplier<Item> crystal) {
		super(new BlockUtil.CompactProperties(Material.STONE, mapColour).stats(10f, 15f).sound(SoundType.GLASS).get());

		this.gemstone = gemstone;
		this.gemtrap = gemtrap;
		this.crystal = crystal;
	}

	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		ItemStack stack = player.getItemInHand(hand);

		if (stack.getItem() == gemstone.get() || stack.getItem() == gemtrap.get()) {
			if (!world.isClientSide()) {
				player.setItemInHand(hand, ItemStack.EMPTY);

				ItemUtil.givePlayerItemOrDrop(player, new ItemStack(crystal.get(), stack.getCount()));
				world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), AoASounds.BLOCK_CRYSTAL_CREATOR_CONVERT.get(), SoundSource.BLOCKS, 1.0f, 1.0f);
			}

			return InteractionResult.SUCCESS;
		}

		return InteractionResult.FAIL;
	}
}
