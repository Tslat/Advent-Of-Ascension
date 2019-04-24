package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.capabilities.handlers.AdventPlayerCapability;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

import static net.tslat.aoa3.common.registration.MaterialsRegister.ARMOURNETHENGEIC;

public class NethengeicArmour extends AdventArmour {
	public NethengeicArmour(String name, String registryName, int renderIndex, EntityEquipmentSlot slot) {
		super(ARMOURNETHENGEIC, name, registryName, renderIndex, slot);
	}

	@Override
	public Enums.ArmourSets setType() {
		return Enums.ArmourSets.NETHENGEIC;
	}

	@Override
	public void handleAttackImmunities(LivingAttackEvent event, AdventPlayerCapability cap) {
		if (event.getSource().isFireDamage())
			event.setCanceled(true);
	}

	@Override
	public void handleDamageTriggers(LivingDamageEvent event, AdventPlayerCapability cap) {
		if (EntityUtil.isMeleeDamage(event.getSource()) && EntityUtil.checkAboveHealthPercentThreshold(cap.getPlayer(), 50) && event.getSource().getImmediateSource() instanceof EntityLivingBase)
			event.getSource().getImmediateSource().setFire(5);
	}

	@Override
	public void setTickEffect(AdventPlayerCapability cap) {
		if (EntityUtil.checkBelowHealthPercentThreshold(cap.getPlayer(), 50))
			cap.getPlayer().addPotionEffect(new PotionEffect(MobEffects.STRENGTH, -1, 1, true, false));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("items.description.fullSetBonus", TextFormatting.GOLD));
		tooltip.add(StringUtil.getColourLocaleString("item.NethengeicArmour.desc.1", TextFormatting.DARK_GREEN));
		tooltip.add(StringUtil.getColourLocaleString("item.NethengeicArmour.desc.2", TextFormatting.DARK_GREEN));
		tooltip.add(StringUtil.getColourLocaleString("item.NethengeicArmour.desc.3", TextFormatting.DARK_GREEN));
	}
}
