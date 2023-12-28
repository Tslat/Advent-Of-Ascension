package net.tslat.aoa3.util;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.library.object.explosion.ExplosionInfo;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public final class LocaleUtil {
	public static Component getFormattedItemDescriptionText(Item item, ItemDescriptionType type, int descNumber, Component... args) {
		return getFormattedItemDescriptionText("item." + ForgeRegistries.ITEMS.getKey(item).getNamespace() + "." + ForgeRegistries.ITEMS.getKey(item).getPath() + ".desc." + descNumber, type, args);
	}

	public static MutableComponent getFormattedItemDescriptionText(String langKey, ItemDescriptionType type, Component... args) {
		return Component.translatable(langKey, (Object[])args).withStyle(type.format);
	}

	public static MutableComponent getLocaleMessage(String langKey) {
		return getLocaleMessage(langKey, (ChatFormatting)null);
	}

	public static MutableComponent getLocaleMessage(String langKey, Component... args) {
		return getLocaleMessage(langKey, null, args);
	}

	public static MutableComponent getLocaleMessage(String langKey, @Nullable ChatFormatting format, Component... args) {
		MutableComponent localeMessage = Component.translatable(langKey, (Object[])args);

		if (format != null)
			localeMessage.withStyle(format);

		return localeMessage;
	}

	public static MutableComponent numToComponent(Number number) {
		return Component.literal(String.valueOf(number));
	}

	public static String getLocaleString(String langKey) {
		return getLocaleString(langKey, null);
	}

	public static String getLocaleString(String langKey, @Nullable ChatFormatting colour, String... args) {
		MutableComponent component = Component.translatable(langKey, (Object[])args);

		if (colour != null)
			component.withStyle(colour);

		return component.getString();
	}

	public static MutableComponent getAbilityValueDesc(boolean flat, boolean scaling, boolean percent, Object flatArg, Object scalingArg, Object currentValueArg) {
		if (flat && scaling)
			return percent ? getPercentFlatAndScalingAbilityValueDesc(flatArg, scalingArg, currentValueArg) : getFlatAndScalingAbilityValueDesc(flatArg, scalingArg, currentValueArg);

		if (flat)
			return percent ? getPercentFlatAbilityValueDesc(flatArg) : getFlatAbilityValueDesc(flatArg);

		return percent ? getPercentScalingAbilityValueDesc(scalingArg, currentValueArg) : getScalingAbilityValueDesc(scalingArg, currentValueArg);
	}

	public static MutableComponent getScalingAbilityValueDesc(Object... args) {
		return Component.translatable("ability." + AdventOfAscension.MOD_ID + ".descriptions.scaling", args);
	}

	public static MutableComponent getFlatAbilityValueDesc(Object... args) {
		return Component.translatable("ability." + AdventOfAscension.MOD_ID + ".descriptions.flat", args);
	}

	public static MutableComponent getFlatAndScalingAbilityValueDesc(Object... args) {
		return Component.translatable("ability." + AdventOfAscension.MOD_ID + ".descriptions.flatAndScaling", args);
	}

	public static MutableComponent getPercentScalingAbilityValueDesc(Object... args) {
		return Component.translatable("ability." + AdventOfAscension.MOD_ID + ".descriptions.scaling.percent", args);
	}

	public static MutableComponent getPercentFlatAbilityValueDesc(Object... args) {
		return Component.translatable("ability." + AdventOfAscension.MOD_ID + ".descriptions.flat.percent", args);
	}

	public static MutableComponent getPercentFlatAndScalingAbilityValueDesc(Object... args) {
		return Component.translatable("ability." + AdventOfAscension.MOD_ID + ".descriptions.flatAndScaling.percent", args);
	}

	public static List<MutableComponent> getExplosionInfoLocale(ExplosionInfo info, boolean extendedInfo, boolean shrapnel) {
		if (extendedInfo) {
			List<MutableComponent> lines = new ObjectArrayList<>();

			lines.add(LocaleUtil.getLocaleMessage(LocaleUtil.createGenericLocaleKey("gui", "tooltip.aoaexplosion.penetration." + (shrapnel ? "shrapnel" : "concussive")), LocaleUtil.getLocaleMessage(NumberUtil.roundToNthDecimalPlace(info.getPenetrationPower(), 1))).setStyle(Style.EMPTY.withColor(ChatFormatting.GRAY)));
			lines.add(LocaleUtil.getLocaleMessage(LocaleUtil.createGenericLocaleKey("gui", "tooltip.aoaexplosion.type." + (shrapnel ? "shrapnel" : "concussive")), ChatFormatting.GRAY));
			lines.add(LocaleUtil.getLocaleMessage(LocaleUtil.createGenericLocaleKey("gui", "tooltip.aoaexplosion.radius"), Component.literal(NumberUtil.roundToNthDecimalPlace(info.getEffectiveRadius(), 1))).setStyle(Style.EMPTY.withColor(ChatFormatting.GRAY)));
			lines.add(LocaleUtil.getLocaleMessage(LocaleUtil.createGenericLocaleKey("gui", "tooltip.aoaexplosion.damage"), Component.literal(NumberUtil.roundToNthDecimalPlace(info.getBaseDamage(), 1))).setStyle(Style.EMPTY.withColor(ChatFormatting.GRAY)));
			lines.add(LocaleUtil.getLocaleMessage(LocaleUtil.createGenericLocaleKey("gui", "tooltip.aoaexplosion.heading"), ChatFormatting.DARK_RED));

			return lines;
		}
		else {
			return List.of(LocaleUtil.getLocaleMessage(LocaleUtil.createGenericLocaleKey("gui", "tooltip.aoaexplosion.basic"), Component.literal(NumberUtil.roundToNthDecimalPlace(info.getBaseDamage(), 1)), Component.literal(NumberUtil.roundToNthDecimalPlace(info.getEffectiveRadius(), 1))).setStyle(Style.EMPTY.withColor(ChatFormatting.DARK_RED)));
		}
	}

	public static String createGenericLocaleKey(String type, String subPath) {
		return type + "." + AdventOfAscension.MOD_ID + "." + subPath;
	}

	public static String createItemDescriptionLocaleKey(String subPath) {
		return createGenericLocaleKey("items", "description." + subPath);
	}

	public static String createDialogueLocaleKey(String subPath) {
		return createGenericLocaleKey("message", "dialogue." + subPath);
	}

	public static String createFeedbackLocaleKey(String subPath) {
		return createGenericLocaleKey("message", "feedback." + subPath);
	}

	public enum ItemDescriptionType {
		BENEFICIAL(ChatFormatting.DARK_GREEN),
		HARMFUL(ChatFormatting.RED),
		NEUTRAL(ChatFormatting.GRAY),
		UNIQUE(ChatFormatting.DARK_PURPLE),
		SPECIAL(ChatFormatting.GOLD),
		ITEM_TYPE_INFO(ChatFormatting.AQUA),
		ITEM_DAMAGE(ChatFormatting.DARK_RED),
		ITEM_AMMO_COST(ChatFormatting.LIGHT_PURPLE);

		public final ChatFormatting format;

		ItemDescriptionType(ChatFormatting format) {
			this.format = format;
		}
	}

	public static class Keys {
		public static final String ABYSS = LocaleUtil.createGenericLocaleKey("dimension", "abyss");
		public static final String BARATHOS = LocaleUtil.createGenericLocaleKey("dimension", "barathos");
		public static final String CANDYLAND = LocaleUtil.createGenericLocaleKey("dimension", "candyland");
		public static final String CELEVE = LocaleUtil.createGenericLocaleKey("dimension", "celeve");
		public static final String CREEPONIA = LocaleUtil.createGenericLocaleKey("dimension", "creeponia");
		public static final String CRYSTEVIA = LocaleUtil.createGenericLocaleKey("dimension", "crystevia");
		public static final String DEEPLANDS = LocaleUtil.createGenericLocaleKey("dimension", "deeplands");
		public static final String DUSTOPIA = LocaleUtil.createGenericLocaleKey("dimension", "dustopia");
		public static final String GARDENCIA = LocaleUtil.createGenericLocaleKey("dimension", "gardencia");
		public static final String GRECKON = LocaleUtil.createGenericLocaleKey("dimension", "greckon");
		public static final String HAVEN = LocaleUtil.createGenericLocaleKey("dimension", "haven");
		public static final String IROMINE = LocaleUtil.createGenericLocaleKey("dimension", "iromine");
		public static final String LBOREAN = LocaleUtil.createGenericLocaleKey("dimension", "lborean");
		public static final String LELYETIA = LocaleUtil.createGenericLocaleKey("dimension", "lelyetia");
		public static final String LUNALUS = LocaleUtil.createGenericLocaleKey("dimension", "lunalus");
		public static final String MYSTERIUM = LocaleUtil.createGenericLocaleKey("dimension", "mysterium");
		public static final String NETHER = LocaleUtil.createGenericLocaleKey("dimension", "nether");
		public static final String NOWHERE = LocaleUtil.createGenericLocaleKey("dimension", "nowhere");
		public static final String OVERWORLD = LocaleUtil.createGenericLocaleKey("dimension", "overworld");
		public static final String PRECASIA = LocaleUtil.createGenericLocaleKey("dimension", "precasia");
		public static final String RUNANDOR = LocaleUtil.createGenericLocaleKey("dimension", "runandor");
		public static final String SHYRELANDS = LocaleUtil.createGenericLocaleKey("dimension", "shyrelands");
		public static final String VOX_PONDS = LocaleUtil.createGenericLocaleKey("dimension", "vox_ponds");

		public static final String BURNS_TARGETS = LocaleUtil.createItemDescriptionLocaleKey("damage.fire");
		public static final String SLOWS_TARGETS = LocaleUtil.createItemDescriptionLocaleKey("damage.slow");
		public static final String FREEZES_TARGETS = LocaleUtil.createItemDescriptionLocaleKey("damage.freeze");
		public static final String POISONS_TARGETS = LocaleUtil.createItemDescriptionLocaleKey("damage.poison");
		public static final String WEAKENS_TARGETS = LocaleUtil.createItemDescriptionLocaleKey("damage.weak");
		public static final String WITHERS_TARGETS = LocaleUtil.createItemDescriptionLocaleKey("damage.wither");
		public static final String EXPLODES_ON_HIT = LocaleUtil.createItemDescriptionLocaleKey("damage.explosion");
		public static final String LEECHES_HEALTH = LocaleUtil.createItemDescriptionLocaleKey("damage.leechHealth");
		public static final String LEECHES_SPIRIT = LocaleUtil.createItemDescriptionLocaleKey("damage.leechSpirit");
		public static final String KNOCKBACK = LocaleUtil.createItemDescriptionLocaleKey("damage.knockback");
		public static final String SPEC_IMMUNE = LocaleUtil.createItemDescriptionLocaleKey("damage.specImmune");

		public static final String ARROW_DAMAGE = LocaleUtil.createItemDescriptionLocaleKey("damage.arrows");
		public static final String ENERGY_DAMAGE = LocaleUtil.createItemDescriptionLocaleKey("damage.energy");
		public static final String GUN_DAMAGE = LocaleUtil.createItemDescriptionLocaleKey("damage.gun");
		public static final String MAGIC_DAMAGE = LocaleUtil.createItemDescriptionLocaleKey("damage.magic");
		public static final String RANGED_DAMAGE = LocaleUtil.createItemDescriptionLocaleKey("damage.ranged");
		public static final String SHOTGUN_DAMAGE = LocaleUtil.createItemDescriptionLocaleKey("damage.shotgun");
		public static final String VULCANE_DAMAGE = LocaleUtil.createItemDescriptionLocaleKey("damage.vulcane");
		public static final String NO_DAMAGE = LocaleUtil.createItemDescriptionLocaleKey("damage.none");

		public static final String AMMO_ITEM = LocaleUtil.createItemDescriptionLocaleKey("ammo.item");
		public static final String AMMO_RESOURCE = LocaleUtil.createItemDescriptionLocaleKey("ammo.resource");
		public static final String ARMOUR_AIRTIGHT = LocaleUtil.createItemDescriptionLocaleKey("armour.airtight");
		public static final String ARMOUR_ANY_SET_HEADER = LocaleUtil.createItemDescriptionLocaleKey("armour.anySet");
		public static final String ARMOUR_PIECE_HEADER = LocaleUtil.createItemDescriptionLocaleKey("armour.piece");
		public static final String ARMOUR_SET_HEADER = LocaleUtil.createItemDescriptionLocaleKey("armour.set");
		public static final String BLASTER_CHARGE = LocaleUtil.createItemDescriptionLocaleKey("blaster.charge");
		public static final String BLASTER_PENETRATION = LocaleUtil.createItemDescriptionLocaleKey("blaster.penetration");
		public static final String BOW_DRAW_TIME = LocaleUtil.createItemDescriptionLocaleKey("bow.drawTime");
		public static final String CANNON_ARMOUR_DAMAGE = LocaleUtil.createItemDescriptionLocaleKey("cannon.armourDamage");
		public static final String FIRING_SPEED = LocaleUtil.createItemDescriptionLocaleKey("gun.firingSpeed");
		public static final String FULLY_AUTOMATIC_GUN = LocaleUtil.createItemDescriptionLocaleKey("gun.fullyAutomatic");
		public static final String HEALING_FOOD_AMOUNT = LocaleUtil.createItemDescriptionLocaleKey("healingFood.desc.2");
		public static final String HEALING_FOOD_DESCRIPTION = LocaleUtil.createItemDescriptionLocaleKey("healingFood.desc.1");
		public static final String INFUSION_BOWL_DESCRIPTION = LocaleUtil.createItemDescriptionLocaleKey("infusionBowl.desc");
		public static final String RANDOM_DAMAGE = LocaleUtil.createItemDescriptionLocaleKey("damage.random");
		public static final String SEMI_AUTOMATIC_GUN = LocaleUtil.createItemDescriptionLocaleKey("gun.semiAutomatic");
		public static final String SKILL_CRYSTAL_DESCRIPTION = LocaleUtil.createItemDescriptionLocaleKey("skillCrystal.desc.1");
		public static final String SKILL_CRYSTAL_SKILL_THRESHOLD = LocaleUtil.createItemDescriptionLocaleKey("skillCrystal.desc.2");
		public static final String SKILL_REQUIREMENT = LocaleUtil.createItemDescriptionLocaleKey("skillRequirement");
		public static final String SNIPER_CROUCH = LocaleUtil.createItemDescriptionLocaleKey("sniper.crouch");
		public static final String STAFF_RUNE_COST = LocaleUtil.createItemDescriptionLocaleKey("staff.runesRequired");
		public static final String STAFF_RUNE_COST_LINE = LocaleUtil.createItemDescriptionLocaleKey("staff.runesRequired.specific");
		public static final String STICKLER_DESCRIPTION_1 = LocaleUtil.createItemDescriptionLocaleKey("stickler.desc.1");
		public static final String STICKLER_DESCRIPTION_2 = LocaleUtil.createItemDescriptionLocaleKey("stickler.desc.2");
		public static final String THROWN_WEAPON = LocaleUtil.createItemDescriptionLocaleKey("thrownWeapon");
		public static final String THROWN_WEAPON_RATE = LocaleUtil.createItemDescriptionLocaleKey("thrownWeapon.throwRate");
		public static final String UNBREAKABLE = "item.unbreakable";
		public static final String VULCANE_COST = LocaleUtil.createItemDescriptionLocaleKey("vulcane.cost");
		public static final String VULCANE_GRACE_PERIOD = LocaleUtil.createItemDescriptionLocaleKey("vulcane.gracePeriod");
		public static final String XP_BONUS = LocaleUtil.createItemDescriptionLocaleKey("skillXpBonus");
		public static final String SKELETAL_TOOL_DESCRIPTION = LocaleUtil.createItemDescriptionLocaleKey("skeletalTool.desc");
		public static final String ENERGISTIC_TOOL_CHARGE = LocaleUtil.createItemDescriptionLocaleKey("energisticTool.charge");
		public static final String ENERGISTIC_TOOL_STORED = LocaleUtil.createItemDescriptionLocaleKey("energisticTool.stored");
		public static final String GOOFY_TOOL_REGEN = LocaleUtil.createItemDescriptionLocaleKey("goofyTool.regen");
	}
}
