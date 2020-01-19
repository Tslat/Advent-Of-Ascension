package net.tslat.aoa3.entity.minions;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.utils.EntityUtil;

import javax.annotation.Nullable;

public class EntityOrbling extends AoAMinion {
	public static final float entityWidth = 1.0f;

	public EntityOrbling(final World world){
		super(world, 200, entityWidth, 1.0f);
	}

	@Override
	public float getEyeHeight() {
		return 0.625f;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		motionY = 0.0f;
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand) {
		if (hand == EnumHand.MAIN_HAND && isTamed() && getOwner() != null) {
			EntityUtil.healEntity(player, 2.0f);
			setDead();

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

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityOrbling;
	}
}
