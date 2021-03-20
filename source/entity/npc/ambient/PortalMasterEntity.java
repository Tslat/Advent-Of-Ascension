package net.tslat.aoa3.entity.npc.ambient;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.entity.base.AoAAmbientNPC;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;

public class PortalMasterEntity extends AoAAmbientNPC {
	public PortalMasterEntity(EntityType<? extends CreatureEntity> entityType, World world) {
		super(entityType, world);
	}

	@Nullable
	@Override
	protected String getInteractMessage(ItemStack heldItem) {
		return null;
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return !WorldUtil.isWorld(level, AoADimensions.MYSTERIUM.key);
	}
}
