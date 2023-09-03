package net.tslat.aoa3.content.entity.base;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeSpawnEggItem;
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
		final T parent = getParent();

		final Vec3 offset = this.posOffset.scale(getScale());
		final double rot = Math.toRadians(-parent.yHeadRot);
		final double cos = Math.cos(rot);
		final double sin = Math.sin(rot);
		final double xOffset = -cos * offset.x + sin * offset.z;
		final double zOffset = sin * offset.x + cos * offset.z;

		setOldPosAndRot();
		setPos(parent.position().x + xOffset, parent.position().y + offset.y, parent.position().z + zOffset);
	}

	@Nullable
	@Override
	public ItemStack getPickResult() {
		final SpawnEggItem egg = ForgeSpawnEggItem.fromEntityType(getParent().getType());

		return egg == null ? null : egg.getDefaultInstance();
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
	public boolean is(Entity entity) {
		return this == entity || getParent() == entity;
	}

	@Override
	public EntityDimensions getDimensions(Pose pose) {
		return this.size.scale(getScale());
	}

	public float getScale() {
		return getParent().getScale();
	}

	@Override
	public boolean shouldBeSaved() {
		return false;
	}

	@Override
	protected void defineSynchedData() {}

	@Override
	protected void readAdditionalSaveData(CompoundTag tag) {}

	@Override
	protected void addAdditionalSaveData(CompoundTag tag) {}
}
