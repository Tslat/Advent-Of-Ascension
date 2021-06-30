package net.tslat.aoa3.entity.misc;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.util.WorldUtil;

public class FakeTntEntity extends TNTEntity {
	private final Entity owner;

	public FakeTntEntity(World world, BlockPos position, Entity owner) {
		super(AoAEntities.Misc.FAKE_TNT.get(), world);

		this.owner = owner;

		moveTo(position.getX(), position.getY(), position.getZ(), random.nextFloat() * 360.0f, 0.0f);
	}

	public FakeTntEntity(EntityType<? extends TNTEntity> entityType, World world) {
		super(entityType, world);

		this.owner = null;
	}

	protected void explode() {
		WorldUtil.createExplosion(this.owner, level, this, 4.0f);
	}

	@Override
	public boolean canChangeDimensions() {
		return false;
	}

	@Override
	public IPacket<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
