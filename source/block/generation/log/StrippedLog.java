package net.tslat.aoa3.block.generation.log;

import net.minecraft.block.Block;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.item.AxeItem;
import net.minecraftforge.fml.RegistryObject;

public class StrippedLog extends RotatedPillarBlock {
	public StrippedLog(RegistryObject<Block> parentLog) {
		super(Block.Properties.from(parentLog.get()));

		AxeItem.BLOCK_STRIPPING_MAP.put(parentLog.get(), this);
	}
}
