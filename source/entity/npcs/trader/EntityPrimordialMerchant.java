package net.tslat.aoa3.entity.npcs.trader;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
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

public class EntityPrimordialMerchant extends AoATrader {
	public static final float entityWidth = 0.5625f;

	public EntityPrimordialMerchant(World world) {
		super(world, entityWidth, 2.0f);
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityPrimordialMerchant;
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
		return Enums.ModGuis.TRADER_PRIMORDIAL_MERCHANT;
	}

	@Override
	protected boolean canDespawn() {
		return world.provider.getDimension() != ConfigurationUtil.MainConfig.dimensionIds.dustopia;
	}

	@Override
	protected void getTradesList(final NonNullList<AoATraderRecipe> newTradesList) {
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.darklyPowder, 7), new ItemStack(WeaponRegister.bowDaybreaker)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper, 8), new ItemStack(Items.BEEF)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper), new ItemStack(Blocks.TORCH, 2)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper, 14), new ItemStack(ItemRegister.bulletLimonite, 4)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.darklyPowder, 2), new ItemStack(ItemRegister.coinSilver, 2), new ItemStack(ArmourRegister.primordialHelmet)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.darklyPowder, 3), new ItemStack(ItemRegister.coinSilver, 3), new ItemStack(ArmourRegister.primordialBody)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.darklyPowder, 2), new ItemStack(ItemRegister.coinSilver, 3), new ItemStack(ArmourRegister.primordialLegs)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.darklyPowder, 2), new ItemStack(ItemRegister.coinSilver, 2), new ItemStack(ArmourRegister.primordialBoots)));
	}
}
