package net.tslat.aoa3.object.entity.mob.deeplands;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.object.entity.base.AoAMeleeMob;

public class DwellerEntity extends AoAMeleeMob {
    public DwellerEntity(EntityType<? extends MonsterEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 2.125f;
    }

}
