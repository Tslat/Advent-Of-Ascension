package net.tslat.aoa3.common.registration;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.tslat.aoa3.entity.animals.EntityCreepCow;
import net.tslat.aoa3.entity.animals.EntityHalycon;
import net.tslat.aoa3.entity.misc.pixon.*;
import net.tslat.aoa3.entity.npcs.*;
import net.tslat.aoa3.entity.npcs.ambient.EntityGorbCitizen;
import net.tslat.aoa3.entity.npcs.ambient.EntityPrimordialGuide;
import net.tslat.aoa3.entity.npcs.ambient.EntityZalChild;
import net.tslat.aoa3.entity.npcs.ambient.EntityZalCitizen;
import net.tslat.aoa3.entity.npcs.banker.*;
import net.tslat.aoa3.entity.npcs.lottoman.*;
import net.tslat.aoa3.entity.npcs.skillmaster.*;

@Mod.EventBusSubscriber
public class GeneralEntityRegister {
	static int entityId = 0;

	@SubscribeEvent
	public static void registerEntities(final RegistryEvent.Register<EntityEntry> ev) {
		ev.getRegistry().registerAll(MobRegister.buildMobEntries());
		ev.getRegistry().registerAll(MinionRegister.buildMinionEntries());
		ev.getRegistry().registerAll(buildEntityEntries());
		ev.getRegistry().registerAll(MiscEntityRegister.buildMiscEntityEntries());
		ev.getRegistry().registerAll(ProjectileRegister.buildProjectileEntries());
	}

