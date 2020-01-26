package net.tslat.aoa3.library;

import net.minecraft.client.audio.MusicTicker;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.EnumHelperClient;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.SoundsRegister;

import javax.annotation.Nullable;

public final class Enums {
	@SideOnly(Side.CLIENT)
	public static final MusicTicker.MusicType NULL_MUSIC = EnumHelperClient.addMusicType("null", SoundsRegister.nullMusic, 0, 1);

	public enum Skills {
		ALCHEMY(0),
		ANIMA(1),
		AUGURY(2),
		BUTCHERY(3),
		CREATION(4),
		ENGINEERING(5),
		EXPEDITION(6),
		EXTRACTION(7),
		FORAGING(8),
		HAULING(9),
		HUNTER(10),
		INFUSION(11),
		INNERVATION(12),
		LOGGING(13),
		RUNATION(14);

		public final int id;

		Skills(final int id) {
			this.id = id;
		}

		public static Skills getById(final int id) {
			switch(id) {
				case 0:
					return ALCHEMY;
				case 1:
					return ANIMA;
				case 2:
					return AUGURY;
				case 3:
					return BUTCHERY;
				case 4:
					return CREATION;
				case 5:
					return ENGINEERING;
				case 6:
					return EXPEDITION;
				case 7:
					return EXTRACTION;
				case 8:
					return FORAGING;
				case 9:
					return HAULING;
				case 10:
					return HUNTER;
				case 11:
					return INFUSION;
				case 12:
					return INNERVATION;
				case 13:
					return LOGGING;
				case 14:
					return RUNATION;
				default:
					return null;
			}
		}
	}

	public enum Deities {
		EREBON,
		LUXON,
		PLUTON,
		SELYAN;
	}

	public enum ArmourSets {
		ALACRITY,
		ALCHEMY,
		ALL,
		AMETHIND,
		ANIMA,
		ARCHAIC,
		AUGURY,
		BARON,
		BATTLEBORN,
		BIOGENIC,
		BOREIC,
		BUTCHERY,
		CANDY,
		COMMANDER,
		CREATION,
		CRYSTALLIS,
		ELECANYTE,
		EMBRODIUM,
		ENGINEERING,
		EXOPLATE,
		EXPEDITION,
		EXPLOSIVE,
		EXTRACTION,
		FORAGING,
		FUNGAL,
		GHASTLY,
		GHOULISH,
		HAULING,
		HAZMAT,
		HUNTER,
		HYDRANGIC,
		HYDROPLATE,
		ICE,
		INFERNAL,
		INFUSION,
		INNERVATION,
		KNIGHT,
		LOGGING,
		LUNAR,
		LYNDAMYTE,
		LYONIC,
		MERCURIAL,
		NECRO,
		NETHENGEIC,
		NIGHTMARE,
		NONE,
		OMNI,
		PHANTASM,
		POISON,
		PREDATIOUS,
		PRIMORDIAL,
		PURITY,
		ROCKBONE,
		ROSIDIAN,
		RUNATION,
		RUNIC,
		SHARPSHOT,
		SKELETAL,
		SPACEKING,
		SPEED,
		SUBTERRANEAN,
		UTOPIAN,
		VOID,
		WEAKEN,
		WITHER,
		ZARGONITE;
	}

	public enum Dimensions {
		ABYSS,
		ANCIENT_CAVERN,
		BARATHOS,
		CANDYLAND,
		CELEVE,
		CREEPONIA,
		CRYSTEVIA,
		DEEPLANDS,
		DUSTOPIA,
		GARDENCIA,
		GRECKON,
		HAVEN,
		IMMORTALLIS,
		IROMINE,
		LBOREAN,
		LELYETIA,
		LUNALUS,
		MYSTERIUM,
		NETHER,
		OVERWORLD,
		PRECASIA,
		RUNANDOR,
		SHYRELANDS,
		THE_END,
		VOX_PONDS
	}

	public enum Resources {
		CREATION,
		ENERGY,
		RAGE,
		SOUL
	}

	public enum Counters {
		ARCHAIC,
		BATTLEBORN,
		CANDY,
		HYDROPLATE,
		KNIGHT,
		MERCURIAL,
		NECRO,
		ROSID,
		RUNIC
	}

	// d = -1 * (4 - 1 / (12.5 * n / 20))
	public enum WeaponSpeed {
		QUADRUPLE(2.4D),
		TRIPLE(1.33333),
		DOUBLE(-0.8D),
		NORMAL(-2.4D),
		HALF(-3.2D),
		THIRD(-3.46667D),
		QUARTER(-3.6D);

