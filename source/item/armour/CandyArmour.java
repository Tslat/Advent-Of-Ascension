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

import static net.tslat.aoa3.common.registration.MaterialsRegister.ARMOURCANDY;

public class CandyArmour extends AdventArmour {
	public CandyArmour(String name, String registryName, int renderIndex, EntityEquipmentSlot slot) {
		super(ARMOURCANDY, name, registryName, renderIndex, slot);
	}

	@Override
	public Enums.ArmourSets setType() {
		return Enums.ArmourSets.CANDY;
	}

	@Override
	public void setTickEffect(AdventPlayerCapability cap) {
		if (cap.isCooledDown(Enums.Counters.CANDY) && EntityUtil.checkBelowHealthPercentThreshold(cap.getPlayer(), 20)) {
			cap.getPlayer().addPotionEffect(new PotionEffect(MobEffects.STRENGTH,140, 1, true, false));
			cap.getPlayer().addPotionEffect(new PotionEffect(MobEffects.SPEED,140, 1, true, false));
			cap.getPlayer().addPotionEffect(new PotionEffect(MobEffects.REGENERATION,140, 1, true, false));
			cap.getPlayer().addPotionEffect(new PotionEffect(MobEffects.RESISTANCE,140, 1, true, false));
			cap.getPlayer().addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION,140, 1, true, false));
			cap.getPlayer().addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING,140, 1, true, false));
			cap.getPlayer().addPotionEffect(new PotionEffect(MobEffects.HASTE,140, 1, true, false));
			cap.setCooldown(Enums.Counters.CANDY, 1800);
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("items.description.fullSetBonus", TextFormatting.GOLD));
		tooltip.add(StringUtil.getColourLocaleString("item.CandyArmour.desc.1", TextFormatting.DARK_GREEN));
		tooltip.add(StringUtil.getColourLocaleString("item.CandyArmour.desc.2", TextFormatting.DARK_GREEN));
	}
}
