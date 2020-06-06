package net.tslat.aoa3.hooks.tconstruct.traits;

import com.google.common.collect.ImmutableList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import slimeknights.tconstruct.library.Util;
import slimeknights.tconstruct.library.modifiers.ModifierAspect;
import slimeknights.tconstruct.library.traits.AbstractTrait;

import java.util.List;

public class TraitBoneShock extends AbstractTrait {
	public TraitBoneShock() {
		super("bone_shock", 0xFFE8A0);

		addAspects(ModifierAspect.weaponOnly);
	}

	@Override
	public float damage(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, float newDamage, boolean isCritical) {
		if (target.getCreatureAttribute() != EnumCreatureAttribute.ARTHROPOD)
			return newDamage + 2;

		return newDamage;
	}

	@Override
	public List<String> getExtraInfo(ItemStack tool, NBTTagCompound modifierTag) {
		return ImmutableList.of(Util.translateFormatted(String.format(LOC_Extra, getModifierIdentifier()), Util.df.format(2)));
	}
}
