package net.tslat.aoa3.entity.npcs.trader;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.entity.base.AoATraderRecipe;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ConfigurationUtil;

public class EntityTokenCollector extends AoATrader {
	public static final float entityWidth = 0.5625f;

	public EntityTokenCollector(World world) {
		super(world, entityWidth, 2.0f);

		this.setEntityInvulnerable(true);
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
		return Enums.ModGuis.TRADER_TOKEN_COLLECTOR;
	}

	@Override
	protected boolean isFixedTradesList() {
		return true;
	}

	@Override
	protected boolean canDespawn() {
		return world.provider.getDimension() != ConfigurationUtil.MainConfig.dimensionIds.immortallis;
	}

	@Override
	protected void getTradesList(final NonNullList<AoATraderRecipe> newTradesList) {
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.DUNGEON_TOKENS, 1), new ItemStack(BlockRegister.IMMORTAL_BANNER, 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.DUNGEON_TOKENS, 1), new ItemStack(WeaponRegister.VULCANE, 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.DUNGEON_TOKENS, 13), new ItemStack(ItemRegister.VULCANE_AUGMENT_FIRE)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.DUNGEON_TOKENS, 9), new ItemStack(ItemRegister.VULCANE_AUGMENT_IMPAIRMENT)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.DUNGEON_TOKENS, 10), new ItemStack(ItemRegister.VULCANE_AUGMENT_POISON)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.DUNGEON_TOKENS, 5), new ItemStack(ItemRegister.VULCANE_AUGMENT_POWER)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.DUNGEON_TOKENS, 15), new ItemStack(ItemRegister.VULCANE_AUGMENT_WITHER)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.DUNGEON_TOKENS, 25), new ItemStack(ItemRegister.VULCANE_AUGMENT_EQUALITY)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.DUNGEON_TOKENS, 20), new ItemStack(ItemRegister.VULCANE_AUGMENT_BATTLE)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.DUNGEON_TOKENS, 28), new ItemStack(WeaponRegister.VULCAMMER_MAUL)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.DUNGEON_TOKENS, 26), new ItemStack(WeaponRegister.METEOR_STAFF)));
	}
}
