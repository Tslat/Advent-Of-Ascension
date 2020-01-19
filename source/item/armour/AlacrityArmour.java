package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.player.PlayerDataManager;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

import static net.tslat.aoa3.common.registration.MaterialsRegister.ARMOUR_ALACRITY;

public class AlacrityArmour extends AdventArmour {
	public AlacrityArmour(String name, String registryName, EntityEquipmentSlot slot) {
		super(ARMOUR_ALACRITY, name, registryName, slot);
	}

	@Override
	public Enums.ArmourSets setType() {
		return Enums.ArmourSets.ALACRITY;
	}

	@Override
	public void onPlayerLandingFall(PlayerDataManager plData, @Nullable HashSet<EntityEquipmentSlot> slots, LivingFallEvent event) {
		if (slots == null) {
			event.setCanceled(true);
		}
		else if (plData.equipment().getCurrentFullArmourSet() != setType()) {
			event.setDamageMultiplier(1 - (slots.size() * 0.2f));
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(pieceEffectHeader());
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.AlacrityArmour.desc.1", Enums.ItemDescriptionType.POSITIVE));
		tooltip.add(setEffectHeader());
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.AlacrityArmour.desc.2", Enums.ItemDescriptionType.POSITIVE));
	}
}
