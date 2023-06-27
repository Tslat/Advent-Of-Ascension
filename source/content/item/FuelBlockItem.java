package net.tslat.aoa3.content.item;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

public class FuelBlockItem extends BlockItem {
	private final int burnTime;

	public FuelBlockItem(Block block, Properties properties) {
		this(block, properties, 300);
	}

	public FuelBlockItem(Block block, Properties properties, int burnTime) {
		super(block, properties);

		this.burnTime = burnTime;
	}

	@Override
	public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
		return this.burnTime;
	}
}
