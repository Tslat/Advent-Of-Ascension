package net.tslat.aoa3.common.registration.entity;

import net.minecraft.SharedConstants;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.RegistryObject;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.entity.base.AoATrader;
import net.tslat.aoa3.content.entity.npc.ambient.*;
import net.tslat.aoa3.content.entity.npc.banker.*;
import net.tslat.aoa3.content.entity.npc.lottoman.LottomanEntity;
import net.tslat.aoa3.content.entity.npc.trader.*;

public final class AoANpcs {
	public static void init() {}

	public static final RegistryObject<EntityType<PathfinderMob>> GORB_CITIZEN = registerNPC("gorb_citizen", GorbCitizenEntity::new, 0.5625f, 1.6875f, 3233315, 4744746);
	public static final RegistryObject<EntityType<PathfinderMob>> PRIMORDIAL_GUIDE = registerNPC("primordial_guide", PrimordialGuideEntity::new, 0.5625f, 2.0f, 7037281, 2960171);
	public static final RegistryObject<EntityType<PathfinderMob>> ZAL_CHILD = registerNPC("zal_child", ZalChildEntity::new, 0.16875f, 0.5625f, 15395546, 2631715);
	public static final RegistryObject<EntityType<PathfinderMob>> ZAL_CITIZEN = registerNPC("zal_citizen", ZalCitizenEntity::new, 0.5625f, 1.875f, 15921894, 3487025);

	public static final RegistryObject<EntityType<PathfinderMob>> CREEP_BANKER = registerNPC("creep_banker", CreepBankerEntity::new, 0.5625f, 2.0f, 0xE6B900, 0x54430C);
	public static final RegistryObject<EntityType<PathfinderMob>> LELYETIAN_BANKER = registerNPC("lelyetian_banker", LelyetianBankerEntity::new, 0.5625f, 2.0f, 14705940, 2955779);
	public static final RegistryObject<EntityType<PathfinderMob>> PRIMORDIAL_BANKER = registerNPC("primordial_banker", PrimordialBankerEntity::new, 0.5625f, 2.0f, 14685204, 1315346);
	public static final RegistryObject<EntityType<PathfinderMob>> SHYRE_BANKER = registerNPC("shyre_banker", ShyreBankerEntity::new, 0.5625f, 2.0f, 0xE56901, 0x5D2809);
	public static final RegistryObject<EntityType<PathfinderMob>> ZAL_BANKER = registerNPC("zal_banker", ZalBankerEntity::new, 0.5625f, 1.875f, 11975695, 4144954);

	public static final RegistryObject<EntityType<AoATrader>> LOTTOMAN = registerNPC("lottoman", LottomanEntity::new, 0.5625f, 2.0f, 0x6B2A25, 0x2E1F14);
	public static final RegistryObject<EntityType<AoATrader>> UNDEAD_HERALD = registerNPC("undead_herald", UndeadHeraldEntity::new, 0.5625f, 2.0f, 0xA7A294, 0x212121);

