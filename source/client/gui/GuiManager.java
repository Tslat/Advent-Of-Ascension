package net.tslat.aoa3.client.gui;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerMerchant;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.gui.blocks.BasicBlockGui;
import net.tslat.aoa3.client.gui.blocks.GuiDivineStation;
import net.tslat.aoa3.client.gui.blocks.GuiFrameBench;
import net.tslat.aoa3.client.gui.blocks.GuiInfusion;
import net.tslat.aoa3.client.gui.mainwindow.AdventMainGui;
import net.tslat.aoa3.client.gui.merchants.BankerGui;
import net.tslat.aoa3.client.gui.merchants.CorruptedTravellerGui;
import net.tslat.aoa3.client.gui.merchants.TraderGui;
import net.tslat.aoa3.client.gui.misc.WornBookGui;
import net.tslat.aoa3.client.gui.realmstonegui.GuiRealmstoneChallengeMenu;
import net.tslat.aoa3.common.containers.*;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.entity.npcs.banker.*;
import net.tslat.aoa3.entity.npcs.lottoman.EntityLottoman;
import net.tslat.aoa3.entity.npcs.skillmaster.EntitySkillMaster;
import net.tslat.aoa3.entity.npcs.trader.*;
import net.tslat.aoa3.item.misc.WornBook;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.StringUtil;

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
					return new ContainerCorruptedTraveller(player, getNearbyEntityGuiTarget(player, EntityCorruptedTraveller.class, x));
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
				case TRADER_UNDEAD_HERALD:
					return getTraderContainer(player, EntityUndeadHerald.class, x);
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
				case INFUSION_TABLE:
					return new ContainerInfusionTable(player, player.world, new BlockPos(x, y, z));
				case FRAME_BENCH:
					return new ContainerFrameBench(player, player.world, new BlockPos(x, y, z));
				case MENDING_TABLE:
					return new ContainerMendingTable(player, player.world, new BlockPos(x, y, z));
				case DIVINE_STATION:
					return new ContainerDivineStation(player, player.world, new BlockPos(x, y, z));
				case WHITEWASHING_TABLE:
					return new ContainerWhitewashingTable(player, player.world, new BlockPos(x, y, z));
				case BANKER:
					return new ContainerBankerTrade(player, getNearbyEntityGuiTarget(player, AoATrader.class, x));
				default:
					return null;
			}

		}

		return null;
	}

	@SideOnly(Side.CLIENT)
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
					EntityCorruptedTraveller traveller = getNearbyEntityGuiTarget(player, EntityCorruptedTraveller.class, x);

					return new CorruptedTravellerGui(new ContainerCorruptedTraveller(player, traveller), traveller);
				case TRADER_CREEP_BANKER:
					return getTraderGui(player, EntityCreepBanker.class, x, "creep_banker_trade");
				case TRADER_CRYSTAL_TRADER:
					return getTraderGui(player, EntityCrystalTrader.class, x, "crystal_trader_trade");
				case TRADER_DUNGEON_KEEPER:
					return getTraderGui(player, EntityDungeonKeeper.class, x, "dungeon_keeper_trade");
				case TRADER_EXPLOSIVES_EXPERT:
					return getTraderGui(player, EntityExplosivesExpert.class, x, "explosives_expert_trade");
				case TRADER_GORB_ARMS_DEALER:
					return getTraderGui(player, EntityGorbArmsDealer.class, x, "gorb_trade");
				case TRADER_GORB_ENGINEER:
					return getTraderGui(player, EntityGorbEngineer.class, x, "gorb_trade");
				case TRADER_LELYETIAN_BANKER:
					return getTraderGui(player, EntityLelyetianBanker.class, x, "lelyetian_trade");
				case TRADER_LELYETIAN_TRADER:
					return getTraderGui(player, EntityLelyetianTrader.class, x, "lelyetian_trade");
				case TRADER_LOTTOMAN:
					return getTraderGui(player, EntityLottoman.class, x, "lottoman_trade");
				case TRADER_METALLOID:
					return getTraderGui(player, EntityMetalloid.class, x, "metalloid_trade");
				case TRADER_NATURALIST:
					return getTraderGui(player, EntityNaturalist.class, x, "naturalist_trade");
				case TRADER_PRIMORDIAL_BANKER:
					return getTraderGui(player, EntityPrimordialBanker.class, x, "primordial_trade");
				case TRADER_PRIMORDIAL_MERCHANT:
					return getTraderGui(player, EntityPrimordialMerchant.class, x, "primordial_trade");
				case TRADER_PRIMORDIAL_SPELLBINDER:
					return getTraderGui(player, EntityPrimordialSpellbinder.class, x, "primordial_trade");
				case TRADER_PRIMORDIAL_WIZARD:
					return getTraderGui(player, EntityPrimordialWizard.class, x, "primordial_trade");
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
				case TRADER_UNDEAD_HERALD:
					return getTraderGui(player, EntityUndeadHerald.class, x, "undead_herald_trade");
				case TRADER_STORE_KEEPER:
					return getTraderGui(player, EntityStoreKeeper.class, x, "store_keeper_trade");
				case TRADER_TOKEN_COLLECTOR:
					return getTraderGui(player, EntityTokenCollector.class, x, "token_collector_trade");
				case TRADER_TOY_MERCHANT:
					return getTraderGui(player, EntityToyMerchant.class, x, "toy_merchant_trade");
				case TRADER_TROLL_TRADER:
					return getTraderGui(player, EntityTrollTrader.class, x, "troll_trader_trade");
				case TRADER_ZAL_BANKER:
					return getTraderGui(player, EntityZalBanker.class, x, "zal_trade");
				case TRADER_ZAL_GROCER:
					return getTraderGui(player, EntityZalGrocer.class, x, "zal_trade");
				case TRADER_ZAL_HERBALIST:
					return getTraderGui(player, EntityZalHerbalist.class, x, "zal_trade");
				case TRADER_ZAL_SPELLBINDER:
					return getTraderGui(player, EntityZalSpellbinder.class, x, "zal_trade");
				case TRADER_ZAL_VENDOR:
					return getTraderGui(player, EntityZalVendor.class, x, "zal_trade");
				case ADVENT_MAIN_WINDOW:
					return new AdventMainGui(player);
				case INFUSION_TABLE:
					return new GuiInfusion(player);
				case FRAME_BENCH:
					return new GuiFrameBench(player);
				case MENDING_TABLE:
					return new BasicBlockGui(player, StringUtil.getLocaleString("tile.MendingTable.name"), new ContainerMendingTable(player, world, BlockPos.ORIGIN));
				case WHITEWASHING_TABLE:
					return new BasicBlockGui(player, StringUtil.getLocaleString("tile.WhitewashingTable.name"), new ContainerWhitewashingTable(player, world, BlockPos.ORIGIN));
				case DIVINE_STATION:
					return new GuiDivineStation(player, StringUtil.getLocaleString("tile.DivineStation.name"), new ContainerDivineStation(player, world, BlockPos.ORIGIN));
				case REALMSTONE_MENU:
					return new GuiRealmstoneChallengeMenu();
				case BANKER:
					AoATrader banker = getNearbyEntityGuiTarget(player, AoATrader.class, x);

					if (banker == null)
						return null;

					return new BankerGui(new ContainerBankerTrade(player, banker), banker);
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

	@SideOnly(Side.CLIENT)
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
