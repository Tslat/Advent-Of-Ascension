package net.tslat.aoa3.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.tslat.aoa3.player.ClientPlayerDataManager;
import net.tslat.aoa3.player.skill.AoASkill;

import javax.annotation.Nullable;

public final class LocaleUtil {
	public static ITextComponent getFormattedItemDescriptionText(Item item, ItemDescriptionType type, int descNumber, ITextComponent... args) {
		return getFormattedItemDescriptionText("item." + item.getRegistryName().getNamespace() + "." + item.getRegistryName().getPath() + ".desc." + descNumber, type, args);
	}


	public static ITextComponent getFormattedItemDescriptionText(String langKey, ItemDescriptionType type, ITextComponent... args) {
		return new TranslationTextComponent(langKey, (Object[])args).withStyle(type.format);
	}

	public static TranslationTextComponent getLocaleMessage(String langKey) {
		return getLocaleMessage(langKey, (TextFormatting)null);
	}

	public static TranslationTextComponent getLocaleMessage(String langKey, ITextComponent... args) {
		return getLocaleMessage(langKey, null, args);
	}

	public static TranslationTextComponent getLocaleMessage(String langKey, @Nullable TextFormatting format, ITextComponent... args) {
		TranslationTextComponent localeMessage = new TranslationTextComponent(langKey, (Object[])args);

		if (format != null)
			localeMessage.withStyle(format);

		return localeMessage;
	}

	public static StringTextComponent numToComponent(Number number) {
		return new StringTextComponent(String.valueOf(number));
	}

	@OnlyIn(Dist.CLIENT)
	public static String getLocaleString(String langKey) {
		return getLocaleString(langKey, (TextFormatting)null);
	}

	@OnlyIn(Dist.CLIENT)
	public static String getItemName(IItemProvider object) {
		return I18n.get(object.asItem().getDescriptionId());
	}

	@OnlyIn(Dist.CLIENT)
	public static String getLocaleString(String langKey, String... args) {
		return getLocaleString(langKey, null, args);
	}

	@OnlyIn(Dist.CLIENT)
	public static String getLocaleString(String langKey, @Nullable TextFormatting colour, String... args) {
		return (colour != null ? colour : "") + I18n.get(langKey, (Object[])args);
	}

	@OnlyIn(Dist.CLIENT)
	public static ITextComponent getFormattedLevelRestrictedDescriptionText(AoASkill skill, int levelReq) {
		ClientPlayerEntity player = Minecraft.getInstance().player;
		boolean meetsReq = (player != null && player.isCreative()) || ClientPlayerDataManager.getSkill(skill).hasLevel(levelReq);

		return getLocaleMessage("items.description.skillRequirement", meetsReq ? TextFormatting.GREEN : TextFormatting.RED, new StringTextComponent(Integer.toString(levelReq)), skill.getName());
	}

	public enum ItemDescriptionType {
		BENEFICIAL(TextFormatting.DARK_GREEN),
		HARMFUL(TextFormatting.RED),
		NEUTRAL(TextFormatting.DARK_GRAY),
		UNIQUE(TextFormatting.DARK_PURPLE),
		SPECIAL(TextFormatting.GOLD),
		ITEM_TYPE_INFO(TextFormatting.AQUA),
		ITEM_DAMAGE(TextFormatting.DARK_RED),
		ITEM_AMMO_COST(TextFormatting.LIGHT_PURPLE);

		public final TextFormatting format;

		ItemDescriptionType(TextFormatting format) {
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
