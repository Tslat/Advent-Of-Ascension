package net.tslat.aoa3.content.item.weapon.sword;

import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.tslat.aoa3.common.registration.item.AoATiers;
import net.tslat.aoa3.content.capability.volatilestack.VolatileStackCapabilityProvider;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.smartbrainlib.util.RandomUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RosidianSword extends BaseSword {
	public RosidianSword() {
		super(AoATiers.ROSIDIAN);
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, Player player, Entity target) {
		VolatileStackCapabilityProvider.getOrDefault(stack, Direction.NORTH).setValue(player.getAttackStrengthScale(0.0f));

		if (player.getHealth() < player.getMaxHealth()) {
			float motionX = (float)(player.getX() - target.getX()) * 0.1f;
			float motionY = (float)(player.getY() - target.getY()) * 0.1f;
			float motionZ = (float)(player.getZ() - target.getZ()) * 0.1f;

			player.level().addParticle(ParticleTypes.END_ROD, target.getX() + RandomUtil.randomScaledGaussianValue(0.2), target.getY() + target.getBbHeight() / 2f, target.getZ() + RandomUtil.randomScaledGaussianValue(0.2), motionX, motionY, motionZ);

			for (LivingEntity swipeTarget : player.level().getEntitiesOfClass(LivingEntity.class, target.getBoundingBox().inflate(1, 0.25, 1))) {
				if (swipeTarget != target && swipeTarget != player && !player.isAlliedTo(swipeTarget) && player.distanceToSqr(swipeTarget) < 9) {
					motionX = (float)(player.getX() - swipeTarget.getX()) * 0.1f;
					motionY = (float)(player.getY() - swipeTarget.getY()) * 0.1f;
					motionZ = (float)(player.getZ() - swipeTarget.getZ()) * 0.1f;

					player.level().addParticle(ParticleTypes.END_ROD, true, swipeTarget.getX() + RandomUtil.randomScaledGaussianValue(0.2), swipeTarget.getY() + target.getBbHeight() / 2f, swipeTarget.getZ() + RandomUtil.randomScaledGaussianValue(0.2), motionX, motionY, motionZ);
				}
			}
		}

		return false;
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, LivingEntity target, LivingEntity attacker, float attackCooldown) {
		if (attacker.getHealth() < attacker.getMaxHealth() && attackCooldown == 1) {
			EntityUtil.healEntity(attacker, 1);

			if (attacker instanceof Player) {
				for (LivingEntity swipeTarget : attacker.level().getEntitiesOfClass(LivingEntity.class, target.getBoundingBox().inflate(1, 0.25, 1))) {
					if (swipeTarget != target && swipeTarget != attacker && swipeTarget.getHealth() < swipeTarget.getMaxHealth() && !attacker.isAlliedTo(swipeTarget) && attacker.distanceToSqr(swipeTarget) < 9)
						EntityUtil.healEntity(attacker, 0.4f);
				}
			}
		}
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
