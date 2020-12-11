package net.tslat.aoa3.entity.minion;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.tslat.aoa3.util.EntityUtil;

public class OrblingEntity extends AoAMinion {
	public OrblingEntity(EntityType<? extends TameableEntity> entityType, final World world){
		super(entityType, world, 200);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
		return 0.5625f;
	}

	@Override
	public void tick() {
		super.tick();


		setMotion(getMotion().mul(1, 0, 1));
	}

	@Override
	public boolean processInteract(PlayerEntity player, Hand hand) {
		if (hand == Hand.MAIN_HAND && isTamed() && getOwner() != null) {
			EntityUtil.healEntity(player, 2.0f);
			remove();

			return true;
		}

		return super.processInteract(player, hand);
	}

	@Override
	protected double getBaseMoveSpeed() {
		return 0;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 10.0d;
	}

	@Override
	protected boolean isHostile() {
		return false;
	}
}
