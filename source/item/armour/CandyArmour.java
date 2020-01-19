package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.player.PlayerDataManager;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

import static net.tslat.aoa3.common.registration.MaterialsRegister.ARMOUR_CANDY;

public class CandyArmour extends AdventArmour {
	public CandyArmour(String name, String registryName, EntityEquipmentSlot slot) {
		super(ARMOUR_CANDY, name, registryName, slot);
	}

	@Override
	public Enums.ArmourSets setType() {
		return Enums.ArmourSets.CANDY;
	}

	@Override
	public void onEffectTick(PlayerDataManager plData, @Nullable HashSet<EntityEquipmentSlot> slots) {
		if (plData.player().getFoodStats().needFood()) {
			if (slots == null || plData.equipment().isCooledDown(Enums.Counters.CANDY)) {
				if (findAndConsumeFood(plData.player()))
					plData.equipment().setCooldown(Enums.Counters.CANDY, 12000 / (slots == null ? 4 : slots.size()));
			}
		}
	}

	private boolean findAndConsumeFood(EntityPlayer player) {
		for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
			ItemStack stack = player.inventory.getStackInSlot(i);

			if (stack.getItem() instanceof ItemFood) {
				ItemFood food = (ItemFood)stack.getItem();

				if (food.getHealAmount(stack) > 0 && food.getSaturationModifier(stack) > 0) {
					player.inventory.setInventorySlotContents(i, stack.getItem().onItemUseFinish(stack, player.world, player));

					return true;
				}
			}
		}

		return false;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.CandyArmour.desc.1", Enums.ItemDescriptionType.POSITIVE));
		tooltip.add(pieceEffectHeader());
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.CandyArmour.desc.2", Enums.ItemDescriptionType.POSITIVE));
		tooltip.add(setEffectHeader());
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.CandyArmour.desc.3", Enums.ItemDescriptionType.POSITIVE));
	}
}
