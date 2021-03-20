package net.tslat.aoa3.item.weapon.shotgun;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.entity.projectile.gun.LimoniteBulletEntity;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.RandomUtil;

import javax.annotation.Nullable;
import java.util.List;

public class DestructionShotgun extends BaseShotgun {
	public DestructionShotgun(final double dmg, final int pellets, final int durability, final int fireDelayTicks, final float knockbackFactor, final float recoil) {
		super(dmg, pellets, durability, fireDelayTicks, knockbackFactor, recoil);
	}

	@Override
	public void fireShotgun(LivingEntity shooter, Hand hand, float spreadFactor, int pellets) {
		boolean charged = RandomUtil.oneInNChance(5);

		for (int i = 0; i < pellets; i++) {
			BaseBullet pellet = new LimoniteBulletEntity(shooter, this, hand, 4, charged ? 1.5f : 1.0f, 0, (random.nextFloat() - 0.5f) * spreadFactor, (random.nextFloat() - 0.5f) * spreadFactor, (random.nextFloat() - 0.5f) * spreadFactor);
			shooter.level.addFreshEntity(pellet);
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
