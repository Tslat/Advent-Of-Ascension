package net.tslat.aoa3.content.item.weapon.sniper;

import com.google.common.collect.Multimap;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.util.LocaleUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BayonetteSR extends BaseSniper {
	public BayonetteSR(float dmg, int durability, int firingDelayTicks, float recoil) {
		super(dmg, durability, firingDelayTicks, recoil);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_GUN_SNIPER_MEDIUM_FIRE_LONG.get();
	}

	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		stack.hurtAndBreak(1, attacker, user -> user.broadcastBreakEvent(EquipmentSlot.MAINHAND));

		return true;
	}

	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot equipmentSlot, ItemStack stack) {
		Multimap<Attribute, AttributeModifier> multimap = super.getAttributeModifiers(equipmentSlot, stack);

		if (equipmentSlot == EquipmentSlot.MAINHAND)
			multimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", 9, AttributeModifier.Operation.ADDITION));

		return multimap;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		super.appendHoverText(stack, world, tooltip, flag);
		tooltip.add(LocaleUtil.getLocaleMessage("item.modifiers.mainhand"));
		tooltip.add(Component.literal("10 ").append(LocaleUtil.getLocaleMessage("attribute.name.generic.attack_damage")));
	}
}
