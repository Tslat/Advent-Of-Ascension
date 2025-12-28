package net.tslat.aoa3.client.render.dimension;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import it.unimi.dsi.fastutil.floats.FloatConsumer;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.material.FogType;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.ClientOperations;
import net.tslat.aoa3.common.particleoption.EntityTrackingParticleOptions;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.common.registration.custom.AoAWorldEvents;
import net.tslat.aoa3.content.world.event.AoAWorldEventManager;
import net.tslat.aoa3.content.world.event.BarathosSandstormEvent;
import net.tslat.tme.api.object.EasyRandom;
import net.tslat.tme.api.particle.ParticleBuilder;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;

public class BarathosRenderingEffects extends AoADimensionEffectsRenderer {
    public static final ResourceLocation ID = AdventOfAscension.id("barathos");

    private float currentLight = 15;
    private BarathosSandstormEvent sandstorm = null;

    BarathosRenderingEffects() {
        super(Float.NaN, true, DimensionSpecialEffects.SkyType.NORMAL, false, false);
    }

    @Override
    public void adjustFogRender(ClientLevel level, FogRenderer.FogMode fogMode, FogType fogType, Camera camera, FloatConsumer farPlaneDistance, FloatConsumer nearPlaneDistance) {
        if (true)
            return;
        final float cameraHeight = (float)camera.getPosition().y;

        if (cameraHeight < 80 || (cameraHeight > 125 && fogMode == FogRenderer.FogMode.FOG_SKY))
            return;

        float skyLight = level.getBrightness(LightLayer.SKY, camera.getBlockPosition());

        if (currentLight != skyLight) {
            final float signum = Math.signum(skyLight - currentLight);
            skyLight = (currentLight = Mth.clamp(currentLight + signum * (signum < 0 ? 0.005f : 0.01f), 0, 15));
        }

        if (skyLight == 0)
            return;

        final float delta = Math.abs(cameraHeight - 105);
        final float fogCeiling = Math.max(0, cameraHeight - 142);
        final float caveDelta = Math.max(0, 1 - (skyLight / 15f));

        farPlaneDistance.accept(Mth.clamp(150 - delta * 4, 50, 150) + fogCeiling);
        nearPlaneDistance.accept(Mth.clamp(-150 + delta * 7 + caveDelta * 170, -150, 20) + fogCeiling);
    }

    @Override
    public void doFXTick(ClientLevel level, BlockPos playerPos) {
        this.sandstorm = AoAWorldEventManager.getEventById(level, AoAWorldEvents.BARATHOS_SANDSTORM.getId()) instanceof BarathosSandstormEvent event && event.isActive() ? event : null;

        super.doFXTick(level, playerPos);
    }

    @Override
    public boolean spawnAmbientParticle(ClientLevel level, BlockPos pos, Biome biome, EasyRandom random) {
        if (pos.getY() > 125)
            return true;

        if (pos.getY() >= 85) {
            final long gameTick = level.getGameTime();

            if (this.sandstorm != null) {
                if (level.getBrightness(LightLayer.SKY, pos) == 15) {
                    float intensity = this.sandstorm.getIntensity(gameTick);
                    float rotProgress = ((gameTick / 60f) % 360) * Mth.DEG_TO_RAD;
                    Vec3 angle = new Vec3(Math.cos(rotProgress), 0, Math.sin(rotProgress)).scale(Mth.sin(((gameTick / (gameTick % 1000 > 250 ? 20f : 1f)) % 360) * Mth.DEG_TO_RAD) * 0.4f * intensity + 0.9f);

                    ParticleBuilder.forPositions(EntityTrackingParticleOptions.ambient(AoAParticleTypes.SANDSTORM), Vec3.atLowerCornerOf(pos).add(random.nextDouble(), random.nextDouble(), random.nextDouble()))
                            .scaleMod(0.6f * intensity)
                            .lifespan(Mth.ceil(5 / random.valueBetween(0.2f, 1f)))
                            .colourTint(0xC4C0A1)
                            .velocity(angle)
                            .spawnClientParticles(level);
                }
            }
            else if (0.01f * 0.98572f > random.nextFloat()) {
                float rotProgress = ((gameTick / 60f) % 360) * Mth.DEG_TO_RAD;
                Vec3 angle = new Vec3(Math.cos(rotProgress), 0, Math.sin(rotProgress)).scale(Mth.sin(((gameTick / (gameTick % 1000 > 250 ? 20f : 1f)) % 360) * Mth.DEG_TO_RAD) * 0.3f);

                ParticleBuilder.forPositions(EntityTrackingParticleOptions.ambient(AoAParticleTypes.SANDSTORM), Vec3.atLowerCornerOf(pos).add(random.nextDouble(), random.nextDouble(), random.nextDouble()))
                        .scaleMod(0.3f)
                        .lifespan(Mth.ceil(10 / random.valueBetween(0.2f, 1f)))
                        .colourTint(0xC4C0A1)
                        .velocity(angle)
                        .spawnClientParticles(level);
            }
        }
        else if (0.1f * 0.98572f >= random.nextFloat()) {
            ParticleBuilder.forPositions(ParticleTypes.SMOKE, Vec3.atLowerCornerOf(pos).add(random.nextDouble(), random.nextDouble(), random.nextDouble()))
                    .lifespan(Mth.ceil(5 / random.valueBetween(0.2f, 1f)))
                    .velocity(0, 0.05f, 0)
                    .spawnClientParticles(level);
        }

        return true;
    }

