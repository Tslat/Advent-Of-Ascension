package net.tslat.aoa3.content.entity.npc.ambient;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.content.entity.base.AoAAmbientNPC;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;

public class ZalCitizenEntity extends AoAAmbientNPC {
	public ZalCitizenEntity(EntityType<? extends CreatureEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
		return 0.6875f;
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return !WorldUtil.isWorld(level, AoADimensions.LUNALUS.key);
	}

	@Nullable
	@Override
	protected String getInteractMessage(ItemStack heldItem) {
		if (heldItem.getItem() == AoAItems.ALIEN_ORB.get()) {
			return "message.dialogue.zal_citizen.alienOrb";
		}
		else {
			return "message.dialogue.zal_citizen." + random.nextInt(5);
		}
	}
}
