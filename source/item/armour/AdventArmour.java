package net.tslat.aoa3.item.armour;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.oredict.OreDictionary;
import net.tslat.aoa3.capabilities.handlers.AdventPlayerCapability;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.library.Enums;

public abstract class AdventArmour extends ItemArmor {

	public AdventArmour(ArmorMaterial material, String name, String registryName, int renderIndex, EntityEquipmentSlot slot) {
		super(material, renderIndex, slot);
		setCreativeTab(CreativeTabsRegister.armourTab);
		setUnlocalizedName(name);
		setRegistryName("aoa3:" + registryName);
	}

	public abstract Enums.ArmourSets setType();

	public void handleDamageReductions(LivingHurtEvent event, AdventPlayerCapability cap) {}

	public void handleAttackBuffs(LivingHurtEvent event, AdventPlayerCapability cap) {}

	public void handleAttackImmunities(LivingAttackEvent event, AdventPlayerCapability cap) {}

	public void handleDamageTriggers(LivingDamageEvent event, AdventPlayerCapability cap) {}

	public void setTickEffect(AdventPlayerCapability cap) {}

	public void setUnequipEffect(AdventPlayerCapability cap) {}

	public void setEquipEffect(AdventPlayerCapability cap) {}

	@Override
	public boolean getIsRepairable(ItemStack stack, ItemStack repairMaterial) {
		return OreDictionary.itemMatches(repairMaterial, new ItemStack(ItemRegister.ingotRosite), false) || super.getIsRepairable(stack, repairMaterial);
	}

	public boolean isSetHelmet(AdventArmour helmet) {
		return this.setType() == helmet.setType() || helmet.setType() == Enums.ArmourSets.ALL;
	}
}
