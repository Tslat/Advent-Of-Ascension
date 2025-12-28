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
import net.tslat.tme.api.util.RandomUtil;
import net.tslat.tme.api.object.builder.EffectBuilder;

import java.util.EnumSet;
import java.util.List;

public class WeakenArmour extends AdventArmour {
	public WeakenArmour(ArmorItem.Type slot) {
		super(AoAArmourMaterials.WEAKEN, slot, 44);
	}

	@Override
	public void afterTakingDamage(LivingEntity entity, EnumSet<Piece> equippedPieces, LivingDamageEvent.Post ev) {
		if (ev.getNewDamage() > 0 && RandomUtil.percentChance(perPieceValue(equippedPieces, 0.175f)) && DamageUtil.isMeleeDamage(ev.getSource()) && ev.getSource().getEntity() instanceof LivingEntity attacker)
			EntityUtil.applyPotions(entity, entity, new EffectBuilder(MobEffects.WEAKNESS, 60).level(equippedPieces.contains(Piece.FULL_SET) ? 2 : 1).isAmbient());
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		tooltip.add(pieceEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.weaken_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(setEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.weaken_armour.desc.2", LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}
