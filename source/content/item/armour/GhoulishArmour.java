package net.tslat.aoa3.content.item.armour;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.List;

public class GhoulishArmour extends AdventArmour {
	public GhoulishArmour(ArmorItem.Type slot) {
		super(ItemUtil.customArmourMaterial("aoa3:ghoulish", 61, new int[] {6, 6, 8, 6}, 10, SoundEvents.ARMOR_EQUIP_GENERIC, 7), slot);
	}

	@Override
	public Type getSetType() {
		return Type.GHOULISH;
	}

	@Override
	public void onDamageDealt(ServerPlayerDataManager plData, @Nullable HashSet<EquipmentSlot> slots, LivingHurtEvent event) {
		if (slots != null && DamageUtil.isEnergyDamage(event.getSource()))
			event.setAmount(event.getAmount() * (1 + (slots.size() * 0.1f)));
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(pieceEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.ghoulish_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(setEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.ghoulish_armour.desc.2", LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}
