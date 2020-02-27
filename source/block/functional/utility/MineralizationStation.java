package net.tslat.aoa3.block.functional.utility;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.utils.ItemUtil;

public class MineralizationStation extends Block {
	public MineralizationStation() {
		super(Material.ROCK);
		setTranslationKey("MineralizationStation");
		setRegistryName("aoa3:mineralization_station");
		setHardness(5.0f);
		setResistance(10.0f);
		setSoundType(SoundType.STONE);
		setCreativeTab(CreativeTabsRegister.functionalBlocksTab);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!world.isRemote && !player.getHeldItem(hand).isEmpty()) {
			ItemStack stack = player.getHeldItem(hand);
			Item tokensItem = null;
			int baseAmount = 5;

			switch (stack.getItem().getTranslationKey().substring(5)) {
				case "Sapphire":
					baseAmount = 10;
					tokensItem = ItemRegister.coinCopper;
					break;
				case "Amethyst":
					baseAmount = 8;
					tokensItem = ItemRegister.coinCopper;
					break;
				case "LimoniteIngot":
					baseAmount = 5;
					tokensItem = ItemRegister.coinCopper;
					break;
				case "RositeIngot":
					baseAmount = 6;
					tokensItem = ItemRegister.coinCopper;
					break;
				case "Jade":
					baseAmount = 7;
					tokensItem = ItemRegister.coinCopper;
					break;
				case "BaronyteIngot":
					baseAmount = 7;
					tokensItem = ItemRegister.tokensBaron;
					break;
				case "BlaziumIngot":
				case "VarsiumIngot":
					baseAmount = 8;
					tokensItem = ItemRegister.tokensBaron;
					break;
				case "EmberstoneIngot":
					baseAmount = 9;
					tokensItem = ItemRegister.tokensNether;
					break;
				case "GhastlyIngot":
				case "GhoulishIngot":
					baseAmount = 8;
					tokensItem = ItemRegister.tokensGreckon;
					break;
				case "LunarIngot":
					baseAmount = 8;
					tokensItem = ItemRegister.tokensLunar;
					break;
				case "LyonIngot":
					baseAmount = 8;
					tokensItem = ItemRegister.tokensIromine;
					break;
				case "MystiteIngot":
					baseAmount = 8;
					tokensItem = ItemRegister.tokensMysterium;
					break;
				case "Shyregem":
				case "ShyrestoneIngot":
					baseAmount = 8;
					tokensItem = ItemRegister.tokensShyrelands;
					break;
				case "SkeletalIngot":
					baseAmount = 7;
					tokensItem = ItemRegister.tokensPrecasian;
					break;
				case "ElecaniumIngot":
					baseAmount = 9;
					tokensItem = ItemRegister.tokensRunandor;
					break;
				case "Bloodstone":
					baseAmount = 8;
					tokensItem = ItemRegister.tokensAbyss;
					break;
				case "Crystallite":
					baseAmount = 8;
					tokensItem = ItemRegister.tokensHaven;
					break;
				case "Gemenyte":
				case "Jewelyte":
				case "Ornamyte":
					baseAmount = 7;
					tokensItem = ItemRegister.tokensCreeponia;
					break;
				case "BlueCrystal":
				case "GreenCrystal":
				case "PurpleCrystal":
				case "RedCrystal":
				case "WhiteCrystal":
				case "YellowCrystal":
					baseAmount = 4;
					tokensItem = ItemRegister.tokensCrystevia;
					break;
				case "BlankRealmstone":
					player.setHeldItem(hand, new ItemStack(ItemRegister.realmstoneIromine));
					player.inventoryContainer.detectAndSendChanges();
					return true;
				default:
					break;
			}

			if (tokensItem != null) {
				if (!player.capabilities.isCreativeMode)
					stack.shrink(1);

				ItemUtil.givePlayerItemOrDrop(player, new ItemStack(tokensItem, baseAmount + player.getRNG().nextInt(baseAmount)));
			}
		}

		return true;
	}
}
