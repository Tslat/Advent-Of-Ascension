package net.tslat.aoa3.object.item.weapon.gun;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.object.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.object.entity.projectile.gun.WartDartEntity;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.List;

public class WartGun extends BaseGun {
	public WartGun(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(AoAItemGroups.GUNS, dmg, durability, firingDelayTicks, recoil);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_GUN_BLOWPIPE_SHOOT.get();
	}

	@Override
	public boolean isFullAutomatic() {
		return false;
	}

	@Override
	public Item getAmmoItem() {
		return Items.NETHER_WART;
	}

	@Override
	protected void doImpactEffect(Entity target, LivingEntity shooter, BaseBullet bullet, float bulletDmgMultiplier) {
		if (target instanceof LivingEntity)
			target.setSecondsOnFire(3);
	}

	@Override
	public BaseBullet createProjectileEntity(LivingEntity shooter, ItemStack gunStack, Hand hand) {
		return new WartDartEntity(shooter, this, hand, 120, 0);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Constants.BURNS_TARGETS, LocaleUtil.ItemDescriptionType.BENEFICIAL));

		super.appendHoverText(stack, world, tooltip, flag);
	}

	@Override
	protected void doFiringEffects(LivingEntity shooter, BaseBullet bullet, ItemStack stack, Hand hand) {
		doFiringSound(shooter, bullet, stack, hand);
	}
}
