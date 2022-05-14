package net.tslat.aoa3.content.item.weapon.maul;

import com.google.common.collect.Multimap;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.content.capability.volatilestack.VolatileStackCapabilityHandles;
import net.tslat.aoa3.content.capability.volatilestack.VolatileStackCapabilityProvider;
import net.tslat.aoa3.library.constant.AttackSpeed;
import net.tslat.aoa3.player.resource.AoAResource;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;
import java.util.ConcurrentModificationException;
import java.util.List;

public class ElectronMaul extends BaseMaul {
	public ElectronMaul() {
		super(25.0f, AttackSpeed.THIRD, 6.5d, 1500);
	}

	@Override
	public void inventoryTick(ItemStack stack, Level world, Entity entity, int itemSlot, boolean isSelected) {
		if (world.getGameTime() % 10 == 0 && entity instanceof LivingEntity) {
			VolatileStackCapabilityHandles cap = VolatileStackCapabilityProvider.getOrDefault(stack, null);

			try {
				if (isSelected) {
					float currentKnockbackMod = cap.getValue();
					float currentCalcBuff = getKnockbackMultiplier(entity);

					if (currentKnockbackMod != currentCalcBuff) {
						((LivingEntity)entity).getAttributes().removeAttributeModifiers(getAttributeModifiers(EquipmentSlot.MAINHAND, stack));
						cap.setValue(currentCalcBuff);
						((LivingEntity)entity).getAttributes().addTransientAttributeModifiers(getAttributeModifiers(EquipmentSlot.MAINHAND, stack));
					}
				}
				else if (cap.getValue() != 0 && ((LivingEntity)entity).getMainHandItem().isEmpty()) {
					((LivingEntity)entity).getAttributes().removeAttributeModifiers(getAttributeModifiers(EquipmentSlot.MAINHAND, stack));
					cap.setValue(0);
				}
			}
			catch (ConcurrentModificationException ex) {
				// Don't really have a way of pre-empting this issue, and I hate this solution but idk what else I can do
			}
		}
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, Entity target, LivingEntity attacker, float attackCooldown) {
		VolatileStackCapabilityHandles cap = VolatileStackCapabilityProvider.getOrDefault(stack, null);

		if (cap.getValue() > 0.75f)
			WorldUtil.spawnLightning((ServerLevel)attacker.level, (ServerPlayer)attacker, target.getX(), target.getY(), target.getZ(), false, false);

		if (attacker instanceof ServerPlayer player) {
			AoAResource.Instance spirit = PlayerUtil.getResource(player, AoAResources.SPIRIT.get());

			spirit.consume(spirit.getCurrentValue(), true);
		}
	}

	private float getKnockbackMultiplier(Entity holder) {
		if (holder instanceof ServerPlayer) {
			AoAResource.Instance spirit = PlayerUtil.getResource((ServerPlayer)holder, AoAResources.SPIRIT.get());

			return 1 + spirit.getCurrentValue() / spirit.getMaxValue();
		}

		return 1f;
	}

	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
		Multimap<Attribute, AttributeModifier> modifierMap =  super.getAttributeModifiers(slot, stack);

		if (slot == EquipmentSlot.MAINHAND) {
			VolatileStackCapabilityHandles cap = VolatileStackCapabilityProvider.getOrDefault(stack, null);

			ItemUtil.setAttribute(modifierMap, Attributes.ATTACK_KNOCKBACK, KNOCKBACK_MODIFIER_UUID, getBaseKnockback() * (cap.getValue() == 0 ? 1 : cap.getValue()));
		}

		return modifierMap;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
	}
}
