package net.tslat.aoa3.content.block.functional.utility;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.BlockHitResult;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.item.AoAArmour;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.util.BlockUtil;
import net.tslat.aoa3.util.ItemUtil;

public class PetalCraftingStation extends Block {
	public PetalCraftingStation() {
		super(new BlockUtil.CompactProperties(Material.STONE, MaterialColor.COLOR_PURPLE).stats(5f, 10f).get());
	}

	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		if (player.getItemInHand(hand).getItem() == AoAItems.PETALS.get()) {
			if (!world.isClientSide()) {
				if (!player.isCreative())
					player.getItemInHand(hand).shrink(1);

				switch (player.getRandom().nextInt(4)) {
					case 0 -> ItemUtil.givePlayerItemOrDrop(player, new ItemStack(AoAArmour.HYDRANGIC_ARMOUR.boots.get()));
					case 1 -> ItemUtil.givePlayerItemOrDrop(player, new ItemStack(AoAArmour.HYDRANGIC_ARMOUR.leggings.get()));
					case 2 -> ItemUtil.givePlayerItemOrDrop(player, new ItemStack(AoAArmour.HYDRANGIC_ARMOUR.chestplate.get()));
					case 3 -> ItemUtil.givePlayerItemOrDrop(player, new ItemStack(AoAArmour.HYDRANGIC_ARMOUR.helmet.get()));
				}

				world.playSound(null, pos, AoASounds.BLOCK_PETAL_CRAFTING_STATION_USE.get(), SoundSource.BLOCKS, 1.0f, 1.0f);
			}

			return InteractionResult.SUCCESS;
		}

		return InteractionResult.PASS;
	}
}
