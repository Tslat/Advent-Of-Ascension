package net.tslat.aoa3.entity.npcs.banker;

import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.entity.base.AoATraderRecipe;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class EntityLelyetianBanker extends AoATrader {
	public static final float entityWidth = 0.5625f;

	public EntityLelyetianBanker(World world) {
		super(world, entityWidth, 2.0f);
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
		return Enums.ModGuis.TRADER_LELYETIAN_BANKER;
	}

	@Nullable
	@Override
	protected ITextComponent getInteractMessage() {
		return StringUtil.getLocale("message.dialogue.lelyetian_banker." + rand.nextInt(5));
	}

	@Override
	protected ArrayList<AoATraderRecipe> getNewTrades(final ArrayList<AoATraderRecipe> newList) {
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinLunaver, 1), new ItemStack(ItemRegister.coinGold, 20)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinGold, 1), new ItemStack(ItemRegister.coinSilver, 20)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinSilver, 1), new ItemStack(ItemRegister.coinCopper, 20)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper, 20), new ItemStack(ItemRegister.coinSilver, 1)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinSilver, 20), new ItemStack(ItemRegister.coinGold, 1)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinGold, 20), new ItemStack(ItemRegister.coinLunaver, 1)));

		return newList;
	}
}
