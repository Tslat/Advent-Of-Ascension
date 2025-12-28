package net.tslat.aoa3.content.item.armour;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.tslat.aoa3.common.registration.item.AoAArmourMaterials;
import net.tslat.aoa3.content.item.weapon.gun.AoAGun;
import net.tslat.aoa3.content.item.weapon.sniper.AoASniper;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.LocaleUtil;

import java.util.EnumSet;
import java.util.List;

public class SharpshotArmour extends AdventArmour {
	public SharpshotArmour(ArmorItem.Type slot) {
		super(AoAArmourMaterials.SHARPSHOT, slot, 54);
	}

	@Override
	public void handleOutgoingAttack(LivingEntity entity, EnumSet<Piece> equippedPieces, LivingIncomingDamageEvent ev) {
		Item gun;

		if (DamageUtil.isGunDamage(ev.getSource()) && ((gun = entity.getMainHandItem().getItem()) instanceof AoAGun || (gun = entity.getOffhandItem().getItem()) instanceof AoAGun)) {
			float mod = equippedPieces.contains(Piece.FULL_SET) && gun instanceof AoASniper ? 1.38f : (1 + perPieceValue(equippedPieces, 0.07f));

			ev.setAmount(ev.getAmount() * mod);
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		tooltip.add(pieceEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.sharpshot_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(setEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.sharpshot_armour.desc.2", LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}
