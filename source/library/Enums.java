package net.tslat.aoa3.library;

import net.minecraft.client.audio.MusicTicker;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.EnumHelperClient;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.SoundsRegister;

import javax.annotation.Nullable;

public class Enums {
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
		ANCIENTCAVERN,
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
		PRECASIA,
		RUNANDOR,
		SHYRELANDS,
		VOXPONDS
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

	public class RGBIntegers {
		public static final int BLACK = 0;
		public static final int BLUE = 255;
		public static final int BONE = 16448150;
		public static final int BRIGHT_TURQUOISE = 58861;
		public static final int BROWN = 9593401;
		public static final int CYAN = 65535;
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

	public class AttributeUUIDS {
		public static final String MAX_HEALTH = "00e6648a-d6ee-4894-95e3-f9668d58339d";
		public static final String MOVEMENT_SPEED = "a1371c64-c09e-4ed6-adfd-5afbaea79369";
		public static final String ATTACK_SPEED_MAINHAND = "99fdc256-279e-4c8e-b1c6-9209571f134e";
		public static final String ATTACK_SPEED_OFFHAND = "63f030a6-7269-444d-b26c-ae3514b36e1b";
		public static final String VANILLA_ATTACK_SPEED = "FA233E1C-4180-4865-B01B-BCCE9785ACA3";
		public static final String MOB_BLOODTHIRST_BUFF = "2803f9b4-57ed-471f-8a0e-7a41fa100608";
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
		HUNTER_ENTITY,
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
		ENERGY,
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
		TRADER_PORTAL_MASTER(14),
		TRADER_PRIMORDIAL_BANKER(15),
		TRADER_PRIMORDIAL_MERCHANT(16),
		TRADER_PRIMORDIAL_SPELLBINDER(17),
		TRADER_PRIMORDIAL_WIZARD(18),
		TRADER_PROFESSOR(19),
		TRADER_REALMSHIFTER(20),
		TRADER_SHYRE_ARCHER(21),
		TRADER_SHYRE_BANKER(22),
		TRADER_SKILL_MASTER(23),
		TRADER_SOUL_AGENT(24),
		TRADER_STORE_KEEPER(25),
		TRADER_TOKEN_COLLECTOR(26),
		TRADER_TOY_MERCHANT(27),
		TRADER_TROLL_TRADER(28),
		TRADER_ZAL_BANKER(29),
		TRADER_ZAL_GROCER(30),
		TRADER_ZAL_HERBALIST(31),
		TRADER_ZAL_SPELLBINDER(32),
		TRADER_ZAL_VENDOR(33),
		ADVENT_MAIN_WINDOW(100);

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
					return TRADER_PORTAL_MASTER;
				case 15:
					return TRADER_PRIMORDIAL_BANKER;
				case 16:
					return TRADER_PRIMORDIAL_MERCHANT;
				case 17:
					return TRADER_PRIMORDIAL_SPELLBINDER;
				case 18:
					return TRADER_PRIMORDIAL_WIZARD;
				case 19:
					return TRADER_PROFESSOR;
				case 20:
					return TRADER_REALMSHIFTER;
				case 21:
					return TRADER_SHYRE_ARCHER;
				case 22:
					return TRADER_SHYRE_BANKER;
				case 23:
					return TRADER_SKILL_MASTER;
				case 24:
					return TRADER_SOUL_AGENT;
				case 25:
					return TRADER_STORE_KEEPER;
				case 26:
					return TRADER_TOKEN_COLLECTOR;
				case 27:
					return TRADER_TOY_MERCHANT;
				case 28:
					return TRADER_TROLL_TRADER;
				case 29:
					return TRADER_ZAL_BANKER;
				case 30:
					return TRADER_ZAL_GROCER;
				case 31:
					return TRADER_ZAL_HERBALIST;
				case 32:
					return TRADER_ZAL_SPELLBINDER;
				case 33:
					return TRADER_ZAL_VENDOR;
				case 100:
					return ADVENT_MAIN_WINDOW;
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
		Hell
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
}
