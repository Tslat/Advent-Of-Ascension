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

public class EntityToyMerchant extends AoATrader {
	public static final float entityWidth = 0.5625f;

	public EntityToyMerchant(World world) {
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
		return Enums.ModGuis.TRADER_TOY_MERCHANT;
	}

	@Nullable
	@Override
	protected ITextComponent getInteractMessage() {
		return StringUtil.getLocale("message.dialogue.toy_merchant." + rand.nextInt(5));
	}

	@Override
	protected ArrayList<AoATraderRecipe> getNewTrades(final ArrayList<AoATraderRecipe> newList) {
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.circusCoin, 20), new ItemStack(ItemRegister.toyGyrocopter)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.circusCoin, 1), new ItemStack(ItemRegister.balloon, 32)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.circusCoin, 5), new ItemStack(WeaponRegister.blasterConfettiCluster)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.circusCoin, 20), new ItemStack(WeaponRegister.blasterPartyPopper)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.circusCoin, 30), new ItemStack(WeaponRegister.cannonBalloonBomber)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.circusCoin, 45), new ItemStack(WeaponRegister.cannonBozoBlaster)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.circusCoin, 60), new ItemStack(WeaponRegister.staffShow)));

		return newList;
	}
}
