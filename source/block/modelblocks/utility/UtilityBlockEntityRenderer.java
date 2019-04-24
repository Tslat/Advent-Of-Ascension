package net.nevermine.block.modelblocks.utility;

import cpw.mods.fml.client.registry.ClientRegistry;
import net.nevermine.assist.TileResourceLocation;
import net.nevermine.common.nevermine;
import net.nevermine.izer.SpecialBlockizer;

public class UtilityBlockEntityRenderer {
	private static TileResourceLocation x;

	public static void init() {
		nevermine.registerItemRenderer(SpecialBlockizer.ChargingTable, new RenderUtilityBlockItem(SpecialBlockizer.ChargingTable));
		nevermine.registerItemRenderer(SpecialBlockizer.Iropole, new RenderUtilityBlockItem(SpecialBlockizer.Iropole));
		nevermine.registerItemRenderer(SpecialBlockizer.HydroTable, new RenderUtilityBlockItem(SpecialBlockizer.HydroTable));
		nevermine.registerItemRenderer(SpecialBlockizer.Teasink, new RenderUtilityBlockItem(SpecialBlockizer.Teasink));
		nevermine.registerItemRenderer(SpecialBlockizer.TeasinkFull, new RenderUtilityBlockItem(SpecialBlockizer.TeasinkFull));
		nevermine.registerItemRenderer(SpecialBlockizer.FiltrationSystemOn, new RenderUtilityBlockItem(SpecialBlockizer.FiltrationSystemOn));
		nevermine.registerItemRenderer(SpecialBlockizer.FiltrationSystemOff, new RenderUtilityBlockItem(SpecialBlockizer.FiltrationSystemOff));
		nevermine.registerItemRenderer(SpecialBlockizer.BlueShroom, new RenderUtilityBlockItem(SpecialBlockizer.BlueShroom));
		nevermine.registerItemRenderer(SpecialBlockizer.GreenShroom, new RenderUtilityBlockItem(SpecialBlockizer.GreenShroom));
		nevermine.registerItemRenderer(SpecialBlockizer.YellowShroom, new RenderUtilityBlockItem(SpecialBlockizer.YellowShroom));
		nevermine.registerItemRenderer(SpecialBlockizer.PurpleShroom, new RenderUtilityBlockItem(SpecialBlockizer.PurpleShroom));
		nevermine.registerItemRenderer(SpecialBlockizer.OrangeShroom, new RenderUtilityBlockItem(SpecialBlockizer.OrangeShroom));
		nevermine.registerItemRenderer(SpecialBlockizer.ShroomStem, new RenderUtilityBlockItem(SpecialBlockizer.ShroomStem));
		nevermine.registerItemRenderer(SpecialBlockizer.VoxLog1, new RenderUtilityBlockItem(SpecialBlockizer.VoxLog1));
		nevermine.registerItemRenderer(SpecialBlockizer.VoxLog2, new RenderUtilityBlockItem(SpecialBlockizer.VoxLog2));
		nevermine.registerItemRenderer(SpecialBlockizer.VoxShroom, new RenderUtilityBlockItem(SpecialBlockizer.VoxShroom));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityUtilityBlock.class, new RenderUtilityBlock());
	}
}
