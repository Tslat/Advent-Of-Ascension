package net.tslat.aoa3.entity.npcs.trader;

import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.*;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.entity.base.AoATraderRecipe;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ConfigurationUtil;

import javax.annotation.Nullable;

public class EntityZalSpellbinder extends AoATrader {
	public static final float entityWidth = 0.5625f;

	public EntityZalSpellbinder(World world) {
		super(world, entityWidth, 1.875f);
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityZalSpellbinder;
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
		return Enums.ModGuis.TRADER_ZAL_SPELLBINDER;
	}

	@Override
	protected boolean canDespawn() {
		return world.provider.getDimension() != ConfigurationUtil.MainConfig.dimensionIds.lunalus;
	}

	@Override
	protected void getTradesList(final NonNullList<AoATraderRecipe> newTradesList) {
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper), new ItemStack(ItemRegister.runeLunar)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper), new ItemStack(ItemRegister.runeDistortion)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper), new ItemStack(ItemRegister.runeLife)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper), new ItemStack(ItemRegister.runeKinetic)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinSilver), new ItemStack(BlockRegister.runeShrine)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinLunaver, 2), ItemEnchantedBook.getEnchantedItemStack(new EnchantmentData(Enchantments.MENDING, 1))));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinSilver, 7), new ItemStack(WeaponRegister.cannonSuperCannon, 1), new ItemStack(WeaponRegister.cannonUltraCannon)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinGold, 10), new ItemStack(ToolRegister.axeSoulstone)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinGold, 11), new ItemStack(ToolRegister.shovelSoulstone)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinGold, 12), new ItemStack(ToolRegister.pickaxeSoulstone)));
	}
}
