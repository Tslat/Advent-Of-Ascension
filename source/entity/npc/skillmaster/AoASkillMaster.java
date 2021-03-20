package net.tslat.aoa3.entity.npc.skillmaster;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.tslat.aoa3.entity.base.AoATrader;

public abstract class AoASkillMaster extends AoATrader {
	public AoASkillMaster(EntityType<? extends CreatureEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected boolean isFixedTradesList() {
		return true;
	}
}
