package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.tslat.aoa3.common.registration.AoAEnchantments;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.player.PlayerDataManager;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

public class NecroArmour extends AdventArmour {
	public NecroArmour(EquipmentSlotType slot) {
		super(ItemUtil.customArmourMaterial("aoa3:necro", 64, new int[] {5, 8, 9, 4}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 7), slot);
	}

	@Override
	public AdventArmour.Type setType() {
		return AdventArmour.Type.NECRO;
	}

	@Override
	public void onEffectTick(PlayerDataManager plData, @Nullable HashSet<EquipmentSlotType> slots) {
		super.onEffectTick(plData, slots);
	}

	@Override
	public void onPostAttackReceived(PlayerDataManager plData, @Nullable HashSet<EquipmentSlotType> slots, LivingDamageEvent event) {
		if (slots == null && !DamageUtil.isEnvironmentalDamage(event.getSource()) && event.getAmount() > plData.player().getHealth() && plData.equipment().isCooledDown("necro_armour")) {
			PlayerEntity pl = plData.player();

			event.setAmount(0);
			plData.equipment().setCooldown("necro_armour", 72000);
			pl.inventory.damageArmor(500);

			if (pl.getHealth() < 4)
				pl.setHealth(4);

			((ServerWorld)pl.world).spawnParticle(ParticleTypes.HEART, pl.getPosX(), pl.getBoundingBox().maxY, pl.getPosZ(), 5, 0, 0, 0, (double)0);
		}
	}

	@Override
	public void onPlayerDeath(PlayerDataManager plData, @Nullable HashSet<EquipmentSlotType> slots, LivingDeathEvent event) {
		if (slots != null) {
			int count = slots.size();
			int inventoryIndex = 0;
			PlayerInventory inv = plData.player().inventory;

			while (count > 0 && inventoryIndex < inv.getSizeInventory()) {
				ItemStack stack = inv.getStackInSlot(inventoryIndex);

				if (!stack.isEmpty() && EnchantmentHelper.getEnchantmentLevel(AoAEnchantments.INTERVENTION.get(), stack) == 0 && EnchantmentHelper.getEnchantmentLevel(Enchantments.VANISHING_CURSE, stack) == 0) {
					plData.storeInterventionItem(stack);
					inv.setInventorySlotContents(inventoryIndex, ItemStack.EMPTY);
					count--;
				}

				inventoryIndex++;
			}
		}
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.necro_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.necro_armour.desc.2", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(pieceEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.necro_armour.desc.3", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(setEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.necro_armour.desc.4", LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}
