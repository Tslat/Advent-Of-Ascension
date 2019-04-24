package net.tslat.aoa3.client.gui;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerMerchant;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.tslat.aoa3.client.gui.mainwindow.AdventMainGui;
import net.tslat.aoa3.client.gui.merchants.TraderGui;
import net.tslat.aoa3.client.gui.misc.WornBookGui;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.entity.npcs.*;
import net.tslat.aoa3.entity.npcs.banker.*;
import net.tslat.aoa3.entity.npcs.lottoman.EntityLottoman;
import net.tslat.aoa3.entity.npcs.skillmaster.EntitySkillMaster;
import net.tslat.aoa3.item.misc.WornBook;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

public class GuiManager implements IGuiHandler {
	@Override
	public Object getServerGuiElement(int guiId, EntityPlayer player, World world, int x, int y, int z) {
		Enums.ModGuis gui = Enums.ModGuis.getById(guiId);

		if (gui != null) {
			switch (gui) {
				case TRADER_ASSASSIN:
					return getTraderContainer(player, EntityAssassin.class, x);
				case TRADER_CORRUPTED_TRAVELLER:
					return getTraderContainer(player, EntityCorruptedTraveller.class, x);
				case TRADER_CREEP_BANKER:
					return getTraderContainer(player, EntityCreepBanker.class, x);
				case TRADER_CRYSTAL_TRADER:
					return getTraderContainer(player, EntityCrystalTrader.class, x);
				case TRADER_DUNGEON_KEEPER:
					return getTraderContainer(player, EntityDungeonKeeper.class, x);
				case TRADER_EXPLOSIVES_EXPERT:
					return getTraderContainer(player, EntityExplosivesExpert.class, x);
				case TRADER_GORB_ARMS_DEALER:
					return getTraderContainer(player, EntityGorbArmsDealer.class, x);
				case TRADER_GORB_ENGINEER:
					return getTraderContainer(player, EntityGorbEngineer.class, x);
				case TRADER_LELYETIAN_BANKER:
					return getTraderContainer(player, EntityLelyetianBanker.class, x);
				case TRADER_LELYETIAN_TRADER:
					return getTraderContainer(player, EntityLelyetianTrader.class, x);
				case TRADER_LOTTOMAN:
					return getTraderContainer(player, EntityLottoman.class, x);
				case TRADER_METALLOID:
					return getTraderContainer(player, EntityMetalloid.class, x);
				case TRADER_NATURALIST:
					return getTraderContainer(player, EntityNaturalist.class, x);
				case TRADER_PORTAL_MASTER:
					return getTraderContainer(player, EntityPortalMaster.class, x);
				case TRADER_PRIMORDIAL_BANKER:
					return getTraderContainer(player, EntityPrimordialBanker.class, x);
				case TRADER_PRIMORDIAL_MERCHANT:
					return getTraderContainer(player, EntityPrimordialMerchant.class, x);
				case TRADER_PRIMORDIAL_SPELLBINDER:
					return getTraderContainer(player, EntityPrimordialSpellbinder.class, x);
				case TRADER_PRIMORDIAL_WIZARD:
					return getTraderContainer(player, EntityPrimordialWizard.class, x);
				case TRADER_PROFESSOR:
					return getTraderContainer(player, EntityProfessor.class, x);
				case TRADER_REALMSHIFTER:
					return getTraderContainer(player, EntityRealmshifter.class, x);
				case TRADER_SHYRE_ARCHER:
					return getTraderContainer(player, EntityShyreArcher.class, x);
				case TRADER_SHYRE_BANKER:
					return getTraderContainer(player, EntityShyreBanker.class, x);
				case TRADER_SKILL_MASTER:
					return getTraderContainer(player, EntitySkillMaster.class, x);
				case TRADER_SOUL_AGENT:
					return getTraderContainer(player, EntitySoulAgent.class, x);
				case TRADER_STORE_KEEPER:
					return getTraderContainer(player, EntityStoreKeeper.class, x);
				case TRADER_TOKEN_COLLECTOR:
					return getTraderContainer(player, EntityTokenCollector.class, x);
				case TRADER_TOY_MERCHANT:
					return getTraderContainer(player, EntityToyMerchant.class, x);
				case TRADER_TROLL_TRADER:
					return getTraderContainer(player, EntityTrollTrader.class, x);
				case TRADER_ZAL_BANKER:
					return getTraderContainer(player, EntityZalBanker.class, x);
				case TRADER_ZAL_GROCER:
					return getTraderContainer(player, EntityZalGrocer.class, x);
				case TRADER_ZAL_HERBALIST:
					return getTraderContainer(player, EntityZalHerbalist.class, x);
				case TRADER_ZAL_SPELLBINDER:
					return getTraderContainer(player, EntityZalSpellbinder.class, x);
				case TRADER_ZAL_VENDOR:
					return getTraderContainer(player, EntityZalVendor.class, x);
				default:
					return null;
			}

		}

		return null;
	}

