package net.tslat.aoa3.content.entity.npc.trader;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoAWeapons;
import net.tslat.aoa3.content.entity.base.AoATrader;
import net.tslat.aoa3.content.item.misc.ReservedItem;

public class AssassinEntity extends AoATrader {
	private static final Int2ObjectMap<VillagerTrades.ITrade[]> TRADES = new TradeListBuilder()
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

	public AssassinEntity(EntityType<? extends AoATrader> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected boolean isOverworldNPC() {
		return true;
	}

	@Override
	public ActionResultType mobInteract(PlayerEntity player, Hand hand) {
		ItemStack heldStack = player.getItemInHand(hand);

		if (heldStack.getItem() == AoAItems.ROCK_BONES.get()) {
			if (!level.isClientSide)
				player.setItemInHand(hand, ((ReservedItem)AoAItems.MILLENNIUM_UPGRADER.get()).newValidStack());

			return ActionResultType.SUCCESS;
		}

		return super.mobInteract(player, hand);
	}

	@Override
	public Int2ObjectMap<VillagerTrades.ITrade[]> getTradesMap() {
		return TRADES;
	}
}
