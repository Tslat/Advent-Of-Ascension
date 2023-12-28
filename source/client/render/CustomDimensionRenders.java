package net.tslat.aoa3.client.render;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.RegisterDimensionSpecialEffectsEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.event.dimension.NowhereEvents;
import net.tslat.aoa3.util.RenderUtil;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;


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
		AdventOfAscension.modEventBus.addListener(EventPriority.NORMAL, false, RegisterDimensionSpecialEffectsEvent.class, ev -> {
			DimensionSpecialEffects voidSkyRenderer = new VoidSky();
			DimensionSpecialEffects lunalusRenderer = new Lunalus();

			ev.register(VoidSky.ID, voidSkyRenderer);
			ev.register(CelestialOnly.ID, new CelestialOnly());
			ev.register(Shyrelands.ID, new Shyrelands());
			ev.register(Precasia.ID, new Precasia());
			ev.register(Lunalus.ID, lunalusRenderer);
			ev.register(Nowhere.ID, new Nowhere(lunalusRenderer, voidSkyRenderer));
		});
	}

	public static class VoidSky extends DimensionSpecialEffects {
		public static final ResourceLocation ID = new ResourceLocation(AdventOfAscension.MOD_ID, "void_sky");

		public VoidSky() {
			super(Float.NaN, false, SkyType.NONE, false, true);
		}

		@Override
		public boolean renderClouds(ClientLevel level, int ticks, float partialTick, PoseStack poseStack, double camX, double camY, double camZ, Matrix4f projectionMatrix) {
			return true;
		}

		@Override
		public boolean renderSky(ClientLevel level, int ticks, float partialTick, PoseStack poseStack, Camera camera, Matrix4f projectionMatrix, boolean isFoggy, Runnable setupFog) {
			return true;
		}

		@Override
		public boolean renderSnowAndRain(ClientLevel level, int ticks, float partialTick, LightTexture lightTexture, double camX, double camY, double camZ) {
			return true;
		}

		@Override
		public boolean tickRain(ClientLevel level, int ticks, Camera camera) {
			return true;
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

	public static class Precasia extends DimensionSpecialEffects {
		public static final ResourceLocation ID = new ResourceLocation(AdventOfAscension.MOD_ID, "precasia");

		public Precasia() {
			super(192, true, DimensionSpecialEffects.SkyType.NORMAL, false, false);
		}

		@Override
		public Vec3 getBrightnessDependentFogColor(Vec3 biomeFogColour, float celestialAngle) {
			return biomeFogColour.multiply(celestialAngle * 0.94F + 0.06F, celestialAngle * 0.94F + 0.06F, celestialAngle * 0.91F + 0.09F);
		}

		@Override // Show fog at X/Z
		public boolean isFoggyAt(int posX, int posY) {
			return false;
		}
	}

	public static class Lunalus extends DimensionSpecialEffects {
		public static final ResourceLocation ID = new ResourceLocation(AdventOfAscension.MOD_ID, "lunalus");
		private static final ResourceLocation TEXTURE = new ResourceLocation(AdventOfAscension.MOD_ID, "textures/gui/realmstonegui/background.png");

		public Lunalus() {
			super(Float.NaN, true, SkyType.NONE, false, true);
		}

		@Override
		public boolean renderSky(ClientLevel level, int ticks, float partialTick, PoseStack poseStack, Camera camera, Matrix4f projectionMatrix, boolean isFoggy, Runnable setupFog) {
			RenderSystem.enableBlend();
			RenderSystem.defaultBlendFunc();
			RenderSystem.depthMask(false);
			RenderUtil.prepRenderTexture(TEXTURE);

			BufferBuilder buffer = Tesselator.getInstance().getBuilder();

			for(int i = 0; i < 6; ++i) {
				poseStack.pushPose();

				switch (i) {
					case 1 -> poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
					case 2 -> poseStack.mulPose(Axis.XP.rotationDegrees(-90.0F));
					case 3 -> poseStack.mulPose(Axis.XP.rotationDegrees(180.0F));
					case 4 -> poseStack.mulPose(Axis.ZP.rotationDegrees(90.0F));
					case 5 -> poseStack.mulPose(Axis.ZP.rotationDegrees(-90.0F));
					default -> {}
				}

				Matrix4f matrix4f = poseStack.last().pose();

				buffer.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR);
				buffer.vertex(matrix4f, -100, -100, -100).uv(0, 0).color(150, 150, 150, 255).endVertex();
				buffer.vertex(matrix4f, -100, -100, 100).uv(0, 1).color(150, 150, 150, 255).endVertex();
				buffer.vertex(matrix4f, 100, -100, 100).uv(1, 1).color(150, 150, 150, 255).endVertex();
				buffer.vertex(matrix4f, 100, -100, -100).uv(1, 0).color(150, 150, 150, 255).endVertex();
				BufferUploader.drawWithShader(buffer.end());
				poseStack.popPose();
			}

			RenderSystem.depthMask(true);
			RenderSystem.disableBlend();

			return true;
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

	public static class Nowhere extends DimensionSpecialEffects {
		public static final ResourceLocation ID = new ResourceLocation(AdventOfAscension.MOD_ID, "nowhere");

		private final Minecraft minecraft = Minecraft.getInstance();
		private final DimensionSpecialEffects lunalusRenderer;
		private final DimensionSpecialEffects voidSkyRenderer;

		public Nowhere(DimensionSpecialEffects lunalusRenderer, DimensionSpecialEffects voidSkyRenderer) {
			super(Float.NaN, false, SkyType.NONE, false, true);

			this.lunalusRenderer = lunalusRenderer;
			this.voidSkyRenderer = voidSkyRenderer;
		}

		@Override
		public boolean renderClouds(ClientLevel level, int ticks, float partialTick, PoseStack poseStack, double camX, double camY, double camZ, Matrix4f projectionMatrix) {
			return voidSkyRenderer.renderClouds(level, ticks, partialTick, poseStack, camX, camY, camZ, projectionMatrix);
		}

		@Override
		public boolean renderSky(ClientLevel level, int ticks, float partialTick, PoseStack poseStack, Camera camera, Matrix4f projectionMatrix, boolean isFoggy, Runnable setupFog) {
			if (NowhereEvents.isInParkourRegion(minecraft.player.blockPosition())) {
				lunalusRenderer.renderSky(level, ticks, partialTick, poseStack, camera, projectionMatrix, isFoggy, setupFog);

				return true;
			}
			else {
				return voidSkyRenderer.renderSky(level, ticks, partialTick, poseStack, camera, projectionMatrix, isFoggy, setupFog);
			}
		}

		@Override
		public boolean renderSnowAndRain(ClientLevel level, int ticks, float partialTick, LightTexture lightTexture, double camX, double camY, double camZ) {
			return voidSkyRenderer.renderSnowAndRain(level, ticks, partialTick, lightTexture, camX, camY, camZ);
		}

		@Override
		public boolean tickRain(ClientLevel level, int ticks, Camera camera) {
			return voidSkyRenderer.tickRain(level, ticks, camera);
		}

		@Override // Sky & distant fog colour - Biome fog & celestial angle
		public Vec3 getBrightnessDependentFogColor(Vec3 biomeFogColour, float celestialAngle) {
			return voidSkyRenderer.getBrightnessDependentFogColor(biomeFogColour, celestialAngle);
		}

		@Override // Show fog at X/Z
		public boolean isFoggyAt(int posX, int posZ) {
			return voidSkyRenderer.isFoggyAt(posX, posZ);
		}

		@Nullable
		@Override // Adjust fog/sky colour for sunset/sunrise
		public float[] getSunriseColor(float celestialAngle, float partialTicks) {
			return voidSkyRenderer.getSunriseColor(celestialAngle, partialTicks);
		}
	}
}
