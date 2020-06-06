package net.tslat.aoa3.entity.npcs.trader;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ArmourRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.entity.base.AoATraderRecipe;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ConfigurationUtil;

import javax.annotation.Nullable;

public class EntityGorbEngineer extends AoATrader {
	public static final float entityWidth = 0.5625f;

	public EntityGorbEngineer(World world) {
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
		return LootSystemRegister.entityGorbEngineer;
	}

	@Override
	protected Enums.ModGuis getTraderGui() {
		return Enums.ModGuis.TRADER_GORB_ENGINEER;
	}

	@Override
	protected boolean canDespawn() {
		return world.provider.getDimension() != ConfigurationUtil.MainConfig.dimensionIds.mysterium;
	}

	@Override
	protected void getTradesList(final NonNullList<AoATraderRecipe> newTradesList) {
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.GOLD_COIN, 3), new ItemStack(WeaponRegister.ULTRAFLAME)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.PHANTASM, 10), new ItemStack(ItemRegister.SILVER_COIN, 2), new ItemStack(ArmourRegister.PHANTASM_HELMET)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.PHANTASM, 15), new ItemStack(ItemRegister.SILVER_COIN, 2), new ItemStack(ArmourRegister.PHANTASM_CHESTPLATE)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.PHANTASM, 12), new ItemStack(ItemRegister.SILVER_COIN, 2), new ItemStack(ArmourRegister.PHANTASM_LEGS)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.PHANTASM, 8), new ItemStack(ItemRegister.SILVER_COIN, 2), new ItemStack(ArmourRegister.PHANTASM_BOOTS)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.POWER_CORE), new ItemStack(ItemRegister.SILVER_COIN, 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.SCRAP_METAL), new ItemStack(ItemRegister.COPPER_COIN, 8)));
	}
}
