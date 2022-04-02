package net.tslat.aoa3.content.item.armour;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PlayerUtil;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

public class HazmatArmour extends AdventArmour {
	public HazmatArmour(EquipmentSlot slot) {
		super(ItemUtil.customArmourMaterial("aoa3:hazmat", 30, new int[] {2, 5, 6, 2}, 10, SoundEvents.ARMOR_EQUIP_LEATHER, 0), slot);
	}

	@Override
	public Type setType() {
		return Type.HAZMAT;
	}

	@Override
	public void onEffectTick(ServerPlayerDataManager plData, @Nullable HashSet<EquipmentSlot> slots) {
		if (slots == null) {
			if (plData.player().isInWater())
				plData.player().setAirSupply(-10);
		}
	}

	@Override
	public boolean isHelmetAirTight(ServerPlayer player) {
		return PlayerUtil.isWearingFullSet(player, setType());
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(setEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.helmet.airTight", LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO));
	}
}
