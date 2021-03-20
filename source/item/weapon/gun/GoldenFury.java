package net.tslat.aoa3.item.weapon.gun;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.tslat.aoa3.common.registration.AoAEnchantments;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.entity.projectile.gun.LimoniteBulletEntity;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.List;

public class GoldenFury extends BaseGun {
	public GoldenFury(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(AoAItemGroups.GUNS, dmg, durability, firingDelayTicks, recoil);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_FAST_RIFLE_FIRE.get();
	}

	@Override
	public BaseBullet findAndConsumeAmmo(PlayerEntity player, ItemStack gunStack, Hand hand) {
		if (ItemUtil.findInventoryItem(player, new ItemStack(AoAItems.LIMONITE_BULLET.get()), true, 1 + EnchantmentHelper.getItemEnchantmentLevel(AoAEnchantments.GREED.get(), gunStack))) {
			LimoniteBulletEntity bullet = new LimoniteBulletEntity(player, (BaseGun)gunStack.getItem(), hand, 120, 0);

			if (!player.level.isClientSide) {
				for (int i = 0; i < 6; i++) {
					((ServerWorld)player.level).sendParticles(ParticleTypes.DRAGON_BREATH, bullet.getX() + random.nextGaussian() / 5d, bullet.getY() + random.nextGaussian() / 5d, bullet.getZ() + random.nextGaussian() / 5d, 1, 0, 0, 0, 0d);
				}
			}

			return bullet;
		}

		return null;
	}

	@Override
	protected void doImpactEffect(Entity target, LivingEntity shooter, BaseBullet bullet, float bulletDmgMultiplier) {
		AreaEffectCloudEntity cloud = new AreaEffectCloudEntity(bullet.level, (target.getX() + bullet.getX()) / 2d, (target.getY() + bullet.getY()) / 2d, (target.getZ() + bullet.getZ()) / 2d);

		cloud.setOwner(shooter);
		cloud.setParticle(ParticleTypes.DRAGON_BREATH);
		cloud.setRadius(1f);
		cloud.setDuration(20);
		cloud.setRadiusPerTick((5.0F - cloud.getRadius()) / (float)cloud.getDuration());
		cloud.addEffect(new EffectInstance(Effects.HARM, 1, 0));

		bullet.level.addFreshEntity(cloud);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
