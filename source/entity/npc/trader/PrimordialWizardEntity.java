package net.tslat.aoa3.entity.npc.trader;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoAWeapons;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.entity.npc.AoATraderRecipe;
import net.tslat.aoa3.util.WorldUtil;

public class PrimordialWizardEntity extends AoATrader {
	public PrimordialWizardEntity(EntityType<? extends CreatureEntity> entityType, World world) {
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
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.PRIMORDIAL_SKULL.get(), 1), new ItemStack(AoAItems.COPPER_COIN.get(), 15)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.COPPER_COIN.get(), 2), new ItemStack(AoAWeapons.HELLFIRE.get(), 2)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.COPPER_COIN.get(), 3), new ItemStack(AoAWeapons.VULKRAM.get(), 3)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.GOLD_COIN.get(), 2), new ItemStack(AoAWeapons.PRIMORDIAL_STAFF.get(), 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.LUNAVER_COIN.get(), 1), new ItemStack(Items.DRAGON_BREATH, 1)));
	}
}
