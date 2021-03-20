package net.tslat.aoa3.block.generation.log;

import net.minecraft.block.Block;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.item.AxeItem;
import net.minecraftforge.fml.RegistryObject;

import net.minecraft.block.AbstractBlock;

public class StrippedLog extends RotatedPillarBlock {
	public StrippedLog(RegistryObject<Block> parentLog) {
		super(AbstractBlock.Properties.copy(parentLog.get()));

		AxeItem.STRIPABLES.put(parentLog.get(), this);
	}
}
