package net.tslat.aoa3.content.item.armour;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.List;

public class InfernalArmour extends AdventArmour {
	public InfernalArmour(ArmorItem.Type slot) {
		super(ItemUtil.customArmourMaterial("aoa3:infernal", 39, new int[] {4, 7, 8, 3}, 10, SoundEvents.ARMOR_EQUIP_GENERIC, 3), slot);
	}

	@Override
	public Type getSetType() {
		return Type.INFERNAL;
	}

	@Override
	public void onAttackReceived(ServerPlayerDataManager plData, @Nullable HashSet<EquipmentSlot> slots, LivingHurtEvent event) {
		if (event.getSource().is(DamageTypeTags.IS_FIRE) && slots != null)
			event.setAmount(event.getAmount() *  (1 - 0.15f * slots.size()));
	}

	@Override
	public void onPreAttackReceived(ServerPlayerDataManager plData, @Nullable HashSet<EquipmentSlot> slots, LivingAttackEvent event) {
		if (event.getSource().is(DamageTypeTags.IS_FIRE) && slots == null)
			event.setCanceled(true);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(pieceEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.infernal_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(setEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.infernal_armour.desc.2", LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}
