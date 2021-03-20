package net.tslat.aoa3.item.weapon.maul;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAGameRules;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;
import net.tslat.aoa3.util.constant.AttackSpeed;

import javax.annotation.Nullable;
import java.util.List;

public class VulcammerMaul extends BaseMaul {
	public VulcammerMaul() {
		super(28.0f, AttackSpeed.THIRD, 3.5D, 1750);
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, PlayerEntity attacker, Entity target, float finalDmg, float attackCooldown) {
		if (attackCooldown > 0.85f) {
			boolean doWorldDamage = WorldUtil.checkGameRule(attacker.level, AoAGameRules.DESTRUCTIVE_WEAPON_PHYSICS);

			WorldUtil.createExplosion(attacker, attacker.level, (attacker.getX() + target.getX()) / 2d, (attacker.getY() + target.getY()) / 2d, (attacker.getZ() + target.getZ()) / 2d, 2f, doWorldDamage ? Explosion.Mode.DESTROY : Explosion.Mode.NONE, doWorldDamage);

			if (!doWorldDamage) {
				for (LivingEntity entity : attacker.level.getEntitiesOfClass(LivingEntity.class, target.getBoundingBox().inflate(2), EntityUtil.Predicates.HOSTILE_MOB)) {
					entity.setSecondsOnFire(3);
				}
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
	}
}
