package net.tslat.aoa3.block.functional.utility;

import net.minecraft.block.material.Material;
import net.tslat.aoa3.block.BasicNonCubeBlock;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;

public class ChargingTable extends BasicNonCubeBlock {
	public ChargingTable() {
		super("ChargingTable", "charging_table", Material.ROCK);
		setHardness(5f);
		setResistance(4f);
		setCreativeTab(CreativeTabsRegister.FUNCTIONAL_BLOCKS);
	}
}
