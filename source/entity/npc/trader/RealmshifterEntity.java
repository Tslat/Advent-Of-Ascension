package net.tslat.aoa3.entity.npc.trader;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.entity.npc.AoATraderRecipe;

public class RealmshifterEntity extends AoATrader {
	public RealmshifterEntity(EntityType<? extends CreatureEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected boolean isOverworldNPC() {
		return true;
	}

	@Override
	protected void getTradesList(final NonNullList<AoATraderRecipe> newTradesList) {
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.COPPER_COIN.get(), 20), new ItemStack(AoABlocks.CARVED_RUNE_OF_TRAVEL.get(), 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.COPPER_COIN.get(), 20), new ItemStack(AoABlocks.CARVED_RUNE_OF_SPACE.get(), 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.COPPER_COIN.get(), 20), new ItemStack(AoABlocks.CARVED_RUNE_OF_REALITY.get(), 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.COPPER_COIN.get(), 20), new ItemStack(AoABlocks.CARVED_RUNE_OF_DIRECTION.get(), 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.SILVER_COIN.get(), 1), new ItemStack(AoABlocks.ANCIENT_ROCK.get(), 64)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.COPPER_COIN.get(), 10), new ItemStack(AoABlocks.ANCIENT_ROCK.get(), 30)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.COPPER_COIN.get(), 1), new ItemStack(AoABlocks.ANCIENT_ROCK.get(), 3)));
	}
}
