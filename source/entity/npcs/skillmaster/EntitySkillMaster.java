package net.tslat.aoa3.entity.npcs.skillmaster;

import net.minecraft.world.World;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.library.Enums;

public abstract class EntitySkillMaster extends AoATrader {
	public static final float entityWidth = 0.5625f;

	public EntitySkillMaster(World world) {
		super(world, entityWidth, 2.0f);
	}

	@Override
	protected double getBaseMaxHealth() {
		return 50;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.329;
	}

	@Override
	protected Enums.ModGuis getTraderGui() {
		return Enums.ModGuis.TRADER_SKILL_MASTER;
	}

	@Override
	protected boolean isFixedTradesList() {
		return true;
	}
}
