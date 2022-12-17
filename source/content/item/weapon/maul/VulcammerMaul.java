package net.tslat.aoa3.content.item.weapon.maul;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoAGameRules;
import net.tslat.aoa3.library.constant.AttackSpeed;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;
import java.util.List;

public class VulcammerMaul extends BaseMaul {
	public VulcammerMaul() {
		super(28.0f, AttackSpeed.THIRD, 8.2d, 1750);
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, Entity target, LivingEntity attacker, float attackCooldown) {
		if (attackCooldown > 0.85f) {
			boolean doWorldDamage = AoAGameRules.checkDestructiveWeaponPhysics(attacker.level);

			WorldUtil.createExplosion(attacker, attacker.level, (attacker.getX() + target.getX()) / 2d, (attacker.getY() + target.getY()) / 2d, (attacker.getZ() + target.getZ()) / 2d, 2f, doWorldDamage ? Level.ExplosionInteraction.BLOCK : Level.ExplosionInteraction.NONE, doWorldDamage);

			if (!doWorldDamage) {
				for (LivingEntity entity : attacker.level.getEntitiesOfClass(LivingEntity.class, target.getBoundingBox().inflate(2), EntityUtil.Predicates.HOSTILE_MOB)) {
					entity.setSecondsOnFire(3);
				}
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
	}
}
