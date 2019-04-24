package net.tslat.aoa3.entity.npcs;

import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.entity.base.AoATraderRecipe;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class EntityLelyetianTrader extends AoATrader {
	public static final float entityWidth = 0.5625f;

	public EntityLelyetianTrader(World world) {
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
	protected Enums.ModGuis getTraderGui() {
		return Enums.ModGuis.TRADER_LELYETIAN_TRADER;
	}

	@Nullable
	@Override
	protected ITextComponent getInteractMessage() {
		return StringUtil.getLocale("message.dialogue.lelyetian_trader." + rand.nextInt(5));
	}

	@Override
	protected ArrayList<AoATraderRecipe> getNewTrades(final ArrayList<AoATraderRecipe> newList) {
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.zhinxDust, 45), new ItemStack(WeaponRegister.sniperDecimator)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinLunaver, 2), new ItemStack(ItemRegister.zhinxDust, 25), new ItemStack(WeaponRegister.gunCyclone)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinLunaver, 3), new ItemStack(ItemRegister.zhinxDust, 20), new ItemStack(ItemRegister.weaponParts)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinLunaver, 3), new ItemStack(ItemRegister.darkBones, 20), new ItemStack(ItemRegister.weaponParts)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinLunaver, 3), new ItemStack(ItemRegister.fleshyBones, 20), new ItemStack(ItemRegister.weaponParts)));

		return newList;
	}
}
