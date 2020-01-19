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

public class EntityZalVendor extends AoATrader {
	public static final float entityWidth = 0.5625f;

	public EntityZalVendor(World world) {
		super(world, entityWidth, 1.875f);
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityZalVendor;
	}

	@Override
	public float getEyeHeight() {
		return 0.6875f;
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
		return Enums.ModGuis.TRADER_ZAL_VENDOR;
	}

	@Override
	protected boolean canDespawn() {
		return world.provider.getDimension() != ConfigurationUtil.MainConfig.dimensionIds.lunalus;
	}

	@Override
	protected void getTradesList(final NonNullList<AoATraderRecipe> newTradesList) {
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper, 5), new ItemStack(ItemRegister.lunarade, 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.lunaradeMug, 1), new ItemStack(ItemRegister.coinCopper, 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinGold, 1), new ItemStack(WeaponRegister.bowSlingshot, 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper, 6), new ItemStack(ItemRegister.popShot, 2)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinSilver, 2), new ItemStack(BlockRegister.lunarOrbDusk, 1)));
	}
}
