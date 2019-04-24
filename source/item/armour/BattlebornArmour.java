package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.capabilities.handlers.AdventPlayerCapability;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

import static net.tslat.aoa3.common.registration.MaterialsRegister.ARMOURBATTLEBORN;

public class BattlebornArmour extends AdventArmour {
	public BattlebornArmour(String name, String registryName, int renderIndex, EntityEquipmentSlot slot) {
		super(ARMOURBATTLEBORN, name, registryName, renderIndex, slot);
	}

	@Override
	public Enums.ArmourSets setType() {
		return Enums.ArmourSets.BATTLEBORN;
	}

	@Override
	public void setTickEffect(AdventPlayerCapability cap) {
		cap.resourceRegen(Enums.Resources.RAGE, 0.15f);

		if (EntityUtil.checkBelowHealthPercentThreshold(cap.getPlayer(), 30) && cap.isCooledDown(Enums.Counters.BATTLEBORN)) {
			cap.resourceRegen(Enums.Resources.RAGE, 200.0f);
			cap.setCooldown(Enums.Counters.BATTLEBORN, 600);
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("items.description.fullSetBonus", TextFormatting.GOLD));
		tooltip.add(StringUtil.getColourLocaleString("item.BattlebornArmour.desc.1", TextFormatting.DARK_GREEN));
		tooltip.add(StringUtil.getColourLocaleString("item.BattlebornArmour.desc.2", TextFormatting.DARK_GREEN));
		tooltip.add(StringUtil.getColourLocaleString("item.BattlebornArmour.desc.3", TextFormatting.DARK_GREEN));
	}
}
