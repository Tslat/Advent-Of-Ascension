package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.capabilities.handlers.AdventPlayerCapability;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.item.misc.RuneItem;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.StringUtil;

import java.util.HashMap;
import java.util.List;

import static net.tslat.aoa3.common.registration.MaterialsRegister.ARMOURHYDRANGIC;

public class HydrangicArmour extends AdventArmour {
	public HydrangicArmour(String name, String registryName, int renderIndex, EntityEquipmentSlot slot) {
		super(ARMOURHYDRANGIC, name, registryName, renderIndex, slot);
	}

	@Override
	public Enums.ArmourSets setType() {
		return Enums.ArmourSets.HYDRANGIC;
	}

	@Override
	public void setTickEffect(AdventPlayerCapability cap) {
		EntityPlayer pl = cap.getPlayer();

		if (EntityUtil.getCurrentHealthPercent(pl) < 10) {
			if (!pl.capabilities.isCreativeMode) {
				HashMap<RuneItem, Integer> runeMap = new HashMap<RuneItem, Integer>();

				runeMap.put(ItemRegister.runeLife, 10);
				runeMap.put(ItemRegister.runeDistortion, 10);

				if (!ItemUtil.findAndConsumeRunes(runeMap, pl, false, null))
					return;
			}

			EntityUtil.healEntity(pl, 10);
			pl.inventory.armorInventory.get(0).damageItem(200, pl);
			pl.inventory.armorInventory.get(1).damageItem(200, pl);
			pl.inventory.armorInventory.get(2).damageItem(200, pl);
			pl.inventory.armorInventory.get(3).damageItem(200, pl);
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("items.description.fullSetBonus", TextFormatting.GOLD));
		tooltip.add(StringUtil.getColourLocaleString("item.HydrangicArmour.desc.1", TextFormatting.DARK_GREEN));
		tooltip.add(StringUtil.getColourLocaleString("item.HydrangicArmour.desc.2", TextFormatting.DARK_GREEN));
		tooltip.add(StringUtil.getColourLocaleString("item.HydrangicArmour.desc.3", TextFormatting.DARK_GREEN));
	}
}
