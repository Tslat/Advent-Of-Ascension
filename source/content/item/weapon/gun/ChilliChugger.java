package net.tslat.aoa3.content.item.weapon.gun;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.AoACreativeModeTabs;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.content.entity.projectile.gun.ChilliShotEntity;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.List;

public class ChilliChugger extends BaseGun {
	double dmg;
	int firingDelay;

	public ChilliChugger(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(AoACreativeModeTabs.GUNS, dmg, durability, firingDelayTicks, recoil);
		this.dmg = dmg;
		this.firingDelay = firingDelayTicks;
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_GUN_AIR_CANNON_FIRE.get();
	}

	@Override
	protected float getFiringSoundPitchAdjust() {
		return 0.85f;
	}

	@Override
	public Item getAmmoItem() {
		return AoAItems.CHILLI.get();
	}

	@Override
	public BaseBullet createProjectileEntity(LivingEntity shooter, ItemStack gunStack, InteractionHand hand) {
		return new ChilliShotEntity(shooter, this, hand, 120, 0);
	}

	@Override
	public void doImpactDamage(Entity target, LivingEntity shooter, BaseBullet bullet, Vec3 impactPosition, float bulletDmgMultiplier) {
		super.doImpactDamage(target, shooter, bullet, impactPosition, bulletDmgMultiplier);

		if (target instanceof LivingEntity)
			target.setSecondsOnFire(6);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Constants.BURNS_TARGETS, LocaleUtil.ItemDescriptionType.BENEFICIAL));

		super.appendHoverText(stack, world, tooltip, flag);
	}
}
