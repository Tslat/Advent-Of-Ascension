package net.nevermine.assist;

import net.nevermine.common.nevermine;
import net.nevermine.event.creature.CreatureInnervation;
import net.nevermine.event.dimensional.*;
import net.nevermine.event.dimensional.overworld.*;
import net.nevermine.event.equipment.*;
import net.nevermine.event.player.EntityConstructorEvent;
import net.nevermine.event.player.liveDieEvent;
import net.nevermine.event.player.worldJoin;
import net.nevermine.event.resource.*;
import net.nevermine.resource.boss.bossBarTickHandler;
import net.nevermine.resource.creation.creationTickHandler;
import net.nevermine.resource.energy.energyTickHandler;
import net.nevermine.resource.rage.rageTickHandler;
import net.nevermine.resource.soulpower.soulPowerTickHandler;

public class AddBusses {
	public static void init() {
		nevermine.addEventBus(new EntityConstructorEvent());
		nevermine.addSpecialEventBus(new worldJoin());
		nevermine.addEventBus(new worldJoin());
		nevermine.addEventBus(new ArmorEffects());
		nevermine.addSpecialEventBus(new ArmorEffects());
		nevermine.addSpecialEventBus(new RageTicker());
		nevermine.addEventBus(new RageTicker());
		nevermine.addSpecialEventBus(new CreatureInnervation());
		nevermine.addSpecialEventBus(new AnimaEvent());
		nevermine.addEventBus(new CreatureInnervation());
		nevermine.addSpecialEventBus(new SniperEvent());
		nevermine.addSpecialEventBus(new HoldItemEvent());
		nevermine.addSpecialEventBus(new HelmetEvent());
		nevermine.addEventBus(new SniperEvent());
		nevermine.addSpecialEventBus(new ExtractionRequirement());
		nevermine.addEventBus(new ArtistryRequirement());
		nevermine.addSpecialEventBus(new GardencianOceanEvent());
		nevermine.addSpecialEventBus(new CandylandStickyEvent());
		nevermine.addSpecialEventBus(new ShyrelandsAtmosphereEvent());
		nevermine.addSpecialEventBus(new CavernAmbientEvent());
		nevermine.addSpecialEventBus(new BloodHuntEvent());
		nevermine.addSpecialEventBus(new SoulScurryEvent());
		nevermine.addSpecialEventBus(new LelyetianUnder());
		nevermine.addSpecialEventBus(new TributeTicker());
		nevermine.addEventBus(new TributeTicker());
		nevermine.addSpecialEventBus(new CreepDayEvent());
		nevermine.addSpecialEventBus(new DeathDayEvent());
		nevermine.addSpecialEventBus(new BigDayEvent());
		nevermine.addSpecialEventBus(new LunarEvent());
		nevermine.addSpecialEventBus(new ExpeditionEvent());
		nevermine.addEventBus(new ExpeditionEvent());
		nevermine.addSpecialEventBus(new liveDieEvent());
		nevermine.postForgeEvent(new liveDieEvent());
		nevermine.addEventBus(new HarvestingTracker());
		nevermine.addEventBus(new AnimaEvent());
		nevermine.addSpecialEventBus(new energyTickHandler());
		nevermine.addSpecialEventBus(new creationTickHandler());
		nevermine.addSpecialEventBus(new soulPowerTickHandler());
		nevermine.addSpecialEventBus(new rageTickHandler());
		nevermine.addSpecialEventBus(new bossBarTickHandler());
	}
}
