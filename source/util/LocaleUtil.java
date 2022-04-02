package net.tslat.aoa3.util;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.player.ClientPlayerDataManager;
import net.tslat.aoa3.player.skill.AoASkill;

import javax.annotation.Nullable;

public final class LocaleUtil {
	public static Component getFormattedItemDescriptionText(Item item, ItemDescriptionType type, int descNumber, TextComponent... args) {
		return getFormattedItemDescriptionText("item." + item.getRegistryName().getNamespace() + "." + item.getRegistryName().getPath() + ".desc." + descNumber, type, args);
	}

	public static Component getFormattedItemDescriptionText(String langKey, ItemDescriptionType type, Component... args) {
		return new TranslatableComponent(langKey, (Object[])args).withStyle(type.format);
	}

	public static TranslatableComponent getLocaleMessage(String langKey) {
		return getLocaleMessage(langKey, (ChatFormatting)null);
	}

	public static TranslatableComponent getLocaleMessage(String langKey, Component... args) {
		return getLocaleMessage(langKey, null, args);
	}

	public static TranslatableComponent getLocaleMessage(String langKey, @Nullable ChatFormatting format, Component... args) {
		TranslatableComponent localeMessage = new TranslatableComponent(langKey, (Object[])args);

		if (format != null)
			localeMessage.withStyle(format);

		return localeMessage;
	}

	public static TextComponent numToComponent(Number number) {
		return new TextComponent(String.valueOf(number));
	}

	@OnlyIn(Dist.CLIENT)
	public static String getLocaleString(String langKey) {
		return getLocaleString(langKey, (ChatFormatting)null);
	}

	@OnlyIn(Dist.CLIENT)
	public static String getItemName(ItemLike object) {
		return I18n.get(object.asItem().getDescriptionId());
	}

	@OnlyIn(Dist.CLIENT)
	public static String getLocaleString(String langKey, String... args) {
		return getLocaleString(langKey, null, args);
	}

	@OnlyIn(Dist.CLIENT)
	public static String getLocaleString(String langKey, @Nullable ChatFormatting colour, String... args) {
		return (colour != null ? colour : "") + I18n.get(langKey, (Object[])args);
	}

	@OnlyIn(Dist.CLIENT)
	public static Component getFormattedLevelRestrictedDescriptionText(AoASkill skill, int levelReq) {
		LocalPlayer player = Minecraft.getInstance().player;
		boolean meetsReq = (player != null && player.isCreative()) || ClientPlayerDataManager.get().getSkill(skill).hasLevel(levelReq);

		return getLocaleMessage("items.description.skillRequirement", meetsReq ? ChatFormatting.GREEN : ChatFormatting.RED, new TextComponent(Integer.toString(levelReq)), skill.getName());
	}

	public static TranslatableComponent getAbilityValueDesc(boolean flat, boolean scaling, boolean percent, Object flatArg, Object scalingArg, Object currentValueArg) {
		if (flat && scaling)
			return percent ? getPercentFlatAndScalingAbilityValueDesc(flatArg, scalingArg, currentValueArg) : getFlatAndScalingAbilityValueDesc(flatArg, scalingArg, currentValueArg);

		if (flat)
			return percent ? getPercentFlatAbilityValueDesc(flatArg) : getFlatAbilityValueDesc(flatArg);

		return percent ? getPercentScalingAbilityValueDesc(scalingArg, currentValueArg) : getScalingAbilityValueDesc(scalingArg, currentValueArg);
	}

	public static TranslatableComponent getScalingAbilityValueDesc(Object... args) {
		return new TranslatableComponent("ability." + AdventOfAscension.MOD_ID + ".descriptions.scaling", args);
	}

	public static TranslatableComponent getFlatAbilityValueDesc(Object... args) {
		return new TranslatableComponent("ability." + AdventOfAscension.MOD_ID + ".descriptions.flat", args);
	}

