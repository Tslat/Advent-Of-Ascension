package net.tslat.aoa3.entity.npc.trader;

import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoATools;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.entity.npc.AoATraderRecipe;
import net.tslat.aoa3.util.WorldUtil;

public class PrimordialSpellbinderEntity extends AoATrader {
	public PrimordialSpellbinderEntity(EntityType<? extends CreatureEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 1.73125f;
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return !WorldUtil.isWorld(level, AoADimensions.DUSTOPIA.key);
	}

	@Override
	protected void getTradesList(final NonNullList<AoATraderRecipe> newTradesList) {
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.COPPER_COIN.get()), new ItemStack(AoAItems.STORM_RUNE.get())));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.COPPER_COIN.get()), new ItemStack(AoAItems.POWER_RUNE.get())));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.COPPER_COIN.get()), new ItemStack(AoAItems.WITHER_RUNE.get())));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.COPPER_COIN.get()), new ItemStack(AoAItems.COMPASS_RUNE.get())));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.LUNAVER_COIN.get()), EnchantedBookItem.createForEnchantment(new EnchantmentData(Enchantments.SILK_TOUCH, 1))));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.GOLD_COIN.get(), 8), new ItemStack(AoATools.ENERGISTIC_AXE.get())));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.GOLD_COIN.get(), 10), new ItemStack(AoATools.ENERGISTIC_PICKAXE.get())));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.GOLD_COIN.get(), 9), new ItemStack(AoATools.ENERGISTIC_SHOVEL.get())));
	}
}
