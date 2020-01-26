package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.FoodStats;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.player.PlayerDataManager;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

import static net.tslat.aoa3.common.registration.MaterialsRegister.ARMOUR_SKELETAL;

public class SkeletalArmour extends AdventArmour {
	public SkeletalArmour(String name, String registryName, EntityEquipmentSlot slot) {
		super(ARMOUR_SKELETAL, name, registryName, slot);
	}

	@Override
	public Enums.ArmourSets setType() {
		return Enums.ArmourSets.SKELETAL;
	}

	@Override
	public void onEffectTick(PlayerDataManager plData, @Nullable HashSet<EntityEquipmentSlot> slots) {
		if (slots == null && plData.player().getFoodStats().getSaturationLevel() < 1) {
			FoodStats foodStats = plData.player().getFoodStats();
			int foodLvl = foodStats.getFoodLevel();

			foodStats.addStats(1, 0.5f);
			foodStats.setFoodLevel(foodLvl);
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(setEffectHeader());
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.SkeletalArmour.desc.1", Enums.ItemDescriptionType.POSITIVE));
	}
}