	public static TranslatableComponent getFlatAndScalingAbilityValueDesc(Object... args) {
		return new TranslatableComponent("ability." + AdventOfAscension.MOD_ID + ".descriptions.flatAndScaling", args);
	}

	public static TranslatableComponent getPercentScalingAbilityValueDesc(Object... args) {
		return new TranslatableComponent("ability." + AdventOfAscension.MOD_ID + ".descriptions.scaling.percent", args);
	}

	public static TranslatableComponent getPercentFlatAbilityValueDesc(Object... args) {
		return new TranslatableComponent("ability." + AdventOfAscension.MOD_ID + ".descriptions.flat.percent", args);
	}

	public static TranslatableComponent getPercentFlatAndScalingAbilityValueDesc(Object... args) {
		return new TranslatableComponent("ability." + AdventOfAscension.MOD_ID + ".descriptions.flatAndScaling.percent", args);
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

	public static class Constants {
		public static final String ABYSS = "dimension.aoa3.abyss";
		public static final String BARATHOS = "dimension.aoa3.barathos";
		public static final String CANDYLAND = "dimension.aoa3.candyland";
		public static final String CELEVE = "dimension.aoa3.celeve";
		public static final String CREEPONIA = "dimension.aoa3.creeponia";
		public static final String CRYSTEVIA = "dimension.aoa3.crystevia";
		public static final String DEEPLANDS = "dimension.aoa3.deeplands";
		public static final String DUSTOPIA = "dimension.aoa3.dustopia";
		public static final String GARDENCIA = "dimension.aoa3.gardencia";
		public static final String GRECKON = "dimension.aoa3.greckon";
		public static final String HAVEN = "dimension.aoa3.haven";
		public static final String IROMINE = "dimension.aoa3.iromine";
		public static final String LBOREAN = "dimension.aoa3.lborean";
		public static final String LELYETIA = "dimension.aoa3.lelyetia";
		public static final String LUNALUS = "dimension.aoa3.lunalus";
		public static final String MYSTERIUM = "dimension.aoa3.mysterium";
		public static final String NETHER = "dimension.aoa3.nether";
		public static final String NOWHERE = "dimension.aoa3.nowhere";
		public static final String OVERWORLD = "dimension.aoa3.overworld";
		public static final String PRECASIA = "dimension.aoa3.precasia";
		public static final String RUNANDOR = "dimension.aoa3.runandor";
		public static final String SHYRELANDS = "dimension.aoa3.shyrelands";
		public static final String VOX_PONDS = "dimension.aoa3.vox_ponds";

		public static final String BURNS_TARGETS = "items.description.damage.fire";
		public static final String SLOWS_TARGETS = "items.description.damage.slow";
		public static final String POISONS_TARGETS = "items.description.damage.poison";
		public static final String WEAKENS_TARGETS = "items.description.damage.weak";
		public static final String WITHERS_TARGETS = "items.description.damage.wither";
		public static final String EXPLODES_ON_HIT = "items.description.damage.explosion";
		public static final String LEECHES_HEALTH = "items.description.damage.leech";
		public static final String KNOCKBACK = "items.description.damage.knockback";
		public static final String SPEC_IMMUNE = "items.description.damage.specImmune";

		public static final String AMMO_RESOURCE = "items.description.ammo.resource";
		public static final String AMMO_ITEM = "items.description.ammo.item";
		public static final String FIRING_SPEED = "items.description.gun.firingSpeed";
		public static final String RANDOM_DAMAGE = "items.description.damage.random";
		public static final String ARMOUR_SET_HEADER = "items.description.armour.set";
		public static final String ARMOUR_PIECE_HEADER = "items.description.armour.piece";
		public static final String ARMOUR_ANY_SET_HEADER = "items.description.armour.anySet";
		public static final String SKILL_REQUIREMENT = "items.description.skillRequirement";
		public static final String XP_BONUS = "items.description.skillXpBonus";
	}
}
