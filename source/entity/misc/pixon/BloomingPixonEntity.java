package net.tslat.aoa3.entity.misc.pixon;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.tslat.aoa3.advent.AdventOfAscension;

import javax.annotation.Nonnull;

public class BloomingPixonEntity extends PixonEntity {
    public BloomingPixonEntity(EntityType<? extends CreatureEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public int getHarvestLevelReq() {
        return 81;
    }
}
