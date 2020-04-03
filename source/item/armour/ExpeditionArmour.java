package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingFallEvent;
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

import static net.tslat.aoa3.common.registration.MaterialsRegister.ARMOUR_EXPEDITION;

public class ExpeditionArmour extends AdventArmour implements SkillItem {
	public ExpeditionArmour(String name, String registryName, EntityEquipmentSlot slot) {
		super(ARMOUR_EXPEDITION, name, registryName, slot);
	}

	@Override
	public Enums.ArmourSets setType() {
		return Enums.ArmourSets.EXPEDITION;
	}

	@Override
	public Enums.Skills getSkill() {
		return Enums.Skills.EXPEDITION;
	}

	@Override
	public int getLevelReq() {
		return 100;
	}

	@Override
	public void addBuffs(PlayerDataManager.PlayerBuffs plBuffs, @Nullable EntityEquipmentSlot slot) {
		if (slot == null)
			plBuffs.addXpModifier(Enums.Skills.EXPEDITION, 0.3f);
	}

	@Override
	public void removeBuffs(PlayerDataManager.PlayerBuffs plBuffs, @Nullable EntityEquipmentSlot slot) {
		if (slot == null)
			plBuffs.removeXpModifier(Enums.Skills.EXPEDITION, 0.3f);
	}

	@Override
	public void onUnequip(PlayerDataManager plData, @Nullable EntityEquipmentSlot slot) {
		if (slot == null) {
			PotionEffect nightVision = plData.player().getActivePotionEffect(MobEffects.NIGHT_VISION);

			if (nightVision != null && nightVision.getDuration() <= 300)
				plData.player().removePotionEffect(MobEffects.NIGHT_VISION);
		}
	}

	@Override
	public void onPlayerLandingFall(PlayerDataManager plData, @Nullable HashSet<EntityEquipmentSlot> slots, LivingFallEvent event) {
		if (slots == null)
			event.setDamageMultiplier(event.getDamageMultiplier() * 0.5f);
	}

	@Override
	public void onEffectTick(PlayerDataManager plData, @Nullable HashSet<EntityEquipmentSlot> slots) {
		if (slots == null) {
			EntityPlayer pl = plData.player();

			pl.addPotionEffect(new PotionEffect(MobEffects.SPEED, -1, 1, true, false));
			pl.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, -1, 0, true, false));

			PotionEffect nightVision = pl.getActivePotionEffect(MobEffects.NIGHT_VISION);

			if (nightVision == null || nightVision.getDuration() < 250)
				pl.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 300, 0, true, false));
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(setEffectHeader());
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.ExpeditionArmour.desc.1", Enums.ItemDescriptionType.POSITIVE));
		tooltip.add(ItemUtil.getFormattedDescriptionText("items.description.skillXpBonus", Enums.ItemDescriptionType.POSITIVE, Integer.toString(30), StringUtil.getLocaleString("skills.expedition.name")));
		tooltip.add(ItemUtil.getFormattedLevelRestrictedDescriptionText(Enums.Skills.EXPEDITION, 100));
	}
}
