package net.tslat.aoa3.entity.mobs.deeplands;

import net.minecraft.world.World;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

public class EntityDwellerClone extends AoAMeleeMob {
    public static final float entityWidth = 1.2f;

    public EntityDwellerClone(EntityDweller host) {
        this(host.world);
        this.setLocationAndAngles(host.posX, host.posY, host.posZ, rand.nextFloat() * 360, 1.0f);
    }

    public EntityDwellerClone(World world) {
        super(world, entityWidth, 2.4f);
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.8;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 20;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 5;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (ticksExisted >= 100) {
            world.createExplosion(this, posX, posY, posZ, 2, false);
            setDead();
        }
    }
}
