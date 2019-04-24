package net.nevermine.block.modelblocks.animated;

import cpw.mods.fml.client.registry.ClientRegistry;
import net.nevermine.assist.TileResourceLocation;
import net.nevermine.common.nevermine;
import net.nevermine.izer.SpecialBlockizer;

public class AnimatedModelBlockEntityRenderer {
	private static TileResourceLocation x;

	public static void init() {
		nevermine.registerItemRenderer(SpecialBlockizer.Campfire, new RenderAnimatedModelBlockItem(SpecialBlockizer.Campfire));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAnimatedModelBlock.class, new RenderAnimatedModelBlock());
	}
}
