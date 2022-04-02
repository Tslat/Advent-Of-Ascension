package net.tslat.aoa3.content.item.armour;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

public class PurityArmour extends AdventArmour {
	public PurityArmour(EquipmentSlot slot) {
		super(ItemUtil.customArmourMaterial("aoa3:purity", 61, new int[] {5, 8, 8, 3}, 10, SoundEvents.ARMOR_EQUIP_GENERIC, 7), slot);
	}

	@Override
	public Type setType() {
		return Type.PURITY;
	}

	@Override
	public void onEffectTick(ServerPlayerDataManager plData, @Nullable HashSet<EquipmentSlot> slots) {
		if (slots == null && !plData.player().getActiveEffectsMap().isEmpty())
			checkAndRemoveEffects(plData.player(), MobEffects.WEAKNESS, MobEffects.MOVEMENT_SLOWDOWN, MobEffects.DIG_SLOWDOWN, MobEffects.BLINDNESS, MobEffects.CONFUSION);
	}

	private void checkAndRemoveEffects(Player pl, MobEffect... effects) {
		for (MobEffect effect : effects) {
			if (pl.hasEffect(effect))
				pl.removeEffect(effect);
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(setEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.purity_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}
