package net.tslat.aoa3.entity.npcs;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.entity.base.AoATraderRecipe;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.StringUtil;
import net.tslat.aoa3.utils.WorldUtil;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class EntityCorruptedTraveller extends AoATrader {
	public static final float entityWidth = 0.5625f;

	public EntityCorruptedTraveller(World world) {
		super(world, entityWidth, 2.0f);
	}

	@Override
	protected double getBaseMaxHealth() {
		return 50;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.329;
	}

	@Override
	protected Enums.ModGuis getTraderGui() {
		return Enums.ModGuis.TRADER_CORRUPTED_TRAVELLER;
	}

	@Override
	protected boolean canSpawnOnBlock(IBlockState block) {
		return super.canSpawnOnBlock(block) && WorldUtil.isNaturalOverworldBlock(block);
	}

	@Override
	protected boolean isFixedTradesList() {
		return true;
	}

	@Nullable
	@Override
	protected ITextComponent getInteractMessage() {
		return StringUtil.getLocale("message.dialogue.corrupted_traveller." + rand.nextInt(5));
	}

	@Override
	protected ArrayList<AoATraderRecipe> getNewTrades(final ArrayList<AoATraderRecipe> newList) {
		newList.add(new AoATraderRecipe(new ItemStack(Items.COOKED_BEEF, 1), new ItemStack(ItemRegister.wornBook, 1)));
		newList.add(new AoATraderRecipe(new ItemStack(Items.COOKED_PORKCHOP, 1), new ItemStack(ItemRegister.wornBook, 1)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.ursaMeat, 1), new ItemStack(ItemRegister.wornBook, 1)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.chimeraChop, 1), new ItemStack(ItemRegister.wornBook, 1)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.furlionChop, 1), new ItemStack(ItemRegister.wornBook, 1)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.halyconBeef, 1), new ItemStack(ItemRegister.wornBook, 1)));

		return newList;
	}
}
