package net.tslat.aoa3.entity.npc.trader;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Effects;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.entity.npc.AoATraderRecipe;
import net.tslat.aoa3.util.PotionUtil;
import net.tslat.aoa3.util.WorldUtil;

public class StoreKeeperEntity extends AoATrader {
	public StoreKeeperEntity(EntityType<? extends CreatureEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return !WorldUtil.isWorld(level, AoADimensions.VOX_PONDS.key);
	}

	@Override
	protected void getTradesList(final NonNullList<AoATraderRecipe> newTradesList) {
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.TOXIC_LUMP.get(), 1), new ItemStack(AoAItems.VOX_PONDS_TOKENS.get(), 10)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(Items.POISONOUS_POTATO, 1), new ItemStack(AoAItems.COPPER_COIN.get(), 16)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.GOLD_COIN.get(), 1), new ItemStack(AoABlocks.VOX_GLASS.get(), 32)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.SILVER_COIN.get(), 4), poisonPotionStack()));
	}

	private ItemStack poisonPotionStack() {
		return new PotionUtil.PotionBuilder(Items.LINGERING_POTION).addEffect(new PotionUtil.EffectBuilder(Effects.POISON, 600).build()).withTranslationKey("item.minecraft.lingering_potion.effect.poison").build();
	}
}
