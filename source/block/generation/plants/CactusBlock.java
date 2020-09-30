package net.tslat.aoa3.block.generation.plants;

import net.minecraft.block.BlockCactus;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.block.CustomStateMapperBlock;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;

import java.util.Random;

public class CactusBlock extends BlockCactus implements CustomStateMapperBlock {
	public CactusBlock(String name, String registryName) {
		super();
		setTranslationKey(name);
		setRegistryName("aoa3:" + registryName);
		setHardness(0.4f);
		setTickRandomly(false);
		setDefaultState(blockState.getBaseState());
		setSoundType(SoundType.CLOTH);
		setCreativeTab(CreativeTabsRegister.GENERATION_BLOCKS);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public StateMap getStateMapper() {
		return (new StateMap.Builder().ignore(BlockCactus.AGE)).build();
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {}

	@Override
	public boolean canBlockStay(World world, BlockPos pos) {
		IBlockState baseBlock = world.getBlockState(pos.down());

		return baseBlock.getMaterial() == Material.SAND || baseBlock.getBlock() == this;
	}

	@Override
	public void onEntityCollision(World world, BlockPos pos, IBlockState state, Entity entity) {
		entity.attackEntityFrom(DamageSource.CACTUS, 2.0f);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState();
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return 0;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, AGE);
	}
}
