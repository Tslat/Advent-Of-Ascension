package net.tslat.aoa3.content.world.gen.structure.structures.special;

import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.jigsaw.JigsawPiece;
import net.minecraft.world.gen.feature.structure.AbstractVillagePiece;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.content.world.gen.structure.JigsawAssembler;
import net.tslat.aoa3.content.world.gen.structure.structures.AoAStructureBase;
import net.tslat.aoa3.content.world.gen.structure.structures.AoAStructureStart;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class HauntedDungeonStructure extends AoAStructureBase<NoFeatureConfig> {
	private static final List<MobSpawnInfo.Spawners> DEFAULT_MOBS = Arrays.asList(
			new MobSpawnInfo.Spawners(EntityType.CAVE_SPIDER, 5, 1, 3),
			new MobSpawnInfo.Spawners(EntityType.SPIDER, 7, 1, 2),
			new MobSpawnInfo.Spawners(AoAEntities.Mobs.BANSHEE.get(), 2, 1, 1),
			new MobSpawnInfo.Spawners(AoAEntities.Mobs.UNDEAD_TROLL.get(), 2, 1, 1),
			new MobSpawnInfo.Spawners(AoAEntities.Mobs.NIGHTMARE_SPIDER.get(), 2, 1, 1));

	public HauntedDungeonStructure(GenerationStage.Decoration decorationStage, String templatePoolPath) {
		super(NoFeatureConfig.CODEC, decorationStage, templatePoolPath);
	}

	@Override
	public boolean getDefaultRestrictsSpawnsToInside() {
		return true;
	}

	@Override
	public List<MobSpawnInfo.Spawners> getDefaultSpawnList() {
		return DEFAULT_MOBS;
	}

	@Override
	protected AoAStartFactory<NoFeatureConfig> getStructureStart() {
		return (structure, chunkX, chunkZ, boundingBox, references, seed) -> new AoAStructureStart<NoFeatureConfig>(structure, chunkX, chunkZ, boundingBox, references, seed) {
			@Override
			protected void generateStructurePieces(DynamicRegistries registries, int maxDepth, ChunkGenerator chunkGenerator, TemplateManager templateManager, BlockPos chunkCenter, Random rand, boolean bool1, boolean generateOnSurface, NoFeatureConfig structureConfig) {
				Structure.bootstrap();

				VillageConfig config = new VillageConfig(() -> registries.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY).get(getFeature().getTemplatePoolPath()), maxDepth);
				JigsawManager.IPieceFactory factory = AbstractVillagePiece::new;
				List<? super AbstractVillagePiece> pieces = getPieces();
				Rotation rotation = shouldAvoidRotating() ? Rotation.NONE : Rotation.getRandom(rand);
				JigsawPattern jigsawpattern = config.startPool().get();
				JigsawPiece jigsawpiece = jigsawpattern.getRandomTemplate(rand);
				AbstractVillagePiece villagePiece = factory.create(templateManager, jigsawpiece, chunkCenter, jigsawpiece.getGroundLevelDelta(), rotation, jigsawpiece.getBoundingBox(templateManager, chunkCenter, rotation));
				MutableBoundingBox villageBoundingBox = villagePiece.getBoundingBox();
				int centerX = (villageBoundingBox.x1 + villageBoundingBox.x0) / 2;
				int centerZ = (villageBoundingBox.z1 + villageBoundingBox.z0) / 2;
				int centerY = chunkCenter.getY();
				int yOffset = villageBoundingBox.y0 + villagePiece.getGroundLevelDelta();

				if (generateOnSurface)
					centerY = chunkCenter.getY() + chunkGenerator.getFirstFreeHeight(centerX, centerZ, Heightmap.Type.WORLD_SURFACE_WG);

				villagePiece.move(0, centerY + 1 - yOffset, 0);
				pieces.add(villagePiece);

				if (config.maxDepth() > 0)
					JigsawAssembler.preGenPieces(villagePiece, new JigsawAssembler(registries.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY), config.maxDepth(), factory, chunkGenerator, templateManager, pieces, rand) {
						@Nullable
						@Override
						protected Pair<JigsawPattern, JigsawPattern> getPools(Template.BlockInfo jigsawInfo, int yMin) {
							if (yMin > 60)
								return super.getPools(jigsawInfo, yMin);

							ResourceLocation basePool = new ResourceLocation(jigsawInfo.nbt.getString("pool"));
							JigsawPattern newPool = null;

							if (basePool.getPath().contains("large_rooms"))
								return super.getPools(jigsawInfo, yMin);

							if (yMin <= 15)
								return null;

							if (basePool.getPath().endsWith("medium_rooms")) {
								if (yMin <= 35 || rand.nextFloat() < 5 / (float)yMin) {
									newPool = getPool(new ResourceLocation(basePool.getNamespace(), basePool.getPath() + "_to_large"));
								}
								else {
									newPool = getPool(basePool);
								}
							}
							else if (basePool.getPath().endsWith("small_rooms")) {
								if (yMin <= 50 || rand.nextFloat() < 2 / (float)yMin) {
									newPool = getPool(new ResourceLocation(basePool.getNamespace(), basePool.getPath() + "_to_med"));
								}
								else {
									newPool = getPool(basePool);
								}
							}

							if (newPool == null)
								return null;

							JigsawPattern fallbackPool = getPool(newPool.getFallback());

							if (fallbackPool == null)
								return null;

							return Pair.of(newPool, fallbackPool);
						}
					});

				calculateBoundingBox();
				doPostPlacementOperations(maxDepth, chunkGenerator, chunkCenter, rand);
			}
		};
	}
}
