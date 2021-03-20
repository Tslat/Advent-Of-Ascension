package net.tslat.aoa3.item.weapon.greatblade;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeMod;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.constant.AttackSpeed;

import javax.annotation.Nullable;
import java.util.List;

public class MillenniumGreatblade extends BaseGreatblade {
	private final double baseDmg;
	private final double maxDmg;

	public MillenniumGreatblade() {
		super(26.5f, AttackSpeed.GREATBLADE, 2050);

		this.baseDmg = getAttackDamage() - (getAttackDamage() / 2d);
		this.maxDmg = getAttackDamage() + (getAttackDamage() / 2d);
	}

	@Override
	protected double getDamageForAttack(ItemStack stack, Entity target, LivingEntity attacker, double baseDmg) {
		return (float)(random.nextFloat() * (maxDmg - baseDmg) + baseDmg) - 1;
	}

	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot, ItemStack stack) {
		Multimap<Attribute, AttributeModifier> multimap = HashMultimap.<Attribute, AttributeModifier>create();

		if (equipmentSlot == EquipmentSlotType.MAINHAND) {
			multimap.put(ForgeMod.REACH_DISTANCE.get(), ATTACK_REACH_MODIFIER);
			multimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", 0, AttributeModifier.Operation.MULTIPLY_TOTAL));
			multimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", getAttackSpeed(), AttributeModifier.Operation.ADDITION));
		}

		return multimap;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Constants.RANDOM_DAMAGE, LocaleUtil.ItemDescriptionType.ITEM_DAMAGE, new StringTextComponent(Double.toString(baseDmg)), new StringTextComponent(Double.toString(maxDmg))));
	}
}
