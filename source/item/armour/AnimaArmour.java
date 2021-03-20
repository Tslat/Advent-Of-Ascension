package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.item.SkillItem;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.constant.Skills;
import net.tslat.aoa3.util.player.PlayerDataManager;

import javax.annotation.Nullable;
import java.util.List;

public class AnimaArmour extends AdventArmour implements SkillItem {
	public AnimaArmour(EquipmentSlotType slot) {
		super(ItemUtil.customArmourMaterial("aoa3:anima", 65, new int[] {6, 7, 9, 4}, 10, SoundEvents.ARMOR_EQUIP_GENERIC, 7), slot);
	}

	@Override
	public AdventArmour.Type setType() {
		return AdventArmour.Type.ANIMA;
	}

	@Override
	public Skills getSkill() {
		return Skills.ANIMA;
	}

	@Override
	public int getLevelReq() {
		return 100;
	}

	@Override
	public void addBuffs(PlayerDataManager.PlayerBuffs plBuffs, @Nullable EquipmentSlotType slot) {
		if (slot == null)
			plBuffs.addXpModifier(Skills.ANIMA, 0.3f);
	}

	@Override
	public void removeBuffs(PlayerDataManager.PlayerBuffs plBuffs, @Nullable EquipmentSlotType slot) {
		if (slot == null)
			plBuffs.removeXpModifier(Skills.ANIMA, 0.3f);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(setEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.anima_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Constants.XP_BONUS, LocaleUtil.ItemDescriptionType.BENEFICIAL, new StringTextComponent("30"), LocaleUtil.getLocaleMessage(LocaleUtil.Constants.ANIMA)));
		tooltip.add(LocaleUtil.getFormattedLevelRestrictedDescriptionText(Skills.ANIMA, 100));
	}
}
