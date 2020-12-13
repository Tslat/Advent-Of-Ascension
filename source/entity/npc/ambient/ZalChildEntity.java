package net.tslat.aoa3.entity.npc.ambient;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.entity.base.AoAAmbientNPC;
import net.tslat.aoa3.item.misc.ReservedItem;
import net.tslat.aoa3.util.DamageUtil;

import javax.annotation.Nullable;

public class ZalChildEntity extends AoAAmbientNPC {
	public ZalChildEntity(EntityType<? extends CreatureEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
		return 0.20625f;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 15;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.23;
	}

	@Override
	public boolean canDespawn(double distanceToClosestPlayer) {
		return world.getDimension().getType() != AoADimensions.LUNALUS.type();
	}

	@Nullable
	@Override
	protected String getInteractMessage(ItemStack heldItem) {
		return "message.dialogue.zal_child." + rand.nextInt(5);
	}


	@Override
	protected boolean processInteract(PlayerEntity player, Hand hand) {
		ItemStack heldStack = player.getHeldItem(hand);

		if (heldStack.getItem() == AoAItems.ALIEN_ORB.get()) {
			if (!world.isRemote) {
				player.setHeldItem(hand, ((ReservedItem)AoAItems.FLESHY_BONES.get()).newValidStack());
				DamageUtil.killEntityCleanly(this);
			}

			return true;
		}

		return super.processInteract(player, hand);
	}
}
