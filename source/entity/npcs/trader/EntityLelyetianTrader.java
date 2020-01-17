package net.tslat.aoa3.entity.npcs.trader;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.entity.base.AoATraderRecipe;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ConfigurationUtil;

import javax.annotation.Nullable;

public class EntityLelyetianTrader extends AoATrader {
	public static final float entityWidth = 0.5625f;

	public EntityLelyetianTrader(World world) {
		super(world, entityWidth, 2.0f);
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityLelyetianTrader;
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
		return Enums.ModGuis.TRADER_LELYETIAN_TRADER;
	}

	@Override
	protected boolean canDespawn() {
		return world.provider.getDimension() != ConfigurationUtil.MainConfig.dimensionIds.lelyetia;
	}

	@Override
	protected void getTradesList(final NonNullList<AoATraderRecipe> newTradesList) {
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinSilver, 10), new ItemStack(ItemRegister.yellowSpores, 5), new ItemStack(WeaponRegister.gunGaugeRifle)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinSilver, 10), new ItemStack(ItemRegister.orangeSpores, 5), new ItemStack(WeaponRegister.gunGaugeRifle)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.zhinxDust), new ItemStack(ItemRegister.coinCopper, 4)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinSilver, 2), new ItemStack(BlockRegister.glassLelyetian, 64)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper, 10), new ItemStack(BlockRegister.glassLelyetian, 14)));
	}
}
