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

import static net.tslat.aoa3.common.registration.MaterialsRegister.ARMOUR_GHOULISH;

public class GhoulishArmour extends AdventArmour {
	public GhoulishArmour(String name, String registryName, EntityEquipmentSlot slot) {
		super(ARMOUR_GHOULISH, name, registryName, slot);
	}

	@Override
	public Enums.ArmourSets setType() {
		return Enums.ArmourSets.GHOULISH;
	}

	@Override
	public void onDamageDealt(PlayerDataManager plData, @Nullable HashSet<EntityEquipmentSlot> slots, LivingHurtEvent event) {
		if (slots != null && EntityUtil.isBlasterDamage(event.getSource()))
			event.setAmount(event.getAmount() * (1 + (slots.size() * 0.1f)));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(pieceEffectHeader());
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.GhoulishArmour.desc.1", Enums.ItemDescriptionType.POSITIVE));
		tooltip.add(setEffectHeader());
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.GhoulishArmour.desc.2", Enums.ItemDescriptionType.POSITIVE));
	}
}
