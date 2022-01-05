package net.tslat.aoa3.object.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.player.ServerPlayerDataManager;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

public class NethengeicArmour extends AdventArmour {
	public NethengeicArmour(EquipmentSlotType slot) {
		super(ItemUtil.customArmourMaterial("aoa3:nethengeic", 41, new int[] {3, 7, 8, 4}, 10, SoundEvents.ARMOR_EQUIP_GENERIC, 3), slot);
	}

	@Override
	public AdventArmour.Type setType() {
		return AdventArmour.Type.NETHENGEIC;
	}

	@Override
	public void onDamageDealt(ServerPlayerDataManager plData, @Nullable HashSet<EquipmentSlotType> slots, LivingHurtEvent event) {
		if (slots == null && plData.player().isOnFire())
			event.setAmount(event.getAmount() * 1.5f);

		if (slots != null && event.getEntityLiving().isOnFire())
			event.setAmount(event.getAmount() * (1 + (0.1f * slots.size())));
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(pieceEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.nethengeic_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(setEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.nethengeic_armour.desc.2", LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}
