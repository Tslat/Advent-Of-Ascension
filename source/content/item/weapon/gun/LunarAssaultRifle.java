package net.tslat.aoa3.content.item.weapon.gun;

import it.unimi.dsi.fastutil.floats.FloatFloatPair;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.tslat.aoa3.content.entity.projectile.base.WeaponProjectile;
import net.tslat.aoa3.library.object.extension.MutableFloat;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.util.RandomUtil;
import net.tslat.tme.api.object.RayTrace;

import java.util.List;

public class LunarAssaultRifle extends AoAGun {
	public LunarAssaultRifle(Item.Properties properties) {
		super(properties);
	}

	@Override
	protected void modifyImpactDamage(ServerLevel level, WeaponProjectile projectile, RayTrace<?> rayTrace, Entity hitEntity, DamageSource source, MutableFloat damage) {
		FloatFloatPair damageRange = getDamageRange(damage.getValue());

		damage.setValue(RandomUtil.valueBetween(damageRange.leftFloat(), damageRange.rightFloat()));
		super.modifyImpactDamage(level, projectile, rayTrace, hitEntity, source, damage);
	}

	private FloatFloatPair getDamageRange(float baseDamage) {
		float deviation = baseDamage / 2f;

		return FloatFloatPair.of(baseDamage - deviation, baseDamage + deviation);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		super.appendHoverText(stack, context, tooltip, flag);

		FloatFloatPair damageRange = getDamageRange(getGunDamage(stack));
		tooltip.set(1, LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.RANDOM_DAMAGE, LocaleUtil.ItemDescriptionType.ITEM_DAMAGE, LocaleUtil.numToComponent(damageRange.leftFloat()), LocaleUtil.numToComponent(damageRange.rightFloat())));
	}
}
