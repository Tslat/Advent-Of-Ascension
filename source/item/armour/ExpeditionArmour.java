package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.tslat.aoa3.item.SkillItem;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.constant.Skills;
import net.tslat.aoa3.util.player.PlayerDataManager;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

public class ExpeditionArmour extends AdventArmour implements SkillItem {
	public ExpeditionArmour(EquipmentSlotType slot) {
		super(ItemUtil.customArmourMaterial("aoa3:expedition", 65, new int[] {6, 7, 9, 4}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 7), slot);
	}

	@Override
	public AdventArmour.Type setType() {
		return AdventArmour.Type.EXPEDITION;
	}

	@Override
	public Skills getSkill() {
		return Skills.EXPEDITION;
	}

	@Override
	public int getLevelReq() {
		return 100;
	}

	@Override
	public void addBuffs(PlayerDataManager.PlayerBuffs plBuffs, @Nullable EquipmentSlotType slot) {
		if (slot == null)
			plBuffs.addXpModifier(Skills.EXPEDITION, 0.3f);
	}

	@Override
	public void removeBuffs(PlayerDataManager.PlayerBuffs plBuffs, @Nullable EquipmentSlotType slot) {
		if (slot == null)
			plBuffs.removeXpModifier(Skills.EXPEDITION, 0.3f);
	}

	@Override
	public void onUnequip(PlayerDataManager plData, @Nullable EquipmentSlotType slot) {
		if (slot == null) {
			EffectInstance nightVision = plData.player().getActivePotionEffect(Effects.NIGHT_VISION);

			if (nightVision != null && nightVision.getDuration() <= 300)
				plData.player().removePotionEffect(Effects.NIGHT_VISION);
		}
	}

	@Override
	public void onPlayerLandingFall(PlayerDataManager plData, @Nullable HashSet<EquipmentSlotType> slots, LivingFallEvent event) {
		if (slots == null)
			event.setDamageMultiplier(event.getDamageMultiplier() * 0.5f);
	}

	@Override
	public void onEffectTick(PlayerDataManager plData, @Nullable HashSet<EquipmentSlotType> slots) {
		if (slots == null) {
			PlayerEntity pl = plData.player();

			pl.addPotionEffect(new EffectInstance(Effects.SPEED, -1, 1, true, false));
			pl.addPotionEffect(new EffectInstance(Effects.WATER_BREATHING, -1, 0, true, false));

			EffectInstance nightVision = pl.getActivePotionEffect(Effects.NIGHT_VISION);

			if (nightVision == null || nightVision.getDuration() < 250)
				pl.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 300, 0, true, false));
		}
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(setEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.expedition_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Constants.XP_BONUS, LocaleUtil.ItemDescriptionType.BENEFICIAL, "30", LocaleUtil.getLocaleString(LocaleUtil.Constants.EXPEDITION)));
		tooltip.add(LocaleUtil.getFormattedLevelRestrictedDescriptionText(Skills.EXPEDITION, 100));
	}
}
