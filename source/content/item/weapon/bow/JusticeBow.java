package net.tslat.aoa3.content.item.weapon.bow;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.phys.EntityHitResult;
import net.tslat.aoa3.util.LocaleUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class JusticeBow extends AoABow {
	public JusticeBow(Item.Properties properties) {
		super(properties);
	}

	@Override
	public float getArrowDamage(Projectile projectile, @Nullable Entity shooter, EntityHitResult hitResult, ItemStack stack, float baseDamage, float velocity, boolean isCritical) {
		float damage = super.getArrowDamage(projectile, shooter, hitResult, stack, baseDamage, velocity, isCritical);

		return shooter instanceof LivingEntity livingShooter && livingShooter.getLastHurtByMob() == hitResult.getEntity() ? damage * 1.25f : damage;
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, context, tooltip, flag);
	}
}
