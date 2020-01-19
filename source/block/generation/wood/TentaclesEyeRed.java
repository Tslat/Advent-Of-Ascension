package net.tslat.aoa3.block.generation.wood;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.tslat.aoa3.block.BasicBlock;
import net.tslat.aoa3.common.registration.ItemRegister;

import java.util.Random;

public class TentaclesEyeRed extends BasicBlock {
	public TentaclesEyeRed() {
		super("TentaclesEyeRed", "tentacles_eye_red", Material.GOURD, 3.0f, 1.0f);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return rand.nextFloat() < 0.05f ? ItemRegister.staringEye : super.getItemDropped(state, rand, fortune);
	}
}
