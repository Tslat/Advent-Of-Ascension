package net.tslat.aoa3.content.entity.misc.pixon;

import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.tslat.aoa3.util.EntityUtil;

public class GlowingPixonEntity extends PixonEntity {
    public GlowingPixonEntity(EntityType<? extends PathfinderMob> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    public int getHarvestLevelReq() {
        return 40;
    }

    @Override
    public boolean checkSpawnObstruction(LevelReader world) {
        return world.isUnobstructed(this);
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor world, MobSpawnType reason) {
        if (!EntityUtil.isNaturalSpawnReason(reason))
            return true;

       return world.getBlockState(blockPosition()).getFluidState().is(FluidTags.WATER);
    }
}
