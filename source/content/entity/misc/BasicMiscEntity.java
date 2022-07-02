package net.tslat.aoa3.content.entity.misc;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.network.NetworkHooks;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public abstract class BasicMiscEntity extends Entity implements IAnimatable {
	private final AnimationFactory animationFactory = new AnimationFactory(this);

	public BasicMiscEntity(EntityType<?> entityType, Level level) {
		super(entityType, level);
	}

	@Override
	public boolean isPushable() {
		return isAlive();
	}

	@Override
	public boolean canBeCollidedWith() {
		return isAlive();
	}

	@Override
	public boolean isPickable() {
		return isAlive();
	}

	@Override
	public boolean canChangeDimensions() {
		return false;
	}

	@Override
	public void tick() {
		super.tick();

		if (!isNoGravity()) {
			double gravity = -0.08d;
			FluidType submergedFluid = getEyeInFluidType();

			if (submergedFluid != ForgeMod.EMPTY_TYPE.get())
				gravity *= getFluidMotionScale(submergedFluid);

			setDeltaMovement(getDeltaMovement().add(0, gravity, 0));
		}

		BlockPos feetPos = getBlockPosBelowThatAffectsMyMovement();
		float blockFriction = onGround ? level.getBlockState(feetPos).getFriction(level, feetPos, this) * 0.91f : 0.91f;

		setDeltaMovement(getDeltaMovement().multiply(blockFriction, 0.98d,  blockFriction));

		if (getDeltaMovement().lengthSqr() != 0)
			move(MoverType.SELF, getDeltaMovement());
	}

	@Override
	protected void defineSynchedData() {}

	@Override
	protected void readAdditionalSaveData(CompoundTag compound) {}

	@Override
	protected void addAdditionalSaveData(CompoundTag compound) {}

	@Override
	public Packet<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	public void registerControllers(AnimationData data) {}

	@Override
	public AnimationFactory getFactory() {
		return this.animationFactory;
	}
}
