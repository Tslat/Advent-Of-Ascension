package net.tslat.aoa3.entity.npcs.lottoman;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.entity.base.AoATraderRecipe;
import net.tslat.aoa3.utils.ConfigurationUtil;

import javax.annotation.Nullable;

public class EntityRockyLottoman extends EntityLottoman {
	public EntityRockyLottoman(World world) {
		super(world);
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityRockyLottoman;
	}

	@Override
	protected boolean canDespawn() {
		return world.provider.getDimension() != ConfigurationUtil.MainConfig.dimensionIds.deeplands;
	}

	@Override
	protected boolean isOverworldNPC() {
		return false;
	}

	@Override
	protected void getTradesList(final NonNullList<AoATraderRecipe> newTradesList) {
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.tokensDeeplands, 28), new ItemStack(ItemRegister.lottoTotem)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.tokensDeeplands, 15), new ItemStack(ItemRegister.weaponsCase)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.tokensDeeplands, 10), new ItemStack(ItemRegister.runeBox)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.tokensDeeplands, 21), new ItemStack(ItemRegister.treasureBox)));
	}
}
