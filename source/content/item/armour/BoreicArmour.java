package net.tslat.aoa3.content.item.armour;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.tslat.aoa3.common.registration.AoAExplosions;
import net.tslat.aoa3.common.registration.item.AoAArmourMaterials;
import net.tslat.aoa3.library.builder.AoAExplosionBuilder;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.explosion.StandardExplosion;
import net.tslat.tme.api.object.builder.EffectBuilder;
import net.tslat.tme.api.util.EntityRetrievalUtil;

import java.util.EnumSet;
import java.util.List;

public class BoreicArmour extends AdventArmour {
	public BoreicArmour(ArmorItem.Type slot) {
		super(AoAArmourMaterials.BOREIC, slot, 62);
	}

	@Override
	public void afterTakingDamage(LivingEntity entity, EnumSet<Piece> equippedPieces, LivingDamageEvent.Post ev) {
		if (ev.getNewDamage() > 0 && entity.isInWater() && !DamageUtil.isEnvironmentalDamage(ev.getSource())) {
			AoAExplosionBuilder.at(entity, AoAExplosions.boreicArmour(equippedPieces.size()), StandardExplosion::new).explode();

			if (equippedPieces.contains(Piece.FULL_SET)) {
				EntityUtil.applyPotions(EntityRetrievalUtil.getEntities(entity, 4, LivingEntity.class, target -> EntityUtil.areProbablyEnemies(target, entity)),
										entity, new EffectBuilder(MobEffects.MOVEMENT_SLOWDOWN, 40).level(2).isAmbient());
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.boreic_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(pieceEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.boreic_armour.desc.2", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(setEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.boreic_armour.desc.3", LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}
