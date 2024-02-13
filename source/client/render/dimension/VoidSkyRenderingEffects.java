package net.tslat.aoa3.client.render.dimension;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.advent.AdventOfAscension;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;

public class VoidSkyRenderingEffects extends DimensionSpecialEffects {
    public static final ResourceLocation ID = new ResourceLocation(AdventOfAscension.MOD_ID, "void_sky");

    public VoidSkyRenderingEffects() {
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