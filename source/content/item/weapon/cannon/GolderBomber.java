package net.tslat.aoa3.content.item.weapon.cannon;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.content.entity.projectile.base.WeaponProjectile;
import net.tslat.aoa3.content.entity.projectile.cannon.GoldenCannonballEntity;
import net.tslat.aoa3.util.LocaleUtil;

import java.util.List;

public class GolderBomber extends AoACannon {
	public GolderBomber(Item.Properties properties) {
		super(properties);
	}

	@Override
	public WeaponProjectile createProjectileEntity(Level level, WeaponFiringContext context) {
		return new GoldenCannonballEntity(level, context);
	}

	@Override
	protected void onGunFire(ServerLevel level, WeaponFiringContext context, WeaponProjectile projectile) {
		Entity bullet = createProjectileEntity(level, context).fromArmPosWithOffset(0, 0.325f);

		bullet.setDeltaMovement(projectile.asEntity().getDeltaMovement());
		level.addFreshEntity(bullet);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));

		super.appendHoverText(stack, context, tooltip, flag);
	}
}
