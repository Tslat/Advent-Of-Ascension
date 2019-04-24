package net.tslat.aoa3.block.functional.utility;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.utils.ItemUtil;

public class CrystalCreator extends Block {
	private Item crystalType;

	public CrystalCreator(String name, String registryName) {
		super(Material.ROCK);
		setUnlocalizedName(name);
		setRegistryName("aoa3:" + registryName);
		setHardness(-1f);
		setResistance(999999999f);
		setSoundType(SoundType.GLASS);
		setCreativeTab(CreativeTabsRegister.functionalBlocksTab);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!world.isRemote && !player.getHeldItem(hand).isEmpty()) {
			ItemStack stack = player.getHeldItem(hand);
			Item crystal = Items.AIR;

			switch (crystalType.getUnlocalizedName()) {
				case "item.BlueCrystal":
					if (stack.getItem() != ItemRegister.gemstonesBlue)
						return false;

					crystal = ItemRegister.crystalBlue;
					break;
				case "item.GreenCrystal":
					if (stack.getItem() != ItemRegister.gemstonesGreen)
						return false;

					crystal = ItemRegister.crystalGreen;
					break;
				case "item.RedCrystal":
					if (stack.getItem() != ItemRegister.gemstonesRed)
						return false;

					crystal = ItemRegister.crystalRed;
					break;
				case "item.PurpleCrystal":
					if (stack.getItem() != ItemRegister.gemstonesPurple)
						return false;

					crystal = ItemRegister.crystalPurple;
					break;
				case "item.WhiteCrystal":
					if (stack.getItem() != ItemRegister.gemstonesWhite)
						return false;

					crystal = ItemRegister.crystalWhite;
					break;
				case "item.YellowCrystal":
					if (stack.getItem() != ItemRegister.gemstonesYellow)
						return false;

					crystal = ItemRegister.crystalYellow;
					break;
			}

			if (!player.capabilities.isCreativeMode)
				stack.shrink(1);

			if (AdventOfAscension.rand.nextInt(8) == 0) {
				ItemUtil.givePlayerItemOrDrop(player, new ItemStack(crystal));
				world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundsRegister.crystalCreatorUse, SoundCategory.BLOCKS, 1.0f, 1.0f);
			}

			return true;
		}

		return true;
	}

	public void setActivationCrystal(Item crystal) {
		if (crystalType == null)
			crystalType = crystal;
	}
}
