package net.tslat.aoa3.item.weapon.gun;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.entity.projectile.gun.DischargeShotEntity;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.List;

public class DischargeRifle extends BaseGun {
	private final int firingDelay;

	public DischargeRifle(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(AoAItemGroups.GUNS, dmg, durability, firingDelayTicks, recoil);
		this.firingDelay = firingDelayTicks;
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_DISCHARGE_GUN_FIRE.get();
	}

	@Override
	public Item getAmmoItem() {
		return AoAItems.DISCHARGE_CAPSULE.get();
	}

	@Override
	public BaseBullet createProjectileEntity(LivingEntity shooter, ItemStack gunStack, Hand hand) {
		return new DischargeShotEntity(shooter, this, hand, 120, 0);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		super.appendHoverText(stack, world, tooltip, flag);

		tooltip.set(1, LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Constants.EXPLODES_ON_HIT, LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}
