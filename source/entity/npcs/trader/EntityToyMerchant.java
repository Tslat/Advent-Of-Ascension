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
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.circusCoin, 5), new ItemStack(ItemRegister.balloon, 10)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.circusCoin, 46), new ItemStack(ItemRegister.gravitator)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.circusCoin, 15), new ItemStack(ItemRegister.toyGyrocopter)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.circusCoin, 64), new ItemStack(ItemRegister.circusCoin, 64), new ItemStack(ItemRegister.upgradeKitSmiley)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.circusCoin, 15), new ItemStack(WeaponRegister.blasterConfettiCluster)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.circusCoin, 25), new ItemStack(WeaponRegister.cannonBalloonBomber)));
	}
}
