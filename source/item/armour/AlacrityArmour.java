package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.capabilities.handlers.AdventPlayerCapability;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

import static net.tslat.aoa3.common.registration.MaterialsRegister.ARMOURALACRITY;

public class AlacrityArmour extends AdventArmour {
	public AlacrityArmour(String name, String registryName, int renderIndex, EntityEquipmentSlot slot) {
		super(ARMOURALACRITY, name, registryName, renderIndex, slot);
	}

	@Override
	public void setTickEffect(AdventPlayerCapability cap) {
		cap.getPlayer().addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, -1, 4));
	}

	@Override
	public Enums.ArmourSets setType() {
		return Enums.ArmourSets.ALACRITY;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("items.description.fullSetBonus", TextFormatting.GOLD));
		tooltip.add(StringUtil.getColourLocaleString("item.AlacrityArmour.desc.1", TextFormatting.DARK_GREEN));
		tooltip.add(StringUtil.getColourLocaleString("item.AlacrityArmour.desc.2", TextFormatting.DARK_GREEN));
	}
}