    @Override
    public boolean isFoggyAt(int posX, int posY) {
        //if (true)return false;

        return posY > 85 && posY < 105;
    }

    @Nullable
    @Override
    public float[] getSunriseColor(float timeOfDay, float partialTick) {
        return super.getSunriseColor(timeOfDay, partialTick);
    }

    @Override
    public Vec3 getBrightnessDependentFogColor(Vec3 fogColour, float brightness) {
        double delta = 1 - Mth.clamp((70 - ClientOperations.getPlayer().getY()) / 10f, 0, 1);

        return fogColour.multiply(Math.max(168 / 255f, delta), Math.max(19 / 255f, delta), Math.max(0, delta));
    }

    @Override
    public boolean renderSky(ClientLevel level, int ticks, float partialTick, Matrix4f frustumMatrix, Camera camera, Matrix4f projectionMatrix, boolean isFoggy, Runnable setupFog) {
        Minecraft mc = Minecraft.getInstance();
        LevelRenderer levelRenderer = mc.levelRenderer;

        setupFog.run();

        FogType screenFogType = camera.getFluidInCamera();

        if (screenFogType != FogType.POWDER_SNOW && screenFogType != FogType.LAVA && !levelRenderer.doesMobEffectBlockSky(camera)) {
            PoseStack poseStack = new PoseStack();

            poseStack.mulPose(frustumMatrix);

            if (mc.level.effects().skyType() == SkyType.END) {
                levelRenderer.renderEndSky(poseStack);
            }
            else if (mc.level.effects().skyType() == SkyType.NORMAL) {
                Vec3 skyColour = level.getSkyColor(mc.gameRenderer.getMainCamera().getPosition(), partialTick);
                Tesselator tesselator = Tesselator.getInstance();
                ShaderInstance shaderInstance = RenderSystem.getShader();
                float rainStrength = 1 - level.getRainLevel(partialTick);
                float[] sunriseColour = level.effects().getSunriseColor(level.getTimeOfDay(partialTick), partialTick);

                FogRenderer.levelFogColor();
                RenderSystem.depthMask(false);
                RenderSystem.setShaderColor((float)skyColour.x, (float)skyColour.y, (float)skyColour.z, 1);

                levelRenderer.skyBuffer.bind();
                levelRenderer.skyBuffer.drawWithShader(poseStack.last().pose(), projectionMatrix, shaderInstance);
                VertexBuffer.unbind();
                RenderSystem.enableBlend();

                if (sunriseColour != null) {
                    RenderSystem.setShader(GameRenderer::getPositionColorShader);
                    RenderSystem.setShaderColor(1, 1, 1, 1);
                    poseStack.pushPose();
                    poseStack.mulPose(Axis.XP.rotationDegrees(90));
                    poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.sin(level.getSunAngle(partialTick)) < 0 ? 180 : 0));
                    poseStack.mulPose(Axis.ZP.rotationDegrees(90));

                    Matrix4f pose = poseStack.last().pose();
                    BufferBuilder buffer = tesselator.begin(VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION_COLOR);

                    buffer.addVertex(pose, 0, 100, 0).setColor(sunriseColour[0], sunriseColour[1], sunriseColour[2], sunriseColour[3]);

                    for (int i = 0; i <= 16; i++) {
                        float angle = i * Mth.TWO_PI / 16;
                        float cosine = Mth.cos(angle);

                        buffer.addVertex(pose, Mth.sin(angle) * 120, cosine * 120, -cosine * 40 * sunriseColour[3])
                                .setColor(sunriseColour[0], sunriseColour[1], sunriseColour[2], 0);
                    }

                    BufferUploader.drawWithShader(buffer.buildOrThrow());
                    poseStack.popPose();
                }

                RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
                poseStack.pushPose();
                RenderSystem.setShaderColor(1, 1, 1, rainStrength);
                poseStack.mulPose(Axis.YP.rotationDegrees(-90));
                poseStack.mulPose(Axis.XP.rotationDegrees(level.getTimeOfDay(partialTick) * 360));

                Matrix4f pose = poseStack.last().pose();
                float celestialRadius = 65;

                RenderSystem.setShader(GameRenderer::getPositionTexShader);
                RenderSystem.setShaderTexture(0, LevelRenderer.SUN_LOCATION);

                BufferBuilder buffer = tesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);

