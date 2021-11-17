package net.tslat.aoa3.item.weapon.thrown;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effects;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.entity.projectile.thrown.GooBallEntity;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PotionUtil;

import javax.annotation.Nullable;
import java.util.List;

public class GooBall extends BaseThrownWeapon {
	public GooBall() {
		super(3.5f, 7);
	}

	@Override
	protected float getFiringSoundPitchAdjust() {
		return 0.5f;
	}

	@Override
	public BaseBullet createProjectileEntity(LivingEntity shooter, ItemStack gunStack, Hand hand) {
		return new GooBallEntity(shooter, this);
	}

	@Override
	protected void doImpactEffect(Entity target, LivingEntity shooter, BaseBullet bullet, float bulletDmgMultiplier) {
		EntityUtil.applyPotions(target, new PotionUtil.EffectBuilder(Effects.MOVEMENT_SLOWDOWN, 60).level(2));
		shooter.level.playSound(null, bullet.getX(), bullet.getY(), bullet.getZ(), AoASounds.GOO_BALL_IMPACT.get(), SoundCategory.PLAYERS, 1.0f, 1.0f);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Constants.SLOWS_TARGETS, LocaleUtil.ItemDescriptionType.BENEFICIAL));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
