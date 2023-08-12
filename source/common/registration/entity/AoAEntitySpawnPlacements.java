package net.tslat.aoa3.common.registration.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.registries.ForgeRegistries;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.content.entity.mob.nether.NethengeicBeastEntity;
import net.tslat.aoa3.util.WorldUtil;
import org.apache.logging.log4j.Level;

import static net.minecraft.world.entity.SpawnPlacements.Type.*;
import static net.minecraft.world.level.levelgen.Heightmap.Types.MOTION_BLOCKING;
import static net.minecraft.world.level.levelgen.Heightmap.Types.MOTION_BLOCKING_NO_LEAVES;

public final class AoAEntitySpawnPlacements {
    private static final SpawnPlacements.Type AMPHIBIOUS = SpawnPlacements.Type.create("AMPHIBIOUS", ((level, pos, entityType) -> NaturalSpawner.canSpawnAtBody((level.getFluidState(pos).isEmpty() ? ON_GROUND : IN_WATER), level, pos, entityType)));

    public static void lateInit() {
        Logging.logStatusMessage("Setting entity spawn placements");

        setOverworldSpawnPlacements();
        setNetherSpawnPlacements();
        setPrecasiaSpawnPlacements();
        setMiscSpawnPlacements();
    }

    private static void setOverworldSpawnPlacements() {
        setSpawnPlacement(AoAMobs.ANCIENT_GOLEM.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnBuilder.DEFAULT_DAY_MONSTER.noLowerThanY(65));
        setSpawnPlacement(AoAMobs.BOMB_CARRIER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnBuilder.DEFAULT_DAY_MONSTER.noLowerThanY(55).spawnChance(1 / 5f));
        setSpawnPlacement(AoAMobs.BUSH_BABY.get(), ON_GROUND, MOTION_BLOCKING, SpawnBuilder.DEFAULT_DAY_MONSTER.noLowerThanY(65));
        setSpawnPlacement(AoAMobs.CHARGER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnBuilder.DEFAULT_DAY_MONSTER);
        setSpawnPlacement(AoAMobs.CHOMPER.get(), AMPHIBIOUS, MOTION_BLOCKING_NO_LEAVES, SpawnBuilder.DEFAULT_DAY_NIGHT_MONSTER.difficultyBasedSpawnChance(0.1f));
        setSpawnPlacement(AoAMobs.CYCLOPS.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnBuilder.DEFAULT_DAY_MONSTER.noLowerThanY(55));
        setSpawnPlacement(AoAMobs.GHOST.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnBuilder.DEFAULT_MONSTER.noHigherThanY(0).spawnChance(1 / 2f));
        setSpawnPlacement(AoAMobs.GOBLIN.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnBuilder.DEFAULT_DAY_MONSTER);
        setSpawnPlacement(AoAMobs.ICE_GIANT.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnBuilder.DEFAULT_DAY_MONSTER.spawnChance(1 / 15f));
        setSpawnPlacement(AoAMobs.KING_CHARGER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnBuilder.DEFAULT_DAY_MONSTER.spawnChance(1 / 20f));
        setSpawnPlacement(AoAMobs.LEAFY_GIANT.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnBuilder.DEFAULT_DAY_MONSTER.spawnChance(1 / 15f));
        setSpawnPlacement(AoAMobs.SAND_GIANT.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnBuilder.DEFAULT_DAY_MONSTER.spawnChance(1 / 15f));
        setSpawnPlacement(AoAMobs.SASQUATCH.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnBuilder.DEFAULT_DAY_MONSTER.noLowerThanY(55));
        setSpawnPlacement(AoAMobs.STONE_GIANT.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnBuilder.DEFAULT_DAY_MONSTER.spawnChance(1 / 15f));
        setSpawnPlacement(AoAMobs.TREE_SPIRIT.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnBuilder.DEFAULT_DAY_MONSTER.noLowerThanY(55).spawnChance(1 / 10f));
        setSpawnPlacement(AoAMobs.VOID_WALKER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnBuilder.DEFAULT_MONSTER.noHigherThanY(0));
        setSpawnPlacement(AoAMobs.WOOD_GIANT.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnBuilder.DEFAULT_DAY_MONSTER.spawnChance(1 / 15f));
        setSpawnPlacement(AoAMobs.YETI.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnBuilder.DEFAULT_DAY_MONSTER.noLowerThanY(45).spawnChance(1 / 2f));
    }

