package net.tslat.aoa3.entity.npcs;

import net.minecraft.init.Items;
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

public class EntityZalGrocer extends AoATrader {
	public static final float entityWidth = 0.5625f;

	public EntityZalGrocer(World world) {
		super(world, entityWidth, 1.875f);
	}

	@Override
	public float getEyeHeight() {
		return 0.6875f;
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
		return Enums.ModGuis.TRADER_ZAL_GROCER;
	}

	@Nullable
	@Override
	protected ITextComponent getInteractMessage() {
		return StringUtil.getLocale("message.dialogue.zal_grocer." + rand.nextInt(5));
	}

	@Override
	protected ArrayList<AoATraderRecipe> getNewTrades(final ArrayList<AoATraderRecipe> newList) {
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinSilver, 5), new ItemStack(Items.COOKIE, 5)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinSilver, 15), new ItemStack(Items.COOKIE, 20)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinSilver, 40), new ItemStack(Items.COOKIE, 64)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinSilver, 10), new ItemStack(Items.BEEF, 5)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinSilver, 50), new ItemStack(Items.BEEF, 30)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinGold, 5), new ItemStack(Items.BEEF, 64)));

		return newList;
	}
}
