package net.tslat.aoa3.common.registration.entity;

import net.minecraft.SharedConstants;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.RegistryObject;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.content.entity.base.AoATrader;
import net.tslat.aoa3.content.entity.npc.ambient.*;
import net.tslat.aoa3.content.entity.npc.banker.*;
import net.tslat.aoa3.content.entity.npc.lottoman.*;
import net.tslat.aoa3.content.entity.npc.trader.*;

public final class AoANpcs {
	public static void init() {}

	public static final RegistryObject<EntityType<PathfinderMob>> GORB_CITIZEN = registerNPC("gorb_citizen", GorbCitizenEntity::new, 0.5625f, 1.6875f, 3233315, 4744746);
	public static final RegistryObject<EntityType<PathfinderMob>> PRIMORDIAL_GUIDE = registerNPC("primordial_guide", PrimordialGuideEntity::new, 0.5625f, 2.0f, 7037281, 2960171);
	public static final RegistryObject<EntityType<PathfinderMob>> ZAL_CHILD = registerNPC("zal_child", ZalChildEntity::new, 0.16875f, 0.5625f, 15395546, 2631715);
	public static final RegistryObject<EntityType<PathfinderMob>> ZAL_CITIZEN = registerNPC("zal_citizen", ZalCitizenEntity::new, 0.5625f, 1.875f, 15921894, 3487025);

	public static final RegistryObject<EntityType<PathfinderMob>> CREEP_BANKER = registerNPC("creep_banker", CreepBankerEntity::new, 0.5625f, 2.0f, 407559, 5395971);
	public static final RegistryObject<EntityType<PathfinderMob>> LELYETIAN_BANKER = registerNPC("lelyetian_banker", LelyetianBankerEntity::new, 0.5625f, 2.0f, 14705940, 2955779);
	public static final RegistryObject<EntityType<PathfinderMob>> PRIMORDIAL_BANKER = registerNPC("primordial_banker", PrimordialBankerEntity::new, 0.5625f, 2.0f, 14685204, 1315346);
	public static final RegistryObject<EntityType<PathfinderMob>> SHYRE_BANKER = registerNPC("shyre_banker", ShyreBankerEntity::new, 0.5625f, 2.0f, 15369239, 1101037);
	public static final RegistryObject<EntityType<PathfinderMob>> ZAL_BANKER = registerNPC("zal_banker", ZalBankerEntity::new, 0.5625f, 1.875f, 11975695, 4144954);

	public static final RegistryObject<EntityType<AoATrader>> LOTTOMAN = registerNPC("lottoman", LottomanEntity::new, 0.5625f, 2.0f, 15723491, 789515);

