package net.tslat.aoa3.content.entity.base;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.entity.PartEntity;
import org.jetbrains.annotations.Nullable;

public class AoAEntityPart<T extends LivingEntity> extends PartEntity<T> {
	private final Vec3 posOffset;
	private float damageMultiplier = 1;
	private final EntityDimensions size;

	public AoAEntityPart(T parent, float width, float height, float offsetX, float offsetY, float offsetZ) {
		super(parent);

		this.size = EntityDimensions.scalable(width, height);
		this.posOffset = new Vec3(offsetX, offsetY, offsetZ);

		setPos(parent.position().add(offsetX, offsetY, offsetZ));
		refreshDimensions();
	}

	public AoAEntityPart<T> setDamageMultiplier(float multiplier) {
		this.damageMultiplier = multiplier;

		return this;
	}

	public void updatePosition() {
		T parent = getParent();

		double rot = Math.toRadians(-parent.yHeadRot);
		double cos = Math.cos(rot);
		double sin = Math.sin(rot);
		double xRot = cos * this.posOffset.x + sin * this.posOffset.z;
		double zRot = sin * this.posOffset.x + cos * this.posOffset.z;

		setOldPosAndRot();
		setPos(parent.position().x + xRot, parent.position().y + this.posOffset.y, parent.position().z + zRot);
	}

	@Nullable
	@Override
	public ItemStack getPickResult() {
		return getParent().getPickResult();
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		return !isInvulnerableTo(source) && getParent().hurt(source, amount * this.damageMultiplier);
	}

	@Override
	public boolean isPickable() {
		return true;
	}

	@Override
	public ItemStack getPickedResult(HitResult target) {
		return getParent().getPickResult();
	}

	@Override
	public boolean is(Entity entity) {
		return this == entity || getParent() == entity;
	}

	@Override
	public EntityDimensions getDimensions(Pose pose) {
		return this.size;
	}

	@Override
	public boolean shouldBeSaved() {
		return false;
	}

	@Override
	protected void defineSynchedData() {}

	@Override
	protected void readAdditionalSaveData(CompoundTag pCompound) {}

	@Override
	protected void addAdditionalSaveData(CompoundTag pCompound) {}
}
