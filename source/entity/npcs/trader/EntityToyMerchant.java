package net.tslat.aoa3.entity.npcs.trader;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.entity.base.AoATraderRecipe;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ConfigurationUtil;

import javax.annotation.Nullable;

public class EntityToyMerchant extends AoATrader {
	public static final float entityWidth = 0.5625f;

	public EntityToyMerchant(World world) {
		super(world, entityWidth, 2.0f);
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityToyMerchant;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 30;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.329;
	}

	@Override
	protected Enums.ModGuis getTraderGui() {
		return Enums.ModGuis.TRADER_TOY_MERCHANT;
	}

	@Override
	protected boolean canDespawn() {
		return world.provider.getDimension() != ConfigurationUtil.MainConfig.dimensionIds.celeve;
	}

	@Override
	protected void getTradesList(final NonNullList<AoATraderRecipe> newTradesList) {
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.CIRCUS_COIN, 5), new ItemStack(ItemRegister.BALLOON, 10)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.CIRCUS_COIN, 46), new ItemStack(ItemRegister.GRAVITATOR)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.CIRCUS_COIN, 15), new ItemStack(ItemRegister.TOY_GYROCOPTER)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.CIRCUS_COIN, 64), new ItemStack(ItemRegister.CIRCUS_COIN, 64), new ItemStack(ItemRegister.SMILEY_UPGRADE_KIT), 0, 1));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.CIRCUS_COIN, 15), new ItemStack(WeaponRegister.CONFETTI_CLUSTER)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.CIRCUS_COIN, 25), new ItemStack(WeaponRegister.BALLOON_BOMBER)));
	}
}