		public final double value;

		WeaponSpeed(final double speed) {
			this.value = speed;
		}
	}

	public enum ScreenOverlays {
		SCRATCHES(0),
		BLOODY(1),
		STATIC(2),
		GRILLFACE(3),
		DARKNESS(4),
		EILOSAPIEN(5),
		PURPLE_FOG(6),
		CIRCLES(7),
		CONIFERON_VINES(8),
		SPIKEY_CIRCLES(9),
		SHYRE_DIZZY(10),
		SHYRE_BLIND(11),
		LIGHTWALKER(12);

		public final int id;

		ScreenOverlays(final int id) {
			this.id = id;
		}

		public static ScreenOverlays getById(final int id) {
			switch(id) {
				case 0:
					return SCRATCHES;
				case 1:
					return BLOODY;
				case 2:
					return STATIC;
				case 3:
					return GRILLFACE;
				case 4:
					return DARKNESS;
				case 5:
					return EILOSAPIEN;
				case 6:
					return PURPLE_FOG;
				case 7:
					return CIRCLES;
				case 8:
					return CONIFERON_VINES;
				case 9:
					return SPIKEY_CIRCLES;
				case 10:
					return SHYRE_DIZZY;
				case 11:
					return SHYRE_BLIND;
				case 12:
					return LIGHTWALKER;
				default:
					return null;
			}
		}
	}

	public static final class RGBIntegers {
		public static final int BLACK = 0;
		public static final int BLUE = 255;
		public static final int BONE = 16448150;
		public static final int BRIGHT_TURQUOISE = 58861;
		public static final int BROWN = 9593401;
		public static final int CYAN = 65535;
		public static final int DARK_GRAY = 1973526;
		public static final int DARK_LIME_GREEN = 39168;
		public static final int DARK_VIOLET = 8519858;
		public static final int DE_YORK = 8699004;
		public static final int DEEP_PINK = 16711794;
		public static final int ELECTRIC_BLUE = 8191999;
		public static final int ELECTRIC_LIME = 13622528;
		public static final int GOLD = 13413376;
		public static final int GOLD_YELLOW = 16768768;
		public static final int GOLDEN_POPPY = 14073088;
		public static final int GREEN = 65280;
		public static final int HELIOTROPE = 12732927;
		public static final int IRIS_BLUE = 44504;
		public static final int LAVENDER_BLUSH = 16769787;
		public static final int LIGHT_CORAL = 15698295;
		public static final int MANGO_TANGO = 14509824;
		public static final int MISTY_ROSE = 16771304;
		public static final int OLIVE = 5924864;
		public static final int ORANGE = 14653696;
		public static final int PIGMENT_GREEN = 37698;
		public static final int PINK = 16738740;
		public static final int PURPLE = 12665047;
		public static final int RED = 16711680;
		public static final int RED_2 = 15007744;
		public static final int SILVER = 11908533;
		public static final int TANGERINE_YELLOW = 15257600;
		public static final int TOXIC_GREEN = 3368448;
		public static final int TYRIAN_PURPLE = 7012434;
		public static final int WHITE = 16777215;
		public static final int YELLOW = 16776960;
		public static final int YELLOW_2 = 16248576;
	}

	public enum CreatureEvents {
		BIG_DAY,
		BLOOD_HUNT,
		CREEP_DAY,
		DEATH_DAY,
		LUNAR_INVASION,
		SOUL_SCURRY,
		FULL_MOON
	}

	public enum ScopeScreens {
		BASIC,
		DOTTED,
		SCRATCHES,
		REDLIGHT,
		FLORO,
		MARKER,
		MONSTER,
		DISCHARGE,
		MOON,
		CRYSTAL,
		CANDY,
		DIAMOND,
		BOULDER
	}

	public enum HelmetScreens {
		NIGHT_VISION_GOGGLES
	}

	public enum Runes {
		Unpowered(0),
		Charged(1),
		Compass(2),
		Distortion(3),
		Energy(4),
		Fire(5),
		Kinetic(6),
		Life(7),
		Lunar(8),
		Poison(9),
		Power(10),
		Storm(11),
		Strike(12),
		Water(13),
		Wind(14),
		Wither(15);

		public final int meta;

		Runes(final int meta) {
			this.meta = meta;
		}

