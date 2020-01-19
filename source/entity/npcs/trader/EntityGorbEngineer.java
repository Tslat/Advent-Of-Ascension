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
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinGold, 3), new ItemStack(WeaponRegister.swordUltraflame)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.phantasm, 8), new ItemStack(ItemRegister.coinSilver, 2), new ItemStack(ArmourRegister.phantasmBoots)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.phantasm, 12), new ItemStack(ItemRegister.coinSilver, 2), new ItemStack(ArmourRegister.phantasmLegs)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.phantasm, 15), new ItemStack(ItemRegister.coinSilver, 2), new ItemStack(ArmourRegister.phantasmBody)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.phantasm, 10), new ItemStack(ItemRegister.coinSilver, 2), new ItemStack(ArmourRegister.phantasmHelmet)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.powerCore), new ItemStack(ItemRegister.coinSilver, 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.scrapMetal), new ItemStack(ItemRegister.coinCopper, 8)));
	}
}
