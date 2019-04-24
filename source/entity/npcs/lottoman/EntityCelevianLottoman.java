package net.tslat.aoa3.entity.npcs.lottoman;

import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.entity.base.AoATraderRecipe;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class EntityCelevianLottoman extends EntityLottoman {
	public EntityCelevianLottoman(World world) {
		super(world);
	}

	@Nullable
	@Override
	protected ITextComponent getInteractMessage() {
		return StringUtil.getLocale("message.dialogue.celevian_lottoman." + rand.nextInt(5));
	}

	@Override
	protected ArrayList<AoATraderRecipe> getNewTrades(final ArrayList<AoATraderRecipe> newList) {
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.tokensCeleve, 10), new ItemStack(ItemRegister.totemGoofyGreatblade)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.tokensCeleve, 10), new ItemStack(ItemRegister.totemWhimsyWinder)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.tokensCeleve, 40), new ItemStack(ItemRegister.tokensCeleve, 35), new ItemStack(ItemRegister.totemGoofyGreatblade, 10)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.tokensCeleve, 40), new ItemStack(ItemRegister.tokensCeleve, 35), new ItemStack(ItemRegister.totemWhimsyWinder, 10)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinGold, 5), new ItemStack(ItemRegister.magicRepairDust)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinGold, 50), new ItemStack(ItemRegister.magicRepairDust, 15)));

		return newList;
	}
}
