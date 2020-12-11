package net.tslat.aoa3.entity.misc;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.boss.GyroEntity;

public class GyrocopterEntity extends Entity {
    public GyrocopterEntity(PlayerEntity player) {
        this(AoAEntities.Misc.GYROCOPTER.get(), player.world);

        double offsetX = -MathHelper.sin(player.rotationYaw * (float)Math.PI / 180f);
        double offsetZ = MathHelper.cos(player.rotationYaw * (float)Math.PI / 180f);

        setLocationAndAngles(player.getPosX() + offsetX * 1.5, player.getPosY(), player.getPosZ() + offsetZ * 1.5, player.rotationYaw, 0);
    }

    public GyrocopterEntity(EntityType<? extends Entity> entityType, World world) {
        super(entityType, world);

        setSilent(true);
    }

    @Override
    protected float getEyeHeight(Pose pose, EntitySize size) {
        return 0.8125f;
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected void registerData() {}

    @Override
    protected void readAdditional(CompoundNBT compound) {}

    @Override
    protected void writeAdditional(CompoundNBT compound) {}

    @Override
    public void tick() {
        super.tick();

        if (ticksExisted >= 200) {
            if (!world.isRemote)
                world.addEntity(new GyroEntity(this));

            remove();

            return;
        }

        move(MoverType.SELF, new Vec3d(0, 0.1d, 0));
    }

    @Override
    public boolean canBePushed() {
        return false;
    }

    @Override
    public boolean canBeCollidedWith() {
        return false;
    }

    @Override
    public boolean isInvulnerable() {
        return true;
    }

    @Override
    public boolean isInvulnerableTo(DamageSource source) {
        return source != DamageSource.OUT_OF_WORLD;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState block) {}
}