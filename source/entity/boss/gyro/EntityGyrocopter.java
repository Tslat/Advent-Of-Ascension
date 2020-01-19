package net.tslat.aoa3.entity.boss.gyro;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityGyrocopter extends Entity {
    public EntityGyrocopter(EntityPlayer player) {
        this(player.world);

        double offsetX = -MathHelper.sin(player.rotationYaw * (float)Math.PI / 180f);
        double offsetZ = MathHelper.cos(player.rotationYaw * (float)Math.PI / 180f);

        setLocationAndAngles(player.posX + offsetX * 1.5, player.posY, player.posZ + offsetZ * 1.5, player.rotationYaw, 0);
    }

    public EntityGyrocopter(World world) {
        super(world);

        setSize(1.375f, 1.625f);
        setSilent(true);
    }

    @Override
    public float getEyeHeight() {
        return 0.8125f;
    }

    @Override
    protected void entityInit() {}

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {}

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {}

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (ticksExisted >= 200) {
            if (!world.isRemote)
                world.spawnEntity(new EntityGyro(this));
            setDead();

            return;
        }

        move(MoverType.SELF, 0, 0.1f, 0);
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
    public boolean getIsInvulnerable() {
        return true;
    }

    @Override
    public boolean isEntityInvulnerable(DamageSource source) {
        return source != DamageSource.OUT_OF_WORLD;
    }

    @Override
    protected void playStepSound(BlockPos pos, Block blockIn) {}
}