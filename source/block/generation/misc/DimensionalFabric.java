package net.tslat.aoa3.block.generation.misc;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.tslat.aoa3.util.BlockUtil;

import javax.annotation.Nullable;

public class DimensionalFabric extends Block {
	public DimensionalFabric() {
		super(new BlockUtil.CompactProperties(Material.BARRIER, MaterialColor.COLOR_BLACK).unbreakable().noSpawns().sound(SoundType.WOOL).get());
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return VoxelShapes.empty();
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return VoxelShapes.block();
	}

	@Override
	public VoxelShape getOcclusionShape(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return VoxelShapes.block();
	}

	@Override
	public boolean canCreatureSpawn(BlockState state, IBlockReader world, BlockPos pos, EntitySpawnPlacementRegistry.PlacementType type, @Nullable EntityType<?> entityType) {
		return false;
	}

	@Override
	public boolean isPossibleToRespawnInThis() {
		return false;
	}

	@Override
	public void onProjectileHit(World worldIn, BlockState state, BlockRayTraceResult hit, ProjectileEntity projectile) {
		projectile.remove();
	}
}
