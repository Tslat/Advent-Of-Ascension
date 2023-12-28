package net.tslat.aoa3.content.item.weapon.cannon;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.projectile.cannon.HeavyBoneCannonballEntity;
import net.tslat.aoa3.content.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.effectslib.api.util.EffectBuilder;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PredatorianBlaster extends BaseCannon {
	public PredatorianBlaster(float dmg, int durability, int firingDelayTicks, float recoil) {
		super(dmg, durability, firingDelayTicks, recoil);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_LIGHT_CANNON_FIRE.get();
	}

	@Override
	public BaseBullet createProjectileEntity(LivingEntity shooter, ItemStack gunStack, InteractionHand hand) {
		return new HeavyBoneCannonballEntity(shooter, this, hand, 120, 0);
	}

	@Override
	protected void doImpactEffect(Entity target, LivingEntity shooter, BaseBullet bullet, Vec3 impactPos, float bulletDmgMultiplier) {
		if (target instanceof LivingEntity)
			EntityUtil.applyPotions(target, new EffectBuilder(MobEffects.WITHER, 100).level(2));
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.WITHERS_TARGETS, LocaleUtil.ItemDescriptionType.BENEFICIAL));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
