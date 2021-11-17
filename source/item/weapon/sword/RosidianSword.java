package net.tslat.aoa3.item.weapon.sword;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.tslat.aoa3.capabilities.volatilestack.VolatileStackCapabilityProvider;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.misc.AttackSpeed;

import javax.annotation.Nullable;
import java.util.List;

public class RosidianSword extends BaseSword {
	public RosidianSword() {
		super(ItemUtil.customItemTier(2000, AttackSpeed.NORMAL, 15.5f, 4, 10, null));
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity target) {
		VolatileStackCapabilityProvider.getOrDefault(stack, Direction.NORTH).setValue(player.getAttackStrengthScale(0.0f));

		if (player.getHealth() < player.getMaxHealth()) {
			float motionX = (float)(player.getX() - target.getX()) * 0.1f;
			float motionY = (float)(player.getY() - target.getY()) * 0.1f;
			float motionZ = (float)(player.getZ() - target.getZ()) * 0.1f;

			player.level.addParticle(ParticleTypes.END_ROD, target.getX() + random.nextGaussian() * 0.2, target.getY() + target.getBbHeight() / 2f, target.getZ() + random.nextGaussian() * 0.2, motionX, motionY, motionZ);

			for (LivingEntity swipeTarget : player.level.getEntitiesOfClass(LivingEntity.class, target.getBoundingBox().inflate(1, 0.25, 1))) {
				if (swipeTarget != target && swipeTarget != player && !player.isAlliedTo(swipeTarget) && player.distanceToSqr(swipeTarget) < 9) {
					motionX = (float)(player.getX() - swipeTarget.getX()) * 0.1f;
					motionY = (float)(player.getY() - swipeTarget.getY()) * 0.1f;
					motionZ = (float)(player.getZ() - swipeTarget.getZ()) * 0.1f;

					player.level.addParticle(ParticleTypes.END_ROD, true, swipeTarget.getX() + random.nextGaussian() * 0.2, swipeTarget.getY() + target.getBbHeight() / 2f, swipeTarget.getZ() + random.nextGaussian() * 0.2, motionX, motionY, motionZ);
				}
			}
		}

		return false;
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, LivingEntity target, LivingEntity attacker, float attackCooldown) {
		if (attacker.getHealth() < attacker.getMaxHealth() && attackCooldown == 1) {
			EntityUtil.healEntity(attacker, 1);

			if (attacker instanceof PlayerEntity) {
				for (LivingEntity swipeTarget : attacker.level.getEntitiesOfClass(LivingEntity.class, target.getBoundingBox().inflate(1, 0.25, 1))) {
					if (swipeTarget != target && swipeTarget != attacker && swipeTarget.getHealth() < swipeTarget.getMaxHealth() && !attacker.isAlliedTo(swipeTarget) && attacker.distanceToSqr(swipeTarget) < 9)
						EntityUtil.healEntity(attacker, 0.4f);
				}
			}
		}
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
