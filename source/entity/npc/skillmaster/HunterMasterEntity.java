package net.tslat.aoa3.entity.npc.skillmaster;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAArmour;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.entity.npc.AoATraderRecipe;
import net.tslat.aoa3.util.WorldUtil;

public class HunterMasterEntity extends AoASkillMaster {
	public HunterMasterEntity(EntityType<? extends CreatureEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return !WorldUtil.isWorld(level, AoADimensions.LELYETIA.key);
	}

	@Override
	protected void getTradesList(final NonNullList<AoATraderRecipe> newTradesList) {
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.LUNAVER_COIN.get()), new ItemStack(AoAArmour.HUNTER_ARMOUR.helmet.get())));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.LUNAVER_COIN.get()), new ItemStack(AoAArmour.HUNTER_ARMOUR.chestplate.get())));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.LUNAVER_COIN.get()), new ItemStack(AoAArmour.HUNTER_ARMOUR.leggings.get())));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.LUNAVER_COIN.get()), new ItemStack(AoAArmour.HUNTER_ARMOUR.boots.get())));
	}
}
