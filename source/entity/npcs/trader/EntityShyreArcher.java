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

public class EntityShyreArcher extends AoATrader {
	public static final float entityWidth = 0.5625f;

	public EntityShyreArcher(World world) {
		super(world, entityWidth, 2.0f);
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityShyreArcher;
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
		return Enums.ModGuis.TRADER_SHYRE_ARCHER;
	}

	@Override
	protected boolean canDespawn() {
		return world.provider.getDimension() != ConfigurationUtil.MainConfig.dimensionIds.shyrelands;
	}

	@Override
	protected void getTradesList(final NonNullList<AoATraderRecipe> newTradesList) {
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.gemShyregem, 7), new ItemStack(ItemRegister.ingotShyrestone, 12), new ItemStack(WeaponRegister.bowSunshine)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper, 3), new ItemStack(ItemRegister.hollyArrow)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper, 6), new ItemStack(ItemRegister.hollyArrowSpectral)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper, 1), new ItemStack(Items.ARROW)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinSilver, 9), new ItemStack(WeaponRegister.cannonUltraCannon, 1), new ItemStack(WeaponRegister.cannonGigaCannon)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.gemShyregem, 2), new ItemStack(ItemRegister.ingotShyrestone, 2), new ItemStack(ItemRegister.ancientRing)));
	}
}
