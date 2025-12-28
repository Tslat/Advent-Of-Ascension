package net.tslat.aoa3.content.item.armour;

import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.tslat.aoa3.common.registration.item.AoAArmourMaterials;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.object.builder.EffectBuilder;

import java.util.EnumSet;
import java.util.List;

public class BiogenicArmour extends AdventArmour {
	public BiogenicArmour(ArmorItem.Type slot) {
		super(AoAArmourMaterials.BIOGENIC, slot, 38);
	}

	@Override
	public void onArmourTick(LivingEntity entity, EnumSet<Piece> equippedPieces) {
		if (equippedPieces.contains(Piece.FULL_SET)) {
			if (entity.isInWater())
				entity.setAirSupply(-10);

			if (entity.isEyeInFluidType(NeoForgeMod.WATER_TYPE.value())) {
				EntityUtil.applyPotions(entity, entity, new EffectBuilder(MobEffects.NIGHT_VISION, 300).isAmbient().hideParticles());
			}
			else {
				MobEffectInstance nightVision = entity.getEffect(MobEffects.NIGHT_VISION);

				if (nightVision != null && nightVision.getDuration() <= 300)
					entity.removeEffect(MobEffects.NIGHT_VISION);
			}
		}
	}

	@Override
	public void afterTakingDamage(LivingEntity entity, EnumSet<Piece> equippedPieces, LivingDamageEvent.Post ev) {
		if (ev.getNewDamage() > 0 && DamageUtil.isMeleeDamage(ev.getSource()) && ev.getSource().getEntity() instanceof LivingEntity attacker)
			EntityUtil.applyPotions(attacker, entity, new EffectBuilder(MobEffects.MOVEMENT_SLOWDOWN, Mth.ceil(ev.getNewDamage() * perPieceValue(equippedPieces, 3))).level(equippedPieces.size() >= 2 ? 2 : 1));
	}

	@Override
	public void onUnequip(LivingEntity entity, Piece piece, EnumSet<Piece> equippedPieces) {
		if (piece == Piece.FULL_SET) {
			MobEffectInstance nightVision = entity.getEffect(MobEffects.NIGHT_VISION);

			if (nightVision != null && nightVision.getDuration() <= 300)
				entity.removeEffect(MobEffects.NIGHT_VISION);
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.biogenic_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(pieceEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.biogenic_armour.desc.2", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(setEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.biogenic_armour.desc.3", LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}
