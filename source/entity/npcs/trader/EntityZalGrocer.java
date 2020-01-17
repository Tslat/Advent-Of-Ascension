package net.tslat.aoa3.entity.npcs.trader;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.entity.base.AoATraderRecipe;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ConfigurationUtil;

import javax.annotation.Nullable;

public class EntityZalGrocer extends AoATrader {
	public static final float entityWidth = 0.5625f;

	public EntityZalGrocer(World world) {
		super(world, entityWidth, 1.875f);
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityZalGrocer;
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
		return Enums.ModGuis.TRADER_ZAL_GROCER;
	}

	@Override
	protected boolean canDespawn() {
		return world.provider.getDimension() != ConfigurationUtil.MainConfig.dimensionIds.lunalus;
	}

	@Override
	protected void getTradesList(final NonNullList<AoATraderRecipe> newTradesList) {
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinSilver, 5), new ItemStack(Items.COOKIE, 5)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinSilver, 15), new ItemStack(Items.COOKIE, 20)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinSilver, 40), new ItemStack(Items.COOKIE, 64)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinSilver, 10), new ItemStack(Items.BEEF, 5)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinSilver, 50), new ItemStack(Items.BEEF, 30)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinGold, 5), new ItemStack(Items.BEEF, 64)));
	}
}
