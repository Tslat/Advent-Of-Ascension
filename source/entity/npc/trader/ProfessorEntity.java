package net.tslat.aoa3.entity.npc.trader;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoAWeapons;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.entity.npc.AoATraderRecipe;

public class ProfessorEntity extends AoATrader {
	public ProfessorEntity(EntityType<? extends CreatureEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 1.7625f;
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
	public boolean canDespawn(double distanceToClosestPlayer) {
		return world.getDimension().getType() != AoADimensions.IROMINE.type();
	}

	@Override
	protected void getTradesList(final NonNullList<AoATraderRecipe> newTradesList) {
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.GOLD_COIN.get(), 1), new ItemStack(AoAItems.LYON_INGOT.get(), 5), new ItemStack(AoAItems.MECHA_GEAR.get(), 1), 0, 9999));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.SILVER_COIN.get(), 1), new ItemStack(Items.IRON_NUGGET, 5), new ItemStack(AoAItems.DISCHARGE_CAPSULE.get(), 8), 0, 9999));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.SILVER_COIN.get(), 5), new ItemStack(AoAItems.MAGNET_SHARD.get(), 2), new ItemStack(AoAItems.SILVRO_COIN.get(), 1), 0, 9999));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.COPPER_COIN.get(), 15), ItemStack.EMPTY, new ItemStack(AoAItems.SCRAP_METAL.get(), 1), 0, 9999));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoABlocks.IRO_CRATE.get(), 1), ItemStack.EMPTY, new ItemStack(AoAItems.GOLD_SPRING.get(), 1), 0, 9999));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.SILVER_COIN.get(), 5), new ItemStack(AoAWeapons.MINI_CANNON.get(), 1), new ItemStack(AoAWeapons.SUPER_CANNON.get(), 1), 0, 9999));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.SILVER_COIN.get(), 3), new ItemStack(AoAItems.LYON_INGOT.get(), 7), new ItemStack(AoAWeapons.DEMOLISHER.get(), 1), 0, 9999));
	}
}
