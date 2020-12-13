package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.item.SkillItem;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.constant.Skills;

import javax.annotation.Nullable;
import java.util.List;

public class AlchemyArmour extends AdventArmour implements SkillItem {
	public AlchemyArmour(EquipmentSlotType slot) {
		super(ItemUtil.customArmourMaterial("aoa3:alchemy", 65, new int[] {6, 7, 9, 4}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 7), slot);
	}

	@Override
	public AdventArmour.Type setType() {
		return AdventArmour.Type.ALCHEMY;
	}

	@Override
	public Skills getSkill() {
		return Skills.ALCHEMY;
	}

	@Override
	public int getLevelReq() {
		return 100;
	}


	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedLevelRestrictedDescriptionText(Skills.ALCHEMY, 100));
	}
}
