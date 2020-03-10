package net.tslat.aoa3.entity.npcs.ambient;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.entity.base.AoAAmbientNPC;
import net.tslat.aoa3.utils.ConfigurationUtil;

import javax.annotation.Nullable;

public class EntityPortalMaster extends AoAAmbientNPC {
	public static final float entityWidth = 0.5625f;

	public EntityPortalMaster(World world) {
		super(world, entityWidth, 2.0f);
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityPortalMaster;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 30;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.329;
	}

	@Nullable
	@Override
	protected String getInteractMessage(ItemStack heldItem) {
		return null;
	}

	@Override
	protected boolean canDespawn() {
		return world.provider.getDimension() != ConfigurationUtil.MainConfig.dimensionIds.mysterium;
	}
}
