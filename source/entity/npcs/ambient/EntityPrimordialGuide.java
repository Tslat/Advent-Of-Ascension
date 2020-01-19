package net.tslat.aoa3.entity.npcs.ambient;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.entity.base.AoAAmbientNPC;
import net.tslat.aoa3.utils.ConfigurationUtil;

import javax.annotation.Nullable;

public class EntityPrimordialGuide extends AoAAmbientNPC {
	public static final float entityWidth = 0.5625f;

	public EntityPrimordialGuide(World world) {
		super(world, entityWidth, 2.0f);
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityPrimordialGuide;
	}

	@Override
	public float getEyeHeight() {
		return 1.73125f;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 30;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.23;
	}

	@Override
	protected boolean canDespawn() {
		return world.provider.getDimension() != ConfigurationUtil.MainConfig.dimensionIds.dustopia;
	}

	@Nullable
	@Override
	protected String getInteractMessage(ItemStack heldItem) {
		return "message.dialogue.primordial_guide." + rand.nextInt(5);
	}
}
