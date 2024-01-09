package net.tslat.aoa3.content.item.weapon.blaster;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.entity.PartEntity;
import net.tslat.aoa3.common.registration.AoADataAttachments;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.projectile.blaster.IroMinerShotEntity;
import net.tslat.aoa3.content.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.LocaleUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

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
	public void fireBlaster(ServerLevel level, LivingEntity shooter, ItemStack blaster) {
		shooter.level().addFreshEntity(new IroMinerShotEntity(shooter, this, 60));
	}

	@Override
	public boolean doEntityImpact(BaseEnergyShot shot, Entity target, LivingEntity shooter) {
		ItemStack heldStack = shooter.getMainHandItem();
		float damageMod = 1;

		if (heldStack.getItem() == this) {
			float damageScaling = heldStack.getData(AoADataAttachments.DAMAGE_SCALING);
			UUID lastTarget = heldStack.getData(AoADataAttachments.LAST_TARGET);
			UUID targetUUID = target instanceof PartEntity<?> partEntity ? partEntity.getParent().getUUID() : target.getUUID();

			if (targetUUID.equals(lastTarget)) {
				damageMod = damageScaling + 0.02f;
				heldStack.setData(AoADataAttachments.DAMAGE_SCALING, damageMod);
			}
			else {
				heldStack.setData(AoADataAttachments.LAST_TARGET, targetUUID);
				heldStack.setData(AoADataAttachments.DAMAGE_SCALING, 1f);
			}
		}

		return DamageUtil.doEnergyProjectileAttack(shooter, shot, target, (float)getDamage() * damageMod);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
