package net.tslat.aoa3.entity.npc.skillmaster;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAArmour;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.entity.npc.AoATraderRecipe;

public class AnimaMasterEntity extends AoASkillMaster {
	public AnimaMasterEntity(EntityType<? extends CreatureEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected void getTradesList(final NonNullList<AoATraderRecipe> newTradesList) {
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.LUNAVER_COIN.get()), ItemStack.EMPTY, new ItemStack(AoAArmour.ANIMA_ARMOUR.helmet.get()), 0, 9999));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.LUNAVER_COIN.get()), ItemStack.EMPTY, new ItemStack(AoAArmour.ANIMA_ARMOUR.chestplate.get()), 0, 9999));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.LUNAVER_COIN.get()), ItemStack.EMPTY, new ItemStack(AoAArmour.ANIMA_ARMOUR.leggings.get()), 0, 9999));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.LUNAVER_COIN.get()), ItemStack.EMPTY, new ItemStack(AoAArmour.ANIMA_ARMOUR.boots.get()), 0, 9999));
	}
}
