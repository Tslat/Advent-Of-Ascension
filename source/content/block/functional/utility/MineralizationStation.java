package net.tslat.aoa3.content.block.functional.utility;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.util.BlockUtil;
import net.tslat.aoa3.util.ItemUtil;

public class MineralizationStation extends Block {
	public MineralizationStation() {
		super(new BlockUtil.CompactProperties(Material.STONE, MaterialColor.COLOR_BROWN).stats(5f, 10f).get());
	}

	@Override
	public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if (!world.isClientSide && !player.getItemInHand(hand).isEmpty()) {
			ItemStack stack = player.getItemInHand(hand);
			Item tokensItem = null;
			int baseAmount = 5;

			switch (stack.getItem().getRegistryName().getPath()) {
				case "blue_crystal":
				case "green_crystal":
				case "purple_crystal":
				case "red_crystal":
				case "white_crystal":
				case "yellow_crystal":
					baseAmount = 4;
					tokensItem = AoAItems.COPPER_COIN.get();
					break;
				case "limonite_ingot":
					baseAmount = 5;
					tokensItem = AoAItems.COPPER_COIN.get();
					break;
				case "rosite_ingot":
				case "jade":
				case "amethyst":
					baseAmount = 6;
					tokensItem = AoAItems.COPPER_COIN.get();
					break;
				case "sapphire":
				case "gemenyte":
				case "jewelyte":
				case "ornamyte":
				case "skeletal_ingot":
				case "blazium_ingot":
				case "varsium_ingot":
				case "baronyte_ingot":
				case "emberstone_ingot":
					baseAmount = 7;
					tokensItem = AoAItems.COPPER_COIN.get();
					break;
				case "crystallite":
				case "mystite_ingot":
				case "lyon_ingot":
					baseAmount = 9;
					tokensItem = AoAItems.COPPER_COIN.get();
					break;
				case "bloodstone":
				case "elecanium_ingot":
				case "ghastly_ingot":
				case "ghoulish_ingot":
				case "lunar_ingot":
					baseAmount = 10;
					tokensItem = AoAItems.COPPER_COIN.get();
					break;
				case "shyregem":
				case "shyrestone_ingot":
					baseAmount = 11;
					tokensItem = AoAItems.COPPER_COIN.get();
					break;
				case "blank_realmstone":
					player.setItemInHand(hand, new ItemStack(AoAItems.IROMINE_REALMSTONE.get()));
					player.inventoryMenu.broadcastChanges();
					return ActionResultType.SUCCESS;
				default:
					break;
			}

			if (tokensItem != null) {
				if (!player.isCreative())
					stack.shrink(1);

				ItemUtil.givePlayerItemOrDrop(player, new ItemStack(tokensItem, baseAmount + player.getRandom().nextInt(baseAmount)));
			}
		}

		return ActionResultType.SUCCESS;
	}
}