	private static EntityEntry[] buildEntityEntries() {
		return new EntityEntry[]{
			newEntry("abyssal_lottoman", EntityAbyssalLottoman.class),
			newEntry("ambient_pixon", EntityAmbientPixon.class),
			newEntry("anima_master", EntityAnimaMaster.class),
			newEntry("assassin", EntityAssassin.class),
			newEntry("augury_master", EntityAuguryMaster.class),
			newEntry("baron_lottoman", EntityBaronLottoman.class),
			newEntry("blooming_pixon", EntityBloomingPixon.class),
			newEntry("boreic_lottoman", EntityBoreicLottoman.class),
			newEntry("butchery_master", EntityButcheryMaster.class),
			newEntry("candied_lottoman", EntityCandiedLottoman.class),
			newEntry("celevian_lottoman", EntityCelevianLottoman.class),
			newEntry("corrupted_traveller", EntityCorruptedTraveller.class),
			newEntry("creation_master", EntityCreationMaster.class),
			newEntry("creep_banker", EntityCreepBanker.class),
			newEntry("creep_cow", EntityCreepCow.class),
			newEntry("creeponia_lottoman", EntityCreeponiaLottoman.class),
			newEntry("crystal_lottoman", EntityCrystalLottoman.class),
			newEntry("crystal_trader", EntityCrystalTrader.class),
			newEntry("dungeon_keeper", EntityDungeonKeeper.class),
			newEntry("dustopian_lottoman", EntityDustopianLottoman.class),
			newEntry("expedition_master", EntityExpeditionMaster.class),
			newEntry("explosives_expert", EntityExplosivesExpert.class),
			newEntry("extraction_master", EntityExtractionMaster.class),
			newEntry("floro_lottoman", EntityFloroLottoman.class),
			newEntry("foraging_master", EntityForagingMaster.class),
			newEntry("glaring_pixon", EntityGlaringPixon.class),
			newEntry("gleaming_pixon", EntityGleamingPixon.class),
			newEntry("glistening_pixon", EntityGlisteningPixon.class),
			newEntry("glowing_pixon", EntityGlowingPixon.class),
			newEntry("golden_lottoman", EntityGoldenLottoman.class),
			newEntry("gorb_arms_dealer", EntityGorbArmsDealer.class),
			newEntry("gorb_citizen", EntityGorbCitizen.class),
			newEntry("gorb_engineer", EntityGorbEngineer.class),
			newEntry("halycon", EntityHalycon.class),
			newEntry("hauling_master", EntityHaulingMaster.class),
			newEntry("haunted_lottoman", EntityHauntedLottoman.class),
			newEntry("hunter_master", EntityHunterMaster.class),
			newEntry("infusion_master", EntityInfusionMaster.class),
			newEntry("innervation_master", EntityInnervationMaster.class),
			newEntry("lelyetian_banker", EntityLelyetianBanker.class),
			newEntry("lelyetian_lottoman", EntityLelyetianLottoman.class),
			newEntry("lelyetian_trader", EntityLelyetianTrader.class),
			newEntry("logging_master", EntityLoggingMaster.class),
			newEntry("lottoman", EntityLottoman.class),
			newEntry("lunar_lottoman", EntityLunarLottoman.class),
			newEntry("metalloid", EntityMetalloid.class),
			newEntry("mystic_lottoman", EntityMysticLottoman.class),
			newEntry("naturalist", EntityNaturalist.class),
			newEntry("portal_master", EntityPortalMaster.class),
			newEntry("precasian_lottoman", EntityPrecasianLottoman.class),
			newEntry("primordial_banker", EntityPrimordialBanker.class),
			newEntry("primordial_guide", EntityPrimordialGuide.class),
			newEntry("primordial_merchant", EntityPrimordialMerchant.class),
			newEntry("primordial_spellbinder", EntityPrimordialSpellbinder.class),
			newEntry("primordial_wizard", EntityPrimordialWizard.class),
			newEntry("professor", EntityProfessor.class),
			newEntry("radiant_pixon", EntityRadiantPixon.class),
			newEntry("realmshifter", EntityRealmshifter.class),
			newEntry("rocky_lottoman", EntityRockyLottoman.class),
			newEntry("runation_master", EntityRunationMaster.class),
			newEntry("runic_lottoman", EntityRunicLottoman.class),
			newEntry("shining_pixon", EntityShiningPixon.class),
			newEntry("shyre_archer", EntityShyreArcher.class),
			newEntry("shyre_banker", EntityShyreBanker.class),
			newEntry("shyrelands_lottoman", EntityShyrelandsLottoman.class),
			newEntry("soul_agent", EntitySoulAgent.class),
			newEntry("store_keeper", EntityStoreKeeper.class),
			newEntry("token_collector", EntityTokenCollector.class),
			newEntry("toxic_lottoman", EntityToxicLottoman.class),
			newEntry("toy_merchant", EntityToyMerchant.class),
			newEntry("troll_trader", EntityTrollTrader.class),
			newEntry("twinkling_lottoman", EntityTwinklingLottoman.class),
			newEntry("withering_lottoman", EntityWitheringLottoman.class),
			newEntry("zal_banker", EntityZalBanker.class),
			newEntry("zal_child", EntityZalChild.class),
			newEntry("zal_citizen", EntityZalCitizen.class),
			newEntry("zal_grocer", EntityZalGrocer.class),
			newEntry("zal_herbalist", EntityZalHerbalist.class),
			newEntry("zal_spellbinder", EntityZalSpellbinder.class),
			newEntry("zal_vendor", EntityZalVendor.class)
		};
	}

	private static <E extends Entity> EntityEntry newEntry(final String name, final Class<E> entityClass) {
		final EntityEntryBuilder<E> builder = EntityEntryBuilder.create();
		final ResourceLocation resource = new ResourceLocation("aoa3", name);

		builder.entity(entityClass);
		builder.tracker(120, 1, true);
		builder.egg(16777215, 0);

		EntityEntryBuilder.BuiltEntityEntry entry = (EntityEntryBuilder.BuiltEntityEntry)builder.id(resource, GeneralEntityRegister.entityId++).name(resource.getResourceDomain() + "." + resource.getResourcePath()).build();
		entry.addedToRegistry();

		return entry;
	}
}
