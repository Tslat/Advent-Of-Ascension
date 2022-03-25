package net.tslat.aoa3.content.entity.misc.faunamancytrap;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public abstract class FaunamancyTrapEntity extends Entity {

	public FaunamancyTrapEntity(EntityType<? extends FaunamancyTrapEntity> entityType, PlayerEntity player, int lifeSpan) {
		this(entityType, player.level);
	}

	public FaunamancyTrapEntity(EntityType<? extends FaunamancyTrapEntity> entityType, World level) {
		super(entityType, level);
	}

	@Override
	public IPacket<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
