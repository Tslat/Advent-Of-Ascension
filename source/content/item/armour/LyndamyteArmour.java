package net.tslat.aoa3.content.item.armour;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.smartbrainlib.util.RandomUtil;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.List;

public class LyndamyteArmour extends AdventArmour {
	public LyndamyteArmour(ArmorItem.Type slot) {
		super(ItemUtil.customArmourMaterial("aoa3:lyndamyte", 35, new int[] {3, 6, 8, 3}, 10, SoundEvents.ARMOR_EQUIP_GENERIC, 2), slot);
	}

	@Override
	public Type getSetType() {
		return Type.LYNDAMYTE;
	}

	@Override
	public void onPostAttackReceived(ServerPlayerDataManager plData, @Nullable HashSet<EquipmentSlot> slots, LivingDamageEvent event) {
		if (slots != null && !plData.player().level().isClientSide && DamageUtil.isMeleeDamage(event.getSource()) && RandomUtil.percentChance(0.25f * slots.size()))
			event.getSource().getEntity().setSecondsOnFire(5);
	}

	@Override
	public void onDamageDealt(ServerPlayerDataManager plData, @Nullable HashSet<EquipmentSlot> slots, LivingHurtEvent event) {
		if (slots == null && DamageUtil.isMeleeDamage(event.getSource()))
			event.getSource().getEntity().setSecondsOnFire(5);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(pieceEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.lyndamyte_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(setEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.lyndamyte_armour.desc.2", LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}
