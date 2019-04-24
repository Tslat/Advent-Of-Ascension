package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.capabilities.handlers.AdventPlayerCapability;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

import static net.tslat.aoa3.common.registration.MaterialsRegister.ARMOURWEAKEN;

public class WeakenArmour extends AdventArmour {
	public WeakenArmour(String name, String registryName, int renderIndex, EntityEquipmentSlot slot) {
		super(ARMOURWEAKEN, name, registryName, renderIndex, slot);
	}

	@Override
	public Enums.ArmourSets setType() {
		return Enums.ArmourSets.WEAKEN;
	}

	@Override
	public void handleDamageTriggers(LivingDamageEvent event, AdventPlayerCapability cap) {
		if (EntityUtil.isMeleeDamage(event.getSource()) && event.getSource().getTrueSource() instanceof EntityLivingBase)
			((EntityLivingBase)event.getSource().getTrueSource()).addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 100, 0, true, true));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("items.description.fullSetBonus", TextFormatting.GOLD));
		tooltip.add(StringUtil.getColourLocaleString("item.WeakenArmour.desc.1", TextFormatting.DARK_GREEN));
	}
}
