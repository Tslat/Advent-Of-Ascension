package net.tslat.aoa3.entity.npcs;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
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

public class EntityExplosivesExpert extends AoATrader {
	public static final float entityWidth = 0.5625f;

	public EntityExplosivesExpert(World world) {
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
		return Enums.ModGuis.TRADER_EXPLOSIVES_EXPERT;
	}

	@Nullable
	@Override
	protected ITextComponent getInteractMessage() {
		return StringUtil.getLocale("message.dialogue.explosives_expert." + rand.nextInt(5));
	}

	@Override
	protected ArrayList<AoATraderRecipe> getNewTrades(final ArrayList<AoATraderRecipe> newList) {
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper, 2), new ItemStack(WeaponRegister.throwableGrenade, 8)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper, 2), new ItemStack(ItemRegister.dischargeCapsule, 8)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper, 2), new ItemStack(Item.getItemFromBlock(Blocks.TNT), 8)));

		return newList;
	}
}
