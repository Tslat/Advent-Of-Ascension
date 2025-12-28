package net.tslat.aoa3.content.item.weapon.greatblade;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.tslat.aoa3.common.registration.item.AoAWeapons;
import net.tslat.aoa3.content.item.weapon.thrown.Grenade;
import net.tslat.aoa3.util.InventoryUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;

import java.util.List;

public class BaronGreatblade extends AoAGreatblade {
	public BaronGreatblade(Tier tier, Item.Properties properties) {
		super(tier, properties);
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, LivingEntity target, LivingEntity attacker, float attackCooldown) {
		if (attackCooldown > 0.85f && attacker.level() instanceof ServerLevel level) {
			Grenade grenade = AoAWeapons.GRENADE.get();

			if (!(attacker instanceof Player pl) || InventoryUtil.findItemForConsumption(pl, grenade, pl.hasInfiniteMaterials() ? 0 : 1, true)) {
				ItemStack grenadeStack = grenade.getDefaultInstance();

				grenade.throwProjectile(level, grenade.createFiringContext(grenadeStack, attacker, InteractionHand.MAIN_HAND).build(), grenadeStack);
				ItemUtil.damageItemForUser(level, stack, attacker, EquipmentSlot.MAINHAND);
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
