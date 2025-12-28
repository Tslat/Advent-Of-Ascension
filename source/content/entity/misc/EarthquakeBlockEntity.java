package net.tslat.aoa3.content.entity.misc;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.fluids.FluidType;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.tme.api.util.EntityRetrievalUtil;
import net.tslat.tme.api.object.builder.EffectBuilder;
import org.jetbrains.annotations.Nullable;

public class EarthquakeBlockEntity extends Entity {
    public static final EntityDataAccessor<BlockPos> BLOCK_ORIGIN = SynchedEntityData.defineId(EarthquakeBlockEntity.class, EntityDataSerializers.BLOCK_POS);

    private BlockState block = Blocks.AIR.defaultBlockState();
    private BlockPos blockOrigin = BlockPos.ZERO;
    @Nullable
    private LivingEntity owner = null;
    private float damage = 4;

    public EarthquakeBlockEntity(EntityType<?> entityType, Level level) {
        super(entityType, level);

        this.noPhysics = true;
    }

    public EarthquakeBlockEntity(EntityType<?> entityType, Level level, BlockState block, BlockPos pos, @Nullable LivingEntity owner) {
        this(entityType, level);

        this.owner = owner;
        this.block = block;
        getEntityData().set(BLOCK_ORIGIN, pos);
    }

    public void setOwner(LivingEntity owner) {
        this.owner = owner;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public BlockState getBlock() {
        return this.block;
    }

    public BlockPos getBlockOrigin() {
        return this.blockOrigin;
    }

    @Nullable
    public LivingEntity getOwner() {
        return this.owner;
    }

    public float getDamage() {
        return this.damage;
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean isPushedByFluid() {
        return false;
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    @Override
    public void tick() {
        super.tick();

        if (!isNoGravity()) {
            double gravity = -0.08d;
            FluidType submergedFluid = getEyeInFluidType();

            if (submergedFluid != NeoForgeMod.EMPTY_TYPE.value())
                gravity *= getFluidMotionScale(submergedFluid);

            setDeltaMovement(getDeltaMovement().add(0, gravity, 0));
        }

        if (getDeltaMovement().lengthSqr() != 0) {
            move(MoverType.SELF, getDeltaMovement());

            if (!level().isClientSide) {
                for (LivingEntity entity : EntityRetrievalUtil.getEntities(level(), getBoundingBox().deflate(0.1f), LivingEntity.class, entity -> entity != this.owner)) {
                    if (entity.hurt(DamageUtil.indirectEntityDamage(DamageTypes.MOB_ATTACK_NO_AGGRO, this.owner, this), getDamage()))
                        EntityUtil.applyPotions(entity, getOwner(), new EffectBuilder(MobEffects.MOVEMENT_SLOWDOWN, 60).level(3).isAmbient().hideParticles());
                }
            }
        }

        if (blockPosition().above().equals(this.blockOrigin))
            discard();
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket(ServerEntity entity) {
        return new ClientboundAddEntityPacket(this, entity, Block.getId(getBlock()));
    }

    @Override
    public void recreateFromPacket(ClientboundAddEntityPacket packet) {
        super.recreateFromPacket(packet);

        this.block = Block.stateById(packet.getData());
        this.blocksBuilding = true;

        this.setPos(packet.getX(), packet.getY(), packet.getZ());
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(BLOCK_ORIGIN, BlockPos.ZERO);
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
        super.onSyncedDataUpdated(key);

        if (key.equals(BLOCK_ORIGIN))
            this.blockOrigin = getEntityData().get(BLOCK_ORIGIN);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compound) {}

    @Override
    protected void addAdditionalSaveData(CompoundTag compound) {}
}