		public static Runes getByMeta(int meta) {
			switch(meta) {
				case 0:
					return Unpowered;
				case 1:
					return Charged;
				case 2:
					return Compass;
				case 3:
					return Distortion;
				case 4:
					return Energy;
				case 5:
					return Fire;
				case 6:
					return Kinetic;
				case 7:
					return Life;
				case 8:
					return Lunar;
				case 9:
					return Poison;
				case 10:
					return Power;
				case 11:
					return Storm;
				case 12:
					return Strike;
				case 13:
					return Water;
				case 14:
					return Wind;
				case 15:
					return Wither;
				default:
					return Unpowered;
			}
		}
	}

	public enum MobProperties {
		RANGED_IMMUNE,
		BLASTER_IMMUNE,
		MAGIC_IMMUNE,
		EXPLOSION_IMMUNE,
		FIRE_IMMUNE,
		GUN_IMMUNE,
		MELEE_IMMUNE,
		SPECIAL_COMBAT_ENTITY,
		STATUS_IMMUNE
	}

	public enum MobProjectileType {
		MAGIC,
		PHYSICAL,
		GUN,
		OTHER
	}

	public enum ModGuis {
		WORN_BOOK(0),
		TRADER_ASSASSIN(1),
		TRADER_CORRUPTED_TRAVELLER(2),
		TRADER_CREEP_BANKER(3),
		TRADER_CRYSTAL_TRADER(4),
		TRADER_DUNGEON_KEEPER(5),
		TRADER_EXPLOSIVES_EXPERT(6),
		TRADER_GORB_ARMS_DEALER(7),
		TRADER_GORB_ENGINEER(8),
		TRADER_LELYETIAN_BANKER(9),
		TRADER_LELYETIAN_TRADER(10),
		TRADER_LOTTOMAN(11),
		TRADER_METALLOID(12),
		TRADER_NATURALIST(13),
		TRADER_PRIMORDIAL_BANKER(14),
		TRADER_PRIMORDIAL_MERCHANT(15),
		TRADER_PRIMORDIAL_SPELLBINDER(16),
		TRADER_PRIMORDIAL_WIZARD(17),
		TRADER_PROFESSOR(18),
		TRADER_REALMSHIFTER(19),
		TRADER_SHYRE_ARCHER(20),
		TRADER_SHYRE_BANKER(21),
		TRADER_SKILL_MASTER(22),
		TRADER_UNDEAD_HERALD(23),
		TRADER_STORE_KEEPER(24),
		TRADER_TOKEN_COLLECTOR(25),
		TRADER_TOY_MERCHANT(26),
		TRADER_TROLL_TRADER(27),
		TRADER_ZAL_BANKER(28),
		TRADER_ZAL_GROCER(29),
		TRADER_ZAL_HERBALIST(30),
		TRADER_ZAL_SPELLBINDER(31),
		TRADER_ZAL_VENDOR(32),
		ADVENT_MAIN_WINDOW(100),
		INFUSION_TABLE(101),
		FRAME_BENCH(102),
		REALMSTONE_MENU(103),
		MENDING_TABLE(104),
		WHITEWASHING_TABLE(105),
		DIVINE_STATION(106),
		BANKER(107);

		public int guiId;

		ModGuis(final int guiId) {
			this.guiId = guiId;
		}

