package net.tslat.aoa3.content.entity.npc.trader;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.common.registration.item.AoAWeapons;
import net.tslat.aoa3.content.entity.base.AoATrader;
import net.tslat.aoa3.content.item.misc.ReservedItem;

public class AssassinEntity extends AoATrader {
	private static final Int2ObjectMap<VillagerTrades.ItemListing[]> TRADES = new TradeListBuilder()
			.trades(1,
					BuildableTrade.trade(AoAWeapons.GOO_BALL, 3).cost(AoAItems.COPPER_COIN, 2),
					BuildableTrade.trade(AoAWeapons.SLICE_STAR, 2).cost(AoAItems.COPPER_COIN, 2))
			.trades(2,
					BuildableTrade.trade(AoAWeapons.CHAKRAM, 2).cost(AoAItems.COPPER_COIN, 3).xp(3),
					BuildableTrade.trade(AoAWeapons.VULKRAM, 2).cost(AoAItems.COPPER_COIN, 3).xp(3))
			.trades(3,
					BuildableTrade.trade(AoAItems.METAL_SLUG, 2).cost(AoAItems.COPPER_COIN, 2),
					BuildableTrade.trade(AoAItems.LIMONITE_BULLET, 5).cost(AoAItems.COPPER_COIN, 2))
			.trades(4,
					BuildableTrade.trade(AoAWeapons.HELLFIRE).cost(AoAItems.COPPER_COIN, 2)).build();

	public AssassinEntity(EntityType<? extends AoATrader> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected boolean isOverworldNPC() {
		return true;
	}

	@Override
	public InteractionResult mobInteract(Player player, InteractionHand hand) {
		ItemStack heldStack = player.getItemInHand(hand);

		if (heldStack.getItem() == AoAItems.ROCK_BONES.get()) {
			if (!level().isClientSide)
				player.setItemInHand(hand, ((ReservedItem)AoAItems.MILLENNIUM_UPGRADER.get()).newValidStack());

			return InteractionResult.SUCCESS;
		}

		return super.mobInteract(player, hand);
	}

	@Override
	public Int2ObjectMap<VillagerTrades.ItemListing[]> getTradesMap() {
		return TRADES;
	}
}
