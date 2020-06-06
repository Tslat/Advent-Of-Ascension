package net.tslat.aoa3.entity.npcs.trader;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.entity.base.AoATraderRecipe;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ConfigurationUtil;

import javax.annotation.Nullable;

public class EntityProfessor extends AoATrader {
	public static final float entityWidth = 0.5625f;

	public EntityProfessor(World world) {
		super(world, entityWidth, 2.0f);
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityProfessor;
	}

	@Override
	public float getEyeHeight() {
		return 1.7625f;
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
		return Enums.ModGuis.TRADER_PROFESSOR;
	}

	@Override
	protected boolean isFixedTradesList() {
		return true;
	}

	@Override
	protected boolean canDespawn() {
		return world.provider.getDimension() != ConfigurationUtil.MainConfig.dimensionIds.iromine;
	}

	@Override
	protected void getTradesList(final NonNullList<AoATraderRecipe> newTradesList) {
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.GOLD_COIN, 1), new ItemStack(ItemRegister.LYON_INGOT, 5), new ItemStack(ItemRegister.MECHA_GEAR, 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.SILVER_COIN, 1), new ItemStack(Items.IRON_NUGGET, 5), new ItemStack(ItemRegister.DISCHARGE_CAPSULE, 8)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.SILVER_COIN, 5), new ItemStack(ItemRegister.MAGNET_SHARD, 2), new ItemStack(ItemRegister.SILVRO_COIN, 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.COPPER_COIN, 15), new ItemStack(ItemRegister.SCRAP_METAL, 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(BlockRegister.IRO_CRATE, 1), new ItemStack(ItemRegister.GOLD_SPRING, 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.SILVER_COIN, 5), new ItemStack(WeaponRegister.MINI_CANNON, 1), new ItemStack(WeaponRegister.SUPER_CANNON, 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.SILVER_COIN, 3), new ItemStack(ItemRegister.LYON_INGOT, 7), new ItemStack(WeaponRegister.DEMOLISHER, 1)));
	}
}
