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

public class EntityStoreKeeper extends AoATrader {
	public static final float entityWidth = 0.5625f;

	public EntityStoreKeeper(World world) {
		super(world, entityWidth, 2.0f);
	}

	@Override
	protected double getBaseMaxHealth() {
		return 20;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.329;
	}

	@Override
	protected Enums.ModGuis getTraderGui() {
		return Enums.ModGuis.TRADER_STORE_KEEPER;
	}

	@Nullable
	@Override
	protected ITextComponent getInteractMessage() {
		return StringUtil.getLocale("message.dialogue.store_keeper." + rand.nextInt(5));
	}

	@Override
	protected ArrayList<AoATraderRecipe> getNewTrades(final ArrayList<AoATraderRecipe> newList) {
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinGold, 50), new ItemStack(ItemRegister.sludgeParasite, 2), new ItemStack(WeaponRegister.cannonVoxCannon)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinGold, 50), new ItemStack(ItemRegister.toxicLump, 2), new ItemStack(WeaponRegister.blasterPoisonPlunger)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinGold, 50), new ItemStack(ItemRegister.doomStone, 2), new ItemStack(WeaponRegister.blasterGasBlaster)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinLunaver, 4), new ItemStack(ItemRegister.sludgeParasite, 2), new ItemStack(WeaponRegister.sniperSludgeSniper)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinLunaver, 4), new ItemStack(ItemRegister.toxicLump, 2), new ItemStack(WeaponRegister.staffNoxious)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinLunaver, 4), new ItemStack(ItemRegister.toxicLump, 2), new ItemStack(WeaponRegister.bowToxin)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinLunaver, 4), new ItemStack(ItemRegister.doomStone, 2), new ItemStack(WeaponRegister.gunVileVanquisher)));

		return newList;
	}
}
