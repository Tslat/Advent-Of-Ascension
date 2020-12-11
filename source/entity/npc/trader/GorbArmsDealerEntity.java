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

public class GorbArmsDealerEntity extends AoATrader {
	public GorbArmsDealerEntity(EntityType<? extends CreatureEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 1.5f;
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
	public boolean canDespawn(double distanceToClosestPlayer) {
		return world.getDimension().getType() != AoADimensions.MYSTERIUM.type();
	}

	@Override
	protected void getTradesList(final NonNullList<AoATraderRecipe> newTradesList) {
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.SILVER_COIN.get()), new ItemStack(AoAWeapons.GRENADE.get(), 5)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAWeapons.RUNIC_BOMB.get()), new ItemStack(AoAItems.COPPER_COIN.get(), 7)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(Items.ARROW), new ItemStack(AoAItems.COPPER_COIN.get(), 2)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.GOLD_COIN.get(), 3), new ItemStack(AoAWeapons.LASER_BLASTER.get())));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.GOLD_COIN.get(), 10), new ItemStack(AoAItems.WEAPON_PARTS.get())));
	}
}
