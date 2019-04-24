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

import static net.tslat.aoa3.common.registration.MaterialsRegister.ARMOURRUNIC;

public class RunicArmour extends AdventArmour {
	public RunicArmour(String name, String registryName, int renderIndex, EntityEquipmentSlot slot) {
		super(ARMOURRUNIC, name, registryName, renderIndex, slot);
	}

	@Override
	public Enums.ArmourSets setType() {
		return Enums.ArmourSets.RUNIC;
	}

	@Override
	public void setTickEffect(AdventPlayerCapability cap) {
		final int healthPercent = EntityUtil.getCurrentHealthPercent(cap.getPlayer());

		if (healthPercent < 20)
			cap.getPlayer().addPotionEffect(new PotionEffect(MobEffects.SPEED, -1, 0, true, false));

		if (healthPercent < 33 && cap.isCooledDown(Enums.Counters.RUNIC)) {
			cap.getPlayer().addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 200, 1, true, false));
			cap.setCooldown(Enums.Counters.RUNIC, 600);
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("items.description.fullSetBonus", TextFormatting.GOLD));
		tooltip.add(StringUtil.getColourLocaleString("item.RunicArmour.desc.1", TextFormatting.DARK_GREEN));
		tooltip.add(StringUtil.getColourLocaleString("item.RunicArmour.desc.2", TextFormatting.DARK_GREEN));
		tooltip.add(StringUtil.getColourLocaleString("item.RunicArmour.desc.3", TextFormatting.DARK_GREEN));
	}
}
