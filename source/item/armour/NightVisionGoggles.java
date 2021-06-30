package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.player.PlayerDataManager;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

public class NightVisionGoggles extends AdventArmour implements ScreenOverlayArmour {
	public NightVisionGoggles() {
		super(ItemUtil.customArmourMaterial("aoa3:night_vision_goggles", 27, new int[] {2, 2, 2, 2}, 10, SoundEvents.ARMOR_EQUIP_GENERIC, 1), EquipmentSlotType.HEAD);
	}

	@Override
	public AdventArmour.Type setType() {
		return AdventArmour.Type.ALL;
	}

	@Override
	public AdventArmour.Overlay getOverlay() {
		return Overlay.NIGHT_VISION_GOGGLES;
	}

	@Override
	public void onEffectTick(PlayerDataManager plData, @Nullable HashSet<EquipmentSlotType> slots) {
		plData.player().addEffect(new EffectInstance(Effects.NIGHT_VISION, 300, 0, true, false));
	}

	@Override
	public void onUnequip(PlayerDataManager plData, @Nullable EquipmentSlotType slot) {
		EffectInstance nightVision = plData.player().getEffect(Effects.NIGHT_VISION);

		if (nightVision != null && nightVision.getDuration() < 300)
			plData.player().removeEffect(Effects.NIGHT_VISION);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		tooltip.add(anySetEffectHeader());
	}
}
