package net.tslat.aoa3.entity.misc.pixon;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.tslat.aoa3.advent.AdventOfAscension;

import javax.annotation.Nonnull;

public class GlisteningPixonEntity extends PixonEntity {
    public GlisteningPixonEntity(EntityType<? extends CreatureEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public int getHarvestLevelReq() {
        return 1;
    }

    @Override
    public boolean canSpawn(IWorld world, SpawnReason reason) {
        return world.getDimension().getType() == DimensionType.OVERWORLD && super.canSpawn(world, reason);
    }
}
