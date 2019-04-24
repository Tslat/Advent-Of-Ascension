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

import static net.tslat.aoa3.common.registration.MaterialsRegister.ARMOURBIOGENIC;

public class BiogenicArmour extends AdventArmour {
	public BiogenicArmour(String name, String registryName, int renderIndex, EntityEquipmentSlot slot) {
		super(ARMOURBIOGENIC, name, registryName, renderIndex, slot);
	}

	@Override
	public void setTickEffect(AdventPlayerCapability cap) {
		PotionEffect nightVision = cap.getPlayer().getActivePotionEffect(MobEffects.NIGHT_VISION);

		if (!cap.getPlayer().world.isRemote) {
			if (cap.getPlayer().getBrightness() < 0.4) {
				if (nightVision == null || nightVision.getDuration() < 250)
					cap.getPlayer().addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 300, 0, true, false));
			}
			else {
				if (nightVision != null)
					cap.getPlayer().removePotionEffect(MobEffects.NIGHT_VISION);
			}
		}

		if (EntityUtil.checkAboveHealthPercentThreshold(cap.getPlayer(), 50)) {
			cap.getPlayer().addPotionEffect(new PotionEffect(MobEffects.STRENGTH, -1, 0));
		}
		else if (cap.getPlayer().getHealth() > 0.0f) {
			cap.getPlayer().addPotionEffect(new PotionEffect(MobEffects.SPEED, -1, 1));
		}
	}

	@Override
	public void setUnequipEffect(AdventPlayerCapability cap) {
		PotionEffect nightVision = cap.getPlayer().getActivePotionEffect(MobEffects.NIGHT_VISION);

		if (nightVision != null && nightVision.getDuration() < 300)
			cap.getPlayer().removePotionEffect(MobEffects.NIGHT_VISION);
	}

	@Override
	public Enums.ArmourSets setType() {
		return Enums.ArmourSets.BIOGENIC;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("items.description.fullSetBonus", TextFormatting.GOLD));
		tooltip.add(StringUtil.getColourLocaleString("item.BiogenicArmour.desc.1", TextFormatting.DARK_GREEN));
		tooltip.add(StringUtil.getColourLocaleString("item.BiogenicArmour.desc.2", TextFormatting.DARK_GREEN));
		tooltip.add(StringUtil.getColourLocaleString("item.BiogenicArmour.desc.3", TextFormatting.DARK_GREEN));
	}
}
