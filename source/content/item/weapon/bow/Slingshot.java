package net.tslat.aoa3.content.item.weapon.bow;

import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.phys.EntityHitResult;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.entity.projectile.arrow.PopShotEntity;
import net.tslat.aoa3.util.EnchantmentUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.util.RandomUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Predicate;

public class Slingshot extends AoABow {
	public static final Predicate<ItemStack> AMMO_PREDICATE = stack -> stack.getItem() == AoAItems.POP_SHOT.get() || stack.getItem() == Items.FLINT;

	public Slingshot(Item.Properties properties) {
		super(properties);
	}

	@Override
	public Predicate<ItemStack> getAllSupportedProjectiles() {
		return AMMO_PREDICATE;
	}

	@Override
	public float getArrowDamage(Projectile projectile, @Nullable Entity shooter, EntityHitResult hitResult, ItemStack stack, float baseDamage, float velocity, boolean isCritical) {
		float damage = baseDamage * (velocity / 2f);

		if (isCritical)
			damage += (float)(damage + (damage * RandomUtil.scaledGaussianValue(0.35f)));

		return damage;
	}

	@Override
	public boolean isPrimaryItemFor(ItemStack stack, Holder<Enchantment> enchantment) {
		if (enchantment.is(Enchantments.PUNCH) || enchantment.is(Enchantments.FLAME))
			return false;

		return super.isPrimaryItemFor(stack, enchantment);
	}

	@Override
	public Projectile makeArrow(LivingEntity shooter, ProjectileWeaponItem weaponItem, ItemStack weaponStack, ItemStack ammoStack, float power, float baseDamage, boolean infiniteAmmo) {
		PopShotEntity popShot = new PopShotEntity(AoAProjectiles.POP_SHOT.get(), shooter, shooter.level(), ammoStack, weaponStack);
		int powerEnchant = EnchantmentUtil.getEnchantmentLevel(shooter.level(), weaponStack, Enchantments.POWER);

		popShot.setExplosive(ammoStack.is(AoAItems.POP_SHOT));

		if (powerEnchant > 0)
			popShot.setBaseDamage(baseDamage + powerEnchant + 1d);

		return applyArrowMods(popShot, shooter, ammoStack);
	}

	@Override
	public void shootProjectile(LivingEntity shooter, Projectile projectile, int index, float velocity, float inaccuracy, float verticalOffset, float lateralOffset, @Nullable LivingEntity target) {
		super.shootProjectile(shooter, projectile, index, velocity / 3f * 2f, inaccuracy, verticalOffset, lateralOffset, target);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(1, LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.ARROW_DAMAGE, LocaleUtil.ItemDescriptionType.ITEM_DAMAGE, Component.literal(Double.toString(getBowDamage(stack)))));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.BOW_DRAW_TIME, LocaleUtil.ItemDescriptionType.NEUTRAL, Component.literal(Double.toString(((72000 / getDrawSpeedMultiplier(stack)) / 720) / (double)100))));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.AMMO_ITEM, LocaleUtil.ItemDescriptionType.ITEM_AMMO_COST, LocaleUtil.getLocaleMessage(AoAItems.POP_SHOT.get().getDescriptionId()).append(Component.literal("/")).append(LocaleUtil.getLocaleMessage(Items.FLINT.getDescriptionId()))));
	}
}
