package net.tslat.aoa3.content.item.weapon.staff;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.util.RandomUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GhoulStaff extends AoAStaff<Void> {
	private static final float DAMAGE_SPREAD = 42 / ((16f + 42f) / 2f) - 1;

	public GhoulStaff(Item.Properties properties) {
		super(properties);
	}

	@Override
	public WeaponFiringContext.Builder createProjectileContext(ItemStack stack, @Nullable Entity shooter, InteractionHand hand) {
		float damage = getMagicDamage(stack);

		return super.createProjectileContext(stack, shooter, hand).damage((float)RandomUtil.valueBetween(damage * (1 - DAMAGE_SPREAD), damage * (1 + DAMAGE_SPREAD)));
	}

	@Override
	public void cast(ServerLevel level, LivingEntity caster, ItemStack staff, InteractionHand hand, Void args) {
		fireProjectile(level, caster, staff, hand, AoAProjectiles.GHOUL_SHOT);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.RANDOM_DAMAGE, LocaleUtil.ItemDescriptionType.ITEM_DAMAGE, Component.literal("16"), Component.literal("42")));
		super.appendHoverText(stack, context, tooltip, flag);
	}
}
