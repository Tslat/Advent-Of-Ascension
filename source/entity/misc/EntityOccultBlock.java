package net.tslat.aoa3.entity.misc;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityOccultBlock extends Entity {
	public EntityOccultBlock(World world) {
		super(world);
		setSize(0.99f, 0.99f);
	}

	public EntityOccultBlock(World world, BlockPos pos) {
		super(world);
		setSize(0.99f, 0.99f);
		this.setPositionAndUpdate(pos.getX() + 0.5, pos.getY() + 0.005f, pos.getZ() + 0.5);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		ticksExisted++;

		if (ticksExisted > 150) {
			setDead();
			setPositionAndUpdate(0, 0, 0);
		}
		else if (!world.getBlockState(getPosition()).isFullCube()) {
			setDead();
			setPositionAndUpdate(0, 0, 0);
		}
	}

	@Override
	protected void entityInit() {}

	@Override
	public boolean isGlowing() {
		return true;
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {}
}
