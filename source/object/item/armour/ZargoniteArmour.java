package net.tslat.aoa3.object.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.player.ServerPlayerDataManager;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

public class ZargoniteArmour extends AdventArmour {
	public ZargoniteArmour(EquipmentSlotType slot) {
		super(ItemUtil.customArmourMaterial("aoa3:zargonite", 64, new int[] {5, 8, 9, 4}, 10, SoundEvents.ARMOR_EQUIP_GENERIC, 7), slot);
	}

	@Override
	public AdventArmour.Type setType() {
		return AdventArmour.Type.ZARGONITE;
	}

	@Override
	public void onDamageDealt(ServerPlayerDataManager plData, @Nullable HashSet<EquipmentSlotType> slots, LivingHurtEvent event) {
		if (slots != null && DamageUtil.isMagicDamage(event.getSource(), event.getEntityLiving(), event.getAmount()))
			event.setAmount((float)(event.getAmount() * (1 + (0.1 * slots.size()))));
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(pieceEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.zargonite_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}
