package net.tslat.aoa3.content.item.armour;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.tslat.aoa3.common.registration.item.AoAArmour;
import net.tslat.aoa3.common.registration.item.AoAArmourMaterials;
import net.tslat.aoa3.common.registration.item.AoAEnchantments;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.EnchantmentUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.tme.api.particle.ParticleBuilder;
import org.apache.commons.lang3.mutable.MutableInt;

import java.util.EnumSet;
import java.util.List;

public class NecroArmour extends AdventArmour {
	public NecroArmour(ArmorItem.Type slot) {
		super(AoAArmourMaterials.NECRO, slot, 64);
	}

	@Override
	public void beforeTakingDamage(LivingEntity entity, EnumSet<Piece> equippedPieces, LivingDamageEvent.Pre ev) {
		if (equippedPieces.contains(Piece.FULL_SET) && !DamageUtil.isEnvironmentalDamage(ev.getContainer().getSource()) && ev.getContainer().getNewDamage() >= entity.getHealth() && (!(entity instanceof Player pl) || !isOnCooldown(pl))) {
			ev.getContainer().setNewDamage(0);
			entity.hurtArmor(entity.level().damageSources().generic(), 2000);

			if (entity.getHealth() < 4)
				entity.setHealth(4);

			if (entity instanceof ServerPlayer pl)
				setArmourCooldown(pl, AoAArmour.NECRO_ARMOUR, 72000);

			ParticleBuilder.forRandomPosInEntity(ParticleTypes.HEART, entity)
					.spawnNTimes(5)
					.sendToAllPlayersTrackingEntity(entity);
		}
	}

	@Override
	public void onEntityDeath(LivingEntity entity, EnumSet<Piece> equippedPieces, LivingDeathEvent ev) {
		if (!(entity instanceof ServerPlayer pl))
			return;

		final ServerPlayerDataManager plData = PlayerUtil.getAdventPlayer(pl);
		final Level level = pl.level();
		final MutableInt count = new MutableInt(perPieceValue(equippedPieces, 1));

		if (count.intValue() > 0) {
			plData.storage.putItemsInStorage(stack -> {
				if (!EnchantmentUtil.hasEnchantment(level, stack, AoAEnchantments.INTERVENTION) && !EnchantmentUtil.hasEnchantment(level, stack, Enchantments.VANISHING_CURSE)) {
					count.decrement();

					return true;
				}

				return false;
			}, () -> count.intValue() > 0);
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.necro_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.necro_armour.desc.2", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(pieceEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.necro_armour.desc.3", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(setEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.necro_armour.desc.4", LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}
