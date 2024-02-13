package net.tslat.aoa3.client.render.dimension;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.util.RenderUtil;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;

public class LunalusRenderingEffects extends DimensionSpecialEffects {
    public static final ResourceLocation ID = new ResourceLocation(AdventOfAscension.MOD_ID, "lunalus");
    private static final ResourceLocation TEXTURE = new ResourceLocation(AdventOfAscension.MOD_ID, "textures/gui/realmstonegui/background.png");

    public LunalusRenderingEffects() {
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
