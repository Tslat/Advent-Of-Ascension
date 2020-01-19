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
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper, 1), new ItemStack(Items.CARROT, 3)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper, 1), new ItemStack(Items.POTATO, 2)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper, 1), new ItemStack(Items.MELON, 4)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper, 3), new ItemStack(Items.FISH, 1, 0)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper, 3), new ItemStack(Items.FISH, 1, 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper, 1), new ItemStack(Items.BEETROOT, 2)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper, 1), new ItemStack(Items.COOKIE, 3)));
	}
}
