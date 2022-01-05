package net.tslat.aoa3.object.entity.npc.trader;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.*;
import net.tslat.aoa3.object.entity.base.AoATrader;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;

public class ExplosivesExpertEntity extends AoATrader {
	private static final Int2ObjectMap<VillagerTrades.ITrade[]> TRADES = new TradeListBuilder()
			.trades(1,
					BuildableTrade.trade(AoAWeapons.GRENADE).cost(AoAItems.COPPER_COIN, 5).xp(5).stock(12),
					BuildableTrade.trade(AoAItems.DISCHARGE_CAPSULE).cost(AoAItems.COPPER_COIN, 2))
			.trades(2,
					BuildableTrade.trade(Blocks.TNT).cost(AoAItems.COPPER_COIN, 13).xp(10).stock(12),
					BuildableTrade.trade(AoAItems.COPPER_COIN, 10).cost(Blocks.TNT).xp(10))
			.trades(3,
					BuildableTrade.trade(AoAArmour.OMNI_ARMOUR.helmet).cost(AoAItems.GEMENYTE, 3).cost(AoAItems.UNSTABLE_GUNPOWDER, 2).xp(50).stock(5),
					BuildableTrade.trade(AoAArmour.OMNI_ARMOUR.chestplate).cost(AoAItems.GEMENYTE, 5).cost(AoAItems.UNSTABLE_GUNPOWDER, 3).xp(50).stock(5),
					BuildableTrade.trade(AoAArmour.OMNI_ARMOUR.leggings).cost(AoAItems.GEMENYTE, 4).cost(AoAItems.UNSTABLE_GUNPOWDER, 2).xp(50).stock(5),
					BuildableTrade.trade(AoAArmour.OMNI_ARMOUR.boots).cost(AoAItems.GEMENYTE, 3).cost(AoAItems.UNSTABLE_GUNPOWDER, 2).xp(50).stock(5))
			.trades(4,
					BuildableTrade.trade(AoAItems.LUNAVER_COIN, 50).cost(getExplosiveExpertFireworks()).xp(1000).locked()).build();

	public ExplosivesExpertEntity(EntityType<? extends AoATrader> entityType, World world) {
		super(entityType, world);
	}

	@Override
	public ActionResultType mobInteract(PlayerEntity player, Hand hand) {
		ItemStack heldStack = player.getItemInHand(hand);

		if (heldStack.getItem() == AoAItems.BLANK_REALMSTONE.get() && heldStack.getItem().interactLivingEntity(heldStack, player, this, hand).consumesAction())
			return ActionResultType.SUCCESS;

		return super.mobInteract(player, hand);
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return !WorldUtil.isWorld(level, AoADimensions.CREEPONIA.key);
	}

	@Nullable
	@Override
	public Int2ObjectMap<VillagerTrades.ITrade[]> getTradesMap() {
		return TRADES;
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