		@Nullable
		public static ModGuis getById(final int id) {
			switch (id) {
				case 0:
					return WORN_BOOK;
				case 1:
					return TRADER_ASSASSIN;
				case 2:
					return TRADER_CORRUPTED_TRAVELLER;
				case 3:
					return TRADER_CREEP_BANKER;
				case 4:
					return TRADER_CRYSTAL_TRADER;
				case 5:
					return TRADER_DUNGEON_KEEPER;
				case 6:
					return TRADER_EXPLOSIVES_EXPERT;
				case 7:
					return TRADER_GORB_ARMS_DEALER;
				case 8:
					return TRADER_GORB_ENGINEER;
				case 9:
					return TRADER_LELYETIAN_BANKER;
				case 10:
					return TRADER_LELYETIAN_TRADER;
				case 11:
					return TRADER_LOTTOMAN;
				case 12:
					return TRADER_METALLOID;
				case 13:
					return TRADER_NATURALIST;
				case 14:
					return TRADER_PRIMORDIAL_BANKER;
				case 15:
					return TRADER_PRIMORDIAL_MERCHANT;
				case 16:
					return TRADER_PRIMORDIAL_SPELLBINDER;
				case 17:
					return TRADER_PRIMORDIAL_WIZARD;
				case 18:
					return TRADER_PROFESSOR;
				case 19:
					return TRADER_REALMSHIFTER;
				case 20:
					return TRADER_SHYRE_ARCHER;
				case 21:
					return TRADER_SHYRE_BANKER;
				case 22:
					return TRADER_SKILL_MASTER;
				case 23:
					return TRADER_UNDEAD_HERALD;
				case 24:
					return TRADER_STORE_KEEPER;
				case 25:
					return TRADER_TOKEN_COLLECTOR;
				case 26:
					return TRADER_TOY_MERCHANT;
				case 27:
					return TRADER_TROLL_TRADER;
				case 28:
					return TRADER_ZAL_BANKER;
				case 29:
					return TRADER_ZAL_GROCER;
				case 30:
					return TRADER_ZAL_HERBALIST;
				case 31:
					return TRADER_ZAL_SPELLBINDER;
				case 32:
					return TRADER_ZAL_VENDOR;
				case 100:
					return ADVENT_MAIN_WINDOW;
				case 101:
					return INFUSION_TABLE;
				case 102:
					return FRAME_BENCH;
				case 103:
					return REALMSTONE_MENU;
				case 104:
					return MENDING_TABLE;
				case 105:
					return WHITEWASHING_TABLE;
				case 106:
					return DIVINE_STATION;
				case 107:
					return BANKER;
				default:
					return null;
			}
		}
	}

	public enum CommandFeedbackType {
		INFO(TextFormatting.GRAY),
		SUCCESS(TextFormatting.GREEN),
		WARN(TextFormatting.RED),
		ERROR(TextFormatting.DARK_RED);

		private final TextFormatting colour;

		CommandFeedbackType(TextFormatting colour) {
			this.colour = colour;
		}

		public TextFormatting getColour() {
			return colour;
		}
	}

	public enum MainWindowThemes {
		Default,
		Jungle,
		Ancient_Ruins,
		Hell,
		Crystals
	}

	public enum PlayerCrownTypes {
		Donator,
		Super_Donator,
		Crazy_Donator,
		Wiki_Editor,
		Tslat,
		Ursun;

		public enum ChooseableCrownTypes {
			Donator,
			Super_Donator,
			Wiki_Editor;

			public PlayerCrownTypes toBaseType() {
				switch (this) {
					case Donator:
						return PlayerCrownTypes.Donator;
					case Super_Donator:
						return PlayerCrownTypes.Super_Donator;
					case Wiki_Editor:
						return PlayerCrownTypes.Wiki_Editor;
					default:
						return null;
				}
			}
		}
	}

	public enum ArmourListenerTypes {
		AFTER_ATTACK,
		ATTACK_RECEIVED,
		DAMAGE_DEALT,
		DEATH,
		EQUIP,
		FALL,
		INCOMING_ATTACK,
		TICK,
		UNEQUIP
	}

	public enum BlockUpdateFlags {
		BLOCK_UPDATE(1),
		SYNC_CLIENT(2),
		PREVENT_RERENDER(4),
		DESYNC_RENDERS(8),
		NO_OBSERVERS(16);

		public int value;

		BlockUpdateFlags(int flagValue) {
			this.value = flagValue;
		}
	}

	public enum ItemDescriptionType {
		POSITIVE(TextFormatting.DARK_GREEN),
		NEGATIVE(TextFormatting.RED),
		NEUTRAL(TextFormatting.DARK_GRAY),
		UNIQUE(TextFormatting.DARK_PURPLE),
		ITEM_TYPE_INFO(TextFormatting.AQUA),
		ITEM_DAMAGE(TextFormatting.DARK_RED),
		ITEM_AMMO_COST(TextFormatting.LIGHT_PURPLE);

		public final TextFormatting format;

		ItemDescriptionType(TextFormatting format) {
			this.format = format;
		}
	}

	public static final class EntityAnimations {
		public static final String ATTACK_1 = "ATTACK_1";
		public static final String ATTACK_2 = "ATTACK_2";
		public static final String HURT_1 = "HURT_1";
		public static final String HURT_2 = "HURT_2";
		public static final String DEATH = "DEATH";
		public static final String IDLE_1 = "IDLE_1";
		public static final String IDLE_2 = "IDLE_2";
		public static final String IDLE_3 = "IDLE_3";
		public static final String INTERACT = "INTERACT";
	}
}
