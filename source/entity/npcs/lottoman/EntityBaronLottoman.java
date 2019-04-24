package net.tslat.aoa3.entity.npcs.lottoman;

import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.entity.base.AoATraderRecipe;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class EntityBaronLottoman extends EntityLottoman {
	public EntityBaronLottoman(World world) {
		super(world);
	}

	@Nullable
	@Override
	protected ITextComponent getInteractMessage() {
		return StringUtil.getLocale("message.dialogue.baron_lottoman." + rand.nextInt(5));
	}

	@Override
	protected ArrayList<AoATraderRecipe> getNewTrades(final ArrayList<AoATraderRecipe> newList) {
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.tokensBaron, 10), new ItemStack(ItemRegister.totemBaronStaff)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.tokensBaron, 10), new ItemStack(ItemRegister.totemBaronSSR)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.tokensBaron, 40), new ItemStack(ItemRegister.tokensBaron, 35), new ItemStack(ItemRegister.totemBaronStaff, 10)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.tokensBaron, 40), new ItemStack(ItemRegister.tokensBaron, 35), new ItemStack(ItemRegister.totemBaronSSR, 10)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinGold, 5), new ItemStack(ItemRegister.magicRepairDust)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinGold, 50), new ItemStack(ItemRegister.magicRepairDust, 15)));

		return newList;
	}
}
