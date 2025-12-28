package net.tslat.aoa3.content.item.armour;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.damagesource.DamageContainer;
import net.neoforged.neoforge.event.entity.EntityInvulnerabilityCheckEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.tslat.aoa3.common.registration.item.AoAArmourMaterials;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.object.builder.EffectBuilder;

import java.util.EnumSet;
import java.util.List;

public class WitherArmour extends AdventArmour {
	public WitherArmour(ArmorItem.Type slot) {
		super(AoAArmourMaterials.WITHER, slot, 53);
	}

	@Override
	public void checkDamageInvulnerability(LivingEntity entity, EnumSet<Piece> equippedPieces, EntityInvulnerabilityCheckEvent ev) {
		if (equippedPieces.contains(Piece.FULL_SET) && ev.getSource().is(Tags.DamageTypes.IS_WITHER)) {
			ev.setInvulnerable(true);
			EntityUtil.applyPotions(entity, entity, new EffectBuilder(MobEffects.DAMAGE_RESISTANCE, 60).isAmbient().hideEffectIcon());
		}
	}

	@Override
	public void handleIncomingDamage(LivingEntity entity, EnumSet<Piece> equippedPieces, LivingIncomingDamageEvent ev) {
		if (ev.getSource().is(Tags.DamageTypes.IS_WITHER))
			ev.addReductionModifier(DamageContainer.Reduction.ARMOR, (container, reduction) -> DamageUtil.percentDamageReduction(container, reduction, perPieceValue(equippedPieces, 0.25f)));
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		tooltip.add(pieceEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.wither_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(setEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.wither_armour.desc.2", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.wither_armour.desc.3", LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}