    private static void setNetherSpawnPlacements() {
        setSpawnPlacement(AoAMobs.EMBRAKE.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, new SpawnBuilder<>().noPeacefulSpawn().spawnChance(1 / 2f).noSpawnOn(Blocks.NETHER_WART_BLOCK).ifValidSpawnBlock());
        setSpawnPlacement(AoAMobs.FLAMEWALKER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, new SpawnBuilder<>().noPeacefulSpawn().noSpawnOn(Blocks.NETHER_WART_BLOCK).ifValidSpawnBlock());
        setSpawnPlacement(AoAMobs.INFERNAL.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, new SpawnBuilder<>().noPeacefulSpawn().spawnChance(1 / 10f).noSpawnOn(Blocks.NETHER_WART_BLOCK).ifValidSpawnBlock());
        setSpawnPlacement(AoAMobs.LITTLE_BAM.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, new SpawnBuilder<>().noPeacefulSpawn().spawnChance(1 / 2f).noSpawnOn(Blocks.NETHER_WART_BLOCK).ifValidSpawnBlock());
        setSpawnPlacement(AoAMobs.NETHENGEIC_BEAST.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, new SpawnBuilder<>().noPeacefulSpawn().noSpawnOn(Blocks.NETHER_WART_BLOCK).ifValidSpawnBlock().and(NethengeicBeastEntity::checkSpawnConditions));
    }

    private static void setPrecasiaSpawnPlacements() {
        setSpawnPlacement(AoAMobs.SPINOLEDON.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnBuilder.DEFAULT_DAY_NIGHT_MONSTER.noLowerThanY(60).difficultyBasedSpawnChance(0.05f));
        setSpawnPlacement(AoAAnimals.HORNDRON.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnBuilder.DEFAULT_ANIMAL);
    }

    private static void setMiscSpawnPlacements() {
        setSpawnPlacement(AoAAnimals.SHINY_SQUID.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, new SpawnBuilder<>(GlowSquid::checkGlowSquideSpawnRules).spawnChance(1 / 1000f));

        setSpawnPlacement(AoANpcs.LOTTOMAN.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, new SpawnBuilder<>().ifValidSpawnBlock());
        setSpawnPlacement(AoANpcs.UNDEAD_HERALD.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, new SpawnBuilder<>().ifValidSpawnBlock());

        setSpawnPlacement(AoAAnimals.BLUE_GEMTRAP.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnBuilder.DEFAULT_FISH);
        setSpawnPlacement(AoAAnimals.CANDLEFISH.get(), IN_LAVA, MOTION_BLOCKING_NO_LEAVES, SpawnBuilder.DEFAULT_LAVA_FISH);
        setSpawnPlacement(AoAAnimals.CHARRED_CHAR.get(), IN_LAVA, MOTION_BLOCKING_NO_LEAVES, SpawnBuilder.DEFAULT_LAVA_FISH);
        setSpawnPlacement(AoAAnimals.CHOCAW.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnBuilder.DEFAULT_FISH);
        setSpawnPlacement(AoAAnimals.CRIMSON_SKIPPER.get(), IN_LAVA, MOTION_BLOCKING_NO_LEAVES, SpawnBuilder.DEFAULT_LAVA_FISH);
        setSpawnPlacement(AoAAnimals.CRIMSON_STRIPEFISH.get(), IN_LAVA, MOTION_BLOCKING_NO_LEAVES, SpawnBuilder.DEFAULT_LAVA_FISH);
        setSpawnPlacement(AoAAnimals.DARK_HATCHETFISH.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnBuilder.DEFAULT_FISH);
        setSpawnPlacement(AoAAnimals.GREEN_GEMTRAP.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnBuilder.DEFAULT_FISH);
        setSpawnPlacement(AoAAnimals.HYDRONE.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnBuilder.DEFAULT_FISH);
        setSpawnPlacement(AoAAnimals.IRONBACK.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnBuilder.DEFAULT_FISH);
        setSpawnPlacement(AoAAnimals.JAMFISH.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnBuilder.DEFAULT_FISH);
        setSpawnPlacement(AoAAnimals.PARAPIRANHA.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnBuilder.DEFAULT_FISH);
        setSpawnPlacement(AoAAnimals.PEARL_STRIPEFISH.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnBuilder.DEFAULT_FISH);
        setSpawnPlacement(AoAAnimals.PURPLE_GEMTRAP.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnBuilder.DEFAULT_FISH);
        setSpawnPlacement(AoAAnimals.RAINBOWFISH.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnBuilder.DEFAULT_FISH);
        setSpawnPlacement(AoAAnimals.RAZORFISH.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnBuilder.DEFAULT_FISH);
        setSpawnPlacement(AoAAnimals.RED_GEMTRAP.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnBuilder.DEFAULT_FISH);
        setSpawnPlacement(AoAAnimals.REEFTOOTH.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnBuilder.DEFAULT_FISH);
        setSpawnPlacement(AoAAnimals.ROCKETFISH.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnBuilder.DEFAULT_FISH);
        setSpawnPlacement(AoAAnimals.SAILBACK.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnBuilder.DEFAULT_FISH);
        setSpawnPlacement(AoAAnimals.SAPPHIRE_STRIDER.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnBuilder.DEFAULT_FISH);
        setSpawnPlacement(AoAAnimals.SKELECANTH.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnBuilder.DEFAULT_FISH);
        setSpawnPlacement(AoAAnimals.WHITE_GEMTRAP.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnBuilder.DEFAULT_FISH);
        setSpawnPlacement(AoAAnimals.YELLOW_GEMTRAP.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnBuilder.DEFAULT_FISH);
        setSpawnPlacement(AoAAnimals.TURQUOISE_STRIPEFISH.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnBuilder.DEFAULT_FISH);
        setSpawnPlacement(AoAAnimals.VIOLET_SKIPPER.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnBuilder.DEFAULT_FISH);
    }

