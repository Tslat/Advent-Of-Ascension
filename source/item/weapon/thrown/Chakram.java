package net.tslat.aoa3.item.weapon.thrown;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effects;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEnchantments;
import net.tslat.aoa3.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.entity.projectile.thrown.ChakramEntity;
import net.tslat.aoa3.item.weapon.gun.BaseGun;
import net.tslat.aoa3.util.*;

import javax.annotation.Nullable;
import java.util.List;

public class Chakram extends BaseThrownWeapon {
	public static final float dmg = 4f;

	public Chakram() {
		super(dmg, 7);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return SoundEvents.ARROW_SHOOT;
	}

	@Override
	public BaseBullet findAndConsumeAmmo(PlayerEntity player, ItemStack weaponStack, Hand hand) {
		BaseGun item = (BaseGun)weaponStack.getItem();

		if (ItemUtil.findInventoryItem(player, new ItemStack(this), true, 1 + EnchantmentHelper.getItemEnchantmentLevel(AoAEnchantments.GREED.get(), weaponStack)))
			return new ChakramEntity(player, item);

		return null;
	}

	@Override
	public void doImpactDamage(Entity target, LivingEntity shooter, BaseBullet bullet, float bulletDmgMultiplier) {
		if (target != null && DamageUtil.dealRangedDamage(target, shooter, bullet, dmg * bulletDmgMultiplier))
			EntityUtil.applyPotions(target, new PotionUtil.EffectBuilder(Effects.POISON, 60).level(2));
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Constants.POISONS_TARGETS, LocaleUtil.ItemDescriptionType.BENEFICIAL));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
