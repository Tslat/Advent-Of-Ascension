package net.tslat.aoa3.content.item.weapon.gun;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.content.entity.projectile.gun.ShoeShotEntity;
import net.tslat.aoa3.util.AdvancementUtil;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.smartbrainlib.util.RandomUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ShoeFlinger extends BaseGun {
	double dmg;
	int firingDelay;

	public ShoeFlinger(float dmg, int durability, int firingDelayTicks, float recoil) {
		super(dmg, durability, firingDelayTicks, recoil);

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
		return Items.LEATHER_BOOTS;
	}

	@Override
	public BaseBullet createProjectileEntity(LivingEntity shooter, ItemStack gunStack, InteractionHand hand) {
		return new ShoeShotEntity(shooter, this, hand, 120, 0);
	}

	@Override
	protected void doImpactEffect(Entity target, LivingEntity shooter, BaseBullet bullet, Vec3 impactPos, float bulletDmgMultiplier) {
		if (target instanceof LivingEntity) {
			LivingEntity livingTarget = (LivingEntity)target;

			DamageUtil.doScaledKnockback(livingTarget, shooter, 1.35f, 1, 1, 1);

			if (shooter instanceof ServerPlayer && livingTarget.getHealth() == 0 && !target.canChangeDimensions())
				AdvancementUtil.completeAdvancement((ServerPlayer)shooter, new ResourceLocation(AdventOfAscension.MOD_ID, "overworld/la_chancla"), "shoe_flinger_boss_kill");

			if (!livingTarget.hasItemInSlot(EquipmentSlot.FEET))
				livingTarget.setItemSlot(EquipmentSlot.FEET, new ItemStack(Items.LEATHER_BOOTS));
		}
	}

	@Override
	protected void doFiringSound(LivingEntity shooter, BaseBullet bullet, ItemStack stack, InteractionHand hand) {
		shooter.level().playSound(null, shooter.getX(), shooter.getY(), shooter.getZ(), getFiringSound(), SoundSource.PLAYERS, 1.0f, getFiringSoundPitchAdjust() + (float)RandomUtil.randomScaledGaussianValue(0.01f));
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.UNIQUE, 1));

		super.appendHoverText(stack, world, tooltip, flag);
	}
}
