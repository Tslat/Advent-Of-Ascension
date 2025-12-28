package net.tslat.aoa3.content.item.armour;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantments;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.item.AoAArmourMaterials;
import net.tslat.aoa3.util.AdvancementUtil;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.EnchantmentUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.util.RandomUtil;

import java.util.EnumSet;
import java.util.List;

public class RockboneArmour extends AdventArmour {
	public RockboneArmour(ArmorItem.Type slot) {
		super(AoAArmourMaterials.ROCKBONE, slot, 45);
	}

	@Override
	public void handleIncomingDamage(LivingEntity entity, EnumSet<Piece> equippedPieces, LivingIncomingDamageEvent ev) {
		if (DamageUtil.isRangedDamage(ev.getSource())) {
			if (equippedPieces.contains(Piece.FULL_SET) && RandomUtil.oneInNChance(10)) {
				ev.setCanceled(true);

				if (entity instanceof ServerPlayer pl) {
					for (ItemStack armour : pl.getArmorSlots()) {
						if (EnchantmentUtil.getEnchantmentLevel(pl.level(), armour, Enchantments.PROJECTILE_PROTECTION) < 4)
							return;
					}

					AdvancementUtil.grantCriterion(pl, AdventOfAscension.id("completionist/reverse_stormtrooper"), "max_dodge");
				}

				return;
			}

			ev.setAmount(ev.getAmount() * (1 - perPieceValue(equippedPieces, 0.1f)));
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		tooltip.add(pieceEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.rockbone_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(setEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.rockbone_armour.desc.2", LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}
