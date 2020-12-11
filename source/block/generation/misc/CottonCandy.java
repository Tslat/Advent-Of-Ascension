package net.tslat.aoa3.block.generation.misc;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.common.ToolType;
import net.tslat.aoa3.util.BlockUtil;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;

public class CottonCandy extends Block implements IShearable {
	public CottonCandy(MaterialColor mapColour) {
		super(BlockUtil.generateBlockProperties(Material.GOURD, mapColour, 0.4f, 0, SoundType.CLOTH));
	}

	@Override
	public boolean isShearable(@Nonnull ItemStack item, IWorldReader world, BlockPos pos) {
		return true;
	}

	@Override
	public boolean isToolEffective(BlockState state, ToolType tool) {
		return tool == ToolType.get("sword") || tool == ToolType.get("shears");
	}

	@Nonnull
	@Override
	public List<ItemStack> onSheared(@Nonnull ItemStack item, IWorld world, BlockPos pos, int fortune) {
		return Arrays.asList(new ItemStack(Item.getItemFromBlock(this), fortune + 1));
	}
}
