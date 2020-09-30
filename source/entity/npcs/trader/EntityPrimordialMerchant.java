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
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.DARKLY_POWDER, 7), new ItemStack(WeaponRegister.DAYBREAKER_BOW)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.COPPER_COIN, 8), new ItemStack(Items.BEEF)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.COPPER_COIN), new ItemStack(Blocks.TORCH, 2)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.COPPER_COIN, 14), new ItemStack(ItemRegister.LIMONITE_BULLET, 4)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.DARKLY_POWDER, 2), new ItemStack(ItemRegister.SILVER_COIN, 2), new ItemStack(ArmourRegister.PRIMORDIAL_HELMET)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.DARKLY_POWDER, 3), new ItemStack(ItemRegister.SILVER_COIN, 3), new ItemStack(ArmourRegister.PRIMORDIAL_CHESTPLATE)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.DARKLY_POWDER, 2), new ItemStack(ItemRegister.SILVER_COIN, 3), new ItemStack(ArmourRegister.PRIMORDIAL_LEGS)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.DARKLY_POWDER, 2), new ItemStack(ItemRegister.SILVER_COIN, 2), new ItemStack(ArmourRegister.PRIMORDIAL_BOOTS)));
	}
}
