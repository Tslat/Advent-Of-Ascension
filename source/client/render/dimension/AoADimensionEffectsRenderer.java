package net.tslat.aoa3.client.render.dimension;

import com.mojang.blaze3d.vertex.PoseStack;
import it.unimi.dsi.fastutil.floats.FloatConsumer;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.FogType;
import net.minecraft.world.phys.Vec3;
import net.tslat.tme.api.object.EasyRandom;
import net.tslat.tme.api.util.BlockRetrievalUtil;
import org.apache.commons.lang3.tuple.Triple;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import java.util.List;

public abstract class AoADimensionEffectsRenderer extends DimensionSpecialEffects {
    protected static final ResourceLocation MOON_TEXTURE = ResourceLocation.withDefaultNamespace("textures/environment/moon_phases.png");
    protected static final ResourceLocation SUN_TEXTURE = ResourceLocation.withDefaultNamespace("textures/environment/sun.png");

    protected final boolean noClouds;
    protected final boolean noSky;
    protected boolean noWeather = false;

    protected AoADimensionEffectsRenderer(float cloudHeight, boolean hasGround, SkyType skyType, boolean forceBrightLightmap, boolean constantAmbientLight) {
        super(cloudHeight, hasGround, skyType, forceBrightLightmap, constantAmbientLight);

        this.noClouds = Float.isNaN(cloudHeight);
        this.noSky = skyType == SkyType.NONE;
    }

    protected void noWeather() {
        this.noWeather = true;
    }

    @Nullable
    @Override
    public float[] getSunriseColor(float timeOfDay, float partialTick) {
        return super.getSunriseColor(timeOfDay, partialTick);
    }

    @Override
    public Vec3 getBrightnessDependentFogColor(Vec3 fogColour, float brightness) {
        return fogColour.multiply(brightness * 0.94f + 0.06F, brightness * 0.94f + 0.06f, brightness * 0.91f + 0.09f);
    }

    @Override
    public boolean isFoggyAt(int posX, int posY) {
        return false;
    }

    @Override
    public boolean renderClouds(ClientLevel level, int ticks, float partialTick, PoseStack poseStack, double camX, double camY, double camZ, Matrix4f frustumMatrix, Matrix4f projectionMatrix) {
        return this.noClouds;
    }

    @Override
    public boolean renderSky(ClientLevel level, int ticks, float partialTick, Matrix4f frustumMatrix, Camera camera, Matrix4f projectionMatrix, boolean isFoggy, Runnable setupFog) {
        return this.noSky;
    }

    @Override
    public boolean renderSnowAndRain(ClientLevel level, int ticks, float partialTick, LightTexture lightTexture, double camX, double camY, double camZ) {
        return this.noWeather;
    }

    @Override
    public boolean tickRain(ClientLevel level, int ticks, Camera camera) {
        return this.noWeather;
    }

    @Override
    public void adjustLightmapColors(ClientLevel level, float partialTicks, float skyDarken, float blockLightRedFlicker, float skyLight, int pixelX, int pixelY, Vector3f colors) {}

    public void adjustFogRender(ClientLevel level, FogRenderer.FogMode fogMode, FogType fogType, Camera camera, FloatConsumer farPlaneDistance, FloatConsumer nearPlaneDistance) {}

    public void doFXTick(ClientLevel level, BlockPos playerPos) {
        final ChunkPos chunkPos = new ChunkPos(playerPos);

        if (!level.getChunkSource().hasChunk(chunkPos.x + 2, chunkPos.z + 2))
            return;

        final EasyRandom random = EasyRandom.wrap(level.random);
        final Block markerParticleTarget = level.getMarkerParticleTarget();
        final List<Triple<BlockState, FluidState, BlockPos>> fxBlocks = BlockRetrievalUtil.getBlocks(level, reader -> {
            List<Triple<BlockState, FluidState, BlockPos>> blocks = new ObjectArrayList<>();

            for (int radius = 16; radius <= 32; radius += 16) {
                for (int i = 0; i < 667; i++) {
                    BlockPos blockPos = playerPos.offset(random.numberBetween(-radius, radius), random.numberBetween(-radius, radius), random.numberBetween(-radius, radius));

                    blocks.add(Triple.of(reader.getBlockState(blockPos), reader.getFluidState(blockPos), blockPos));
                }
            }

            return blocks;
        });

        for (Triple<BlockState, FluidState, BlockPos> block : fxBlocks) {
            tickBlockFx(level, block, playerPos, random, markerParticleTarget);
        }
    }

    public void tickBlockFx(ClientLevel level, Triple<BlockState, FluidState, BlockPos> block, BlockPos playerPos, EasyRandom random, Block markerParticleTarget) {
        BlockState state = block.getLeft();
        BlockPos pos = block.getRight();

        state.getBlock().animateTick(state, level, pos, random);

        if (!block.getMiddle().isEmpty())
            doFluidFXTick(level, block, playerPos, random);

        if (state.is(markerParticleTarget))
            level.addParticle(new BlockParticleOption(ParticleTypes.BLOCK_MARKER, state), pos.getX() + 0.5d, pos.getY() + 0.5d, pos.getZ() + 0.5d, 0, 0, 0);

        if (!state.isCollisionShapeFullBlock(level, pos))
            doAmbientParticleFXTick(level, pos, random, playerPos);
    }

    protected void doFluidFXTick(ClientLevel level, Triple<BlockState, FluidState, BlockPos> block, BlockPos playerPos, EasyRandom random) {
        ParticleOptions dripParticle = block.getMiddle().getDripParticle();
        BlockPos pos = block.getRight();

        block.getMiddle().animateTick(level, pos, random);

        if (dripParticle != null && level.random.nextInt(10) == 0) {
            BlockPos belowPos = pos.below();

            level.trySpawnDripParticles(belowPos, level.getBlockState(belowPos), dripParticle, block.getLeft().isFaceSturdy(level, pos, Direction.DOWN));
        }
    }

    protected void doAmbientParticleFXTick(ClientLevel level, BlockPos pos, EasyRandom random, BlockPos playerPos) {
        Biome biome = level.getBiome(pos).value();

        if (spawnAmbientParticle(level, pos, biome, random))
            return;

        biome.getAmbientParticle().ifPresent(settings -> {
            if (settings.canSpawn(random)) {
                level.addParticle(
                        settings.getOptions(),
                        pos.getX() + random.nextDouble(),
                        pos.getY() + random.nextDouble(),
                        pos.getZ() + random.nextDouble(),
                        0,
                        0,
                        0);
            }
        });
    }

    public boolean spawnAmbientParticle(ClientLevel level, BlockPos pos, Biome biome, EasyRandom random) {
        return false;
    }
}
