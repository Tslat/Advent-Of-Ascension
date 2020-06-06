package net.tslat.aoa3.entity.npcs.trader;

import net.minecraft.init.Items;
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

public class EntityShyreArcher extends AoATrader {
	public static final float entityWidth = 0.5625f;

	public EntityShyreArcher(World world) {
		super(world, entityWidth, 2.0f);
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityShyreArcher;
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
		return Enums.ModGuis.TRADER_SHYRE_ARCHER;
	}

	@Override
	protected boolean canDespawn() {
		return world.provider.getDimension() != ConfigurationUtil.MainConfig.dimensionIds.shyrelands;
	}

	@Override
	protected void getTradesList(final NonNullList<AoATraderRecipe> newTradesList) {
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.SHYREGEM, 7), new ItemStack(ItemRegister.SHYRESTONE_INGOT, 12), new ItemStack(WeaponRegister.SUNSHINE_BOW)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.COPPER_COIN, 3), new ItemStack(ItemRegister.HOLLY_ARROW)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.COPPER_COIN, 6), new ItemStack(ItemRegister.SPECTRAL_HOLLY_ARROW)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.COPPER_COIN, 1), new ItemStack(Items.ARROW)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.SILVER_COIN, 9), new ItemStack(WeaponRegister.ULTRA_CANNON, 1), new ItemStack(WeaponRegister.GIGA_CANNON)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.SHYREGEM, 2), new ItemStack(ItemRegister.SHYRESTONE_INGOT, 2), new ItemStack(ItemRegister.ANCIENT_RING)));
	}
}
