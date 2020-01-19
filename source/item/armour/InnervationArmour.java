package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.item.SkillItem;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.library.misc.AoAAttributes;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.StringUtil;
import net.tslat.aoa3.utils.player.PlayerDataManager;

import javax.annotation.Nullable;
import java.util.List;

import static net.tslat.aoa3.common.registration.MaterialsRegister.ARMOUR_INNERVATION;

public class InnervationArmour extends AdventArmour implements SkillItem {
	public InnervationArmour(String name, String registryName, EntityEquipmentSlot slot) {
		super(ARMOUR_INNERVATION, name, registryName, slot);
	}

	@Override
	public Enums.ArmourSets setType() {
		return Enums.ArmourSets.INNERVATION;
	}

	@Override
	public Enums.Skills getSkill() {
		return Enums.Skills.INNERVATION;
	}

	@Override
	public int getLevelReq() {
		return 100;
	}

	@Override
	public void addBuffs(PlayerDataManager.PlayerBuffs plBuffs, @Nullable EntityEquipmentSlot slot) {
		if (slot == null)
			plBuffs.addXpModifier(Enums.Skills.INNERVATION, 0.3f);
	}

	@Override
	public void removeBuffs(PlayerDataManager.PlayerBuffs plBuffs, @Nullable EntityEquipmentSlot slot) {
		if (slot == null)
			plBuffs.removeXpModifier(Enums.Skills.INNERVATION, 0.3f);
	}

	@Override
	public void onEquip(PlayerDataManager plData, @Nullable EntityEquipmentSlot slot) {
		if (slot == null)
			EntityUtil.applyAttributeModifierSafely(plData.player(), SharedMonsterAttributes.MAX_HEALTH, AoAAttributes.INNERVATION_ARMOUR_SET);
	}

	@Override
	public void onUnequip(PlayerDataManager plData, @Nullable EntityEquipmentSlot slot) {
		if (slot == null)
			EntityUtil.removeAttributeModifier(plData.player(), SharedMonsterAttributes.MAX_HEALTH, AoAAttributes.INNERVATION_ARMOUR_SET);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(setEffectHeader());
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.InnervationArmour.desc.1", Enums.ItemDescriptionType.POSITIVE));
		tooltip.add(ItemUtil.getFormattedDescriptionText("items.description.skillXpBonus", Enums.ItemDescriptionType.POSITIVE, Integer.toString(30), StringUtil.getLocaleString("skills.innervation.name")));
		tooltip.add(ItemUtil.getFormattedLevelRestrictedDescriptionText(Enums.Skills.INNERVATION, 100));
	}
}
