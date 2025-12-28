package net.tslat.aoa3.content.entity.projectile.arrow;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.AoAExplosions;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.library.builder.AoAExplosionBuilder;
import net.tslat.tme.api.explosion.StandardExplosion;

import javax.annotation.Nullable;

public class PopShotEntity extends AbstractArrow {
	protected boolean isExplosive;

	public PopShotEntity(EntityType<? extends PopShotEntity> entityType, Level level) {
		super(entityType, level);
	}

	public PopShotEntity(EntityType<? extends PopShotEntity> entityType, double x, double y, double z, Level level, ItemStack pickupItemStack, @Nullable ItemStack firedFromWeapon) {
		super(entityType, x, y, z, level, pickupItemStack, firedFromWeapon);
	}

	public PopShotEntity(EntityType<? extends PopShotEntity> entityType, LivingEntity owner, Level level, ItemStack pickupItemStack, @Nullable ItemStack firedFromWeapon) {
		super(entityType, owner, level, pickupItemStack, firedFromWeapon);
	}

	@Override
	protected ItemStack getDefaultPickupItem() {
		return AoAItems.POP_SHOT.toStack();
	}

	public void setExplosive(boolean explosive) {
		this.isExplosive = explosive;

		setPickupItemStack(explosive ? ItemStack.EMPTY : getDefaultPickupItem());
	}

	public boolean isExplosive() {
		return this.isExplosive;
	}

	@Override
	protected void onHitBlock(BlockHitResult result) {
		super.onHitBlock(result);

		explode(result.getLocation());
	}

	@Override
	protected void onHitEntity(EntityHitResult result) {
		super.onHitEntity(result);

		explode(result.getLocation());
	}

	protected void explode(Vec3 position) {
		if (level() instanceof ServerLevel level) {
			AoAExplosionBuilder.at(level, position, AoAExplosions.POP_SHOT, StandardExplosion::new).explodingEntity(this).explode();
			discard();
		}
	}

	@Override
	protected void doPostHurtEffects(LivingEntity target) {}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);

		compound.putBoolean("explosive", this.isExplosive);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);

		if (compound.contains("explosive"))
			this.isExplosive = compound.getBoolean("explosive");
	}
}
