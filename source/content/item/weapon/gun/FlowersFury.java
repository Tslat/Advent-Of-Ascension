package net.tslat.aoa3.content.item.weapon.gun;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.content.entity.projectile.base.WeaponProjectile;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.util.RandomUtil;
import net.tslat.tme.api.object.RayTrace;

import java.util.List;

public class FlowersFury extends AoAGun {
	public FlowersFury(Item.Properties properties) {
		super(properties);
	}

	@Override
	protected void onDamageEntity(Level level, WeaponProjectile projectile, RayTrace<?> rayTrace, Entity hitEntity, float damage) {
		if (EntityUtil.areProbablyEnemies(hitEntity, projectile.getShooter()) && RandomUtil.oneInNChance(20)) {
			/*RosidEntity rosid = new RosidEntity(AoAEntities.Minions.ROSID.get(), shooter.level);

			if (shooter instanceof Player)
				rosid.tame((Player)shooter);

			rosid.setPos(target.getX(), target.getY(), target.getZ());
			shooter.level.addFreshEntity(rosid);*/ // TODO
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, context, tooltip, flag);
	}
}
