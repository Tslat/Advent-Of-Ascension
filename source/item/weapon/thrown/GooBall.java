package net.tslat.aoa3.item.weapon.thrown;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effects;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEnchantments;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.entity.projectile.thrown.GooBallEntity;
import net.tslat.aoa3.item.weapon.gun.BaseGun;
import net.tslat.aoa3.util.*;

import javax.annotation.Nullable;
import java.util.List;

public class GooBall extends BaseThrownWeapon {
	public static final float dmg = 3.5f;

	public GooBall() {
		super(dmg, 7);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return SoundEvents.ENTITY_ARROW_SHOOT;
	}

	@Override
	public BaseBullet findAndConsumeAmmo(PlayerEntity player, ItemStack weaponStack, Hand hand) {
		BaseGun item = (BaseGun)weaponStack.getItem();

		if (ItemUtil.findInventoryItem(player, new ItemStack(this), true, 1 + EnchantmentHelper.getEnchantmentLevel(AoAEnchantments.GREED.get(), weaponStack)))
			return new GooBallEntity(player, item);

		return null;
	}

	@Override
	public void doImpactDamage(Entity target, LivingEntity shooter, BaseBullet gooBall, float bulletDmgMultiplier) {
		if (target != null && DamageUtil.dealRangedDamage(target, shooter, gooBall, dmg * bulletDmgMultiplier)) {
			EntityUtil.applyPotions(target, new PotionUtil.EffectBuilder(Effects.SLOWNESS, 60).level(2));
			shooter.world.playSound(null, gooBall.getPosX(), gooBall.getPosY(), gooBall.getPosZ(), AoASounds.GOO_BALL_IMPACT.get(), SoundCategory.PLAYERS, 1.0f, 1.0f);
		}
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Constants.SLOWS_TARGETS, LocaleUtil.ItemDescriptionType.BENEFICIAL));
		super.addInformation(stack, world, tooltip, flag);
	}
}
