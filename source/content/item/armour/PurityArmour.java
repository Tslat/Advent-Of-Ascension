package net.tslat.aoa3.content.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.player.ServerPlayerDataManager;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

public class PurityArmour extends AdventArmour {
	public PurityArmour(EquipmentSlotType slot) {
		super(ItemUtil.customArmourMaterial("aoa3:purity", 61, new int[] {5, 8, 8, 3}, 10, SoundEvents.ARMOR_EQUIP_GENERIC, 7), slot);
	}

	@Override
	public AdventArmour.Type setType() {
		return AdventArmour.Type.PURITY;
	}

	@Override
	public void onEffectTick(ServerPlayerDataManager plData, @Nullable HashSet<EquipmentSlotType> slots) {
		if (slots == null && !plData.player().getActiveEffectsMap().isEmpty())
			checkAndRemoveEffects(plData.player(), Effects.WEAKNESS, Effects.MOVEMENT_SLOWDOWN, Effects.DIG_SLOWDOWN, Effects.BLINDNESS, Effects.CONFUSION);
	}

	private void checkAndRemoveEffects(PlayerEntity pl, Effect... effects) {
		for (Effect effect : effects) {
			if (pl.hasEffect(effect))
				pl.removeEffect(effect);
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(setEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.purity_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}
