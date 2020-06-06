package net.tslat.aoa3.hooks.tconstruct.modifiers;

import com.google.common.collect.ImmutableList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.tslat.aoa3.hooks.tconstruct.traits.Traits;
import slimeknights.tconstruct.library.Util;
import slimeknights.tconstruct.library.modifiers.ModifierTrait;

import java.util.List;

public class ModSurpriseMe extends ModifierTrait {
	public ModSurpriseMe() {
		super("surprise_me", 0xF200FF, 3, 4);
	}

	@Override
	public float damage(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, float newDamage, boolean isCritical) {
		int modifierStage = Traits.getModifierStage(tool, this);
		float adjustment = 1 + 0.02f * modifierStage;
		float baseDmg = damage * adjustment;

		return baseDmg + (float)(random.nextGaussian() * baseDmg * (2 * adjustment - 2));
	}

	@Override
	public List<String> getExtraInfo(ItemStack tool, NBTTagCompound modifierTag) {
		return ImmutableList.of(Util.translateFormatted(String.format(LOC_Extra, getModifierIdentifier()), Util.dfPercent.format(Traits.getModifierStage(tool, this) * 0.02)));
	}
}
