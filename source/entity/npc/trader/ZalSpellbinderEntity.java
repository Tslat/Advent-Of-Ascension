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
import net.tslat.aoa3.common.registration.*;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.entity.npc.AoATraderRecipe;
import net.tslat.aoa3.util.WorldUtil;

public class ZalSpellbinderEntity extends AoATrader {
	public ZalSpellbinderEntity(EntityType<? extends CreatureEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 0.6875f;
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return !WorldUtil.isWorld(level, AoADimensions.LUNALUS.key);
	}

	@Override
	protected void getTradesList(final NonNullList<AoATraderRecipe> newTradesList) {
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.COPPER_COIN.get()), new ItemStack(AoAItems.LUNAR_RUNE.get())));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.COPPER_COIN.get()), new ItemStack(AoAItems.DISTORTION_RUNE.get())));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.COPPER_COIN.get()), new ItemStack(AoAItems.LIFE_RUNE.get())));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.COPPER_COIN.get()), new ItemStack(AoAItems.KINETIC_RUNE.get())));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.SILVER_COIN.get()), new ItemStack(AoABlocks.RUNE_SHRINE.get())));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.LUNAVER_COIN.get(), 2), EnchantedBookItem.createForEnchantment(new EnchantmentData(Enchantments.MENDING, 1))));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.SILVER_COIN.get(), 7), new ItemStack(AoAWeapons.SUPER_CANNON.get(), 1), new ItemStack(AoAWeapons.ULTRA_CANNON.get())));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.GOLD_COIN.get(), 10), new ItemStack(AoATools.SOULSTONE_AXE.get())));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.GOLD_COIN.get(), 11), new ItemStack(AoATools.SOULSTONE_SHOVEL.get())));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.GOLD_COIN.get(), 12), new ItemStack(AoATools.SOULSTONE_PICKAXE.get())));
	}
}
