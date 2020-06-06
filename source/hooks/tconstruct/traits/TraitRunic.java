package net.tslat.aoa3.hooks.tconstruct.traits;

import com.google.common.collect.ImmutableList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.tslat.aoa3.utils.EntityUtil;
import slimeknights.tconstruct.library.Util;
import slimeknights.tconstruct.library.modifiers.ModifierAspect;
import slimeknights.tconstruct.library.traits.AbstractTraitLeveled;

import java.util.List;

public class TraitRunic extends AbstractTraitLeveled {
	public TraitRunic(int level) {
		super("runic", String.valueOf(level), 0x89FBFF, 2, level);

		addAspects(ModifierAspect.weaponOnly);
	}

	@Override
	public float damage(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, float newDamage, boolean isCritical) {
		return damage * (1 - Traits.getTraitLevel(tool, this) * 0.2f);
	}

	@Override
	public void onHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, boolean isCritical) {
		float magicDmg = (damage / (1 - Traits.getTraitLevel(tool, this) * 0.2f) - damage) / 2f;

		EntityUtil.dealMagicDamage(null, player, target, magicDmg, false);
	}

	@Override
	public List<String> getExtraInfo(ItemStack tool, NBTTagCompound modifierTag) {
		return ImmutableList.of(Util.translateFormatted(String.format(LOC_Extra, getModifierIdentifier()), Util.dfPercent.format(Traits.getTraitLevel(tool, this) / 10f)));
	}
}
