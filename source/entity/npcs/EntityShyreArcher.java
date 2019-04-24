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

public class EntityShyreArcher extends AoATrader {
	public static final float entityWidth = 0.5625f;

	public EntityShyreArcher(World world) {
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
		return Enums.ModGuis.TRADER_SHYRE_ARCHER;
	}

	@Nullable
	@Override
	protected ITextComponent getInteractMessage() {
		return StringUtil.getLocale("message.dialogue.shyre_archer." + rand.nextInt(5));
	}

	@Override
	protected ArrayList<AoATraderRecipe> getNewTrades(final ArrayList<AoATraderRecipe> newList) {
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.shyregem, 7), new ItemStack(ItemRegister.ingotShyrestone, 20), new ItemStack(WeaponRegister.bowJustice)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.shyregem, 7), new ItemStack(ItemRegister.ingotShyrestone, 20), new ItemStack(WeaponRegister.bowShyregem)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.shyregem, 7), new ItemStack(ItemRegister.ingotShyrestone, 20), new ItemStack(WeaponRegister.bowSunshine)));

		return newList;
	}
}
