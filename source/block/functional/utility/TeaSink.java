package net.tslat.aoa3.block.functional.utility;

import net.minecraft.block.Block;
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
import net.minecraftforge.registries.IForgeRegistry;
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
		setCreativeTab(CreativeTabsRegister.functionalBlocksTab);
		this.setDefaultState(this.blockState.getBaseState().withProperty(BlockHorizontal.FACING, EnumFacing.NORTH));
		fullState = new TeaSink(true);
	}

	private TeaSink(boolean full) {
		super("TeaSinkFull", "tea_sink_full", Material.WOOD, 5.0f, 3.0f);
		setCreativeTab(CreativeTabsRegister.functionalBlocksTab);
		this.setDefaultState(this.blockState.getBaseState().withProperty(BlockHorizontal.FACING, EnumFacing.NORTH));
		fullState = this;
	}

	public TeaSink registerFullSink(IForgeRegistry<Block> registry) {
		registry.register(fullState);

		return this;
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			if (state.getBlock() == fullState) {
				if (player.getHeldItem(hand).getItem() == ItemRegister.cup && player.inventory.hasItemStack(new ItemStack(ItemRegister.teaShreddings))) {
					boolean success = false;

					if (ItemUtil.consumeItem(player, new ItemStack(ItemRegister.mysticShrooms))) {
						ItemUtil.consumeItem(player, new ItemStack(ItemRegister.teaShreddings));
						ItemUtil.givePlayerItemOrDrop(player, new ItemStack(ItemRegister.fungalTea));

						success = true;
					}
					else if (ItemUtil.consumeItem(player, new ItemStack(ItemRegister.natureMelonSlice))) {
						ItemUtil.consumeItem(player, new ItemStack(ItemRegister.teaShreddings));
						ItemUtil.givePlayerItemOrDrop(player, new ItemStack(ItemRegister.naturalTea));

						success = true;
					}
					else if (ItemUtil.consumeItem(player, new ItemStack(ItemRegister.teaShreddings))) {
						ItemUtil.givePlayerItemOrDrop(player, new ItemStack(ItemRegister.tea));

						success = true;
					}

					if (success) {
						if (!player.capabilities.isCreativeMode)
							player.getHeldItem(hand).shrink(1);

						world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundsRegister.teaSinkUse, SoundCategory.BLOCKS, 1.0f, 1.0f);

						if (AdventOfAscension.rand.nextInt(7) == 0)
							world.setBlockState(pos, BlockRegister.teaSink.getDefaultState().withProperty(BlockHorizontal.FACING, state.getValue(BlockHorizontal.FACING)));

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
					world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundsRegister.teaSinkFill, SoundCategory.BLOCKS, 1.0f, 1.0f);

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
		return new ItemStack(Item.getItemFromBlock(BlockRegister.teaSink));
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
		return this.getDefaultState().withProperty(BlockHorizontal.FACING, EnumFacing.getHorizontal(meta));
	}
}
