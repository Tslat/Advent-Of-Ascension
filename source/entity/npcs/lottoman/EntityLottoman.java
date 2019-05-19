package net.tslat.aoa3.entity.npcs.lottoman;

import net.minecraft.block.state.IBlockState;
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

public class EntityLottoman extends AoATrader {
	public static final float entityWidth = 0.5625f;

	public EntityLottoman(World world) {
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
	protected boolean canSpawnOnBlock(IBlockState block) {
		return super.canSpawnOnBlock(block) && (this.getClass() != EntityLottoman.class || WorldUtil.isNaturalOverworldBlock(block));
	}

	@Override
	protected Enums.ModGuis getTraderGui() {
		return Enums.ModGuis.TRADER_LOTTOMAN;
	}

	@Override
	protected boolean isOverworldNPC() {
		return true;
	}

	@Nullable
	@Override
	protected ITextComponent getInteractMessage() {
		return StringUtil.getLocale("message.dialogue.lottoman." + rand.nextInt(5));
	}

	@Override
	protected ArrayList<AoATraderRecipe> getNewTrades(final ArrayList<AoATraderRecipe> newList) {
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinSilver, 3), new ItemStack(ItemRegister.gemBag)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinSilver, 30), new ItemStack(ItemRegister.gemBag, 13)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinGold, 5), new ItemStack(ItemRegister.magicRepairDust)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinGold, 25), new ItemStack(ItemRegister.coinGold, 25), new ItemStack(ItemRegister.magicRepairDust, 15)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.orbulon, 15), new ItemStack(ItemRegister.magicRepairDust, 2)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinGold, 7), new ItemStack(ItemRegister.shinyBox)));

		return newList;
	}
}
