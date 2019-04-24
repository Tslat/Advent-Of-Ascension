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

public class EntityPrimordialWizard extends AoATrader {
	public static final float entityWidth = 0.5625f;

	public EntityPrimordialWizard(World world) {
		super(world, entityWidth, 2.0f);
	}

	@Override
	public float getEyeHeight() {
		return 1.73125f;
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
		return Enums.ModGuis.TRADER_PRIMORDIAL_WIZARD;
	}

	@Nullable
	@Override
	protected ITextComponent getInteractMessage() {
		return StringUtil.getLocale("message.dialogue.primordial_wizard." + rand.nextInt(5));
	}

	@Override
	protected ArrayList<AoATraderRecipe> getNewTrades(final ArrayList<AoATraderRecipe> newList) {
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinSilver, 1), new ItemStack(ItemRegister.runeWind, 40)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinSilver, 1), new ItemStack(ItemRegister.runeFire, 40)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinSilver, 1), new ItemStack(ItemRegister.runeWither, 30)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinSilver, 1), new ItemStack(ItemRegister.runeStorm, 30)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinSilver, 1), new ItemStack(ItemRegister.runeEnergy, 30)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinSilver, 1), new ItemStack(ItemRegister.runeKinetic, 30)));

		return newList;
	}
}
