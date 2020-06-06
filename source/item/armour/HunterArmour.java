package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.item.SkillItem;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.library.misc.AoAAttributes;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.StringUtil;
import net.tslat.aoa3.utils.player.PlayerDataManager;
import net.tslat.aoa3.utils.skills.HunterUtil;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

import static net.tslat.aoa3.common.registration.MaterialsRegister.ARMOUR_HUNTER;

public class HunterArmour extends AdventArmour implements SkillItem {
	public HunterArmour(String name, String registryName, EntityEquipmentSlot slot) {
		super(ARMOUR_HUNTER, name, registryName, slot);
	}

	@Override
	public Enums.ArmourSets setType() {
		return Enums.ArmourSets.HUNTER;
	}

	@Override
	public Enums.Skills getSkill() {
		return Enums.Skills.HUNTER;
	}

	@Override
	public int getLevelReq() {
		return 100;
	}

	@Override
	public void addBuffs(PlayerDataManager.PlayerBuffs plBuffs, @Nullable EntityEquipmentSlot slot) {
		if (slot == null)
			plBuffs.addXpModifier(Enums.Skills.HUNTER, 0.3f);
	}

	@Override
	public void removeBuffs(PlayerDataManager.PlayerBuffs plBuffs, @Nullable EntityEquipmentSlot slot) {
		if (slot == null)
			plBuffs.removeXpModifier(Enums.Skills.HUNTER, 0.3f);
	}

	@Override
	public void onEquip(PlayerDataManager plData, @Nullable EntityEquipmentSlot slot) {
		EntityUtil.applyAttributeModifierSafely(plData.player(), SharedMonsterAttributes.KNOCKBACK_RESISTANCE, AoAAttributes.HUNTER_ARMOUR_KNOCKBACK);
	}

	@Override
	public void onUnequip(PlayerDataManager plData, @Nullable EntityEquipmentSlot slot) {
		EntityUtil.removeAttributeModifier(plData.player(), SharedMonsterAttributes.KNOCKBACK_RESISTANCE, AoAAttributes.HUNTER_ARMOUR_KNOCKBACK);
	}

	@Override
	public void onDamageDealt(PlayerDataManager plData, @Nullable HashSet<EntityEquipmentSlot> slots, LivingHurtEvent event) {
		if (slots == null && HunterUtil.isHunterCreature(event.getEntityLiving()))
			event.setAmount(event.getAmount() * 1.15f);
	}

	@Override
	public void onAttackReceived(PlayerDataManager plData, @Nullable HashSet<EntityEquipmentSlot> slots, LivingHurtEvent event) {
		if (slots == null && event.getSource().getTrueSource() instanceof EntityLivingBase && HunterUtil.isHunterCreature((EntityLivingBase)event.getSource().getTrueSource()))
			event.setAmount(event.getAmount() * 0.85f);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(setEffectHeader());
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.HunterArmour.desc.1", Enums.ItemDescriptionType.POSITIVE));
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.HunterArmour.desc.2", Enums.ItemDescriptionType.POSITIVE));
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.HunterArmour.desc.3", Enums.ItemDescriptionType.POSITIVE));
		tooltip.add(ItemUtil.getFormattedDescriptionText("items.description.skillXpBonus", Enums.ItemDescriptionType.POSITIVE, Integer.toString(30), StringUtil.getLocaleString("skills.hunter.name")));
		tooltip.add(ItemUtil.getFormattedLevelRestrictedDescriptionText(Enums.Skills.HUNTER, 100));
	}
}
