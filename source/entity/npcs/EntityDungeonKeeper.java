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

public class EntityDungeonKeeper extends AoATrader {
	public static final float entityWidth = 0.5625f;

	public EntityDungeonKeeper(World world) {
		super(world, entityWidth, 2.0f);

		this.setEntityInvulnerable(true);
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
	protected boolean isFixedTradesList() {
		return true;
	}

	@Override
	protected Enums.ModGuis getTraderGui() {
		return Enums.ModGuis.TRADER_DUNGEON_KEEPER;
	}

	@Nullable
	@Override
	protected ITextComponent getInteractMessage() {
		return StringUtil.getLocale("message.dialogue.dungeon_keeper");
	}

	@Override
	protected ArrayList<AoATraderRecipe> getNewTrades(final ArrayList<AoATraderRecipe> newList) {
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinGold, 10), new ItemStack(ItemRegister.progressCoin0, 1)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinGold, 2), new ItemStack(WeaponRegister.vulcane, 1)));

		return newList;
	}
}