    private static <T extends Mob> void setSpawnPlacement(EntityType<T> entityType, SpawnPlacements.Type placementType, Heightmap.Types heightmap, SpawnPlacements.SpawnPredicate<? extends Mob> spawnPredicate) {
        try {
            if (SpawnPlacements.getPlacementType(entityType) == NO_RESTRICTIONS)
                SpawnPlacements.register(entityType, placementType, heightmap, (SpawnPlacements.SpawnPredicate<T>)spawnPredicate);
        }
        catch (IllegalStateException ex) {
            Logging.logMessage(Level.WARN, "Caught duplicate spawn placement registration from: " + ForgeRegistries.ENTITY_TYPES.getKey(entityType).toString());
        }
    }

    private static final class SpawnBuilder<T extends Mob> implements SpawnPlacements.SpawnPredicate<T> {
        static final SpawnBuilder<Mob> DEFAULT_FISH = new SpawnBuilder<>().onlySpawnIn(Blocks.WATER).onlySpawnUnder(Blocks.WATER);
        static final SpawnBuilder<Mob> DEFAULT_LAVA_FISH = new SpawnBuilder<>().onlySpawnIn(Blocks.LAVA).onlySpawnUnder(Blocks.LAVA);
        static final SpawnBuilder<Mob> DEFAULT_MONSTER = new SpawnBuilder<>().noPeacefulSpawn().defaultMonsterLightLevels().ifValidSpawnBlock();
        static final SpawnBuilder<Mob> DEFAULT_DAY_NIGHT_MONSTER = new SpawnBuilder<>().noPeacefulSpawn().defaultMonsterBlockLightLevels().ifValidSpawnBlock();
        static final SpawnBuilder<Mob> DEFAULT_DAY_MONSTER = DEFAULT_DAY_NIGHT_MONSTER.onlyDuringDay().difficultyBasedSpawnChance(0.12f);
        static final SpawnBuilder<Mob> DEFAULT_ANIMAL = new SpawnBuilder<>().animalSpawnRules();

        private final SpawnPlacements.SpawnPredicate<T> predicate;

        SpawnBuilder() {
            this((entityType, world, spawnType, pos, rand) -> true);
        }

        SpawnBuilder(SpawnPlacements.SpawnPredicate<T> predicate) {
            this.predicate = predicate;
        }

        @Override
        public boolean test(EntityType<T> entityType, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource rand) {
            return this.predicate.test(entityType, level, spawnType, pos, rand);
        }

        SpawnBuilder<T> animalSpawnRules() {
            return new SpawnBuilder<>((entityType, level, spawnType, pos, rand) -> this.predicate.test(entityType, level, spawnType, pos, rand) && Animal.checkMobSpawnRules(entityType, level, spawnType, pos, rand));
        }

        SpawnBuilder<T> noPeacefulSpawn() {
            return new SpawnBuilder<>((entityType, level, spawnType, pos, rand) -> this.predicate.test(entityType, level, spawnType, pos, rand) && level.getDifficulty() != Difficulty.PEACEFUL);
        }

        SpawnBuilder<T> ifValidSpawnBlock() {
            return new SpawnBuilder<>((entityType, level, spawnType, pos, rand) -> this.predicate.test(entityType, level, spawnType, pos, rand) && Mob.checkMobSpawnRules(entityType, level, spawnType, pos, rand));
        }

        SpawnBuilder<T> noLowerThanY(int minY) {
            return new SpawnBuilder<>((entityType, level, spawnType, pos, rand) -> this.predicate.test(entityType, level, spawnType, pos, rand) && pos.getY() >= minY);
        }

        SpawnBuilder<T> noHigherThanY(int maxY) {
            return new SpawnBuilder<>((entityType, level, spawnType, pos, rand) -> this.predicate.test(entityType, level, spawnType, pos, rand) && pos.getY() <= maxY);
        }

        SpawnBuilder<T> betweenYLevels(int minY, int maxY) {
            return new SpawnBuilder<>((entityType, level, spawnType, pos, rand) -> this.predicate.test(entityType, level, spawnType, pos, rand) && pos.getY() >= minY && pos.getY() <= maxY);
        }

