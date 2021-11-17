package net.tslat.aoa3.item.weapon.maul;

import com.google.common.collect.Multimap;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.tslat.aoa3.capabilities.volatilestack.VolatileStackCapabilityHandles;
import net.tslat.aoa3.capabilities.volatilestack.VolatileStackCapabilityProvider;
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.player.resource.AoAResource;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.aoa3.util.WorldUtil;
import net.tslat.aoa3.util.misc.AttackSpeed;

import javax.annotation.Nullable;
import java.util.ConcurrentModificationException;
import java.util.List;

public class ElectronMaul extends BaseMaul {
	public ElectronMaul() {
		super(25.0f, AttackSpeed.THIRD, 6.5d, 1500);
	}

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		if (world.getGameTime() % 10 == 0 && entity instanceof LivingEntity) {
			VolatileStackCapabilityHandles cap = VolatileStackCapabilityProvider.getOrDefault(stack, null);

			try {
				if (isSelected) {
					float currentKnockbackMod = cap.getValue();
					float currentCalcBuff = getKnockbackMultiplier(entity);

					if (currentKnockbackMod != currentCalcBuff) {
						((LivingEntity)entity).getAttributes().removeAttributeModifiers(getAttributeModifiers(EquipmentSlotType.MAINHAND, stack));
						cap.setValue(currentCalcBuff);
						((LivingEntity)entity).getAttributes().addTransientAttributeModifiers(getAttributeModifiers(EquipmentSlotType.MAINHAND, stack));
					}
				}
				else if (cap.getValue() != 0 && ((LivingEntity)entity).getMainHandItem().isEmpty()) {
					((LivingEntity)entity).getAttributes().removeAttributeModifiers(getAttributeModifiers(EquipmentSlotType.MAINHAND, stack));
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
			WorldUtil.spawnLightning((ServerWorld)attacker.level, (ServerPlayerEntity)attacker, target.getX(), target.getY(), target.getZ(), false);
	}

	private float getKnockbackMultiplier(Entity holder) {
		if (holder instanceof ServerPlayerEntity) {
			AoAResource.Instance spirit = PlayerUtil.getResource((ServerPlayerEntity)holder, AoAResources.SPIRIT.get()); // TODO Check this

			return 1 + spirit.getCurrentValue() / spirit.getMaxValue();
		}

		return 1f;
	}

	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType slot, ItemStack stack) {
		Multimap<Attribute, AttributeModifier> modifierMap =  super.getAttributeModifiers(slot, stack);

		if (slot == EquipmentSlotType.MAINHAND) {
			VolatileStackCapabilityHandles cap = VolatileStackCapabilityProvider.getOrDefault(stack, null);

			ItemUtil.setAttribute(modifierMap, Attributes.ATTACK_KNOCKBACK, KNOCKBACK_MODIFIER_UUID, getBaseKnockback() * (cap.getValue() == 0 ? 1 : cap.getValue()));
		}

		return modifierMap;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
	}
}
