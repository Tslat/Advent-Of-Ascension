package net.tslat.aoa3.block.generation.plants;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.IShearable;
import net.tslat.aoa3.util.BlockUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class StackablePlant extends Block implements IShearable, IPlantable {
	protected Supplier<Block> hatBlock;
	protected Supplier<Block> stemBlock;
	protected final List<Material> growthMaterials;

	public StackablePlant(Block.Properties properties, Material... growthMaterial) {
		super(properties);

		this.hatBlock = () -> this;
		this.stemBlock = () -> this;
		this.growthMaterials = Arrays.asList(growthMaterial);
	}

	public StackablePlant(Material material, MaterialColor mapColour, SoundType sound, Material... growthMaterial) {
		super(BlockUtil.generateBlockProperties(material, mapColour, 0, 0, sound).doesNotBlockMovement());
		this.hatBlock = () -> this;
		this.stemBlock = () -> this;
		this.growthMaterials = Arrays.asList(growthMaterial);
	}

	public StackablePlant(MaterialColor mapColour, Material... growthMaterials) {
		this(Material.PLANTS, mapColour, SoundType.PLANT, growthMaterials);
	}

	public StackablePlant setStemBlock(Supplier<Block> block) {
		this.stemBlock = block;

		return this;
	}

	public StackablePlant setHatBlock(Supplier<Block> block) {
		this.hatBlock = block;

		return this;
	}

	@Override
	public void harvestBlock(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack) {
		super.harvestBlock(worldIn, player, pos, Blocks.AIR.getDefaultState(), te, stack);
	}

	@Override
	public void onBlockHarvested(World world, BlockPos pos, BlockState state, PlayerEntity player) {
		BlockPos newPos;
		BlockState blockState = world.getBlockState(newPos = pos.up());
		Block block = blockState.getBlock();

		while (block == stemBlock.get() || block == hatBlock.get()) {
			world.setBlockState(newPos, Blocks.AIR.getDefaultState(), 35);
			world.playEvent(player, 2001, newPos, Block.getStateId(blockState));

			if (!world.isRemote() && !player.isCreative()) {
				spawnDrops(state, world, pos, null, player, player.getHeldItemMainhand());
				spawnDrops(blockState, world, newPos, null, player, player.getHeldItemMainhand());
			}

			blockState = world.getBlockState(newPos = newPos.up());
			block = blockState.getBlock();
		}

		super.onBlockHarvested(world, pos, state, player);
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos) {
		BlockState targetState = world.getBlockState(pos.down());

		return ((growthMaterials.isEmpty() || growthMaterials.contains(targetState.getMaterial())) && targetState.isOpaqueCube(world, pos.down())) || targetState.getBlock() == stemBlock.get();
	}

	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos pos, BlockPos facingPos) {
		return !state.isValidPosition(world, pos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(state, facing, facingState, world, pos, facingPos);
	}

	@Override
	public boolean isReplaceable(BlockState state, BlockItemUseContext useContext) {
		return false;
	}

	@Override
	public boolean isShearable(@Nonnull ItemStack item, IWorldReader world, BlockPos pos) {
		return true;
	}

	@Override
	public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
		return type == PathType.AIR && !blocksMovement || super.allowsMovement(state, worldIn, pos, type);
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
		return true;
	}

	@Override
	public BlockState getPlant(IBlockReader world, BlockPos pos) {
		BlockState state = world.getBlockState(pos);

		if (state.getBlock() != this)
			return getDefaultState();

		return state;
	}

	@Nonnull
	@Override
	public List<ItemStack> onSheared(@Nonnull ItemStack item, IWorld world, BlockPos pos, int fortune) {
		if (world instanceof World) {
			ArrayList<ItemStack> drops = new ArrayList<ItemStack>();

			drops.add(new ItemStack(Item.getItemFromBlock(this)));

			return drops;
		}

		return new ArrayList<ItemStack>();
	}

	// TODO Look at random offset? RE: DoublePlantBlock
}
