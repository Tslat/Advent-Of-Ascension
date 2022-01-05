package net.tslat.aoa3.object.item.weapon.blaster;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.tslat.aoa3.object.capability.volatilestack.VolatileStackCapabilityHandles;
import net.tslat.aoa3.object.capability.volatilestack.VolatileStackCapabilityProvider;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.object.entity.projectile.blaster.IroMinerShotEntity;
import net.tslat.aoa3.object.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.List;

public class IroMiner extends BaseBlaster {
	public IroMiner(double dmg, int durability, int fireDelayTicks, float energyCost) {
		super(dmg, durability, fireDelayTicks, energyCost);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_MOON_SHINER_FIRE.get();
	}

	@Override
	public void fire(ItemStack blaster, LivingEntity shooter) {
		shooter.level.addFreshEntity(new IroMinerShotEntity(shooter, this, 60));
	}

	@Override
	public boolean doEntityImpact(BaseEnergyShot shot, Entity target, LivingEntity shooter) {
		ItemStack heldStack = shooter.getMainHandItem();
		float damageMod = 1;

		if (heldStack.getItem() == this) {
			VolatileStackCapabilityHandles cap = VolatileStackCapabilityProvider.getOrDefault(heldStack, null);

			if (cap.getObject() != null &&target.getUUID().equals(cap.getObject())) {
				damageMod = cap.getValue() + 0.02f;
				cap.setValue(damageMod);
			}
			else {
				cap.setObject(target.getUUID());
				cap.setValue(1.0f);
			}
		}

		return DamageUtil.dealBlasterDamage(shooter, target, shot, (float)baseDmg * damageMod, false);
	}

	@Nullable
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {
		CompoundNBT tag = stack.getOrCreateTag();

		tag.putByte("HideFlags", (byte)2);

		return new VolatileStackCapabilityProvider();
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
