package net.tslat.aoa3.object.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
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

public class OceanusHelmet extends AdventArmour {
	public OceanusHelmet() {
		super(ItemUtil.customArmourMaterial("aoa3:oceanus", 60, new int[] {5, 7, 9, 5}, 10, SoundEvents.ARMOR_EQUIP_GENERIC, 7), EquipmentSlotType.HEAD);
	}

	@Override
	public AdventArmour.Type setType() {
		return AdventArmour.Type.ALL;
	}

	@Override
	public void onEffectTick(ServerPlayerDataManager plData, @Nullable HashSet<EquipmentSlotType> slots) {
		plData.player().addEffect(new EffectInstance(Effects.DOLPHINS_GRACE, -1, 1, true, false));

		if (plData.player().isInWater())
			plData.player().setAirSupply(-10);
	}

	@Override
	public boolean isHelmetAirTight(ServerPlayerEntity player) {
		return true;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.helmet.airTight", LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		tooltip.add(anySetEffectHeader());
	}
}
