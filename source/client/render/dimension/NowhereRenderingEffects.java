package net.tslat.aoa3.client.render.dimension;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.event.dimension.NowhereEvents;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;

public class NowhereRenderingEffects extends DimensionSpecialEffects {
    public static final ResourceLocation ID = new ResourceLocation(AdventOfAscension.MOD_ID, "nowhere");

    private final Minecraft minecraft = Minecraft.getInstance();
    private final DimensionSpecialEffects lunalusRenderer;
    private final DimensionSpecialEffects voidSkyRenderer;

    public NowhereRenderingEffects(DimensionSpecialEffects lunalusRenderer, DimensionSpecialEffects voidSkyRenderer) {
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