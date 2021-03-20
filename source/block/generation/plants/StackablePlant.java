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
import net.minecraftforge.common.IForgeShearable;
import net.minecraftforge.common.IPlantable;
import net.tslat.aoa3.util.BlockUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import net.minecraft.block.AbstractBlock;

public class StackablePlant extends Block implements IForgeShearable, IPlantable {
	protected Supplier<Block> hatBlock;
	protected Supplier<Block> stemBlock;
	protected final List<Material> growthMaterials;

	public StackablePlant(AbstractBlock.Properties properties, Material... growthMaterial) {
		super(properties);

		this.hatBlock = () -> this;
		this.stemBlock = () -> this;
		this.growthMaterials = Arrays.asList(growthMaterial);
	}

	public StackablePlant(Material material, MaterialColor mapColour, SoundType sound, Material... growthMaterial) {
		super(BlockUtil.generateBlockProperties(material, mapColour, 0, 0, sound).noCollission());
		this.hatBlock = () -> this;
		this.stemBlock = () -> this;
		this.growthMaterials = Arrays.asList(growthMaterial);
	}

	public StackablePlant(MaterialColor mapColour, Material... growthMaterials) {
		this(Material.PLANT, mapColour, SoundType.GRASS, growthMaterials);
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
	public void playerWillDestroy(World world, BlockPos pos, BlockState state, PlayerEntity player) {
		BlockPos newPos;
		BlockState blockState = world.getBlockState(newPos = pos.above());
		Block block = blockState.getBlock();

		while (block == stemBlock.get() || block == hatBlock.get()) {
			world.setBlock(newPos, Blocks.AIR.defaultBlockState(), 35);
			world.levelEvent(player, 2001, newPos, Block.getId(blockState));

			if (!world.isClientSide() && !player.isCreative()) {
				dropResources(state, world, pos, null, player, player.getMainHandItem());
				dropResources(blockState, world, newPos, null, player, player.getMainHandItem());
			}

			blockState = world.getBlockState(newPos = newPos.above());
			block = blockState.getBlock();
		}

		super.playerWillDestroy(world, pos, state, player);
	}

	@Override
	public boolean canSurvive(BlockState state, IWorldReader world, BlockPos pos) {
		BlockState targetState = world.getBlockState(pos.below());

		return ((growthMaterials.isEmpty() || growthMaterials.contains(targetState.getMaterial())) && targetState.isSolidRender(world, pos.below())) || targetState.getBlock() == stemBlock.get();
	}

	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos pos, BlockPos facingPos) {
		return !state.canSurvive(world, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, facing, facingState, world, pos, facingPos);
	}

	@Override
	public boolean canBeReplaced(BlockState state, BlockItemUseContext useContext) {
		return false;
	}

	@Override
	public boolean isShearable(@Nonnull ItemStack item, World world, BlockPos pos) {
		return true;
	}

	@Override
	public boolean isPathfindable(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
		return type == PathType.AIR && !this.hasCollision || super.isPathfindable(state, worldIn, pos, type);
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
		return true;
	}

	@Override
	public BlockState getPlant(IBlockReader world, BlockPos pos) {
		BlockState state = world.getBlockState(pos);

		if (state.getBlock() != this)
			return defaultBlockState();

		return state;
	}

	@Nonnull
	@Override
	public List<ItemStack> onSheared(@Nullable PlayerEntity player, @Nonnull ItemStack item, World world, BlockPos pos, int fortune) {
		ArrayList<ItemStack> drops = new ArrayList<ItemStack>();

		drops.add(new ItemStack(Item.byBlock(this)));

		return drops;
	}

	// TODO Look at random offset? RE: DoublePlantBlock
}
