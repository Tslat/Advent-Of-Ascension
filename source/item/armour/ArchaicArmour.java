package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.player.PlayerDataManager;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

import static net.tslat.aoa3.common.registration.MaterialsRegister.ARMOUR_ARCHAIC;

public class ArchaicArmour extends AdventArmour {
	public ArchaicArmour(String name, String registryName, EntityEquipmentSlot slot) {
		super(ARMOUR_ARCHAIC, name, registryName, slot);
	}

	@Override
	public void onDamageDealt(PlayerDataManager plData, @Nullable HashSet<EntityEquipmentSlot> slots, LivingHurtEvent event) {
		if (slots != null && EntityUtil.isMeleeDamage(event.getSource()))
			event.setAmount(event.getAmount() * (1 + 0.1875f * slots.size() * (1 - EntityUtil.getCurrentHealthPercent(plData.player()))));
	}

	@Override
	public void onAttackReceived(PlayerDataManager plData, @Nullable HashSet<EntityEquipmentSlot> slots, LivingHurtEvent event) {
		if (slots != null && plData.equipment().getCurrentFullArmourSet() != setType() && EntityUtil.isMeleeDamage(event.getSource()))
			event.setAmount(event.getAmount() * (1 + 0.16f * slots.size() * (1 - EntityUtil.getCurrentHealthPercent(plData.player()))));
	}

	@Override
	public Enums.ArmourSets setType() {
		return Enums.ArmourSets.ARCHAIC;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(pieceEffectHeader());
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.ArchaicArmour.desc.1", Enums.ItemDescriptionType.POSITIVE));
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.ArchaicArmour.desc.2", Enums.ItemDescriptionType.NEGATIVE));
		tooltip.add(setEffectHeader());
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.ArchaicArmour.desc.3", Enums.ItemDescriptionType.POSITIVE));
	}
}
