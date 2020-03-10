package net.tslat.aoa3.entity.npcs.trader;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.entity.base.AoATraderRecipe;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

public class EntityMetalloid extends AoATrader {
	public static final float entityWidth = 0.5625f;

	public EntityMetalloid(World world) {
		super(world, entityWidth, 2.0f);
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityMetalloid;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 25;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.329;
	}

	@Override
	protected Enums.ModGuis getTraderGui() {
		return Enums.ModGuis.TRADER_METALLOID;
	}

	@Override
	protected void getTradesList(final NonNullList<AoATraderRecipe> newTradesList) {
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper, 15), new ItemStack(ItemRegister.ingotLimonite, 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper, 20), new ItemStack(ItemRegister.ingotRosite, 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper, 30), new ItemStack(ItemRegister.gemJade, 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper, 40), new ItemStack(ItemRegister.gemAmethyst, 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper, 60), new ItemStack(ItemRegister.gemSapphire, 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper, 45), new ItemStack(ItemRegister.ingotBaronyte, 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper, 50), new ItemStack(ItemRegister.ingotBlazium, 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper, 47), new ItemStack(ItemRegister.ingotVarsium, 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper, 59), new ItemStack(ItemRegister.ingotLyon, 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.ingotLimonite, 1), new ItemStack(ItemRegister.coinCopper, 2)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.ingotRosite, 1), new ItemStack(ItemRegister.coinCopper, 4)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.gemJade, 1), new ItemStack(ItemRegister.coinCopper, 6)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.gemAmethyst, 1), new ItemStack(ItemRegister.coinCopper, 8)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.gemSapphire, 1), new ItemStack(ItemRegister.coinCopper, 10)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.ingotBaronyte, 1), new ItemStack(ItemRegister.coinCopper, 8)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.ingotBlazium, 1), new ItemStack(ItemRegister.coinCopper, 9)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.ingotElecanium, 1), new ItemStack(ItemRegister.coinCopper, 12)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.ingotEmberstone, 1), new ItemStack(ItemRegister.coinCopper, 10)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.ingotGhastly, 1), new ItemStack(ItemRegister.coinCopper, 9)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.ingotGhoulish, 1), new ItemStack(ItemRegister.coinCopper, 9)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.ingotLunar, 1), new ItemStack(ItemRegister.coinCopper, 8)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.ingotLyon, 1), new ItemStack(ItemRegister.coinCopper, 9)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.ingotShyrestone, 1), new ItemStack(ItemRegister.coinCopper, 13)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.ingotSkeletal, 1), new ItemStack(ItemRegister.coinCopper, 8)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.ingotVarsium, 1), new ItemStack(ItemRegister.coinCopper, 8)));
	}
}