        SpawnBuilder<T> spawnChance(float chance) {
            return new SpawnBuilder<>((entityType, level, spawnType, pos, rand) ->  this.predicate.test(entityType, level, spawnType, pos, rand) && rand.nextFloat() < chance);
        }

        SpawnBuilder<T> difficultyBasedSpawnChance(float chance) {
            return new SpawnBuilder<>((entityType, level, spawnType, pos, rand) -> this.predicate.test(entityType, level, spawnType, pos, rand) && rand.nextFloat() < chance * level.getCurrentDifficultyAt(pos).getEffectiveDifficulty());
        }

        SpawnBuilder<T> noSpawnOn(TagKey<Block> blockTag) {
            return new SpawnBuilder<>((entityType, level, spawnType, pos, rand) -> this.predicate.test(entityType, level, spawnType, pos, rand) && !level.getBlockState(pos.below()).is(blockTag));
        }

        SpawnBuilder<T> noSpawnOn(Block block) {
            return new SpawnBuilder<>((entityType, level, spawnType, pos, rand) -> this.predicate.test(entityType, level, spawnType, pos, rand) && !level.getBlockState(pos.below()).is(block));
        }

        SpawnBuilder<T> onlySpawnOn(TagKey<Block> blockTag) {
            return new SpawnBuilder<>((entityType, level, spawnType, pos, rand) -> this.predicate.test(entityType, level, spawnType, pos, rand) && level.getBlockState(pos.below()).is(blockTag));
        }

        SpawnBuilder<T> onlySpawnOn(Block block) {
            return new SpawnBuilder<>((entityType, level, spawnType, pos, rand) -> this.predicate.test(entityType, level, spawnType, pos, rand) && level.getBlockState(pos.below()).is(block));
        }

        SpawnBuilder<T> onlySpawnIn(TagKey<Block> blockTag) {
            return new SpawnBuilder<>((entityType, level, spawnType, pos, rand) -> this.predicate.test(entityType, level, spawnType, pos, rand) && level.getBlockState(pos).is(blockTag));
        }

        SpawnBuilder<T> onlySpawnIn(Block block) {
            return new SpawnBuilder<>((entityType, level, spawnType, pos, rand) -> this.predicate.test(entityType, level, spawnType, pos, rand) && level.getBlockState(pos).is(block));
        }

        SpawnBuilder<T> onlySpawnUnder(TagKey<Block> blockTag) {
            return new SpawnBuilder<>((entityType, level, spawnType, pos, rand) -> this.predicate.test(entityType, level, spawnType, pos, rand) && level.getBlockState(pos.above()).is(blockTag));
        }

        SpawnBuilder<T> onlySpawnUnder(Block block) {
            return new SpawnBuilder<>((entityType, level, spawnType, pos, rand) -> this.predicate.test(entityType, level, spawnType, pos, rand) && level.getBlockState(pos.above()).is(block));
        }

        SpawnBuilder<T> minLightLevel(int lightLevel) {
            return new SpawnBuilder<>((entityType, level, spawnType, pos, rand) -> this.predicate.test(entityType, level, spawnType, pos, rand) && WorldUtil.getLightLevel(level, pos, false, false) >= lightLevel);
        }

        SpawnBuilder<T> maxLightLevel(int lightLevel) {
            return new SpawnBuilder<>((entityType, level, spawnType, pos, rand) -> this.predicate.test(entityType, level, spawnType, pos, rand) && WorldUtil.getLightLevel(level, pos, false, false) <= lightLevel);
        }

        SpawnBuilder<T> defaultMonsterLightLevels() {
            return new SpawnBuilder<>((entityType, level, spawnType, pos, rand) -> this.predicate.test(entityType, level, spawnType, pos, rand) && Monster.isDarkEnoughToSpawn(level, pos, rand));
        }

        SpawnBuilder<T> defaultMonsterBlockLightLevels() {
            return new SpawnBuilder<>((entityType, level, spawnType, pos, rand) -> this.predicate.test(entityType, level, spawnType, pos, rand) && WorldUtil.getLightLevel(level, pos, true, false) <= level.dimensionType().monsterSpawnBlockLightLimit());
        }

        SpawnBuilder<T> onlyDuringDay() {
            return new SpawnBuilder<>((entityType, level, spawnType, pos, rand) -> this.predicate.test(entityType, level, spawnType, pos, rand) && level.getLevel().isDay());
        }

        SpawnBuilder<T> and(SpawnPlacements.SpawnPredicate<T> predicate) {
            return new SpawnBuilder<>((entityType, level, spawnType, pos, rand) -> this.predicate.test(entityType, level, spawnType, pos, rand) && predicate.test(entityType, level, spawnType, pos, rand));
        }
    }
}
