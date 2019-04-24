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
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

import static net.tslat.aoa3.common.registration.MaterialsRegister.ARMOURPHANTASM;

public class PhantasmArmour extends AdventArmour {
	public PhantasmArmour(String name, String registryName, int renderIndex, EntityEquipmentSlot slot) {
		super(ARMOURPHANTASM, name, registryName, renderIndex, slot);
	}

	@Override
	public Enums.ArmourSets setType() {
		return Enums.ArmourSets.PHANTASM;
	}

	@Override
	public void setTickEffect(AdventPlayerCapability cap) {
		final int healthPercent = EntityUtil.getCurrentHealthPercent(cap.getPlayer());

		if (healthPercent > 50) {
			cap.getPlayer().addPotionEffect(new PotionEffect(MobEffects.SPEED, -1, 0, true, false));
		}
		else if (healthPercent > 25) {
			cap.getPlayer().addPotionEffect(new PotionEffect(MobEffects.SPEED, -1, 1, true, false));
		}
		else {
			cap.getPlayer().addPotionEffect(new PotionEffect(MobEffects.SPEED, -1, 2, true, false));
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("items.description.fullSetBonus", TextFormatting.GOLD));
		tooltip.add(StringUtil.getColourLocaleString("item.PhantasmArmour.desc.1", TextFormatting.DARK_GREEN));
		tooltip.add(StringUtil.getColourLocaleString("item.PhantasmArmour.desc.2", TextFormatting.DARK_GREEN));
	}
}
