package net.tslat.aoa3.entity.misc.pixon;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.tslat.aoa3.util.EntityUtil;

public class GlowingPixonEntity extends PixonEntity {
    public GlowingPixonEntity(EntityType<? extends CreatureEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public int getHarvestLevelReq() {
        return 40;
    }

    @Override
    public boolean checkSpawnObstruction(IWorldReader world) {
        return world.isUnobstructed(this);
    }

    @Override
    public boolean checkSpawnRules(IWorld world, SpawnReason reason) {
        if (!EntityUtil.isNaturalSpawnReason(reason))
            return true;

       return world.getBlockState(blockPosition()).getFluidState().is(FluidTags.WATER);
    }
}