	@Override
	public Object getClientGuiElement(int guiId, EntityPlayer player, World world, int x, int y, int z) {
		Enums.ModGuis gui = Enums.ModGuis.getById(guiId);

		if (gui != null) {
			switch (gui) {
				case WORN_BOOK:
					return new WornBookGui(player, WornBook.getBook(), false);
				case TRADER_ASSASSIN:
					return getTraderGui(player, EntityAssassin.class, x, "assassin_trade");
				case TRADER_CORRUPTED_TRAVELLER:
					return getTraderGui(player, EntityCorruptedTraveller.class, x, "corrupted_traveller_trade");
				case TRADER_CREEP_BANKER:
					return getTraderGui(player, EntityCreepBanker.class, x, "creep_banker_trade");
				case TRADER_CRYSTAL_TRADER:
					return getTraderGui(player, EntityCrystalTrader.class, x, "crystal_trader_trade");
				case TRADER_DUNGEON_KEEPER:
					return getTraderGui(player, EntityDungeonKeeper.class, x, "dungeon_keeper_trade");
				case TRADER_EXPLOSIVES_EXPERT:
					return getTraderGui(player, EntityExplosivesExpert.class, x, "explosives_expert_trade");
				case TRADER_GORB_ARMS_DEALER:
					return getTraderGui(player, EntityGorbArmsDealer.class, x, "gorb_arms_dealer_trade");
				case TRADER_GORB_ENGINEER:
					return getTraderGui(player, EntityGorbEngineer.class, x, "gorb_engineer_trade");
				case TRADER_LELYETIAN_BANKER:
					return getTraderGui(player, EntityLelyetianBanker.class, x, "lelyetian_banker_trade");
				case TRADER_LELYETIAN_TRADER:
					return getTraderGui(player, EntityLelyetianTrader.class, x, "lelyetian_trader_trade");
				case TRADER_LOTTOMAN:
					return getTraderGui(player, EntityLottoman.class, x, "lottoman_trade");
				case TRADER_METALLOID:
					return getTraderGui(player, EntityMetalloid.class, x, "metalloid_trade");
				case TRADER_NATURALIST:
					return getTraderGui(player, EntityNaturalist.class, x, "naturalist_trade");
				case TRADER_PORTAL_MASTER:
					return getTraderGui(player, EntityPortalMaster.class, x, "portal_master_trade");
				case TRADER_PRIMORDIAL_BANKER:
					return getTraderGui(player, EntityPrimordialBanker.class, x, "primordial_banker_trade");
				case TRADER_PRIMORDIAL_MERCHANT:
					return getTraderGui(player, EntityPrimordialMerchant.class, x, "primordial_merchant_trade");
				case TRADER_PRIMORDIAL_SPELLBINDER:
					return getTraderGui(player, EntityPrimordialSpellbinder.class, x, "primordial_spellbinder_trade");
				case TRADER_PRIMORDIAL_WIZARD:
					return getTraderGui(player, EntityPrimordialWizard.class, x, "primordial_wizard_trade");
				case TRADER_PROFESSOR:
					return getTraderGui(player, EntityProfessor.class, x, "professor_trade");
				case TRADER_REALMSHIFTER:
					return getTraderGui(player, EntityRealmshifter.class, x, "realmshifter_trade");
				case TRADER_SHYRE_ARCHER:
					return getTraderGui(player, EntityShyreArcher.class, x, "shyre_archer_trade");
				case TRADER_SHYRE_BANKER:
					return getTraderGui(player, EntityShyreBanker.class, x, "shyre_banker_trade");
				case TRADER_SKILL_MASTER:
					return getTraderGui(player, EntitySkillMaster.class, x, "skill_master_trade");
				case TRADER_SOUL_AGENT:
					return getTraderGui(player, EntitySoulAgent.class, x, "soul_agent_trade");
				case TRADER_STORE_KEEPER:
					return getTraderGui(player, EntityStoreKeeper.class, x, "store_keeper_trade");
				case TRADER_TOKEN_COLLECTOR:
					return getTraderGui(player, EntityTokenCollector.class, x, "token_collector_trade");
				case TRADER_TOY_MERCHANT:
					return getTraderGui(player, EntityToyMerchant.class, x, "toy_merchant_trade");
				case TRADER_TROLL_TRADER:
					return getTraderGui(player, EntityTrollTrader.class, x, "troll_trader_trade");
				case TRADER_ZAL_BANKER:
					return getTraderGui(player, EntityZalBanker.class, x, "zal_banker_trade");
				case TRADER_ZAL_GROCER:
					return getTraderGui(player, EntityZalGrocer.class, x, "zal_grocer_trade");
				case TRADER_ZAL_HERBALIST:
					return getTraderGui(player, EntityZalHerbalist.class, x, "zal_herbalist_trade");
				case TRADER_ZAL_SPELLBINDER:
					return getTraderGui(player, EntityZalSpellbinder.class, x, "zal_spellbinder_trade");
				case TRADER_ZAL_VENDOR:
					return getTraderGui(player, EntityZalVendor.class, x, "zal_vendor_trade");
				case ADVENT_MAIN_WINDOW:
					return new AdventMainGui(player);
				default:
					return null;
			}
		}

		return null;
	}

	private ContainerMerchant getTraderContainer(EntityPlayer pl, Class<? extends AoATrader> traderClass, int entityId) {
		AoATrader trader = getNearbyEntityGuiTarget(pl, traderClass, entityId);

		if (trader != null)
			return new ContainerMerchant(pl.inventory, trader, pl.world);

		return null;
	}

	private Object getTraderGui(EntityPlayer pl, Class<? extends AoATrader> traderClass, int entityId, String guiName) {
		AoATrader trader = getNearbyEntityGuiTarget(pl, traderClass, entityId);

		if (trader != null)
			return new TraderGui(new ContainerMerchant(pl.inventory, trader, pl.world), trader, guiName);

		return null;
	}

	@Nullable
	private <T extends Entity> T getNearbyEntityGuiTarget(EntityPlayer player, Class<? extends T> entityClass, int entityId) {
		for (T e : player.world.getEntitiesWithinAABB(entityClass, player.getEntityBoundingBox().grow(10))) {
			if (e.getEntityId() == entityId)
				return e;
		}

		return null;
	}
}
