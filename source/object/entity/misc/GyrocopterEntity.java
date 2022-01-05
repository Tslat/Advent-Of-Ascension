package net.tslat.aoa3.object.entity.misc;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.object.entity.boss.GyroEntity;

public class GyrocopterEntity extends Entity {
    public GyrocopterEntity(PlayerEntity player) {
        this(AoAEntities.Misc.GYROCOPTER.get(), player.level);

        double offsetX = -MathHelper.sin(player.yRot * (float)Math.PI / 180f);
        double offsetZ = MathHelper.cos(player.yRot * (float)Math.PI / 180f);

        moveTo(player.getX() + offsetX * 1.5, player.getY(), player.getZ() + offsetZ * 1.5, player.yRot, 0);
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
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public boolean canChangeDimensions() {
        return false;
    }

    @Override
    protected void defineSynchedData() {}

    @Override
    protected void readAdditionalSaveData(CompoundNBT compound) {}

    @Override
    protected void addAdditionalSaveData(CompoundNBT compound) {}

    @Override
    public void tick() {
        super.tick();

        if (tickCount >= 200) {
            if (!level.isClientSide)
                level.addFreshEntity(new GyroEntity(this));

            remove();

            return;
        }

        move(MoverType.SELF, new Vector3d(0, 0.1d, 0));
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean isPickable() {
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