package net.tslat.aoa3.content.item.armour;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

public class SkeletalArmour extends AdventArmour {
	public SkeletalArmour(EquipmentSlot slot) {
		super(ItemUtil.customArmourMaterial("aoa3:skeletal", 43, new int[] {3, 7, 8, 4}, 10, SoundEvents.ARMOR_EQUIP_GENERIC, 3), slot);
	}

	@Override
	public Type setType() {
		return Type.SKELETAL;
	}

	@Override
	public void onEffectTick(ServerPlayerDataManager plData, @Nullable HashSet<EquipmentSlot> slots) {
		if (slots == null && plData.player().getFoodData().getSaturationLevel() < 1) {
			FoodData foodStats = plData.player().getFoodData();
			int foodLvl = foodStats.getFoodLevel();

			foodStats.eat(1, 0.5f);
			foodStats.setFoodLevel(foodLvl);
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(setEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.skeletal_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}
