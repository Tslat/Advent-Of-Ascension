package net.tslat.aoa3.content.block.functional.utility;

import net.minecraft.core.BlockPos;
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
import net.neoforged.neoforge.common.Tags;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.RegistryUtil;
import net.tslat.smartbrainlib.util.RandomUtil;

public class MineralizationStation extends Block {
	public MineralizationStation(BlockBehaviour.Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		if (!world.isClientSide && !player.getItemInHand(hand).isEmpty()) {
			ItemStack stack = player.getItemInHand(hand);
			Item tokensItem = null;
			int baseAmount = 5;

			switch (RegistryUtil.getId(stack.getItem()).getPath()) {
				case "blue_crystal", "green_crystal", "purple_crystal", "red_crystal", "white_crystal", "yellow_crystal" -> {
					baseAmount = 4;
					tokensItem = AoAItems.COPPER_COIN.get();
				}
				case "limonite_ingot" -> {
					baseAmount = 5;
					tokensItem = AoAItems.COPPER_COIN.get();
				}
				case "rosite_ingot", "jade", "amethyst" -> {
					baseAmount = 6;
					tokensItem = AoAItems.COPPER_COIN.get();
				}
				case "sapphire", "gemenyte", "jewelyte", "ornamyte", "skeletal_ingot", "blazium_ingot", "varsium_ingot", "baronyte_ingot", "emberstone_ingot" -> {
					baseAmount = 7;
					tokensItem = AoAItems.COPPER_COIN.get();
				}
				case "crystallite", "mystite_ingot", "lyon_ingot" -> {
					baseAmount = 9;
					tokensItem = AoAItems.COPPER_COIN.get();
				}
				case "bloodstone", "elecanium_ingot", "ghastly_ingot", "ghoulish_ingot", "lunar_ingot" -> {
					baseAmount = 10;
					tokensItem = AoAItems.COPPER_COIN.get();
				}
				case "shyregem", "shyrestone_ingot" -> {
					baseAmount = 11;
					tokensItem = AoAItems.COPPER_COIN.get();
				}
				case "blank_realmstone" -> {
					player.setItemInHand(hand, new ItemStack(AoAItems.IROMINE_REALMSTONE.get()));
					player.inventoryMenu.broadcastChanges();
					return InteractionResult.SUCCESS;
				}
				default -> {
					if (stack.is(Tags.Items.INGOTS) || stack.is(Tags.Items.GEMS)) {
						baseAmount = RandomUtil.randomNumberBetween(3, 6);
						tokensItem = AoAItems.COPPER_COIN.get();
					}
				}
			}

			if (tokensItem != null) {
				if (!player.isCreative())
					stack.shrink(1);

				ItemUtil.givePlayerItemOrDrop(player, new ItemStack(tokensItem, baseAmount + player.getRandom().nextInt(baseAmount)));
			}
		}

		return InteractionResult.SUCCESS;
	}
}
