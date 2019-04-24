package net.nevermine.item.crop;

import net.minecraft.block.Block;
import net.minecraft.item.ItemSeeds;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.nevermine.izer.Itemizer;

public class EternalSeeds extends ItemSeeds implements IPlantable {
	private Block blockType;
	private Block soilBlock;

	public EternalSeeds(final Block type, final Block soil) {
		super(type, soil);
		blockType = type;
		soilBlock = soil;
		setCreativeTab(Itemizer.MiscTab);
		setMaxStackSize(64);
	}

	public EnumPlantType getPlantType(final IBlockAccess world, final int x, final int y, final int z) {
		return EnumPlantType.Crop;
	}

	public Block getPlant(final IBlockAccess world, final int x, final int y, final int z) {
		return blockType;
	}

	public int getPlantMetadata(final IBlockAccess world, final int x, final int y, final int z) {
		return 0;
	}
}
