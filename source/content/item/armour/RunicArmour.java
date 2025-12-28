package net.tslat.aoa3.content.item.armour;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.tslat.aoa3.common.registration.item.AoAArmour;
import net.tslat.aoa3.common.registration.item.AoAArmourMaterials;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.object.builder.EffectBuilder;

import java.util.EnumSet;
import java.util.List;

public class RunicArmour extends AdventArmour {
	public RunicArmour(ArmorItem.Type slot) {
		super(AoAArmourMaterials.RUNIC, slot, 67);
	}

	@Override
	public void handleIncomingDamage(LivingEntity entity, EnumSet<Piece> equippedPieces, LivingIncomingDamageEvent ev) {
		if (DamageUtil.isMagicDamage(ev.getSource()))
			ev.setAmount(ev.getAmount() * (1 - perPieceValue(equippedPieces, 0.1f)));
	}

	@Override
	public void afterTakingDamage(LivingEntity entity, EnumSet<Piece> equippedPieces, LivingDamageEvent.Post ev) {
		if (equippedPieces.contains(Piece.FULL_SET) && (!(entity instanceof Player player) || !isOnCooldown(player)) && DamageUtil.isMagicDamage(ev.getSource())) {
			EntityUtil.applyPotions(entity, entity, new EffectBuilder(MobEffects.ABSORPTION, 200).hideParticles());

			if (entity instanceof Player player)
				setArmourCooldown(player, AoAArmour.RUNIC_ARMOUR, 6000);
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		tooltip.add(pieceEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.runic_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(setEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.runic_armour.desc.2", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.runic_armour.desc.3", LocaleUtil.ItemDescriptionType.NEUTRAL));
	}
}
