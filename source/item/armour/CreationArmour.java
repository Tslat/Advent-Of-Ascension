package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.item.SkillItem;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.StringUtil;
import net.tslat.aoa3.utils.player.PlayerDataManager;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

import static net.tslat.aoa3.common.registration.MaterialsRegister.ARMOUR_CREATION;

public class CreationArmour extends AdventArmour implements SkillItem {
	public CreationArmour(String name, String registryName, EntityEquipmentSlot slot) {
		super(ARMOUR_CREATION, name, registryName, slot);
	}

	@Override
	public Enums.ArmourSets setType() {
		return Enums.ArmourSets.CREATION;
	}

	@Override
	public Enums.Skills getSkill() {
		return Enums.Skills.CREATION;
	}

	@Override
	public int getLevelReq() {
		return 100;
	}

	@Override
	public void onEffectTick(PlayerDataManager plData, @Nullable HashSet<EntityEquipmentSlot> slots) {
		if (slots == null)
			plData.stats().regenResource(Enums.Resources.CREATION, 0.05f);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(setEffectHeader());
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.CreationArmour.desc.1", Enums.ItemDescriptionType.POSITIVE));
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.CreationArmour.desc.2", Enums.ItemDescriptionType.POSITIVE));
		tooltip.add(ItemUtil.getFormattedDescriptionText("items.description.skillXpBonus", Enums.ItemDescriptionType.POSITIVE, Integer.toString(30), StringUtil.getLocaleString("skills.creation.name")));
		tooltip.add(ItemUtil.getFormattedLevelRestrictedDescriptionText(Enums.Skills.CREATION, 100));
	}
}