	public static final RegistryObject<EntityType<PathfinderMob>> CORRUPTED_TRAVELLER = registerNPC("corrupted_traveller", CorruptedTravellerEntity::new, 0.5625f, 2.0f, 0x2D231A, 0x54A8B0);
	public static final RegistryObject<EntityType<AoATrader>> CRYSTAL_TRADER = registerNPC("crystal_trader", CrystalTraderEntity::new, 0.5625f, 2.0f, 0x046E12, 0x881A18);
	public static final RegistryObject<EntityType<AoATrader>> DUNGEON_KEEPER = registerNPC("dungeon_keeper", DungeonKeeperEntity::new, 0.5625f, 2.0f, 0x171410, 0x4C4231);
	public static final RegistryObject<EntityType<AoATrader>> EXPLOSIVES_EXPERT = registerNPC("explosives_expert", ExplosivesExpertEntity::new, 0.5625f, 2.0f, 0x740904, 0x8A9227);
	public static final RegistryObject<EntityType<AoATrader>> GORB_ARMS_DEALER = registerNPC("gorb_arms_dealer", GorbArmsDealerEntity::new, 0.5625f, 1.6875f, 4990482, 14195479);
	public static final RegistryObject<EntityType<AoATrader>> GORB_ENGINEER = registerNPC("gorb_engineer", GorbEngineerEntity::new, 0.5625f, 1.6875f, 10032659, 14389134);
	public static final RegistryObject<EntityType<AoATrader>> LELYETIAN_TRADER = registerNPC("lelyetian_trader", LelyetianTraderEntity::new, 0.5625f, 2.0f, 14705940, 2955779);
	public static final RegistryObject<EntityType<AoATrader>> METALLOID = registerNPC("metalloid", MetalloidEntity::new, 0.5625f, 2.0f, 0x22110F, 0x57302B);
	public static final RegistryObject<EntityType<AoATrader>> NATURALIST = registerNPC("naturalist", NaturalistEntity::new, 0.5625f, 2.0f, 0x364015, 0x808D16);
	public static final RegistryObject<EntityType<AoATrader>> PRIMORDIAL_MERCHANT = registerNPC("primordial_merchant", PrimordialMerchantEntity::new, 0.5625f, 2.0f, 909869, 1250835);
	public static final RegistryObject<EntityType<AoATrader>> PRIMORDIAL_SPELLBINDER = registerNPC("primordial_spellbinder", PrimordialSpellbinderEntity::new, 0.5625f, 2.0f, 1059237, 8097765);
	public static final RegistryObject<EntityType<AoATrader>> PRIMORDIAL_WIZARD = registerNPC("primordial_wizard", PrimordialWizardEntity::new, 0.5625f, 2.0f, 4000119, 13023446);
	public static final RegistryObject<EntityType<AoATrader>> PROFESSOR = registerNPC("professor", ProfessorEntity::new, 0.5625f, 2.0f, 13493531, 1644822);
	public static final RegistryObject<EntityType<AoATrader>> SHYRE_ARCHER = registerNPC("shyre_archer", ShyreArcherEntity::new, 0.5625f, 2.0f, 0x171B1F, 0x766006);
	public static final RegistryObject<EntityType<SkillMasterEntity>> SKILL_MASTER = registerNPC("skill_master", SkillMasterEntity::new, 0.5625f, 2.0f, 0x211F1D, 0xE3A602);
	public static final RegistryObject<EntityType<AoATrader>> STORE_KEEPER = registerNPC("store_keeper", StoreKeeperEntity::new, 0.5625f, 2.0f, 0x80935F, 0x5E7041);
	public static final RegistryObject<EntityType<AoATrader>> TOKEN_COLLECTOR = registerNPC("token_collector", TokenCollectorEntity::new, 0.5625f, 2.0f, 0x675105, 0x1C1304);
	public static final RegistryObject<EntityType<AoATrader>> TOY_MERCHANT = registerNPC("toy_merchant", ToyMerchantEntity::new, 0.5625f, 1 + 14 / 16f, 7821066, 14068552);
	public static final RegistryObject<EntityType<AoATrader>> TROLL_TRADER = registerNPC("troll_trader", TrollTraderEntity::new, 0.5625f, 1.8125f, 0x417DB0, 0x6B2969);
	public static final RegistryObject<EntityType<AoATrader>> ZAL_GROCER = registerNPC("zal_grocer", ZalGrocerEntity::new, 0.5625f, 1.875f, 2956551, 6315353);
	public static final RegistryObject<EntityType<AoATrader>> ZAL_HERBALIST = registerNPC("zal_herbalist", ZalHerbalistEntity::new, 0.5625f, 1.875f, 997382, 6315353);
	public static final RegistryObject<EntityType<AoATrader>> ZAL_SPELLBINDER = registerNPC("zal_spellbinder", ZalSpellbinderEntity::new, 0.5625f, 1.875f, 3475048, 6315353);
	public static final RegistryObject<EntityType<AoATrader>> ZAL_VENDOR = registerNPC("zal_vendor", ZalVendorEntity::new, 0.5625f, 1.875f, 4144703, 9933723);

	public static final RegistryObject<EntityType<DryadSpriteEntity>> DRYAD_SPRITE = registerNPC("dryad_sprite", DryadSpriteEntity::new, 1f, 1f, 0x382E19, 0x4C4525);

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

		AoAItems.registerItem(registryName + "_spawn_egg", () -> new ForgeSpawnEggItem(registryObject, primaryEggColour, secondaryEggColour, new Item.Properties()), () -> CreativeModeTabs.SPAWN_EGGS);

		return registryObject;
	}
}
