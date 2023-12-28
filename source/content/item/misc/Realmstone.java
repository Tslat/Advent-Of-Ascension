package net.tslat.aoa3.content.item.misc;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

public class Realmstone extends Item {
	private final RegistryObject<Block> portalBlock;
	private final String dimensionMsgSuffix;

	public Realmstone(RegistryObject<Block> portalBlock, String dimension) {
		super(new Item.Properties().stacksTo(1));

		this.portalBlock = portalBlock;
		this.dimensionMsgSuffix = dimension;
	}

	public RegistryObject<Block> getPortalBlock() {
		return portalBlock;
	}

	public String getDimensionMsgSuffix() {
		return dimensionMsgSuffix;
	}
}
