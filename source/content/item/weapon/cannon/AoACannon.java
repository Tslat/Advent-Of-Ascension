package net.tslat.aoa3.content.item.weapon.cannon;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentTarget;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.entity.projectile.base.PhysicalWeaponProjectile;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.content.entity.projectile.base.WeaponProjectile;
import net.tslat.aoa3.content.item.weapon.gun.AoAGun;
import net.tslat.aoa3.library.object.extension.MutableFloat;
import net.tslat.aoa3.library.object.interfaces.ToFloatFunction;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.object.RayTrace;
import org.apache.commons.lang3.mutable.MutableObject;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AoACannon extends AoAGun {
	public AoACannon(Item.Properties properties) {
		super(properties);
	}

	@Override
	protected void modifyImpactDamage(ServerLevel level, WeaponProjectile projectile, RayTrace<?> rayTrace, Entity hitEntity, DamageSource source, MutableFloat damage) {
		if (hitEntity instanceof LivingEntity target)
			damage.setValue(addCannonDamageBonus(damage.floatValue(), target));

		super.modifyImpactDamage(level, projectile, rayTrace, hitEntity, source, damage);
	}

	public float addCannonDamageBonus(float baseDamage, LivingEntity target) {
		return (float)(baseDamage * (1 + (target.getAttributeValue(Attributes.ARMOR) * 1.5 + target.getAttributeValue(Attributes.ARMOR_TOUGHNESS) * 0.5f) / 100f));
	}

	@Override
	public boolean doEntityImpact(Level level, WeaponProjectile projectile, RayTrace<?> rayTrace, Entity hitEntity) {
		if (level instanceof ServerLevel serverLevel) {
			WeaponFiringContext context = projectile.getShotContext();
			MutableFloat damage = new MutableFloat(context.damage());
			MutableObject<DamageSource> damageSource = new MutableObject<>();
			ToFloatFunction<DamageSource> damageCalculator = source -> {
				modifyImpactDamage(serverLevel, projectile, rayTrace, hitEntity, source, damage);
				damageSource.setValue(source);

				return damage.floatValue();
			};

			if (DamageUtil.doHeavyGunAttack(context.getShooter(), projectile.asEntity(), hitEntity, damageCalculator)) {
				if (hitEntity instanceof Player pl && pl.isBlocking())
					pl.disableShield();

				if (hitEntity instanceof LivingEntity livingTarget)
					DamageUtil.doIndirectKnockback(livingTarget, damage.floatValue() / 10f, projectile.asEntity().getDeltaMovement().normalize());

				if (projectile.getShooter() instanceof LivingEntity shooter) {
					EnchantmentHelper.runIterationOnItem(context.weaponStack(), LivingEntity.getSlotForHand(context.weaponHand()), shooter, (enchant, enchantLevel, enchantItem) ->
							enchant.value().doPostAttack(serverLevel, enchantLevel, enchantItem, EnchantmentTarget.DAMAGING_ENTITY, projectile.asEntity(), damageSource.getValue()));
				}

				onDamageEntity(level, projectile, rayTrace, hitEntity, damage.floatValue());

				return true;
			}

			return false;
		}

		return true;
	}

	@Override
	public WeaponProjectile createProjectileEntity(Level level, WeaponFiringContext context) {
		return new PhysicalWeaponProjectile(AoAProjectiles.CANNONBALL.get(), level, context);
	}

	@Override
	public ItemStack getDefaultCreativeAmmo(@Nullable Player player, ItemStack weapon) {
		ItemStack defaultAmmo = super.getDefaultCreativeAmmo(player, weapon);

		return defaultAmmo.is(AoAItems.LIMONITE_BULLET) ? defaultAmmo : AoAItems.CANNONBALL.toStack();
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		super.appendHoverText(stack, context, tooltip, flag);

		tooltip.add(2, LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.KNOCKBACK, LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO));
		tooltip.add(2, LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.CANNON_ARMOUR_DAMAGE, LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO));
	}
}
