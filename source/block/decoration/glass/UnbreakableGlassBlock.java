package net.tslat.aoa3.block.decoration.glass;

public class UnbreakableGlassBlock extends GlassBlock {
	public UnbreakableGlassBlock(String name, String registryName) {
		super(name, registryName, -1f, 999999999f);
		setCreativeTab(null);
	}
}
