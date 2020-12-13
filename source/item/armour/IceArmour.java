package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;
import java.util.List;

public class IceArmour extends AdventArmour {
	public IceArmour(EquipmentSlotType slot) {
		super(ItemUtil.customArmourMaterial("aoa3:ice", 12, new int[] {2, 6, 6, 2}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 2), slot);
	}

	@Override
	public AdventArmour.Type setType() {
		return AdventArmour.Type.ICE;
	}

	@Override
	public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
		if (!world.isRemote && stack.getDamage() > 0 && player.getRNG().nextFloat() < 0.02f && WorldUtil.getAmbientTemperature(world, player.getPosition()) < 0.15f)
			stack.setDamage(stack.getDamage() - 1);
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.ice_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}
