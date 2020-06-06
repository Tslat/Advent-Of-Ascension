package net.tslat.aoa3.entity.npcs.trader;

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

public class EntityCrystalTrader extends AoATrader {
	public static final float entityWidth = 0.5625f;

	public EntityCrystalTrader(World world) {
		super(world, entityWidth, 2.0f);
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityCrystalTrader;
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
	protected boolean isFixedTradesList() {
		return true;
	}

	@Override
	protected Enums.ModGuis getTraderGui() {
		return Enums.ModGuis.TRADER_CRYSTAL_TRADER;
	}

	@Override
	protected boolean canDespawn() {
		return world.provider.getDimension() != ConfigurationUtil.MainConfig.dimensionIds.crystevia;
	}

	@Override
	protected void getTradesList(final NonNullList<AoATraderRecipe> newTradesList) {
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.BLUE_CRYSTAL, 1), new ItemStack(ItemRegister.CRYSTEVIA_TOKENS, 3)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.GREEN_CRYSTAL, 1), new ItemStack(ItemRegister.CRYSTEVIA_TOKENS, 3)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.PURPLE_CRYSTAL, 1), new ItemStack(ItemRegister.CRYSTEVIA_TOKENS, 3)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.RED_CRYSTAL, 1), new ItemStack(ItemRegister.CRYSTEVIA_TOKENS, 3)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.WHITE_CRYSTAL, 1), new ItemStack(ItemRegister.CRYSTEVIA_TOKENS, 3)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.YELLOW_CRYSTAL, 1), new ItemStack(ItemRegister.CRYSTEVIA_TOKENS, 3)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.BLUE_CRYSTAL, 16), new ItemStack(ItemRegister.BLUE_DRUSE)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.GREEN_CRYSTAL, 16), new ItemStack(ItemRegister.GREEN_DRUSE)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.PURPLE_CRYSTAL, 16), new ItemStack(ItemRegister.PURPLE_DRUSE)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.RED_CRYSTAL, 16), new ItemStack(ItemRegister.RED_DRUSE)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.WHITE_CRYSTAL, 16), new ItemStack(ItemRegister.WHITE_DRUSE)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.YELLOW_CRYSTAL, 16), new ItemStack(ItemRegister.YELLOW_DRUSE)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.RAINBOW_DRUSE, 12), new ItemStack(WeaponRegister.CRYSTAL_MAUL)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.RAINBOW_DRUSE, 12), new ItemStack(WeaponRegister.CRYSTAL_GREATBLADE)));
	}
}
