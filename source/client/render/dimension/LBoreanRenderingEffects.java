package net.tslat.aoa3.client.render.dimension;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.material.FogType;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.util.RenderUtil;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;

class LBoreanRenderingEffects extends DimensionSpecialEffects {
    static final ResourceLocation ID = new ResourceLocation(AdventOfAscension.MOD_ID, "lborean");
    private static final ResourceLocation MOON_TEXTURE = new ResourceLocation("textures/environment/moon_phases.png");
    private static final ResourceLocation SUN_TEXTURE = new ResourceLocation("textures/environment/sun.png");

    public LBoreanRenderingEffects() {
        super(400, true, DimensionSpecialEffects.SkyType.NORMAL, false, false);
    }

    @Override
    public boolean renderSnowAndRain(ClientLevel level, int ticks, float partialTick, LightTexture lightTexture, double camX, double camY, double camZ) {
        return true;
    }

    @Override
    public boolean renderSky(ClientLevel level, int ticks, float partialTick, PoseStack poseStack, Camera camera, Matrix4f projectionMatrix, boolean isFoggy, Runnable setupFog) {
        setupFog.run();
        
        if (!isFoggy) {
            final Minecraft mc = Minecraft.getInstance();
            final LevelRenderer levelRenderer = mc.levelRenderer;
            FogType fogtype = camera.getFluidInCamera();
            
            if (fogtype != FogType.POWDER_SNOW && fogtype != FogType.LAVA && !levelRenderer.doesMobEffectBlockSky(camera)) {
                Vec3 skyColour = level.getSkyColor(mc.gameRenderer.getMainCamera().getPosition(), partialTick);
                float skyColourRed = (float)skyColour.x;
                float skyColourGreen = (float)skyColour.y;
                float skyColourBlue = (float)skyColour.z;
                BufferBuilder bufferBuilder = Tesselator.getInstance().getBuilder();
                ShaderInstance shader = RenderSystem.getShader();

                FogRenderer.levelFogColor();
                RenderSystem.depthMask(false);
                RenderSystem.setShaderColor(skyColourRed, skyColourGreen, skyColourBlue, 1);

                levelRenderer.skyBuffer.bind();
                levelRenderer.skyBuffer.drawWithShader(poseStack.last().pose(), projectionMatrix, shader);
                VertexBuffer.unbind();
                RenderSystem.enableBlend();

                float[] sunriseColour = level.effects().getSunriseColor(level.getTimeOfDay(partialTick), partialTick);

                if (sunriseColour != null) {
                    RenderSystem.setShader(GameRenderer::getPositionColorShader);
                    RenderUtil.resetShaderColour();
                    poseStack.pushPose();
                    poseStack.mulPose(Axis.XP.rotationDegrees(90));
                    float sunAngle = Mth.sin(level.getSunAngle(partialTick)) < 0 ? 180 : 0;
                    poseStack.mulPose(Axis.ZP.rotationDegrees(sunAngle));
                    poseStack.mulPose(Axis.ZP.rotationDegrees(90));
                    float sunriseRed = sunriseColour[0];
                    float sunriseGreen = sunriseColour[1];
                    float sunriseBlue = sunriseColour[2];
                    Matrix4f pose = poseStack.last().pose();
                    bufferBuilder.begin(VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION_COLOR);
                    bufferBuilder.vertex(pose, 0, 100, 0).color(sunriseRed, sunriseGreen, sunriseBlue, sunriseColour[3]).endVertex();

                    for (int section = 0; section <= 16; ++section) {
                        float sectionAngle = (float)section * (float) (Math.PI * 2) / 16.0F;
                        float angleX = Mth.sin(sectionAngle);
                        float angleZ = Mth.cos(sectionAngle);
                        bufferBuilder.vertex(pose, angleX * 120, angleZ * 120, -angleZ * 40 * sunriseColour[3])
                                .color(sunriseColour[0], sunriseColour[1], sunriseColour[2], 0)
                                .endVertex();
                    }

                    BufferUploader.drawWithShader(bufferBuilder.end());
                    poseStack.popPose();
                }

                RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
                poseStack.pushPose();

                float rainAmount = 1 - level.getRainLevel(partialTick);
                float dayAngle = level.getTimeOfDay(partialTick) * 360;
                float celestialQuadRadius = 30;

                RenderSystem.setShaderColor(1, 1, 1, rainAmount);
                poseStack.mulPose(Axis.YP.rotationDegrees(-90));
                poseStack.mulPose(Axis.XP.rotationDegrees(dayAngle));
                Matrix4f pose = poseStack.last().pose();
                RenderSystem.setShader(GameRenderer::getPositionTexShader);

                if (dayAngle > 286 || dayAngle < 82) {
                    RenderSystem.setShaderTexture(0, SUN_TEXTURE);
                    bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
                    bufferBuilder.vertex(pose, -celestialQuadRadius, 100, -celestialQuadRadius).uv(0, 0).endVertex();
                    bufferBuilder.vertex(pose, celestialQuadRadius, 100, -celestialQuadRadius).uv(1, 0).endVertex();
                    bufferBuilder.vertex(pose, celestialQuadRadius, 100, celestialQuadRadius).uv(1, 1).endVertex();
                    bufferBuilder.vertex(pose, -celestialQuadRadius, 100, celestialQuadRadius).uv(0, 1).endVertex();
                    BufferUploader.drawWithShader(bufferBuilder.end());
                }

                if (dayAngle > 101 && dayAngle < 258) {
                    celestialQuadRadius = 20;
                    int moonPhaseIndex = level.getMoonPhase();
                    int phaseRow = moonPhaseIndex % 4;
                    int phaseColumn = moonPhaseIndex / 4 % 2;
                    float uMin = phaseRow / 4f;
                    float vMin = phaseColumn / 2f;
                    float uMax = (phaseRow + 1) / 4f;
                    float vMax = (phaseColumn + 1) / 2f;

                    RenderSystem.setShaderTexture(0, MOON_TEXTURE);
                    bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
                    bufferBuilder.vertex(pose, -celestialQuadRadius, -100, celestialQuadRadius).uv(uMax, vMax).endVertex();
                    bufferBuilder.vertex(pose, celestialQuadRadius, -100, celestialQuadRadius).uv(uMin, vMax).endVertex();
                    bufferBuilder.vertex(pose, celestialQuadRadius, -100, -celestialQuadRadius).uv(uMin, vMin).endVertex();
                    bufferBuilder.vertex(pose, -celestialQuadRadius, -100, -celestialQuadRadius).uv(uMax, vMin).endVertex();
                    BufferUploader.drawWithShader(bufferBuilder.end());
                }

                float starBrightness = level.getStarBrightness(partialTick) * rainAmount;

                if (starBrightness > 0) {
                    RenderSystem.setShaderColor(starBrightness, starBrightness, starBrightness, starBrightness);
                    FogRenderer.setupNoFog();
                    levelRenderer.starBuffer.bind();
                    levelRenderer.starBuffer.drawWithShader(poseStack.last().pose(), projectionMatrix, GameRenderer.getPositionShader());
                    VertexBuffer.unbind();
                    setupFog.run();
                }

                RenderUtil.resetShaderColour();
                RenderSystem.disableBlend();
                RenderSystem.defaultBlendFunc();
                poseStack.popPose();
                RenderSystem.setShaderColor(0, 0, 0, 1);
                double darknessDepth = mc.player.getEyePosition(partialTick).y - level.getLevelData().getHorizonHeight(level);

                if (darknessDepth < 0.0) {
                    poseStack.pushPose();
                    poseStack.translate(0, 12, 0);
                    levelRenderer.darkBuffer.bind();
                    levelRenderer.darkBuffer.drawWithShader(poseStack.last().pose(), projectionMatrix, shader);
                    VertexBuffer.unbind();
                    poseStack.popPose();
                }

                RenderUtil.resetShaderColour();
                RenderSystem.depthMask(true);
            }
        }
        
        return true;
    }

    @Nullable
    @Override
    public float[] getSunriseColor(float pTimeOfDay, float pPartialTicks) {
        return super.getSunriseColor(pTimeOfDay, pPartialTicks);
    }

    @Override
    public boolean tickRain(ClientLevel level, int ticks, Camera camera) {
        return true;
    }

    @Override
    public Vec3 getBrightnessDependentFogColor(Vec3 fogColour, float brightness) {
        return fogColour.multiply(brightness * 0.94F + 0.06F, brightness * 0.94F + 0.06F, brightness * 0.91F + 0.09F);
    }

    @Override
    public boolean isFoggyAt(int pX, int pY) {
        return false;
    }
}
