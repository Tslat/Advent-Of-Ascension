package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.FoodStats;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.player.PlayerDataManager;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

public class SkeletalArmour extends AdventArmour {
	public SkeletalArmour(EquipmentSlotType slot) {
		super(ItemUtil.customArmourMaterial("aoa3:skeletal", 43, new int[] {3, 7, 8, 4}, 10, SoundEvents.ARMOR_EQUIP_GENERIC, 3), slot);
	}

	@Override
	public AdventArmour.Type setType() {
		return AdventArmour.Type.SKELETAL;
	}

	@Override
	public void onEffectTick(PlayerDataManager plData, @Nullable HashSet<EquipmentSlotType> slots) {
		if (slots == null && plData.player().getFoodData().getSaturationLevel() < 1) {
			FoodStats foodStats = plData.player().getFoodData();
			int foodLvl = foodStats.getFoodLevel();

			foodStats.eat(1, 0.5f);
			foodStats.setFoodLevel(foodLvl);
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(setEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.skeletal_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}
