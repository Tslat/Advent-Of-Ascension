package net.tslat.aoa3.content.item.armour;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.tslat.aoa3.common.registration.item.AoAArmourMaterials;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.util.RandomUtil;

import java.util.EnumSet;
import java.util.List;

public class LyndamyteArmour extends AdventArmour {
	public LyndamyteArmour(ArmorItem.Type slot) {
		super(AoAArmourMaterials.LYNDAMYTE, slot, 35);
	}

	@Override
	public void afterTakingDamage(LivingEntity entity, EnumSet<Piece> equippedPieces, LivingDamageEvent.Post ev) {
		if (ev.getNewDamage() > 0 && !entity.level().isClientSide && DamageUtil.isMeleeDamage(ev.getSource()) && RandomUtil.percentChance(perPieceValue(equippedPieces, 0.25f)))
			ev.getSource().getEntity().igniteForSeconds(5);
	}

	@Override
	public void handleOutgoingAttack(LivingEntity entity, EnumSet<Piece> equippedPieces, LivingIncomingDamageEvent ev) {
		if (equippedPieces.contains(Piece.FULL_SET) && DamageUtil.isMeleeDamage(ev.getSource()))
			ev.getEntity().igniteForSeconds(5);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		tooltip.add(pieceEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.lyndamyte_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(setEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.lyndamyte_armour.desc.2", LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}
