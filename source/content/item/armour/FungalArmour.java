package net.tslat.aoa3.content.item.armour;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.tslat.aoa3.common.registration.item.AoAArmourMaterials;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.util.EntityRetrievalUtil;
import net.tslat.tme.api.util.RandomUtil;
import net.tslat.tme.api.object.builder.EffectBuilder;

import java.util.EnumSet;
import java.util.List;

public class FungalArmour extends AdventArmour {
	public FungalArmour(ArmorItem.Type slot) {
		super(AoAArmourMaterials.FUNGAL, slot, 50);
	}

	@Override
	public void afterTakingDamage(LivingEntity entity, EnumSet<Piece> equippedPieces, LivingDamageEvent.Post ev) {
		if (ev.getNewDamage() > 0 && DamageUtil.isMeleeDamage(ev.getSource()) && RandomUtil.percentChance(perPieceValue(equippedPieces, 0.2f))) {
			if (ev.getSource().getEntity() instanceof LivingEntity attacker)
				EntityUtil.applyPotions(attacker, entity, new EffectBuilder(MobEffects.POISON, 60).level(2).isAmbient());

			if (equippedPieces.contains(Piece.FULL_SET) && RandomUtil.oneInNChance(4)) {
				EntityUtil.applyPotions(EntityRetrievalUtil.getEntities(entity, 5, LivingEntity.class, target -> EntityUtil.areProbablyEnemies(target, entity)),
										entity, new EffectBuilder(MobEffects.POISON, 60).level(1).isAmbient());
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.fungal_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(pieceEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.fungal_armour.desc.2", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(setEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.fungal_armour.desc.3", LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}
