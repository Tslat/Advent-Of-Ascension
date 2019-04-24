package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.capabilities.handlers.AdventPlayerCapability;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

import static net.tslat.aoa3.common.registration.MaterialsRegister.ARMOURNECRO;

public class NecroArmour extends AdventArmour {
	public NecroArmour(String name, String registryName, int renderIndex, EntityEquipmentSlot slot) {
		super(ARMOURNECRO, name, registryName, renderIndex, slot);
	}

	@Override
	public Enums.ArmourSets setType() {
		return Enums.ArmourSets.NECRO;
	}

	@Override
	public void handleDamageReductions(LivingHurtEvent event, AdventPlayerCapability cap) {
		if (event.getSource().getDamageType().equals("selfharm") || EntityUtil.isEnvironmentalDamage(event.getSource()))
			event.setAmount(event.getAmount() * 2);
	}

	public void handleAttackBuffs(LivingHurtEvent event, AdventPlayerCapability cap) {
		if (EntityUtil.isMeleeDamage(event.getSource()) && event.getEntity() instanceof EntityLivingBase) {
			EntityUtil.healEntity(cap.getPlayer(), event.getAmount() * 0.1f);
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("items.description.fullSetBonus", TextFormatting.GOLD));
		tooltip.add(StringUtil.getColourLocaleString("item.NecroArmour.desc.1", TextFormatting.DARK_GREEN));
		tooltip.add(StringUtil.getColourLocaleString("item.NecroArmour.desc.2", TextFormatting.DARK_GREEN));
	}
}
