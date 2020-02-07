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

public class EntityGorbArmsDealer extends AoATrader {
	public static final float entityWidth = 0.5625f;

	public EntityGorbArmsDealer(World world) {
		super(world, entityWidth, 1.6875f);
	}

	@Override
	public float getEyeHeight() {
		return 1.5f;
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
		return LootSystemRegister.entityGorbArmsDealer;
	}

	@Override
	protected Enums.ModGuis getTraderGui() {
		return Enums.ModGuis.TRADER_GORB_ARMS_DEALER;
	}

	@Override
	protected boolean canDespawn() {
		return world.provider.getDimension() != ConfigurationUtil.MainConfig.dimensionIds.mysterium;
	}

	@Override
	protected void getTradesList(final NonNullList<AoATraderRecipe> newTradesList) {
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinSilver), new ItemStack(WeaponRegister.throwableGrenade, 5)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(WeaponRegister.throwableRunicBomb), new ItemStack(ItemRegister.coinCopper, 7)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.hollyArrow), new ItemStack(ItemRegister.coinCopper, 2)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinGold, 3), new ItemStack(WeaponRegister.blasterLaserBlaster)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinGold, 10), new ItemStack(ItemRegister.weaponParts)));
	}
}
