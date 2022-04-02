package net.tslat.aoa3.content.entity.misc;

import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;
import net.tslat.aoa3.common.registration.entity.AoAMiscEntities;
import net.tslat.aoa3.util.WorldUtil;

public class FakeTntEntity extends PrimedTnt {
	private final Entity owner;

	public FakeTntEntity(Level world, BlockPos position, Entity owner) {
		super(AoAMiscEntities.FAKE_TNT.get(), world);

		this.owner = owner;

		moveTo(position.getX(), position.getY(), position.getZ(), random.nextFloat() * 360.0f, 0.0f);
	}

	public FakeTntEntity(EntityType<? extends PrimedTnt> entityType, Level world) {
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
	public Packet<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
