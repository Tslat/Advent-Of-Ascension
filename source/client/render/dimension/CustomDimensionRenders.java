package net.tslat.aoa3.client.render.dimension;

import net.neoforged.bus.api.EventPriority;
import net.neoforged.neoforge.client.event.RegisterDimensionSpecialEffectsEvent;
import net.tslat.aoa3.advent.AdventOfAscension;


public final class CustomDimensionRenders {
	/*
		DimensionSpecialEffects constructor:
		p_i241259_1_ - float - Cloud render height in blocks. Set to Float.NAN to remove clouds
		p_i241259_2_ - boolean - Whether the dim is a non-sky-dim. Affects sky colour
		p_i241259_3_ - DimensionSpecialEffects.FogType - Sky render preset type. Affects sky rendering as well as fog
		p_i241259_4_ - boolean - Force bright lightmap - Affects lighting colour
		p_i241259_5_ - boolean - Global ambient light - should blocks be equally lit on all sides
	 */

	public static void init() {
		AdventOfAscension.getModEventBus().addListener(EventPriority.NORMAL, false, RegisterDimensionSpecialEffectsEvent.class, ev -> {
			VoidSkyRenderingEffects voidSkyRenderer = new VoidSkyRenderingEffects();
			LunalusRenderingEffects lunalusRenderer = new LunalusRenderingEffects();

			ev.register(VoidSkyRenderingEffects.ID, voidSkyRenderer);
			ev.register(CelestialOnlyRenderingEffects.ID, new CelestialOnlyRenderingEffects());
			ev.register(ShyrelandsRenderingEffects.ID, new ShyrelandsRenderingEffects());
			ev.register(PrecasiaRenderingEffects.ID, new PrecasiaRenderingEffects());
			ev.register(LBoreanRenderingEffects.ID, new LBoreanRenderingEffects());
			ev.register(LunalusRenderingEffects.ID, lunalusRenderer);
			ev.register(NowhereRenderingEffects.ID, new NowhereRenderingEffects(lunalusRenderer, voidSkyRenderer));
		});
	}
}
