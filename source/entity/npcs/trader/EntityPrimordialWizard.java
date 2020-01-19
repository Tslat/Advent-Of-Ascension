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

public class EntityPrimordialWizard extends AoATrader {
	public static final float entityWidth = 0.5625f;

	public EntityPrimordialWizard(World world) {
		super(world, entityWidth, 2.0f);
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityPrimordialWizard;
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
		return Enums.ModGuis.TRADER_PRIMORDIAL_WIZARD;
	}

	@Override
	protected boolean canDespawn() {
		return world.provider.getDimension() != ConfigurationUtil.MainConfig.dimensionIds.dustopia;
	}

	@Override
	protected void getTradesList(final NonNullList<AoATraderRecipe> newTradesList) {
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.primordialSkull, 1), new ItemStack(ItemRegister.coinCopper, 15)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper, 2), new ItemStack(WeaponRegister.throwableHellfire, 2)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper, 3), new ItemStack(WeaponRegister.throwableVulkram, 3)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinGold, 2), new ItemStack(WeaponRegister.staffPrimordial, 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinLunaver, 1), new ItemStack(Items.DRAGON_BREATH, 1)));
	}
}
