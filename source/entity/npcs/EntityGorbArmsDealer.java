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

public class EntityGorbArmsDealer extends AoATrader {
	public static final float entityWidth = 0.5625f;

	public EntityGorbArmsDealer(World world) {
		super(world, entityWidth, 1.6875f);
	}

	@Override
	public float getEyeHeight() {
		return 1.5f;
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
		return Enums.ModGuis.TRADER_GORB_ARMS_DEALER;
	}

	@Nullable
	@Override
	protected ITextComponent getInteractMessage() {
		return StringUtil.getLocale("message.dialogue.gorb_arms_dealer." + rand.nextInt(5));
	}

	@Override
	protected ArrayList<AoATraderRecipe> getNewTrades(final ArrayList<AoATraderRecipe> newList) {
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.nightmareFlakes, 15), new ItemStack(ItemRegister.ghostlyPowder, 15), new ItemStack(WeaponRegister.bowNightmare)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.phantasm, 15), new ItemStack(ItemRegister.ghostlyPowder, 15), new ItemStack(WeaponRegister.bowScreamer)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.runeStone, 20), new ItemStack(WeaponRegister.bowRunic)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.nightmareFlakes, 15), new ItemStack(ItemRegister.trollSkull, 10), new ItemStack(WeaponRegister.staffNightmare)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.phantasm, 15), new ItemStack(ItemRegister.trollSkull, 10), new ItemStack(WeaponRegister.staffPhantom)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.runeStone, 20), new ItemStack(WeaponRegister.staffRunic)));

		return newList;
	}
}
