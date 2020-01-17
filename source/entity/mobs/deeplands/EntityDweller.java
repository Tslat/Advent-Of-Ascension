package net.tslat.aoa3.entity.mobs.deeplands;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class EntityDweller extends AoAMeleeMob {
    public static final float entityWidth = 0.625f;

    public EntityDweller(World world) {
        super(world, entityWidth, 2.4f);
    }

    @Override
    public float getEyeHeight() {
        return 2.125f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0d;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 50;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 6.5d;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.3d;
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return LootSystemRegister.entityDweller;
    }

    @Override
    public boolean getCanSpawnHere() {
        return posY < 120 && super.getCanSpawnHere();
    }
}
