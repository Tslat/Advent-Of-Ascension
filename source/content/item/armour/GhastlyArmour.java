package net.tslat.aoa3.content.item.armour;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.tslat.aoa3.common.registration.item.AoAArmourMaterials;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.util.EntityRetrievalUtil;
import net.tslat.tme.api.object.builder.EffectBuilder;

import java.util.EnumSet;
import java.util.List;

public class GhastlyArmour extends AdventArmour {
	public GhastlyArmour(ArmorItem.Type slot) {
		super(AoAArmourMaterials.GHASTLY, slot, 62);
	}

	@Override
	public void onArmourTick(LivingEntity entity, EnumSet<Piece> equippedPieces) {
		if (entity.tickCount % 5 == 0 && entity instanceof Player pl ? pl.isShiftKeyDown() : entity.isCrouching())
			EntityUtil.applyPotions(EntityRetrievalUtil.getEntities(entity, perPieceValue(equippedPieces, 4), LivingEntity.class, target -> EntityUtil.areProbablyEnemies(target, entity)),
									entity,
									new EffectBuilder(MobEffects.GLOWING, 6).level(1).isAmbient().hideParticles());
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.ghastly_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(pieceEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.ghastly_armour.desc.2", LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}
