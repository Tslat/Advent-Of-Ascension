package net.tslat.aoa3.content.item.armour;

import net.minecraft.core.NonNullList;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.tslat.aoa3.common.registration.item.AoAEnchantments;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

public class NecroArmour extends AdventArmour {
	public NecroArmour(ArmorItem.Type slot) {
		super(ItemUtil.customArmourMaterial("aoa3:necro", 64, new int[] {5, 8, 9, 4}, 10, SoundEvents.ARMOR_EQUIP_GENERIC, 7), slot);
	}

	@Override
	public Type setType() {
		return Type.NECRO;
	}

	@Override
	public void onPostAttackReceived(ServerPlayerDataManager plData, @Nullable HashSet<EquipmentSlot> slots, LivingDamageEvent event) {
		if (slots == null && !DamageUtil.isEnvironmentalDamage(event.getSource()) && event.getAmount() > plData.player().getHealth() && plData.equipment().isCooledDown("necro_armour")) {
			Player pl = plData.player();

			event.setAmount(0);
			plData.equipment().setCooldown("necro_armour", 72000);
			pl.hurtArmor(pl.level.damageSources().generic(), 2000);

			if (pl.getHealth() < 4)
				pl.setHealth(4);

			((ServerLevel)pl.level).sendParticles(ParticleTypes.HEART, pl.getX(), pl.getBoundingBox().maxY, pl.getZ(), 5, 0, 0, 0, (double)0);
		}
	}

	@Override
	public void onPlayerDeath(ServerPlayerDataManager plData, @Nullable HashSet<EquipmentSlot> slots, LivingDeathEvent event) {
		if (slots != null) {
			int count = slots.size();
			int slotIndex = 0;
			Inventory inv = plData.player().getInventory();

			for (NonNullList<ItemStack> compartment : inv.compartments) {
				for (int i = 0; i < compartment.size(); i++) {
					ItemStack stack = compartment.get(i);

					if (!stack.isEmpty() && stack.getEnchantmentLevel(AoAEnchantments.INTERVENTION.get()) == 0 && stack.getEnchantmentLevel(Enchantments.VANISHING_CURSE) == 0) {
						plData.storeItem(slotIndex + i, stack);
						compartment.set(i, ItemStack.EMPTY);
						count--;

						if (count == 0)
							return;
					}
				}

				slotIndex += compartment.size();
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.necro_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.necro_armour.desc.2", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(pieceEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.necro_armour.desc.3", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(setEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.necro_armour.desc.4", LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}
