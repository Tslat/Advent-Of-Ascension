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
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinGold, 1), new ItemStack(ItemRegister.ingotLyon, 5), new ItemStack(ItemRegister.mechaGear, 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinSilver, 1), new ItemStack(Items.IRON_NUGGET, 5), new ItemStack(ItemRegister.dischargeCapsule, 8)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinSilver, 5), new ItemStack(ItemRegister.magnetShard, 2), new ItemStack(ItemRegister.silvroCoin, 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper, 15), new ItemStack(ItemRegister.scrapMetal, 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(BlockRegister.iroCrate, 1), new ItemStack(ItemRegister.goldSpring, 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinSilver, 5), new ItemStack(WeaponRegister.cannonMiniCannon, 1), new ItemStack(WeaponRegister.cannonSuperCannon, 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinSilver, 3), new ItemStack(ItemRegister.ingotLyon, 7), new ItemStack(WeaponRegister.shotgunDemolisher, 1)));
	}
}
