package net.tslat.aoa3.entity.npcs.ambient;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.entity.base.AoAAmbientNPC;
import net.tslat.aoa3.utils.ConfigurationUtil;
import net.tslat.aoa3.utils.EntityUtil;

import javax.annotation.Nullable;

public class EntityZalChild extends AoAAmbientNPC {
	public static final float entityWidth = 0.16875f;

	public EntityZalChild(World world) {
		super(world, entityWidth, 0.5625f);
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityZalChild;
	}

	@Override
	public float getEyeHeight() {
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
	protected boolean canDespawn() {
		return world.provider.getDimension() != ConfigurationUtil.MainConfig.dimensionIds.lunalus;
	}

	@Nullable
	@Override
	protected String getInteractMessage(ItemStack heldItem) {
		return "message.dialogue.zal_child." + rand.nextInt(5);
	}

	@Override
	protected boolean processInteract(EntityPlayer player, EnumHand hand) {
		ItemStack heldStack = player.getHeldItem(hand);

		if (heldStack.getItem() == ItemRegister.alienOrb) {
			if (!world.isRemote) {
				player.setHeldItem(hand, ItemRegister.fleshyBones.newValidStack());
				EntityUtil.killEntityCleanly(this);
			}

			return true;
		}

		return super.processInteract(player, hand);
	}
}
