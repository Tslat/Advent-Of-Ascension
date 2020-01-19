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

			switch (stack.getItem().getTranslationKey().substring(5)) {
				case "Amethyst":
				case "Sapphire":
				case "LimoniteIngot":
				case "Jade":
					tokensItem = ItemRegister.coinCopper;
					break;
				case "BaronyteIngot":
				case "BlaziumIngot":
				case "VarsiumIngot":
					tokensItem = ItemRegister.tokensBaron;
					break;
				case "EmberstoneIngot":
					tokensItem = ItemRegister.tokensNether;
					break;
				case "GhastlyIngot":
				case "GhoulishIngot":
					tokensItem = ItemRegister.tokensGreckon;
					break;
				case "LunarIngot":
					tokensItem = ItemRegister.tokensLunar;
					break;
				case "LyonIngot":
					tokensItem = ItemRegister.tokensIromine;
					break;
				case "MystiteIngot":
					tokensItem = ItemRegister.tokensMysterium;
					break;
				case "Shyregem":
				case "ShyrestoneIngot":
					tokensItem = ItemRegister.tokensShyrelands;
					break;
				case "SkeletalIngot":
					tokensItem = ItemRegister.tokensPrecasian;
					break;
				case "ElecaniumIngot":
					tokensItem = ItemRegister.tokensRunandor;
					break;
				case "Bloodstone":
					tokensItem = ItemRegister.tokensAbyss;
					break;
				case "Crystallite":
					tokensItem = ItemRegister.tokensHaven;
					break;
				case "Gemenyte":
				case "Jewelyte":
				case "Ornamyte":
					tokensItem = ItemRegister.tokensCreeponia;
					break;
				case "BlueCrystal":
				case "GreenCrystal":
				case "PurpleCrystal":
				case "RedCrystal":
				case "WhiteCrystal":
				case "YellowCrystal":
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

				ItemUtil.givePlayerItemOrDrop(player, new ItemStack(tokensItem, 13 + player.getRNG().nextInt(12)));
			}
		}

		return true;
	}
}
