package net.tslat.aoa3.content.block.functional.misc;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.tslat.aoa3.common.registration.entity.AoAMobs;
import net.tslat.aoa3.common.registration.worldgen.AoAWorldgenKeys;
import net.tslat.aoa3.content.entity.mob.precasia.AttercopusEntity;
import net.tslat.aoa3.library.object.AllDirections;
import net.tslat.aoa3.util.BlockUtil;
import net.tslat.aoa3.util.EntitySpawningUtil;
import net.tslat.aoa3.util.ObjectUtil;
import net.tslat.smartbrainlib.util.EntityRetrievalUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CocoonBlock extends Block {
    public static final BooleanProperty WEBBED = BooleanProperty.create("webbed");
    private static final VoxelShape WEBBED_SHAPE = BlockUtil.pixelBasedCube(3, 1, 3, 13, 15, 13);
    private static final VoxelShape SHAPE = BlockUtil.pixelBasedCube(3, 0, 3, 13, 14, 13);

    public CocoonBlock(Properties properties) {
        super(properties);

        registerDefaultState(getStateDefinition().any().setValue(WEBBED, false));
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return state.getValue(WEBBED) && super.isRandomlyTicking(state);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!shouldTrySpawn(level, pos, state))
            return;

        if (trySpawn(level, pos, random) && random.nextBoolean())
            level.scheduleTick(pos, this, random.nextIntBetweenInclusive(60, 700));
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!shouldTrySpawn(level, pos, state))
            return;

        if (trySpawn(level, pos, random) && random.nextInt(5) == 0)
            level.scheduleTick(pos, this, random.nextIntBetweenInclusive(60, 200));
    }

    private static boolean trySpawn(ServerLevel level, BlockPos pos, RandomSource random) {
        final List<AllDirections> directions = new ObjectArrayList<>(AllDirections.values());

        ObjectUtil.fastShuffleList(directions);

        for (AllDirections direction : directions) {
            BlockPos spawnPos = pos.offset(direction.angle());

            if (!level.getBlockState(spawnPos).isSuffocating(level, spawnPos) && Monster.isDarkEnoughToSpawn(level, pos, random)) {
                EntitySpawningUtil.spawnEntity(level, AoAMobs.ATTERCOPUS.get(), Vec3.atCenterOf(spawnPos), MobSpawnType.SPAWNER);

                return true;
            }
        }

        return false;
    }

    private static boolean shouldTrySpawn(ServerLevel level, BlockPos pos, BlockState state) {
        if (!state.getValue(WEBBED))
            return false;

        final AABB bounds = new AABB(pos);

        if (EntityRetrievalUtil.getPlayers(level, bounds.inflate(30)).isEmpty())
            return false;

        if (level.structureManager().getStructureWithPieceAt(pos, AoAWorldgenKeys.Structures.ATTERCOPUS_NEST) == StructureStart.INVALID_START)
            return false;

        if (EntityRetrievalUtil.getEntities(level, bounds.inflate(10), entity -> entity instanceof AttercopusEntity).size() > 20)
            return false;

        return true;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        if (context.getLevel().getBlockState(context.getClickedPos()).is(Blocks.COBWEB))
            return super.getStateForPlacement(context).setValue(WEBBED, true);

        return super.getStateForPlacement(context);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return state.getValue(WEBBED) ? WEBBED_SHAPE : SHAPE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WEBBED);
    }
}
