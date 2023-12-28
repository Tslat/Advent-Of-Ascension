package net.tslat.aoa3.content.item.weapon.cannon;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.projectile.cannon.MoonShotEntity;
import net.tslat.aoa3.content.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.LocaleUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MoonCannon extends BaseCannon {
	public MoonCannon(float dmg, int durability, int firingDelayTicks, float recoil) {
		super(dmg, durability, firingDelayTicks, recoil);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_ENERGY_CANNON_FIRE.get();
	}

	@Override
	public BaseBullet createProjectileEntity(LivingEntity shooter, ItemStack gunStack, InteractionHand hand) {
		return new MoonShotEntity(shooter, this, hand, 120, 0);
	}

	@Override
	public void doImpactDamage(Entity target, LivingEntity shooter, BaseBullet bullet, Vec3 impactPosition, float bulletDmgMultiplier) {
		if (target != null) {
			if (target instanceof LivingEntity)
				bulletDmgMultiplier *= 1 + (((LivingEntity)target).getAttribute(Attributes.ARMOR).getValue() * 1.50) / 100;

			if (DamageUtil.doHeavyGunAttack(shooter, bullet, target, (float)getDamage() * bulletDmgMultiplier * 0.75f)) {
				if (target instanceof Player && ((Player)target).isBlocking())
					((Player)target).disableShield(true);

				if (target instanceof LivingEntity livingTarget)
					DamageUtil.doScaledKnockback(livingTarget, shooter, ((float)getDamage() * 0.75f * bulletDmgMultiplier) / 20f, 1, 1, 1);
			}

			DamageUtil.doEnergyProjectileAttack(shooter, bullet, target, (float)getDamage() * bulletDmgMultiplier * 0.25f);
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
