package net.tslat.aoa3.entity.misc;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.player.PlayerDataManager;
import net.tslat.aoa3.utils.player.PlayerUtil;
import net.tslat.aoa3.utils.skills.InnervationUtil;

public class EntityHeartStone extends Entity {
    private int size = 1;

    public EntityHeartStone(World world) {
        super(world);

        setSize(0.3f, 0.45f);
        setSilent(true);

        isImmuneToFire = true;
    }

    public EntityHeartStone(World world, BlockPos spawnPosition) {
        this(world);

        setPositionAndUpdate(spawnPosition.getX(), spawnPosition.getY(), spawnPosition.getZ());
    }

    @Override
    public float getEyeHeight() {
        return 0.25f;
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
            setDead();

            return;
        }

        if (motionX != 0 && motionZ != 0) {
            float friction = 0.91F;
            BlockPos.PooledMutableBlockPos blockPosPool = BlockPos.PooledMutableBlockPos.retain(posX, getEntityBoundingBox().minY - 1.0D, posZ);

            if (onGround) {
                IBlockState underState = world.getBlockState(blockPosPool.setPos(posX, getEntityBoundingBox().minY - 1.0D, posZ));
                friction = underState.getBlock().getSlipperiness(underState, world, blockPosPool, this) * 0.91F;
            }
            blockPosPool.release();
            move(MoverType.SELF, motionX, motionY, motionZ);

            motionY *= 0.9800000190734863D;
            motionX *= friction;
            motionZ *= friction;
        }

        BlockPos pos = new BlockPos(posX, 0, posZ);

        if (!world.isRemote || world.isBlockLoaded(pos) && world.getChunk(pos).isLoaded()) {
            if (!hasNoGravity())
                motionY -= 0.08D;
        }
        else if (this.posY > 0.0D) {
            motionY = -0.1D;
        }
        else {
            motionY = 0.0D;
        }

        move(MoverType.SELF, motionX, motionY, motionZ);
    }

    @Override
    public void onCollideWithPlayer(EntityPlayer player) {
        if (!world.isRemote && !isDead) {
            PlayerDataManager plData = PlayerUtil.getAdventPlayer(player);
            int lvl = plData.stats().getLevelForDisplay(Enums.Skills.INNERVATION);

            EntityUtil.healEntity(player, InnervationUtil.getHeartstoneHealAmount(lvl));
            plData.stats().addXp(Enums.Skills.INNERVATION, PlayerUtil.getXpRequiredForNextLevel(lvl) / InnervationUtil.getExpDenominator(lvl), false, false);
            world.playSound(null, posX, posY, posZ, SoundsRegister.HEART_STONE_USE, SoundCategory.NEUTRAL, 1.0f, 1.0f);
            setDead();
        }
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
