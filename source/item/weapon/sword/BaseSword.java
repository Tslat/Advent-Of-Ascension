package net.tslat.aoa3.item.weapon.sword;

import com.google.common.collect.Multimap;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.oredict.OreDictionary;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;

public abstract class BaseSword extends ItemSword implements AdventWeapon {
	private final float dmg;
	private final double speed;

	public BaseSword(final ToolMaterial material, Float dmg, Double speed) {
		super(material);
		this.dmg = dmg == null ? getAttackDamage() : dmg;
		this.speed = speed == null ? Enums.WeaponSpeed.NORMAL.value : speed;
		setCreativeTab(CreativeTabsRegister.swordsTab);
	}

	public float getDamage() {
		return dmg;
	}

	public double getAttackSpeed() {
		return speed;
	}

	@Override
	public boolean getIsRepairable(ItemStack stack, ItemStack repairMaterial) {
		return repairMaterial.getItem() != Items.ENCHANTED_BOOK && OreDictionary.itemMatches(repairMaterial, new ItemStack(ItemRegister.ingotRosite), false) || super.getIsRepairable(stack, repairMaterial);
	}

	@Override
	public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot) {
		Multimap<String, AttributeModifier> modifierMap = super.getItemAttributeModifiers(equipmentSlot);

		if (equipmentSlot == EntityEquipmentSlot.MAINHAND) {
			ItemUtil.setAttribute(modifierMap, SharedMonsterAttributes.ATTACK_DAMAGE, ATTACK_DAMAGE_MODIFIER, dmg);
			ItemUtil.setAttribute(modifierMap, SharedMonsterAttributes.ATTACK_SPEED, ATTACK_SPEED_MODIFIER, speed);
		}

		return modifierMap;
	}
}

