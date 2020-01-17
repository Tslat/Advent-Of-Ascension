package net.tslat.aoa3.block.functional.lights;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.block.BasicBlock;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;

import java.util.Random;

public class LightBlock extends BasicBlock {
	public LightBlock(String name, String registryName, Material material, float luminosity, float hardness, float resistance) {
		super(name, registryName, material, hardness, resistance);
		setLightLevel(luminosity);
		setLightOpacity(0);
		setCreativeTab(CreativeTabsRegister.decorationBlocksTab);
	}

	@Override
	public int quantityDropped(Random random) {
		return 0;
	}

	@Override
	public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		 return true;
	}
}
