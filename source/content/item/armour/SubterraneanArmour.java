package net.tslat.aoa3.content.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.player.ServerPlayerDataManager;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

public class SubterraneanArmour extends AdventArmour {
	private static final AttributeModifier ATTACK_SPEED_DEBUFF = new AttributeModifier(UUID.fromString("d4631555-8ceb-490d-9066-fb4188560b15"), "AoASubterraneanAttackSpeedDebuff", -0.16666667, AttributeModifier.Operation.MULTIPLY_TOTAL);

	public SubterraneanArmour(EquipmentSlotType slot) {
		super(ItemUtil.customArmourMaterial("aoa3:subterranean", 47, new int[] {3, 7, 8, 4}, 10, SoundEvents.ARMOR_EQUIP_GENERIC, 3), slot);
	}

	@Override
	public AdventArmour.Type setType() {
		return AdventArmour.Type.SUBTERRANEAN;
	}

	@Override
	public void onEquip(ServerPlayerDataManager plData, @Nullable EquipmentSlotType slot) {
		if (slot == null)
			EntityUtil.applyAttributeModifierSafely(plData.player(), Attributes.ATTACK_SPEED, ATTACK_SPEED_DEBUFF, false);
	}

	@Override
	public void onUnequip(ServerPlayerDataManager plData, @Nullable EquipmentSlotType slot) {
		if (slot == null)
			EntityUtil.removeAttributeModifier(plData.player(), Attributes.ATTACK_SPEED, ATTACK_SPEED_DEBUFF);
	}

	@Override
	public void onEffectTick(ServerPlayerDataManager plData, @Nullable HashSet<EquipmentSlotType> slots) {
		if (slots == null)
			plData.player().addEffect(new EffectInstance(Effects.DIG_SPEED, -1, 1, true, false));
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(setEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.subterranean_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}
