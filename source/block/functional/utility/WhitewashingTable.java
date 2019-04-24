package net.tslat.aoa3.block.functional.utility;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.utils.ItemUtil;

public class WhitewashingTable extends Block {
	public WhitewashingTable() {
		super(Material.ROCK);
		setUnlocalizedName("WhitewashingTable");
		setRegistryName("aoa3:whitewashing_table");
		setHardness(5.0f);
		setResistance(10.0f);
		setSoundType(SoundType.STONE);
		setCreativeTab(CreativeTabsRegister.functionalBlocksTab);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!world.isRemote && player.getHeldItem(hand).getItem() == Item.getItemFromBlock(Blocks.STONEBRICK)) {
			if (ItemUtil.consumeItem(player, new ItemStack(ItemRegister.whitewashingSolution))) {
				if (!player.capabilities.isCreativeMode)
					player.getHeldItem(hand).shrink(1);

				ItemUtil.givePlayerItemOrDrop(player, new ItemStack(BlockRegister.bricksWhitewash));
				world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundsRegister.whitewashUse, SoundCategory.BLOCKS, 1.0f, 1.0f);

				return true;
			}
		}

		return true;
	}
}
