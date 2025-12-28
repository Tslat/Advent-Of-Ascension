package net.tslat.aoa3.content.item.armour;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.common.damagesource.DamageContainer;
import net.neoforged.neoforge.event.entity.EntityInvulnerabilityCheckEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import net.tslat.aoa3.common.registration.item.AoAArmourMaterials;
import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.object.builder.EffectBuilder;

import java.util.EnumSet;
import java.util.List;

public class PoisonArmour extends AdventArmour {
	public PoisonArmour(ArmorItem.Type slot) {
		super(AoAArmourMaterials.POISON, slot, 56);
	}

	@Override
	public void onEffectApplication(LivingEntity entity, EnumSet<Piece> equippedPieces, MobEffectEvent.Applicable ev) {
		if (equippedPieces.contains(Piece.FULL_SET) && ev.getEffectInstance().getEffect().is(MobEffects.POISON)) {
			ev.setResult(MobEffectEvent.Applicable.Result.DO_NOT_APPLY);

			if (!entity.level().isClientSide)
				AoAScheduler.schedule(1, tick -> EntityUtil.applyPotions(entity, entity, new EffectBuilder(MobEffects.DAMAGE_RESISTANCE, 60).isAmbient().hideEffectIcon()));
		}
	}

	@Override
	public void checkDamageInvulnerability(LivingEntity entity, EnumSet<Piece> equippedPieces, EntityInvulnerabilityCheckEvent ev) {
		if (equippedPieces.contains(Piece.FULL_SET) && DamageUtil.isPoisonDamage(ev.getSource()))
			ev.setInvulnerable(true);
	}

	@Override
	public void handleIncomingDamage(LivingEntity entity, EnumSet<Piece> equippedPieces, LivingIncomingDamageEvent ev) {
		if (DamageUtil.isPoisonDamage(ev.getSource()))
			ev.addReductionModifier(DamageContainer.Reduction.ARMOR, (container, reduction) -> DamageUtil.percentDamageReduction(container, reduction, perPieceValue(equippedPieces, 0.25f)));
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		tooltip.add(pieceEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.poison_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(setEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.poison_armour.desc.2", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.poison_armour.desc.3", LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}
