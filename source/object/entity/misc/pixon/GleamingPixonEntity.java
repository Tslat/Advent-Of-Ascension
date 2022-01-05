package net.tslat.aoa3.object.entity.misc.pixon;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class GleamingPixonEntity extends PixonEntity {
    public GleamingPixonEntity(EntityType<? extends CreatureEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public int getHarvestLevelReq() {
        return 10;
    }
}
