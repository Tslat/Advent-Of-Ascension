package net.tslat.aoa3.block.functional.utility;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
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
import net.tslat.aoa3.block.BasicNonCubeBlock;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.ItemUtil;

import java.util.Random;

public class TeaSink extends BasicNonCubeBlock {
	public final TeaSink fullState;

	public TeaSink() {
		super("TeaSink", "tea_sink", Material.WOOD, 5.0f, 3.0f);
		setCreativeTab(CreativeTabsRegister.FUNCTIONAL_BLOCKS);
		this.setDefaultState(this.blockState.getBaseState().withProperty(BlockHorizontal.FACING, EnumFacing.NORTH));
		fullState = new TeaSink(true);
	}

	private TeaSink(boolean full) {
		super("TeaSinkFull", "tea_sink_full", Material.WOOD, 5.0f, 3.0f);
		setCreativeTab(CreativeTabsRegister.FUNCTIONAL_BLOCKS);
		this.setDefaultState(this.blockState.getBaseState().withProperty(BlockHorizontal.FACING, EnumFacing.NORTH));
		fullState = this;
	}

	public TeaSink getFullSink() {
		return fullState;
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			if (state.getBlock() == fullState) {
				if (player.getHeldItem(hand).getItem() == ItemRegister.CUP && player.inventory.hasItemStack(new ItemStack(ItemRegister.TEA_SHREDDINGS))) {
					boolean success = false;

					if (ItemUtil.findInventoryItem(player, new ItemStack(ItemRegister.MYSTIC_SHROOMS), true, 1)) {
						ItemUtil.findInventoryItem(player, new ItemStack(ItemRegister.TEA_SHREDDINGS), true, 1);
						ItemUtil.givePlayerItemOrDrop(player, new ItemStack(ItemRegister.FUNGAL_TEA));

						success = true;
					}
					else if (ItemUtil.findInventoryItem(player, new ItemStack(ItemRegister.NATURE_MELON_SLICE), true, 1)) {
						ItemUtil.findInventoryItem(player, new ItemStack(ItemRegister.TEA_SHREDDINGS), true, 1);
						ItemUtil.givePlayerItemOrDrop(player, new ItemStack(ItemRegister.NATURAL_TEA));

						success = true;
					}
					else if (ItemUtil.findInventoryItem(player, new ItemStack(ItemRegister.TEA_SHREDDINGS), true, 1)) {
						ItemUtil.givePlayerItemOrDrop(player, new ItemStack(ItemRegister.TEA));

						success = true;
					}

					if (success) {
						if (!player.capabilities.isCreativeMode)
							player.getHeldItem(hand).shrink(1);

						world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundsRegister.TEA_SINK_USE, SoundCategory.BLOCKS, 1.0f, 1.0f);

						if (AdventOfAscension.rand.nextInt(7) == 0)
							world.setBlockState(pos, BlockRegister.TEA_SINK.getDefaultState().withProperty(BlockHorizontal.FACING, state.getValue(BlockHorizontal.FACING)));

						return true;
					}
				}
			}
			else {
				if (player.getHeldItem(hand).getItem() == Items.WATER_BUCKET) {
					if (!player.capabilities.isCreativeMode) {
						player.getHeldItem(hand).shrink(1);
						ItemUtil.givePlayerItemOrDrop(player, new ItemStack(Items.BUCKET));
					}

					world.setBlockState(pos, fullState.getDefaultState().withProperty(BlockHorizontal.FACING, state.getValue(BlockHorizontal.FACING)));
					world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundsRegister.TEA_SINK_FILL, SoundCategory.BLOCKS, 1.0f, 1.0f);

					return true;
				}
			}
		}

		return true;
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		return getDefaultState().withProperty(BlockHorizontal.FACING, EntityUtil.getDirectionFacing(placer, true));
	}

	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(Item.getItemFromBlock(BlockRegister.TEA_SINK));
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(BlockRegister.TEA_SINK);
	}

	@Override
	public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		return false;
	}

	@Override
	public int quantityDropped(Random random) {
		return 1;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, BlockHorizontal.FACING);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(BlockHorizontal.FACING).getHorizontalIndex();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(BlockHorizontal.FACING, EnumFacing.byHorizontalIndex(meta));
	}
}
