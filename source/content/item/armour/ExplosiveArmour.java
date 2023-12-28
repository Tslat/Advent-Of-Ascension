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

public class ExplosiveArmour extends AdventArmour {
	public ExplosiveArmour(ArmorItem.Type slot) {
		super(ItemUtil.customArmourMaterial("aoa3:explosive", 48, new int[] {4, 7, 9, 3}, 10, SoundEvents.ARMOR_EQUIP_GENERIC, 3), slot);
	}

	@Override
	public void onPreAttackReceived(ServerPlayerDataManager plData, @Nullable HashSet<EquipmentSlot> slots, LivingAttackEvent event) {
		if (slots == null && event.getSource().is(DamageTypeTags.IS_EXPLOSION))
			event.setCanceled(true);
	}

	@Override
	public void onAttackReceived(ServerPlayerDataManager plData, @Nullable HashSet<EquipmentSlot> slots, LivingHurtEvent event) {
		if (slots != null && event.getSource().is(DamageTypeTags.IS_EXPLOSION))
			event.setAmount(event.getAmount() * (1 - 0.15f * slots.size()));
	}

	@Override
	public Type getSetType() {
		return Type.EXPLOSIVE;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(pieceEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.explosive_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(setEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.explosive_armour.desc.2", LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}
