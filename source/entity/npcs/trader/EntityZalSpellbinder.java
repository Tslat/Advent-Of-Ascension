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
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.COPPER_COIN), new ItemStack(ItemRegister.LUNAR_RUNE)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.COPPER_COIN), new ItemStack(ItemRegister.DISTORTION_RUNE)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.COPPER_COIN), new ItemStack(ItemRegister.LIFE_RUNE)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.COPPER_COIN), new ItemStack(ItemRegister.KINETIC_RUNE)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.SILVER_COIN), new ItemStack(BlockRegister.RUNE_SHRINE)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.LUNAVER_COIN, 2), ItemEnchantedBook.getEnchantedItemStack(new EnchantmentData(Enchantments.MENDING, 1))));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.SILVER_COIN, 7), new ItemStack(WeaponRegister.SUPER_CANNON, 1), new ItemStack(WeaponRegister.ULTRA_CANNON)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.GOLD_COIN, 10), new ItemStack(ToolRegister.SOULSTONE_AXE)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.GOLD_COIN, 11), new ItemStack(ToolRegister.SOULSTONE_SHOVEL)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.GOLD_COIN, 12), new ItemStack(ToolRegister.SOULSTONE_PICKAXE)));
	}
}
