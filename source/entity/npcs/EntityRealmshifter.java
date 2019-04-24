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

public class EntityRealmshifter extends AoATrader {
	public static final float entityWidth = 0.5625f;

	public EntityRealmshifter(World world) {
		super(world, entityWidth, 2.0f);
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
		return Enums.ModGuis.TRADER_REALMSHIFTER;
	}

	@Override
	protected boolean isOverworldNPC() {
		return true;
	}

	@Nullable
	@Override
	protected ITextComponent getInteractMessage() {
		return StringUtil.getLocale("message.dialogue.realmshifter." + rand.nextInt(5));
	}

	@Override
	protected ArrayList<AoATraderRecipe> getNewTrades(final ArrayList<AoATraderRecipe> newList) {
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.realmstoneAbyss, 1), new ItemStack(ItemRegister.coinSilver, 6)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.realmstoneAncientCavern, 1), new ItemStack(ItemRegister.coinSilver, 9)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.realmstoneBarathos, 1), new ItemStack(ItemRegister.coinSilver, 8)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.realmstoneBorean, 1), new ItemStack(ItemRegister.coinSilver, 7)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.realmstoneCeleve, 1), new ItemStack(ItemRegister.coinSilver, 4)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.realmstoneCrystevia, 1), new ItemStack(ItemRegister.coinSilver, 6)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.realmstoneDeeplands, 1), new ItemStack(ItemRegister.coinCopper, 9)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.realmstoneDustopia, 1), new ItemStack(ItemRegister.coinSilver, 6)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.realmstoneGardencia, 1), new ItemStack(ItemRegister.coinSilver, 2)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.realmstoneHaven, 1), new ItemStack(ItemRegister.coinSilver, 1)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.realmstoneIromine, 1), new ItemStack(ItemRegister.coinCopper, 4)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.realmstoneLelyetia, 1), new ItemStack(ItemRegister.coinSilver, 3)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.realmstoneMysterium, 1), new ItemStack(ItemRegister.coinSilver, 1)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.realmstonePrecasia, 1), new ItemStack(ItemRegister.coinCopper, 16)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.realmstoneVoxPonds, 1), new ItemStack(ItemRegister.coinSilver, 4)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.realmstoneCandyland, 1), new ItemStack(ItemRegister.coinSilver, 5)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.realmstoneCreeponia, 1), new ItemStack(ItemRegister.coinSilver, 6)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.realmstoneImmortallis, 1), new ItemStack(ItemRegister.coinSilver, 6)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.realmstoneShyrelands, 1), new ItemStack(ItemRegister.coinSilver, 9)));

		return newList;
	}
}
