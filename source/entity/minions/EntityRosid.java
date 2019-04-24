package net.tslat.aoa3.entity.minions;

import net.minecraft.world.World;

public class EntityRosid extends AoAMinion {
	public static final float entityWidth = 0.5f;

	public EntityRosid(final World world) {
		super(world, 200, entityWidth, 0.875f);
	}

	@Override
	public float getEyeHeight() {
		return 0.75f;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 20.0d;
	}

	@Override
	protected double getBaseMoveSpeed() {
		return 0.3d;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 6.0d;
	}

	@Override
	protected boolean isHostile() {
		return true;
	}
}
