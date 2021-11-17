package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.player.PlayerDataManager;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

public class PhantasmArmour extends AdventArmour {
	public PhantasmArmour(EquipmentSlotType slot) {
		super(ItemUtil.customArmourMaterial("aoa3:phantasm", 51, new int[] {3, 8, 8, 5}, 10, SoundEvents.ARMOR_EQUIP_GENERIC, 5), slot);
	}

	@Override
	public AdventArmour.Type setType() {
		return AdventArmour.Type.PHANTASM;
	}

	@Override
	public void onEffectTick(PlayerDataManager plData, @Nullable HashSet<EquipmentSlotType> slots) {
		if (slots != null)
			plData.player().addEffect(new EffectInstance(Effects.LUCK, -1, slots.size() - 1, true, false));
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.phantasm_armour.desc.1", LocaleUtil.ItemDescriptionType.UNIQUE));
	}
}
