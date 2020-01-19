package net.tslat.aoa3.entity.npcs.trader;

import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.ToolRegister;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.entity.base.AoATraderRecipe;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ConfigurationUtil;

import javax.annotation.Nullable;

public class EntityPrimordialSpellbinder extends AoATrader {
	public static final float entityWidth = 0.5625f;

	public EntityPrimordialSpellbinder(World world) {
		super(world, entityWidth, 2.0f);
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityPrimordialSpellbinder;
	}

	@Override
	public float getEyeHeight() {
		return 1.73125f;
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
		return Enums.ModGuis.TRADER_PRIMORDIAL_SPELLBINDER;
	}

	@Override
	protected boolean canDespawn() {
		return world.provider.getDimension() != ConfigurationUtil.MainConfig.dimensionIds.dustopia;
	}

	@Override
	protected void getTradesList(final NonNullList<AoATraderRecipe> newTradesList) {
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper), new ItemStack(ItemRegister.runeStorm)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper), new ItemStack(ItemRegister.runePower)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper), new ItemStack(ItemRegister.runeWither)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper), new ItemStack(ItemRegister.runeCompass)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinLunaver), ItemEnchantedBook.getEnchantedItemStack(new EnchantmentData(Enchantments.SILK_TOUCH, 1))));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinGold, 10), new ItemStack(ToolRegister.axeEnergistic)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinGold, 8), new ItemStack(ToolRegister.pickaxeEnergistic)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinGold, 9), new ItemStack(ToolRegister.shovelEnergistic)));
	}
}
