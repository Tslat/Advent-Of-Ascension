package net.tslat.aoa3.hooks.tconstruct.traits;

import com.google.common.collect.ImmutableList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.tslat.aoa3.utils.EntityUtil;
import slimeknights.tconstruct.library.Util;
import slimeknights.tconstruct.library.traits.AbstractTraitLeveled;

import java.util.List;

public class TraitAntiAir extends AbstractTraitLeveled {
	public TraitAntiAir(int level) {
		super("anti_air", String.valueOf(level), 0xCFF99F, 3, level);
	}

	@Override
	public float damage(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, float newDamage, boolean isCritical) {
		if (EntityUtil.isFlyingCreature(target))
			return damage + Traits.getTraitLevel(tool, this) * 1.5f;

		return damage;
	}

	@Override
	public List<String> getExtraInfo(ItemStack tool, NBTTagCompound modifierTag) {
		return ImmutableList.of(Util.translateFormatted(String.format(LOC_Extra, getModifierIdentifier()), Util.df.format(Traits.getTraitLevel(tool, this) * 1.5)));
	}
}
