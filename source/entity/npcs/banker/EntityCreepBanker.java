package net.tslat.aoa3.entity.npcs.banker;

import net.minecraft.item.ItemStack;
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
	protected boolean isFixedTradesList() {
		return true;
	}

	@Override
	protected Enums.ModGuis getTraderGui() {
		return Enums.ModGuis.TRADER_CREEP_BANKER;
	}

	@Nullable
	@Override
	public String getInteractMessage(ItemStack heldStack) {
		if (heldStack.getItem() == ItemRegister.realmstoneBlank) {
			return "message.dialogue.creeponiaBlankRealmstone." + rand.nextInt(3);
		}
		else {
			return null;
		}
	}

	@Override
	protected boolean canDespawn() {
		return world.provider.getDimension() != ConfigurationUtil.MainConfig.dimensionIds.creeponia;
	}

	@Override
	protected void getTradesList(final NonNullList<AoATraderRecipe> newTradesList) {
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinLunaver, 1), new ItemStack(ItemRegister.coinGold, 20)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinGold, 1), new ItemStack(ItemRegister.coinSilver, 20)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinSilver, 1), new ItemStack(ItemRegister.coinCopper, 20)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper, 20), new ItemStack(ItemRegister.coinSilver, 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinSilver, 20), new ItemStack(ItemRegister.coinGold, 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinGold, 20), new ItemStack(ItemRegister.coinLunaver, 1)));
	}
}
