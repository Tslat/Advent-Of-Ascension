package net.tslat.aoa3.client.render;

import net.minecraft.client.world.DimensionRenderInfo;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;
import net.tslat.aoa3.advent.AdventOfAscension;

import javax.annotation.Nullable;

import net.minecraft.client.world.DimensionRenderInfo.FogType;

public final class CustomDimensionRenders {
	/*
		DimensionRenderInfo constructor:
		p_i241259_1_ - float - Cloud render height in blocks. Set to Float.NAN to remove clouds
		p_i241259_2_ - boolean - Whether the dim is a non-sky-dim. Affects sky colour
		p_i241259_3_ - DimensionRenderInfo.FogType - Sky render preset type. Affects sky rendering as well as fog
		p_i241259_4_ - boolean - Force bright lightmap - Affects lighting colour
		p_i241259_5_ - boolean - Global ambient light - should blocks be equally lit on all sides
	 */

	public static void init() {
		DimensionRenderInfo.EFFECTS.put(PlainSky.ID, new PlainSky());
		DimensionRenderInfo.EFFECTS.put(CelestialOnly.ID, new CelestialOnly());
		DimensionRenderInfo.EFFECTS.put(Shyrelands.ID, new Shyrelands());
	}

	public static class PlainSky extends DimensionRenderInfo {
		public static final ResourceLocation ID = new ResourceLocation(AdventOfAscension.MOD_ID, "plain_sky");

		public PlainSky() {
			super(Float.NaN, true, FogType.NONE, false, true);
		}

		@Override // Sky & distant fog colour - Biome fog & celestial angle
		public Vector3d getBrightnessDependentFogColor(Vector3d biomeFogColour, float celestialAngle) {
			return biomeFogColour;
		}

		@Override // Show fog at X/Z
		public boolean isFoggyAt(int posX, int posZ) {
			return false;
		}

		@Nullable
		@Override // Adjust fog/sky colour for sunset/sunrise
		public float[] getSunriseColor(float celestialAngle, float partialTicks) {
			return null;
		}
	}

	public static class CelestialOnly extends DimensionRenderInfo {
		public static final ResourceLocation ID = new ResourceLocation(AdventOfAscension.MOD_ID, "celestial_only");

		public CelestialOnly() {
			super(Float.NaN, true, FogType.NORMAL, false, false);
		}

		@Override
		public Vector3d getBrightnessDependentFogColor(Vector3d biomeFogColour, float celestialAngle) {
			return biomeFogColour.multiply(celestialAngle * 0.94F + 0.06F, celestialAngle * 0.94F + 0.06F, celestialAngle * 0.91F + 0.09F);
		}

		@Override // Show fog at X/Z
		public boolean isFoggyAt(int posX, int posZ) {
			return false;
		}
	}

	public static class Shyrelands extends DimensionRenderInfo {
		public static final ResourceLocation ID = new ResourceLocation(AdventOfAscension.MOD_ID, "shyrelands");

		public Shyrelands() {
			super(40, true, FogType.NORMAL, false, true);
		}

		@Override
		public Vector3d getBrightnessDependentFogColor(Vector3d biomeFogColour, float celestialAngle) {
			return biomeFogColour.multiply(celestialAngle * 0.94F + 0.06F, celestialAngle * 0.94F + 0.06F, celestialAngle * 0.91F + 0.09F);
		}

		@Override // Show fog at X/Z
		public boolean isFoggyAt(int posX, int posZ) {
			return false;
		}
	}
}