	public static final RegistryObject<EntityType<AoATrader>> ASSASSIN = registerNPC("assassin", AssassinEntity::new, 0.5625f, 2.0f, 5638000, 5393748);
	public static final RegistryObject<EntityType<PathfinderMob>> CORRUPTED_TRAVELLER = registerNPC("corrupted_traveller", CorruptedTravellerEntity::new, 0.5625f, 2.0f, 329538, 340761);
	public static final RegistryObject<EntityType<AoATrader>> CRYSTAL_TRADER = registerNPC("crystal_trader", CrystalTraderEntity::new, 0.5625f, 2.0f, 14150368, 1369392);
	public static final RegistryObject<EntityType<AoATrader>> DUNGEON_KEEPER = registerNPC("dungeon_keeper", DungeonKeeperEntity::new, 0.5625f, 2.0f, 3157507, 2825476);
	public static final RegistryObject<EntityType<AoATrader>> EXPLOSIVES_EXPERT = registerNPC("explosives_expert", ExplosivesExpertEntity::new, 0.5625f, 2.0f, 15864595, 16250871);
	public static final RegistryObject<EntityType<AoATrader>> GORB_ARMS_DEALER = registerNPC("gorb_arms_dealer", GorbArmsDealerEntity::new, 0.5625f, 1.6875f, 4990482, 14195479);
	public static final RegistryObject<EntityType<AoATrader>> GORB_ENGINEER = registerNPC("gorb_engineer", GorbEngineerEntity::new, 0.5625f, 1.6875f, 10032659, 14389134);
	public static final RegistryObject<EntityType<AoATrader>> LELYETIAN_TRADER = registerNPC("lelyetian_trader", LelyetianTraderEntity::new, 0.5625f, 2.0f, 14705940, 2955779);
	public static final RegistryObject<EntityType<AoATrader>> METALLOID = registerNPC("metalloid", MetalloidEntity::new, 0.5625f, 2.0f, 3677189, 7555455);
	public static final RegistryObject<EntityType<AoATrader>> NATURALIST = registerNPC("naturalist", NaturalistEntity::new, 0.5625f, 2.0f, 4927582, 10193420);
	public static final RegistryObject<EntityType<AoATrader>> PRIMORDIAL_MERCHANT = registerNPC("primordial_merchant", PrimordialMerchantEntity::new, 0.5625f, 2.0f, 909869, 1250835);
	public static final RegistryObject<EntityType<AoATrader>> PRIMORDIAL_SPELLBINDER = registerNPC("primordial_spellbinder", PrimordialSpellbinderEntity::new, 0.5625f, 2.0f, 1059237, 8097765);
	public static final RegistryObject<EntityType<AoATrader>> PRIMORDIAL_WIZARD = registerNPC("primordial_wizard", PrimordialWizardEntity::new, 0.5625f, 2.0f, 4000119, 13023446);
	public static final RegistryObject<EntityType<AoATrader>> PROFESSOR = registerNPC("professor", ProfessorEntity::new, 0.5625f, 2.0f, 13493531, 1644822);
	public static final RegistryObject<EntityType<AoATrader>> REALMSHIFTER = registerNPC("realmshifter", RealmshifterEntity::new, 0.5625f, 2.0f, 2953404, 1776158);
	public static final RegistryObject<EntityType<AoATrader>> SHYRE_ARCHER = registerNPC("shyre_archer", ShyreArcherEntity::new, 0.5625f, 2.0f, 14393873, 15397397);
	public static final RegistryObject<EntityType<SkillMasterEntity>> SKILL_MASTER = registerNPC("skill_master", SkillMasterEntity::new, 0.5625f, 2.0f, 13882169, 4470027);
	public static final RegistryObject<EntityType<AoATrader>> STORE_KEEPER = registerNPC("store_keeper", StoreKeeperEntity::new, 0.5625f, 2.0f, 2824965, 10530735);
	public static final RegistryObject<EntityType<AoATrader>> TOKEN_COLLECTOR = registerNPC("token_collector", TokenCollectorEntity::new, 0.5625f, 2.0f, 6840842, 13878796);
	public static final RegistryObject<EntityType<AoATrader>> TOY_MERCHANT = registerNPC("toy_merchant", ToyMerchantEntity::new, 0.5625f, 2.0f, 7821066, 14068552);
	public static final RegistryObject<EntityType<AoATrader>> TROLL_TRADER = registerNPC("troll_trader", TrollTraderEntity::new, 0.5625f, 1.8125f, 779757, 415293);
	public static final RegistryObject<EntityType<AoATrader>> UNDEAD_HERALD = registerNPC("undead_herald", UndeadHeraldEntity::new, 0.5625f, 2.0f, 592394, 1302514);
	public static final RegistryObject<EntityType<AoATrader>> ZAL_GROCER = registerNPC("zal_grocer", ZalGrocerEntity::new, 0.5625f, 1.875f, 2956551, 6315353);
	public static final RegistryObject<EntityType<AoATrader>> ZAL_HERBALIST = registerNPC("zal_herbalist", ZalHerbalistEntity::new, 0.5625f, 1.875f, 997382, 6315353);
	public static final RegistryObject<EntityType<AoATrader>> ZAL_SPELLBINDER = registerNPC("zal_spellbinder", ZalSpellbinderEntity::new, 0.5625f, 1.875f, 3475048, 6315353);
	public static final RegistryObject<EntityType<AoATrader>> ZAL_VENDOR = registerNPC("zal_vendor", ZalVendorEntity::new, 0.5625f, 1.875f, 4144703, 9933723);

	public static final RegistryObject<EntityType<DryadSpriteEntity>> DRYAD_SPRITE = registerNPC("dryad_sprite", DryadSpriteEntity::new, 1f, 1f, 2308682, 3174984);

	private static <T extends Mob> RegistryObject<EntityType<T>> registerNPC(String registryName, EntityType.EntityFactory<T> factory, float width, float height, int primaryEggColour, int secondaryEggColour) {
		return registerNPC(registryName, factory, false, width, height, primaryEggColour, secondaryEggColour);
	}

	private static <T extends Mob> RegistryObject<EntityType<T>> registerNPC(String registryName, EntityType.EntityFactory<T> factory, boolean fireImmune, float width, float height, int primaryEggColour, int secondaryEggColour) {
		EntityType.Builder<T> typeBuilder = EntityType.Builder.of(factory, MobCategory.AMBIENT).sized(width, height);

		if (fireImmune)
			typeBuilder.fireImmune();

		RegistryObject<EntityType<T>> registryObject = AoARegistries.ENTITIES.register(registryName, () -> {
			boolean dataFixers = SharedConstants.CHECK_DATA_FIXER_SCHEMA;
			SharedConstants.CHECK_DATA_FIXER_SCHEMA = false;
			EntityType<T> entityType = typeBuilder.build(registryName);
			SharedConstants.CHECK_DATA_FIXER_SCHEMA = dataFixers;

			return entityType;
		});

		AoARegistries.ITEMS.register(registryName + "_spawn_egg", () -> new ForgeSpawnEggItem(registryObject, primaryEggColour, secondaryEggColour, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

		return registryObject;
	}
}
