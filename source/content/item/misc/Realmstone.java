package net.tslat.aoa3.content.item.misc;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;

public class Realmstone extends Item {
	private final DeferredBlock<Block> portalBlock;
	private final String dimensionMsgSuffix;

	public Realmstone(DeferredBlock<Block> portalBlock, String dimension) {
		super(new Item.Properties().stacksTo(1));

		this.portalBlock = portalBlock;
		this.dimensionMsgSuffix = dimension;
	}

	public DeferredBlock<Block> getPortalBlock() {
		return portalBlock;
	}

	public String getDimensionMsgSuffix() {
		return dimensionMsgSuffix;
	}
}
