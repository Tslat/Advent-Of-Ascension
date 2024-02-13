package net.tslat.aoa3.content.entity.npc.trader;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.tslat.aoa3.common.registration.item.AoAArmour;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.common.registration.item.AoAWeapons;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.content.entity.base.AoATrader;
import net.tslat.aoa3.util.WorldUtil;
import org.jetbrains.annotations.Nullable;


public class ExplosivesExpertEntity extends AoATrader {
	private static final Int2ObjectMap<VillagerTrades.ItemListing[]> TRADES = new TradeListBuilder()
			.trades(1,
					BuildableTrade.forItem(AoAWeapons.GRENADE).itemCost(AoAItems.COPPER_COIN, 5).xp(5).stock(12),
					BuildableTrade.forItem(AoAItems.DISCHARGE_CAPSULE).itemCost(AoAItems.COPPER_COIN, 2))
			.trades(2,
					BuildableTrade.forItem(Blocks.TNT).itemCost(AoAItems.COPPER_COIN, 13).xp(10).stock(12),
					BuildableTrade.forItem(AoAItems.COPPER_COIN, 10).itemCost(Blocks.TNT).xp(10))
			.trades(3,
					BuildableTrade.forItem(AoAArmour.OMNI_ARMOUR.helmet).itemCost(AoAItems.GEMENYTE, 3).itemCost(AoAItems.UNSTABLE_GUNPOWDER, 2).xp(50).stock(5),
					BuildableTrade.forItem(AoAArmour.OMNI_ARMOUR.chestplate).itemCost(AoAItems.GEMENYTE, 5).itemCost(AoAItems.UNSTABLE_GUNPOWDER, 3).xp(50).stock(5),
					BuildableTrade.forItem(AoAArmour.OMNI_ARMOUR.leggings).itemCost(AoAItems.GEMENYTE, 4).itemCost(AoAItems.UNSTABLE_GUNPOWDER, 2).xp(50).stock(5),
					BuildableTrade.forItem(AoAArmour.OMNI_ARMOUR.boots).itemCost(AoAItems.GEMENYTE, 3).itemCost(AoAItems.UNSTABLE_GUNPOWDER, 2).xp(50).stock(5))
			.trades(4,
					BuildableTrade.forItem(AoAItems.LUNAVER_COIN, 50).stackCost(ExplosivesExpertEntity::getExplosiveExpertFireworks).xp(1000).locked()).build();

	public ExplosivesExpertEntity(EntityType<? extends AoATrader> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	public InteractionResult mobInteract(Player player, InteractionHand hand) {
		ItemStack heldStack = player.getItemInHand(hand);

		if (heldStack.getItem() == AoAItems.BLANK_REALMSTONE.get() && heldStack.getItem().interactLivingEntity(heldStack, player, this, hand).consumesAction())
			return InteractionResult.SUCCESS;

		return super.mobInteract(player, hand);
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return !WorldUtil.isWorld(level(), AoADimensions.CREEPONIA);
	}

	@Nullable
	@Override
	public Int2ObjectMap<VillagerTrades.ItemListing[]> getTradesMap() {
		return TRADES;
	}

	public static ItemStack getExplosiveExpertFireworks() {
		ItemStack stack = new ItemStack(Items.FIREWORK_ROCKET);
		CompoundTag tag = new CompoundTag();
		CompoundTag fireworksTag = new CompoundTag();

		fireworksTag.putByte("Flight", (byte)42);
		tag.put("Fireworks", fireworksTag);
		tag.putBoolean("AoA", true);
		stack.setTag(tag);

		return stack;
	}
}
