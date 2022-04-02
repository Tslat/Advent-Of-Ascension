package net.tslat.aoa3.content.entity.misc.pixon;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;

public class AmbientPixonEntity extends PixonEntity {
    public AmbientPixonEntity(EntityType<? extends PathfinderMob> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    public int getHarvestLevelReq() {
        return 20;
    }
}
