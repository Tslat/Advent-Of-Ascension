package net.tslat.aoa3.content.item.armour;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.item.AoAArmourMaterials;
import net.tslat.aoa3.util.AttributeUtil;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.object.builder.EffectBuilder;

import java.util.EnumSet;
import java.util.List;

public class SubterraneanArmour extends AdventArmour {
	private static final AttributeModifier ATTACK_SPEED_DEBUFF = new AttributeModifier(AdventOfAscension.id("subterranean_armor_debuff"), -0.16666667, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);

	public SubterraneanArmour(ArmorItem.Type slot) {
		super(AoAArmourMaterials.SUBTERRANEAN, slot, 47);
	}

	@Override
	public void onEquip(LivingEntity entity, Piece piece, EnumSet<Piece> equippedPieces) {
		if (piece == Piece.FULL_SET)
			AttributeUtil.applyTransientModifier(entity, Attributes.ATTACK_SPEED, ATTACK_SPEED_DEBUFF);
	}

	@Override
	public void onUnequip(LivingEntity entity, Piece piece, EnumSet<Piece> equippedPieces) {
		if (piece == Piece.FULL_SET)
			AttributeUtil.removeModifier(entity, Attributes.ATTACK_SPEED, ATTACK_SPEED_DEBUFF);
	}

	@Override
	public void onArmourTick(LivingEntity entity, EnumSet<Piece> equippedPieces) {
		if (equippedPieces.contains(Piece.FULL_SET))
			EntityUtil.applyPotions(entity, entity, new EffectBuilder(MobEffects.DIG_SPEED, 2).level(2).hideParticles());
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		tooltip.add(setEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.subterranean_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}
