package net.tslat.aoa3.content.world.gen.structure;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.WorldGenerationContext;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;
import net.minecraft.world.level.levelgen.structure.pools.DimensionPadding;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.pools.alias.PoolAliasBinding;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;
import net.minecraft.world.level.levelgen.structure.templatesystem.LiquidSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.tslat.aoa3.common.registration.worldgen.AoAStructureTypes;

import java.util.List;
import java.util.Optional;

public class AoAStructure extends Structure {
	public static final MapCodec<AoAStructure> DEFAULT_CODEC = RecordCodecBuilder.mapCodec(codec -> codec.group(
			Structure.settingsCodec(codec),
			JigsawSettings.jigsawSettingsCodec(),
			Settings.extraSettingsCodec()
	).apply(codec, AoAStructure::new));

	protected final JigsawSettings jigsawSettings;
	protected final Settings extraSettings;
	protected final AoAJigsawAssembler assembler;

	public AoAStructure(StructureSettings structureSettings, JigsawSettings jigsawSettings, Settings extraSettings) {
		super(structureSettings);

		this.jigsawSettings = jigsawSettings;
		this.extraSettings = extraSettings;
		this.assembler = getJigsawAssembler();
	}

	protected AoAJigsawAssembler getJigsawAssembler() {
		return new AoAJigsawAssembler() {
			@Override
			protected Optional<GenerationStub> buildGenerationStub(PoolElementStructurePiece startPiece, BoundingBox startPieceBounds, GenerationContext genContext,
																   BlockPos structureOrigin, int surfaceY, int maxPieces, int maxRadius, DimensionPadding padding, LiquidSettings liquidSettings) {

				if (AoAStructure.this.extraSettings.surfaceTest.isPresent()) {
					RuleTest predicate = AoAStructure.this.extraSettings.surfaceTest.get();
					WorldgenRandom rand = genContext.random();
					int startX = structureOrigin.getX();
					int startZ = structureOrigin.getZ();

					for (int i = 0; i < 5; i++) {
						int x = rand.nextInt(startPieceBounds.maxX() - startPieceBounds.minX());
						int z = rand.nextInt(startPieceBounds.maxZ() - startPieceBounds.minZ());
						BlockState blockState = genContext.chunkGenerator().getBaseColumn(startX + x, startZ + z, genContext.heightAccessor(), genContext.randomState()).getBlock(surfaceY);

						if (!predicate.test(blockState, genContext.random()))
							return Optional.empty();
					}
				}

				return Optional.of(new Structure.GenerationStub(getStartPos(startPiece, structureOrigin), pieceBuilder ->
						compileGenerationStub(this, pieceBuilder, startPiece, startPieceBounds, genContext, structureOrigin, surfaceY, maxPieces, maxRadius, padding, liquidSettings)));
			}
		};
	}

	@Override
	public Optional<GenerationStub> findGenerationPoint(GenerationContext genContext) {
		BlockPos startPos = findStartPos(genContext);

		return this.assembler.addPieces(genContext, this.jigsawSettings.startPool, this.jigsawSettings.startJigsawName, this.jigsawSettings.maxPieces,
										startPos, this.jigsawSettings.startHeightmap, 128, this.jigsawSettings.padding, this.jigsawSettings.liquidSettings);
	}

	protected BlockPos findStartPos(GenerationContext genContext) {
		ChunkPos chunkpos = genContext.chunkPos();
		int startHeight = this.jigsawSettings.startHeight.sample(genContext.random(), new WorldGenerationContext(genContext.chunkGenerator(), genContext.heightAccessor()));

		return new BlockPos(chunkpos.getMinBlockX(), startHeight, chunkpos.getMinBlockZ());
	}