                buffer.addVertex(pose, -celestialRadius, 100, -celestialRadius).setUv(0, 0);
                buffer.addVertex(pose, celestialRadius, 100, -celestialRadius).setUv(1, 0);
                buffer.addVertex(pose, celestialRadius, 100, celestialRadius).setUv(1, 1);
                buffer.addVertex(pose, -celestialRadius, 100, celestialRadius).setUv(0, 1);
                buffer.addVertex(pose, -celestialRadius + 40, 100 + 55, -celestialRadius + 40).setUv(0, 0);
                buffer.addVertex(pose, celestialRadius + 40, 100 + 55, -celestialRadius + 40).setUv(1, 0);
                buffer.addVertex(pose, celestialRadius + 40, 100 + 55, celestialRadius + 40).setUv(1, 1);
                buffer.addVertex(pose, -celestialRadius + 40, 100 + 55, celestialRadius + 40).setUv(0, 1);
                BufferUploader.drawWithShader(buffer.buildOrThrow());

                if (level.dimensionType().fixedTime().orElse(0) != 2000) {
                    float starBrightness = level.getStarBrightness(partialTick) * rainStrength;
                    int moonPhase = level.getMoonPhase();
                    int uColumn = moonPhase % 4;
                    int vRow = moonPhase / 4 % 2;
                    float uMin = (float)(uColumn) / 4f;
                    float vMin = (float)(vRow) / 2f;
                    float uMax = (float)(uColumn + 1) / 4f;
                    float vMax = (float)(vRow + 1) / 2f;
                    celestialRadius = 20f;
                    buffer = tesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);

                    RenderSystem.setShaderTexture(0, LevelRenderer.MOON_LOCATION);
                    buffer.addVertex(pose, -celestialRadius, -100, celestialRadius).setUv(uMax, vMax);
                    buffer.addVertex(pose, celestialRadius, -100, celestialRadius).setUv(uMin, vMax);
                    buffer.addVertex(pose, celestialRadius, -100, -celestialRadius).setUv(uMin, vMin);
                    buffer.addVertex(pose, -celestialRadius, -100, -celestialRadius).setUv(uMax, vMin);
                    BufferUploader.drawWithShader(buffer.buildOrThrow());

                    if (starBrightness > 0) {
                        RenderSystem.setShaderColor(starBrightness, starBrightness, starBrightness, starBrightness);
                        FogRenderer.setupNoFog();
                        levelRenderer.starBuffer.bind();
                        levelRenderer.starBuffer.drawWithShader(poseStack.last().pose(), projectionMatrix, GameRenderer.getPositionShader());
                        VertexBuffer.unbind();
                        setupFog.run();
                    }

                }

                RenderSystem.setShaderColor(1, 1, 1, 1);
                RenderSystem.disableBlend();
                RenderSystem.defaultBlendFunc();
                poseStack.popPose();
                RenderSystem.setShaderColor(0, 0, 0, 1);

                if (mc.player.getEyePosition(partialTick).y - level.getLevelData().getHorizonHeight(level) < 0) {
                    poseStack.pushPose();
                    poseStack.translate(0, 12, 0);
                    levelRenderer.darkBuffer.bind();
                    levelRenderer.darkBuffer.drawWithShader(poseStack.last().pose(), projectionMatrix, shaderInstance);
                    VertexBuffer.unbind();
                    poseStack.popPose();
                }

                RenderSystem.setShaderColor(1, 1, 1, 1);
                RenderSystem.depthMask(true);
            }
        }

        return true;
    }
}
