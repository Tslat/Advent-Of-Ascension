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

public class EntityCrystalTrader extends AoATrader {
	public static final float entityWidth = 0.5625f;

	public EntityCrystalTrader(World world) {
		super(world, entityWidth, 2.0f);
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityCrystalTrader;
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
	protected boolean isFixedTradesList() {
		return true;
	}

	@Override
	protected Enums.ModGuis getTraderGui() {
		return Enums.ModGuis.TRADER_CRYSTAL_TRADER;
	}

	@Override
	protected boolean canDespawn() {
		return world.provider.getDimension() != ConfigurationUtil.MainConfig.dimensionIds.crystevia;
	}

	@Override
	protected void getTradesList(final NonNullList<AoATraderRecipe> newTradesList) {
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.crystalBlue, 1), new ItemStack(ItemRegister.tokensCrystevia, 3)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.crystalGreen, 1), new ItemStack(ItemRegister.tokensCrystevia, 3)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.crystalPurple, 1), new ItemStack(ItemRegister.tokensCrystevia, 3)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.crystalRed, 1), new ItemStack(ItemRegister.tokensCrystevia, 3)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.crystalWhite, 1), new ItemStack(ItemRegister.tokensCrystevia, 3)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.crystalYellow, 1), new ItemStack(ItemRegister.tokensCrystevia, 3)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.crystalBlue, 16), new ItemStack(ItemRegister.druseBlue)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.crystalGreen, 16), new ItemStack(ItemRegister.druseGreen)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.crystalPurple, 16), new ItemStack(ItemRegister.drusePurple)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.crystalRed, 16), new ItemStack(ItemRegister.druseRed)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.crystalWhite, 16), new ItemStack(ItemRegister.druseWhite)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.crystalYellow, 16), new ItemStack(ItemRegister.druseYellow)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.druseRainbow, 12), new ItemStack(WeaponRegister.maulCrystal)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.druseRainbow, 12), new ItemStack(WeaponRegister.greatbladeCrystal)));
	}
}
