package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.player.PlayerDataManager;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

public class LyndamyteArmour extends AdventArmour {
	public LyndamyteArmour(EquipmentSlotType slot) {
		super(ItemUtil.customArmourMaterial("aoa3:lyndamyte", 35, new int[] {3, 6, 8, 3}, 10, SoundEvents.ARMOR_EQUIP_GENERIC, 2), slot);
	}

	@Override
	public AdventArmour.Type setType() {
		return AdventArmour.Type.LYNDAMYTE;
	}

	@Override
	public void onPostAttackReceived(PlayerDataManager plData, @Nullable HashSet<EquipmentSlotType> slots, LivingDamageEvent event) {
		if (slots != null && !plData.player().level.isClientSide && DamageUtil.isMeleeDamage(event.getSource()) && random.nextFloat() < 0.25f * slots.size())
			event.getSource().getEntity().setSecondsOnFire(5);
	}

	@Override
	public void onDamageDealt(PlayerDataManager plData, @Nullable HashSet<EquipmentSlotType> slots, LivingHurtEvent event) {
		if (slots == null && DamageUtil.isMeleeDamage(event.getSource()))
			event.getEntityLiving().setSecondsOnFire(5);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(pieceEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.lyndamyte_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(setEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.lyndamyte_armour.desc.2", LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}
