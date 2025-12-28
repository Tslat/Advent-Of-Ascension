package net.tslat.aoa3.content.item.weapon.bow;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.object.positiongenerator.fixed.InCirclePositionGenerator;

import java.util.List;

public class DaybreakerBow extends AoABow {
	public DaybreakerBow(Item.Properties properties) {
		super(properties);
	}

	@Override
	public int getProjectileCount(ItemStack weaponStack, ItemStack ammoStack, LivingEntity shooter, float power) {
		return shooter.getXRot() < -70 ? 9 : 1;
	}

	@Override
	public void fireArrows(ItemStack bowStack, ProjectileWeaponItem weaponItem, ItemStack ammoStack, List<ItemStack> projectileItems, ServerLevel level, LivingEntity shooter, float power, float baseDamage, boolean infiniteAmmo) {
		final EquipmentSlot slot = LivingEntity.getSlotForHand(shooter.getUsedItemHand());
		final float spread = EnchantmentHelper.processProjectileSpread(level, bowStack, shooter, 0);
		final InCirclePositionGenerator offsetGenerator = InCirclePositionGenerator.create(Vec3.ZERO, spread, projectileItems.size() - 1);
		int index = 0;

		for (ItemStack projectileItem : projectileItems) {
			if (projectileItem.isEmpty())
				continue;

			Projectile projectile = makeArrow(shooter, this, bowStack, ammoStack, power, getBowDamage(bowStack), infiniteAmmo);
			float verticalAngleAdjust = 0;
			float horizontalAngleAdjust = 0;

			if (index > 0) {
				Vec3 offset = offsetGenerator.supplyPosition(level, shooter.getRandom());
				verticalAngleAdjust = Mth.cos((float)offset.x);
				horizontalAngleAdjust = Mth.sin((float)offset.z);
			}

			shootProjectile(shooter, projectile, index, power * 3f, 1f, verticalAngleAdjust, horizontalAngleAdjust, null);
			level.addFreshEntity(projectile);
			bowStack.hurtAndBreak(getDurabilityUse(bowStack), shooter, slot);

			if (bowStack.isEmpty())
				break;

			index++;
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, context, tooltip, flag);
	}
}
