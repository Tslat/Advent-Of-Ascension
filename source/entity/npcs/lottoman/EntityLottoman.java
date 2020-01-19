package net.tslat.aoa3.entity.npcs.lottoman;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.entity.base.AoATraderRecipe;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

public class EntityLottoman extends AoATrader {
	public static final float entityWidth = 0.5625f;

	public EntityLottoman(World world) {
		super(world, entityWidth, 2.0f);
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityLottoman;
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
		return Enums.ModGuis.TRADER_LOTTOMAN;
	}

	@Override
	protected boolean isOverworldNPC() {
		return true;
	}

	@Override
	protected void getTradesList(final NonNullList<AoATraderRecipe> newTradesList) {
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinSilver, 14), new ItemStack(ItemRegister.lottoTotem)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper, 31), new ItemStack(ItemRegister.weaponsCase)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper, 14), new ItemStack(ItemRegister.runeBox)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper, 41), new ItemStack(ItemRegister.treasureBox)));
	}
}
