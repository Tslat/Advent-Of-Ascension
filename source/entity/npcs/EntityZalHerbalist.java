package net.tslat.aoa3.entity.npcs;

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

public class EntityZalHerbalist extends AoATrader {
	public static final float entityWidth = 0.5625f;

	public EntityZalHerbalist(World world) {
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
		return Enums.ModGuis.TRADER_ZAL_HERBALIST;
	}

	@Nullable
	@Override
	protected ITextComponent getInteractMessage() {
		return StringUtil.getLocale("message.dialogue.zal_herbalist." + rand.nextInt(5));
	}

	@Override
	protected ArrayList<AoATraderRecipe> getNewTrades(final ArrayList<AoATraderRecipe> newList) {
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinSilver, 5), new ItemStack(ItemRegister.seedsLunacrike, 5)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinSilver, 5), new ItemStack(ItemRegister.seedsLunaGlobe, 5)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinSilver, 5), new ItemStack(ItemRegister.seedsLunalon, 5)));

		return newList;
	}
}
