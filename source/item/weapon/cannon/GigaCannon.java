package net.tslat.aoa3.item.weapon.cannon;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.projectile.cannon.GigaGreenBallEntity;
import net.tslat.aoa3.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.List;

public class GigaCannon extends BaseCannon {
	public GigaCannon(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(dmg, durability, firingDelayTicks, recoil);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_UPPER_CANNON_FIRE.get();
	}

	@Override
	public BaseBullet createProjectileEntity(LivingEntity shooter, ItemStack gunStack, Hand hand) {
		return new GigaGreenBallEntity(shooter, this, hand, 120, 0);
	}

	@Override
	public void doImpactDamage(Entity target, LivingEntity shooter, BaseBullet bullet, float bulletDmgMultiplier) {
		ItemStack gunStack = shooter.getMainHandItem();

		if (!gunStack.isEmpty() && (gunStack.getTag() == null || gunStack.getEnchantmentTags().isEmpty()))
			bulletDmgMultiplier *= 1.2f;

		super.doImpactDamage(target, shooter, bullet, bulletDmgMultiplier);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
