package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.library.misc.AoAAttributes;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.player.PlayerDataManager;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

import static net.tslat.aoa3.common.registration.MaterialsRegister.ARMOUR_BATTLEBORN;

public class BattlebornArmour extends AdventArmour {
	public BattlebornArmour(String name, String registryName, EntityEquipmentSlot slot) {
		super(ARMOUR_BATTLEBORN, name, registryName, slot);
	}

	@Override
	public Enums.ArmourSets setType() {
		return Enums.ArmourSets.BATTLEBORN;
	}

	@Override
	public void onDamageDealt(PlayerDataManager plData, @Nullable HashSet<EntityEquipmentSlot> slots, LivingHurtEvent event) {
		if (slots != null && EntityUtil.isMeleeDamage(event.getSource())) {
			int counter = plData.equipment().getCooldown(Enums.Counters.BATTLEBORN);
			int newAmount = Math.min(300, counter + slots.size() * 6);

			plData.equipment().setCooldown(Enums.Counters.BATTLEBORN, newAmount);
			EntityUtil.removeAttributeModifier(plData.player(), SharedMonsterAttributes.ATTACK_SPEED, AoAAttributes.BATTLEBORN_ARMOUR_BUFF);
			EntityUtil.applyAttributeModifierSafely(plData.player(), SharedMonsterAttributes.ATTACK_SPEED, AoAAttributes.battlebornArmourBuff(Math.min(0.65, newAmount / 240d)));
		}
	}

	@Override
	public void onEffectTick(PlayerDataManager plData, @Nullable HashSet<EntityEquipmentSlot> slots) {
		int counter = plData.equipment().getCooldown(Enums.Counters.BATTLEBORN);

		if (counter == 1) {
			EntityUtil.removeAttributeModifier(plData.player(), SharedMonsterAttributes.ATTACK_SPEED, AoAAttributes.BATTLEBORN_ARMOUR_BUFF);
		}
		else if (counter > 0 && plData.player().world.getTotalWorldTime() % 10 == 0) {
			EntityUtil.removeAttributeModifier(plData.player(), SharedMonsterAttributes.ATTACK_SPEED, AoAAttributes.battlebornArmourBuff(counter));
			EntityUtil.applyAttributeModifierSafely(plData.player(), SharedMonsterAttributes.ATTACK_SPEED, AoAAttributes.battlebornArmourBuff(Math.min(0.65, counter / 240d)));
		}
	}

	@Override
	public void onUnequip(PlayerDataManager plData, @Nullable EntityEquipmentSlot slot) {
		if (slot != null) {
			int cooldown = plData.equipment().getCooldown(Enums.Counters.BATTLEBORN);

			if (cooldown > 0)
				plData.equipment().setCooldown(Enums.Counters.BATTLEBORN, (int)(cooldown * 0.75f));
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.BattlebornArmour.desc.1", Enums.ItemDescriptionType.POSITIVE));
		tooltip.add(pieceEffectHeader());
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.BattlebornArmour.desc.2", Enums.ItemDescriptionType.POSITIVE));
	}
}
