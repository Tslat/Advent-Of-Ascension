package net.tslat.aoa3.block.generation.plants;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PlantStackable extends Block implements IShearable {
	protected PlantStackable hatBlock;
	protected PlantStackable stemBlock;
	protected final List<Material> growthMaterials;

	public PlantStackable(String name, String registryName, Material material, float hardness, Material... growthMaterial) {
		super(material);
		setTranslationKey(name);
		setHardness(hardness);
		setSoundType(SoundType.PLANT);
		setRegistryName("aoa3:" + registryName);
		this.hatBlock = this;
		this.stemBlock = this;
		this.growthMaterials = Arrays.asList(growthMaterial);
		setTickRandomly(true);
		setCreativeTab(CreativeTabsRegister.GENERATION_BLOCKS);
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		if (!world.isRemote) {
			if (!canPlaceBlockAt(world, pos)) {
				if (quantityDropped(rand) > 0)
					dropBlockAsItem(world, pos, state, 0);

				world.setBlockToAir(pos);
			}
		}
	}

	public PlantStackable(String name, String registryName, Material... growthMaterials) {
		this(name, registryName, Material.PLANTS, 0.1f, growthMaterials);
	}

	public PlantStackable setStemBlock(PlantStackable block) {
		this.stemBlock = block;
		block.hatBlock = this;

		return this;
	}

	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos) {
		IBlockState targetState = world.getBlockState(pos.down());

		return ((growthMaterials.isEmpty() || growthMaterials.contains(targetState.getMaterial())) && targetState.isOpaqueCube()) || targetState.getBlock() == stemBlock;
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		IBlockState blockState = world.getBlockState(pos = pos.up());
		Block block = blockState.getBlock();

		while (block == stemBlock || block == hatBlock) {
			if (block.quantityDropped(RANDOM) > 0)
				block.dropBlockAsItem(world, pos, blockState, 0);

			world.setBlockToAir(pos);
			blockState = world.getBlockState(pos = pos.up());
			block = blockState.getBlock();
		}
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return FULL_BLOCK_AABB;
	}

	@Nullable
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	}

	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos) {
		if (!canPlaceBlockAt(world, pos)) {
			if (quantityDropped(RANDOM) > 0)
				dropBlockAsItem(world, pos, state, 0);

			world.setBlockToAir(pos);
		}
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public int quantityDropped(Random random) {
		return 0;
	}

	@Override
	public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos) {
		return false;
	}

	@Override
	public boolean isShearable(@Nonnull ItemStack item, IBlockAccess world, BlockPos pos) {
		return true;
	}

	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess p_193383_1_, IBlockState p_193383_2_, BlockPos p_193383_3_, EnumFacing p_193383_4_) {
		return BlockFaceShape.UNDEFINED;
	}

	@Nonnull
	@Override
	public List<ItemStack> onSheared(@Nonnull ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
		if (world instanceof World) {
			breakBlock((World)world, pos, world.getBlockState(pos));
			ArrayList<ItemStack> drops = new ArrayList<ItemStack>();

			drops.add(new ItemStack(Item.getItemFromBlock(this)));

			return drops;
		}

		return new ArrayList<ItemStack>();
	}
}
