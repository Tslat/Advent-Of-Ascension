package net.tslat.aoa3.entity.npcs.lottoman;

import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.entity.base.AoATraderRecipe;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class EntityShyrelandsLottoman extends EntityLottoman {
	public EntityShyrelandsLottoman(World world) {
		super(world);
	}

	@Nullable
	@Override
	protected ITextComponent getInteractMessage() {
		return StringUtil.getLocale("message.dialogue.shyrelands_lottoman." + rand.nextInt(5));
	}

	@Override
	protected ArrayList<AoATraderRecipe> getNewTrades(final ArrayList<AoATraderRecipe> newList) {
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.tokensShyrelands, 10), new ItemStack(ItemRegister.totemSkyStaff)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.tokensShyrelands, 10), new ItemStack(ItemRegister.totemSoulBone)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.tokensShyrelands, 40), new ItemStack(ItemRegister.tokensShyrelands, 35), new ItemStack(ItemRegister.totemSkyStaff, 10)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.tokensShyrelands, 40), new ItemStack(ItemRegister.tokensShyrelands, 35), new ItemStack(ItemRegister.totemSoulBone, 10)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinGold, 5), new ItemStack(ItemRegister.magicRepairDust)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinGold, 50), new ItemStack(ItemRegister.magicRepairDust, 15)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.tokensShyrelands, 64), new ItemStack(ItemRegister.shyregem, 5), new ItemStack(ItemRegister.strangeStoneBlue)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.tokensShyrelands, 64), new ItemStack(ItemRegister.shyregem, 5), new ItemStack(ItemRegister.strangeStoneWhite)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.tokensShyrelands, 64), new ItemStack(ItemRegister.shyregem, 5), new ItemStack(ItemRegister.strangeStoneYellow)));

		return newList;
	}
}
