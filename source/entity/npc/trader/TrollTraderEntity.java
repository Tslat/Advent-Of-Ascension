package net.tslat.aoa3.entity.npc.trader;

import net.minecraft.block.Blocks;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoAWeapons;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.entity.npc.AoATraderRecipe;

public class TrollTraderEntity extends AoATrader {
	public TrollTraderEntity(EntityType<? extends CreatureEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 1.625f;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 30;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.329;
	}

	@Override
	protected boolean isFixedTradesList() {
		return true;
	}

	@Override
	protected boolean isOverworldNPC() {
		return true;
	}

	@Override
	protected void getTradesList(final NonNullList<AoATraderRecipe> newTradesList) {
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.AMPHIBIYTE_LUNG.get(), 1), ItemStack.EMPTY, new ItemStack(AoAItems.COPPER_COIN.get(), 6), 0, 9999));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.AMPHIBIYTE_LUNG.get(), 30), ItemStack.EMPTY, new ItemStack(AoAWeapons.CORALSTORM_SWORD.get()), 0, 9999));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.COPPER_COIN.get(), 10), ItemStack.EMPTY, new ItemStack(Blocks.SAND, 64), 0, 9999));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.SILVER_COIN.get(), 1), ItemStack.EMPTY, new ItemStack(Items.PRISMARINE_SHARD, 4), 0, 9999));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.SILVER_COIN.get(), 1), ItemStack.EMPTY, new ItemStack(Items.PRISMARINE_CRYSTALS, 2), 0, 9999));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.COPPER_COIN.get(), 3), ItemStack.EMPTY, new ItemStack(Items.INK_SAC), 0, 9999));
	}
}