	protected void compileGenerationStub(AoAJigsawAssembler jigsawAssembler, StructurePiecesBuilder pieceBuilder, PoolElementStructurePiece startPiece, BoundingBox startPieceBounds,
										 Structure.GenerationContext genContext, BlockPos structureOrigin, int surfaceY, int maxPieces, int maxRadius, DimensionPadding padding, LiquidSettings liquidSettings) {
		List<PoolElementStructurePiece> pieces = new ObjectArrayList<>();
		LevelHeightAccessor heightAccessor = genContext.heightAccessor();

		pieces.add(startPiece);

		if (maxPieces > 0) {
			jigsawAssembler.addPieces(
					genContext.randomState(),
					maxPieces,
					genContext.chunkGenerator(),
					genContext.structureTemplateManager(),
					heightAccessor,
					genContext.random(),
					genContext.registryAccess().registryOrThrow(Registries.TEMPLATE_POOL),
					startPiece,
					pieces,
					Shapes.join(
							Shapes.create(new AABB(structureOrigin.getX() - maxRadius, Math.max(heightAccessor.getMinBuildHeight(), heightAccessor.getMinBuildHeight() + padding.bottom()), structureOrigin.getZ() - maxRadius,
												   structureOrigin.getX() + maxRadius + 1, Math.min(heightAccessor.getMaxBuildHeight(), heightAccessor.getMaxBuildHeight() - padding.top()), structureOrigin.getZ() + maxRadius + 1)),
							Shapes.create(AABB.of(startPieceBounds)), BooleanOp.ONLY_FIRST),
					liquidSettings);
			pieces.forEach(pieceBuilder::addPiece);
		}

		int verticalOffset = (AoAStructure.this.extraSettings.alignToTop ? surfaceY - startPieceBounds.maxY() : 0) + this.extraSettings.yOffset.sample(genContext.random());

		if (verticalOffset != 0)
			pieceBuilder.offsetPiecesVertically(verticalOffset);
	}

	@Override
	public StructureType<? extends AoAStructure> type() {
		return AoAStructureTypes.AOA_DEFAULT.get();
	}

	public record JigsawSettings(Holder<StructureTemplatePool> startPool, Optional<ResourceLocation> startJigsawName, int maxPieces,
								 HeightProvider startHeight, Optional<Heightmap.Types> startHeightmap, List<PoolAliasBinding> poolAliases, DimensionPadding padding, LiquidSettings liquidSettings) {
		private static final MapCodec<JigsawSettings> CODEC = RecordCodecBuilder.mapCodec(builder -> builder.group(
				StructureTemplatePool.CODEC.fieldOf("start_pool").forGetter(JigsawSettings::startPool),
				ResourceLocation.CODEC.optionalFieldOf("start_jigsaw_name").forGetter(JigsawSettings::startJigsawName),
				Codec.intRange(0, 20).fieldOf("size").forGetter(JigsawSettings::maxPieces),
				HeightProvider.CODEC.fieldOf("start_height").forGetter(JigsawSettings::startHeight),
				Heightmap.Types.CODEC.optionalFieldOf("project_start_to_heightmap").forGetter(JigsawSettings::startHeightmap),
				Codec.list(PoolAliasBinding.CODEC).optionalFieldOf("pool_aliases", List.of()).forGetter(JigsawSettings::poolAliases),
				DimensionPadding.CODEC.optionalFieldOf("dimension_padding", JigsawStructure.DEFAULT_DIMENSION_PADDING).forGetter(JigsawSettings::padding),
				LiquidSettings.CODEC.optionalFieldOf("liquid_settings", JigsawStructure.DEFAULT_LIQUID_SETTINGS).forGetter(JigsawSettings::liquidSettings)
		).apply(builder, JigsawSettings::new));

		public static <T extends AoAStructure> RecordCodecBuilder<T, JigsawSettings> jigsawSettingsCodec() {
			return JigsawSettings.CODEC.forGetter(instance -> instance.jigsawSettings);
		}
	}

	public record Settings(IntProvider yOffset, Optional<RuleTest> surfaceTest, boolean alignToTop) {
		private static final MapCodec<Settings> CODEC = RecordCodecBuilder.mapCodec(builder -> builder.group(
				IntProvider.CODEC.optionalFieldOf("y_offset", ConstantInt.of(0)).forGetter(Settings::yOffset),
				RuleTest.CODEC.optionalFieldOf("surface_test").forGetter(Settings::surfaceTest),
				Codec.BOOL.optionalFieldOf("align_to_top", false).forGetter(Settings::alignToTop)
		).apply(builder, Settings::new));

		public static <T extends AoAStructure> RecordCodecBuilder<T, Settings> extraSettingsCodec() {
			return Settings.CODEC.forGetter(instance -> instance.extraSettings);
		}
	}
}
