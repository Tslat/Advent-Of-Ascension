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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.util.BlockUtil;
import net.tslat.aoa3.util.ItemUtil;

public class MineralizationStation extends Block {
	public MineralizationStation() {
		super(BlockUtil.generateBlockProperties(Material.ROCK, MaterialColor.BROWN, 5, 10, SoundType.STONE));
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if (!world.isRemote && !player.getHeldItem(hand).isEmpty()) {
			ItemStack stack = player.getHeldItem(hand);
			Item tokensItem = null;
			int baseAmount = 5;

			switch (stack.getItem().getRegistryName().getPath()) {
				case "sapphire":
					baseAmount = 10;
					tokensItem = AoAItems.COPPER_COIN.get();
					break;
				case "amethyst":
					baseAmount = 8;
					tokensItem = AoAItems.COPPER_COIN.get();
					break;
				case "limonite_ingot":
					baseAmount = 5;
					tokensItem = AoAItems.COPPER_COIN.get();
					break;
				case "rosite_ingot":
					baseAmount = 6;
					tokensItem = AoAItems.COPPER_COIN.get();
					break;
				case "jade":
					baseAmount = 7;
					tokensItem = AoAItems.COPPER_COIN.get();
					break;
				case "baronyte_ingot":
					baseAmount = 7;
					tokensItem = AoAItems.BARON_TOKENS.get();
					break;
				case "blazium_ingot":
				case "varsium_ingot":
					baseAmount = 8;
					tokensItem = AoAItems.BARON_TOKENS.get();
					break;
				case "emberstone_ingot":
					baseAmount = 9;
					tokensItem = AoAItems.NETHER_TOKENS.get();
					break;
				case "ghastly_ingot":
				case "ghoulish_ingot":
					baseAmount = 8;
					tokensItem = AoAItems.GRECKON_TOKENS.get();
					break;
				case "lunar_ingot":
					baseAmount = 8;
					tokensItem = AoAItems.LUNAR_TOKENS.get();
					break;
				case "lyon_ingot":
					baseAmount = 8;
					tokensItem = AoAItems.IROMINE_TOKENS.get();
					break;
				case "mystite_ingot":
					baseAmount = 8;
					tokensItem = AoAItems.MYSTERIUM_TOKENS.get();
					break;
				case "shyregem":
				case "shyrestone_ingot":
					baseAmount = 8;
					tokensItem = AoAItems.SHYRELANDS_TOKENS.get();
					break;
				case "skeletal_ingot":
					baseAmount = 7;
					tokensItem = AoAItems.PRECASIAN_TOKENS.get();
					break;
				case "elecanium_ingot":
					baseAmount = 9;
					tokensItem = AoAItems.RUNANDOR_TOKENS.get();
					break;
				case "bloodstone":
					baseAmount = 8;
					tokensItem = AoAItems.ABYSS_TOKENS.get();
					break;
				case "crystallite":
					baseAmount = 8;
					tokensItem = AoAItems.HAVEN_TOKENS.get();
					break;
				case "gemenyte":
				case "jewelyte":
				case "ornamyte":
					baseAmount = 7;
					tokensItem = AoAItems.CREEPONIA_TOKENS.get();
					break;
				case "blue_crystal":
				case "green_crystal":
				case "purple_crystal":
				case "red_crystal":
				case "white_crystal":
				case "yellow_crystal":
					baseAmount = 4;
					tokensItem = AoAItems.CRYSTEVIA_TOKENS.get();
					break;
				case "blank_realmstone":
					player.setHeldItem(hand, new ItemStack(AoAItems.IROMINE_REALMSTONE.get()));
					player.container.detectAndSendChanges();
					return ActionResultType.SUCCESS;
				default:
					break;
			}

			if (tokensItem != null) {
				if (!player.isCreative())
					stack.shrink(1);

				ItemUtil.givePlayerItemOrDrop(player, new ItemStack(tokensItem, baseAmount + player.getRNG().nextInt(baseAmount)));
			}
		}

		return ActionResultType.SUCCESS;
	}
}
