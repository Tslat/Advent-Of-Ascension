package net.tslat.aoa3.content.item.armour;

import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.item.AoAArmourMaterials;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.object.builder.EffectBuilder;

import java.util.EnumSet;
import java.util.List;
import java.util.function.BiConsumer;

public class KnightArmour extends AdventArmour {
	public static final AttributeModifier KNIGHT_BOOTS_BUFF = new AttributeModifier(AdventOfAscension.id("knight_armour_boots"), 5, AttributeModifier.Operation.ADD_VALUE);
	public static final AttributeModifier KNIGHT_LEGS_BUFF = new AttributeModifier(AdventOfAscension.id("knight_armour_leggings"), 5, AttributeModifier.Operation.ADD_VALUE);
	public static final AttributeModifier KNIGHT_CHESTPLATE_BUFF = new AttributeModifier(AdventOfAscension.id("knight_armour_chestplate"), 5, AttributeModifier.Operation.ADD_VALUE);
	public static final AttributeModifier KNIGHT_HELMET_BUFF = new AttributeModifier(AdventOfAscension.id("knight_armour_helmet"), 5, AttributeModifier.Operation.ADD_VALUE);

	public KnightArmour(ArmorItem.Type slot) {
		super(AoAArmourMaterials.KNIGHT, slot, 70);
	}

	@Override
	public void addArmourAttributes(EquipmentSlotGroup slot, BiConsumer<Holder<Attribute>, AttributeModifier> attributes) {
		switch (slot) {
			case FEET -> attributes.accept(Attributes.MAX_HEALTH, KNIGHT_BOOTS_BUFF);
			case LEGS -> attributes.accept(Attributes.MAX_HEALTH, KNIGHT_LEGS_BUFF);
			case CHEST -> attributes.accept(Attributes.MAX_HEALTH, KNIGHT_CHESTPLATE_BUFF);
			case HEAD -> attributes.accept(Attributes.MAX_HEALTH, KNIGHT_HELMET_BUFF);
			case null, default -> {}
		}
	}

	@Override
	public void onArmourTick(LivingEntity entity, EnumSet<Piece> equippedPieces) {
		if (equippedPieces.contains(Piece.FULL_SET) && entity.isAlive() && EntityUtil.getHealthPercent(entity) < 0.2f)
			EntityUtil.applyPotions(entity, entity, new EffectBuilder(MobEffects.DAMAGE_BOOST, 2).level(2));
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		tooltip.add(setEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.knight_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}
