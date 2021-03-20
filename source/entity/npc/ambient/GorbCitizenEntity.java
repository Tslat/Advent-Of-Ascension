package net.tslat.aoa3.entity.npc.ambient;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.entity.base.AoAAmbientNPC;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;

public class GorbCitizenEntity extends AoAAmbientNPC {
	public GorbCitizenEntity(EntityType<? extends CreatureEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
		return 1.5f;
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return !WorldUtil.isWorld(level, AoADimensions.MYSTERIUM.key);
	}

	@Nullable
	@Override
	protected String getInteractMessage(ItemStack heldItem) {
		return "message.dialogue.gorb_citizen." + random.nextInt(5);
	}
}
