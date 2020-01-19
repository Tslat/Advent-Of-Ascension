package net.tslat.aoa3.entity.npcs.ambient;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.entity.base.AoAAmbientNPC;
import net.tslat.aoa3.utils.ConfigurationUtil;

import javax.annotation.Nullable;

public class EntityZalCitizen extends AoAAmbientNPC {
	public static final float entityWidth = 0.5625f;

	public EntityZalCitizen(World world) {
		super(world, entityWidth, 1.875f);
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityZalCitizen;
	}

	@Override
	public float getEyeHeight() {
		return 0.6875f;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 20;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.23;
	}

	@Override
	protected boolean canDespawn() {
		return world.provider.getDimension() != ConfigurationUtil.MainConfig.dimensionIds.lunalus;
	}

	@Nullable
	@Override
	protected String getInteractMessage(ItemStack heldItem) {
		if (heldItem.getItem() == ItemRegister.alienOrb) {
			return "message.dialogue.zal_citizen.alienOrb";
		}
		else {
			return "message.dialogue.zal_citizen." + rand.nextInt(5);
		}
	}
}
