package net.tslat.aoa3.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.ISkyRenderHandler;
import net.tslat.aoa3.advent.AdventOfAscension;

import javax.annotation.Nullable;

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
		DimensionSpecialEffects.EFFECTS.put(PlainSky.ID, new PlainSky());
		DimensionSpecialEffects.EFFECTS.put(CelestialOnly.ID, new CelestialOnly());
		DimensionSpecialEffects.EFFECTS.put(Shyrelands.ID, new Shyrelands());
		DimensionSpecialEffects.EFFECTS.put(Lunalus.ID, new Lunalus());
	}

	public static class PlainSky extends DimensionSpecialEffects {
		public static final ResourceLocation ID = new ResourceLocation(AdventOfAscension.MOD_ID, "plain_sky");

		public PlainSky() {
			super(Float.NaN, true, SkyType.NONE, false, true);
		}

		@Override // Sky & distant fog colour - Biome fog & celestial angle
		public Vec3 getBrightnessDependentFogColor(Vec3 biomeFogColour, float celestialAngle) {
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

	public static class CelestialOnly extends DimensionSpecialEffects {
		public static final ResourceLocation ID = new ResourceLocation(AdventOfAscension.MOD_ID, "celestial_only");

		public CelestialOnly() {
			super(Float.NaN, true, SkyType.NORMAL, false, false);
		}

		@Override
		public Vec3 getBrightnessDependentFogColor(Vec3 biomeFogColour, float celestialAngle) {
			return biomeFogColour.multiply(celestialAngle * 0.94F + 0.06F, celestialAngle * 0.94F + 0.06F, celestialAngle * 0.91F + 0.09F);
		}

		@Override // Show fog at X/Z
		public boolean isFoggyAt(int posX, int posZ) {
			return false;
		}
	}

	public static class Shyrelands extends DimensionSpecialEffects {
		public static final ResourceLocation ID = new ResourceLocation(AdventOfAscension.MOD_ID, "shyrelands");

		public Shyrelands() {
			super(40, true, SkyType.NORMAL, false, true);
		}

		@Override
		public Vec3 getBrightnessDependentFogColor(Vec3 biomeFogColour, float celestialAngle) {
			return biomeFogColour.multiply(celestialAngle * 0.94F + 0.06F, celestialAngle * 0.94F + 0.06F, celestialAngle * 0.91F + 0.09F);
		}

		@Override // Show fog at X/Z
		public boolean isFoggyAt(int posX, int posZ) {
			return false;
		}
	}

	public static class Lunalus extends DimensionSpecialEffects {
		public static final ResourceLocation ID = new ResourceLocation(AdventOfAscension.MOD_ID, "lunalus");

		public Lunalus() {
			super(Float.NaN, true, SkyType.NONE, false, true);
			
			setSkyRenderHandler(new StarfieldSkyRenderer());
		}

		@Override // Sky & distant fog colour - Biome fog & celestial angle
		public Vec3 getBrightnessDependentFogColor(Vec3 biomeFogColour, float celestialAngle) {
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

		private static class StarfieldSkyRenderer implements ISkyRenderHandler {
			private static final ResourceLocation TEXTURE = new ResourceLocation(AdventOfAscension.MOD_ID, "textures/gui/realmstonegui/background.png");
			
			@Override
			public void render(int ticks, float partialTicks, PoseStack matrix, ClientLevel world, Minecraft mc) {
				/*RenderSystem.disableAlphaTest();
				RenderSystem.enableBlend();
				RenderSystem.defaultBlendFunc();
				RenderSystem.depthMask(false);
				mc.getTextureManager().bind(TEXTURE);
				
				Tesselator tesselator = Tesselator.getInstance();
				BufferBuilder buffer = Tesselator.getBuilder();

				for(int i = 0; i < 6; ++i) {
					matrix.pushPose();

					switch (i) {
						case 1 -> matrix.mulPose(Vector3f.XP.rotationDegrees(90.0F));
						case 2 -> matrix.mulPose(Vector3f.XP.rotationDegrees(-90.0F));
						case 3 -> matrix.mulPose(Vector3f.XP.rotationDegrees(180.0F));
						case 4 -> matrix.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
						case 5 -> matrix.mulPose(Vector3f.ZP.rotationDegrees(-90.0F));
						default -> {}
					}

					Matrix4f matrix4f = matrix.last().pose();

					buffer.begin(7, DefaultVertexFormat.POSITION_TEX_COLOR);
					buffer.vertex(matrix4f, -100, -100, -100).uv(0, 0).color(150, 150, 150, 255).endVertex();
					buffer.vertex(matrix4f, -100, -100, 100).uv(0, 1).color(150, 150, 150, 255).endVertex();
					buffer.vertex(matrix4f, 100, -100, 100).uv(1, 1).color(150, 150, 150, 255).endVertex();
					buffer.vertex(matrix4f, 100, -100, -100).uv(1, 0).color(150, 150, 150, 255).endVertex();
					Tesselator.end();
					matrix.popPose();
				}

				RenderSystem.depthMask(true);
				RenderSystem.enableTexture();
				RenderSystem.disableBlend();
				RenderSystem.enableAlphaTest();*/
			}
		}
	}
}
