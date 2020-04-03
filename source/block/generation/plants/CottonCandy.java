package net.tslat.aoa3.block.generation.plants;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.IShearable;
import net.tslat.aoa3.block.BasicBlock;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;

public class CottonCandy extends BasicBlock implements IShearable {
	public CottonCandy(String name, String registryName) {
		super(name, registryName, Material.WEB, 0.4f, 0);
	}

	@Override
	public boolean isToolEffective(String type, IBlockState state) {
		return type.equals("sword") || type.equals("shears");
	}

	@Override
	public boolean isShearable(@Nonnull ItemStack item, IBlockAccess world, BlockPos pos) {
		return true;
	}

	@Nonnull
	@Override
	public List<ItemStack> onSheared(@Nonnull ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
		return Arrays.asList(new ItemStack(Item.getItemFromBlock(this), fortune + 1));
	}
}
