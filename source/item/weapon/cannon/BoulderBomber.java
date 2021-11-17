package net.tslat.aoa3.item.weapon.cannon;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.projectile.cannon.HeavyCannonballEntity;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.List;

public class BoulderBomber extends BaseCannon {
	public BoulderBomber(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(dmg, durability, firingDelayTicks, recoil);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_BIG_BLAST_FIRE.get();
	}

	@Override
	protected boolean fireGun(LivingEntity shooter, ItemStack stack, Hand hand) {
		if (super.fireGun(shooter, stack, hand)) {
			if (!shooter.level.isClientSide)
				shooter.level.addFreshEntity(new HeavyCannonballEntity(shooter, this, hand, 120, 0, 0, 0.325f, 0));

			return true;
		}

		return false;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
