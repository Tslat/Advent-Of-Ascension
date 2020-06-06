package net.tslat.aoa3.entity.npcs.banker;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.entity.base.AoATraderRecipe;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ConfigurationUtil;

import javax.annotation.Nullable;

public class EntityCreepBanker extends AoATrader {
	public static final float entityWidth = 0.5625f;

	public EntityCreepBanker(World world) {
		super(world, entityWidth, 2.0f);
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
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityCreepBanker;
	}

	@Override
	protected Enums.ModGuis getTraderGui() {
		return Enums.ModGuis.BANKER;
	}

	@Override
	protected boolean canDespawn() {
		return world.provider.getDimension() != ConfigurationUtil.MainConfig.dimensionIds.creeponia;
	}

	@Override
	protected boolean processInteract(EntityPlayer player, EnumHand hand) {
		ItemStack heldStack = player.getHeldItem(hand);

		if (heldStack.getItem() == ItemRegister.BLANK_REALMSTONE && heldStack.getItem().itemInteractionForEntity(heldStack, player, this, hand))
			return true;

		return super.processInteract(player, hand);
	}

	@Override
	protected boolean isFixedTradesList() {
		return true;
	}

	@Override
	protected void getTradesList(NonNullList<AoATraderRecipe> newTradesList) {
		newTradesList.add(new AoATraderRecipe(ItemStack.EMPTY, ItemStack.EMPTY));
	}
}
