package net.tslat.aoa3.content.item.armour;

import net.minecraft.network.chat.Component;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.common.damagesource.DamageContainer;
import net.neoforged.neoforge.event.entity.EntityInvulnerabilityCheckEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.item.AoAArmourMaterials;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.object.extension.Text;

import java.util.EnumSet;
import java.util.List;

public class AlacrityArmour extends AdventArmour {
	public AlacrityArmour(ArmorItem.Type slot) {
		super(AoAArmourMaterials.ALACRITY, slot, 55);
	}

	@Override
	public void checkDamageInvulnerability(LivingEntity entity, EnumSet<Piece> equippedPieces, EntityInvulnerabilityCheckEvent ev) {
		if (equippedPieces.contains(Piece.FULL_SET) && ev.getSource().is(DamageTypeTags.IS_FALL))
			ev.setInvulnerable(true);
	}

	@Override
	public void handleIncomingDamage(LivingEntity entity, EnumSet<Piece> equippedPieces, LivingIncomingDamageEvent ev) {
		if (!equippedPieces.contains(Piece.FULL_SET))
			ev.addReductionModifier(DamageContainer.Reduction.ARMOR, (container, reduction) -> DamageUtil.percentDamageReduction(container, reduction, equippedPieces.size() * 0.2f));
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		tooltip.add(pieceEffectHeader());
		tooltip.add(Text.of(LocaleUtil.itemDescKey(AdventOfAscension.id("alacrity_armour"), 1), LocaleUtil.ItemDescriptionType.BENEFICIAL.format));
		tooltip.add(setEffectHeader());
		tooltip.add(Text.of(LocaleUtil.itemDescKey(AdventOfAscension.id("alacrity_armour"), 2), LocaleUtil.ItemDescriptionType.BENEFICIAL.format));
	}
}
