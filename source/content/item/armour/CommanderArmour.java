package net.tslat.aoa3.content.item.armour;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.tslat.aoa3.common.registration.item.AoAArmourMaterials;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.tme.api.util.EntityRetrievalUtil;
import net.tslat.tme.api.object.builder.EffectBuilder;

import java.util.EnumSet;
import java.util.List;

public class CommanderArmour extends AdventArmour {
	public CommanderArmour(ArmorItem.Type slot) {
		super(AoAArmourMaterials.COMMANDER, slot, 62);
	}

	@Override
	public void onArmourTick(LivingEntity entity, EnumSet<Piece> equippedPieces) {
		if (entity.tickCount % 20 == 0) {
			EntityUtil.applyPotions(EntityRetrievalUtil.getEntities(entity, perPieceValue(equippedPieces, 2), LivingEntity.class, target -> target != entity && PlayerUtil.getPlayerOrOwnerIfApplicable(target) == entity),
									entity, new EffectBuilder(MobEffects.DAMAGE_BOOST, 25).level(equippedPieces.contains(Piece.FULL_SET) ? 2 : 1));
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.commander_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(pieceEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.commander_armour.desc.2", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(setEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.commander_armour.desc.3", LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}
