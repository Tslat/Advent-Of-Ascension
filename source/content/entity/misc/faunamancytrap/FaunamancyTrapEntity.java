package net.tslat.aoa3.content.entity.misc.faunamancytrap;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public abstract class FaunamancyTrapEntity extends Entity {

	public FaunamancyTrapEntity(EntityType<? extends FaunamancyTrapEntity> entityType, Player player, int lifeSpan) {
		this(entityType, player.level());
	}

	public FaunamancyTrapEntity(EntityType<? extends FaunamancyTrapEntity> entityType, Level level) {
		super(entityType, level);
	}
}
