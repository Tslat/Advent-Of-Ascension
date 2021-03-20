package net.tslat.aoa3.entity.minion;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
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


		setDeltaMovement(getDeltaMovement().multiply(1, 0, 1));
	}

	@Override
	public ActionResultType mobInteract(PlayerEntity player, Hand hand) {
		if (hand == Hand.MAIN_HAND && isTame() && getOwner() != null) {
			EntityUtil.healEntity(player, 2.0f);
			remove();

			return ActionResultType.SUCCESS;
		}

		return super.mobInteract(player, hand);
	}

	@Override
	protected boolean isHostile() {
		return false;
	}
}
