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

public class EntityGoldenLottoman extends EntityLottoman {
	public EntityGoldenLottoman(World world) {
		super(world);
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityGoldenLottoman;
	}

	@Override
	protected boolean canDespawn() {
		return world.provider.getDimension() != ConfigurationUtil.MainConfig.dimensionIds.iromine;
	}

	@Override
	protected boolean isOverworldNPC() {
		return false;
	}

	@Override
	protected void getTradesList(final NonNullList<AoATraderRecipe> newTradesList) {
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.IROMINE_TOKENS, 28), new ItemStack(ItemRegister.LOTTO_TOTEM)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.IROMINE_TOKENS, 15), new ItemStack(ItemRegister.WEAPONS_CASE)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.IROMINE_TOKENS, 10), new ItemStack(ItemRegister.RUNE_BOX)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.IROMINE_TOKENS, 21), new ItemStack(ItemRegister.TREASURE_BOX)));
	}
}
