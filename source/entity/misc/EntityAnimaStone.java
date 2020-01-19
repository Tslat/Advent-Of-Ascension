package net.tslat.aoa3.entity.misc;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.player.PlayerDataManager;
import net.tslat.aoa3.utils.player.PlayerUtil;
import net.tslat.aoa3.utils.skills.AnimaUtil;

public class EntityAnimaStone extends Entity {
    private int size = 1;

    public EntityAnimaStone(World world) {
        super(world);

        setSize(0.3f, 0.45f);
        setSilent(true);

        isImmuneToFire = true;
    }

    public EntityAnimaStone(World world, BlockPos spawnPosition) {
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
            int lvl = plData.stats().getLevel(Enums.Skills.ANIMA);

            plData.stats().addXp(Enums.Skills.ANIMA, PlayerUtil.getXpRequiredForNextLevel(lvl / AnimaUtil.getExpDenominator(lvl)), false);
            world.playSound(null, posX, posY, posZ, SoundsRegister.heartStonePickup, SoundCategory.PLAYERS, 1.0f, 1.0f);
            setDead();

            if (rand.nextInt(3) == 0)
                ItemUtil.givePlayerItemOrDrop(player, new ItemStack(ItemRegister.fragmentedAnimaStone, 1));
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