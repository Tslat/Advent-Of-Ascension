package net.tslat.aoa3.entity.npc.trader;

import net.minecraft.block.Blocks;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAArmour;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoAWeapons;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.entity.npc.AoATraderRecipe;

public class ExplosivesExpertEntity extends AoATrader {
	public ExplosivesExpertEntity(EntityType<? extends CreatureEntity> entityType, World world) {
		super(entityType, world);
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
	protected boolean processInteract(PlayerEntity player, Hand hand) {
		ItemStack heldStack = player.getHeldItem(hand);

		if (heldStack.getItem() == AoAItems.BLANK_REALMSTONE.get() && heldStack.getItem().itemInteractionForEntity(heldStack, player, this, hand))
			return true;

		return super.processInteract(player, hand);
	}

	@Override
	public boolean canDespawn(double distanceToClosestPlayer) {
		return world.getDimension().getType() != AoADimensions.CREEPONIA.type();
	}

	@Override
	protected void getTradesList(final NonNullList<AoATraderRecipe> newTradesList) {
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.COPPER_COIN.get(), 5), new ItemStack(AoAWeapons.GRENADE.get())));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.COPPER_COIN.get(), 2), new ItemStack(AoAItems.DISCHARGE_CAPSULE.get(), 2)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.COPPER_COIN.get(), 13), new ItemStack(Blocks.TNT)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(Blocks.TNT), new ItemStack(AoAItems.COPPER_COIN.get(), 10)));
		newTradesList.add(new AoATraderRecipe(getExplosiveExpertFireworks(), ItemStack.EMPTY, new ItemStack(AoAItems.LUNAVER_COIN.get(), 50), 1, 1));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.GEMENYTE.get(), 3), new ItemStack(AoAItems.UNSTABLE_GUNPOWDER.get(), 2), new ItemStack(AoAArmour.OMNI_ARMOUR.helmet.get())));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.GEMENYTE.get(), 5), new ItemStack(AoAItems.UNSTABLE_GUNPOWDER.get(), 3), new ItemStack(AoAArmour.OMNI_ARMOUR.chestplate.get())));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.GEMENYTE.get(), 4), new ItemStack(AoAItems.UNSTABLE_GUNPOWDER.get(), 2), new ItemStack(AoAArmour.OMNI_ARMOUR.leggings.get())));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.GEMENYTE.get(), 3), new ItemStack(AoAItems.UNSTABLE_GUNPOWDER.get(), 2), new ItemStack(AoAArmour.OMNI_ARMOUR.boots.get())));
	}

	public static ItemStack getExplosiveExpertFireworks() {
		ItemStack stack = new ItemStack(Items.FIREWORK_ROCKET);
		CompoundNBT tag = new CompoundNBT();
		CompoundNBT fireworksTag = new CompoundNBT();

		fireworksTag.putByte("Flight", (byte)42);
		tag.put("Fireworks", fireworksTag);
		tag.putBoolean("AoA", true);
		stack.setTag(tag);

		return stack;
	}
}
