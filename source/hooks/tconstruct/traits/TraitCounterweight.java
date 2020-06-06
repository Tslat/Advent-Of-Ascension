package net.tslat.aoa3.hooks.tconstruct.traits;

import com.google.common.collect.ImmutableList;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.player.PlayerEvent;
import slimeknights.tconstruct.library.Util;
import slimeknights.tconstruct.library.modifiers.ModifierAspect;
import slimeknights.tconstruct.library.traits.AbstractTraitLeveled;
import slimeknights.tconstruct.library.utils.ToolHelper;

import java.util.List;

public class TraitCounterweight extends AbstractTraitLeveled {
	public TraitCounterweight(int level) {
		super("counterweight", String.valueOf(level), 0xFF8CBF, 2, level);

		addAspects(ModifierAspect.toolOnly);
	}

	@Override
	public void miningSpeed(ItemStack tool, PlayerEvent.BreakSpeed event) {
		if (!event.getEntityPlayer().onGround && ToolHelper.isToolEffective2(tool, event.getState()))
			event.setNewSpeed(event.getOriginalSpeed() * Traits.getTraitLevel(tool, this) * 1.5f);
	}

	@Override
	public List<String> getExtraInfo(ItemStack tool, NBTTagCompound modifierTag) {
		return ImmutableList.of(Util.translateFormatted(String.format(LOC_Extra, getModifierIdentifier()), Util.dfPercent.format((Traits.getTraitLevel(tool, this) * 1.5f) - 1)));
	}
}
