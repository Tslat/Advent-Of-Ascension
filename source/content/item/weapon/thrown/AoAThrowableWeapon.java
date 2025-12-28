package net.tslat.aoa3.content.item.weapon.thrown;

import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.item.AoADataComponents;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.content.entity.projectile.base.WeaponProjectile;
import net.tslat.aoa3.content.item.ProjectileFiringWeapon;
import net.tslat.aoa3.content.item.datacomponent.ThrownWeaponStats;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.tme.api.util.RandomUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class AoAThrowableWeapon extends Item implements ProjectileItem, ProjectileFiringWeapon {
	public AoAThrowableWeapon(Item.Properties properties) {
		super(properties);
	}

	public ThrownWeaponStats getThrownWeaponStats() {
		return getThrownWeaponStats(getDefaultInstance());
	}

	public ThrownWeaponStats getThrownWeaponStats(ItemStack stack) {
		final ThrownWeaponStats stats = stack.get(AoADataComponents.THROWN_WEAPON_STATS);

		return stats != null ? stats : getThrownWeaponStats();
	}

	public float getImpactDamage(ItemStack stack) {
		return getThrownWeaponStats(stack).impactDamage();
	}

	public int getTicksBetweenThrows(ItemStack stack) {
		return getThrownWeaponStats(stack).ticksBetweenThrows();
	}

	@Override
	public boolean isEnchantable(ItemStack stack) {
		return false;
	}

	@Nullable
	public SoundEvent getThrowSound() {
		return SoundEvents.SNOWBALL_THROW;
	}

	public abstract WeaponProjectile createProjectileEntity(Level level, WeaponFiringContext context);
	protected void onProjectileThrown(ServerLevel level, WeaponFiringContext context, WeaponProjectile projectile) {}

	public WeaponFiringContext.Builder createFiringContext(ItemStack stack, @Nullable Entity shooter, InteractionHand hand) {
		return WeaponFiringContext.Builder.of(stack.copy(), shooter, hand).degreesInaccuracy(2f).damage(getImpactDamage(stack));
	}

	@Override
	public ThrowableItemProjectile asProjectile(Level level, Position position, ItemStack stack, Direction direction) {
		ThrowableItemProjectile projectile = createProjectileEntity(level, WeaponFiringContext.Builder.of(stack, null).build()).asEntity();

		projectile.setPos(position.x(), position.y(), position.z());

		return projectile;
	}

	@Nullable
	public WeaponProjectile findAndConsumeAmmo(Level level, WeaponFiringContext context, ItemStack stack) {
		if (context.getShooter() instanceof Player pl)
			stack.consume(1, pl);

		return createProjectileEntity(level, context);
	}

	protected void doThrowEffects(ServerLevel level, WeaponFiringContext context, WeaponProjectile projectile) {
		Vec3 pos = projectile.asEntity().position();
		SoundEvent throwSound = getThrowSound();

		if (throwSound != null)
			level.playSound(null, pos.x, pos.y, pos.z, throwSound, SoundSource.NEUTRAL, 0.5f, 0.4f / (float)RandomUtil.valueBetween(0.8f, 1.2f));
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);

		if (player.isUsingItem() && player.isBlocking())
			return InteractionResultHolder.pass(stack);

		if (player.getAttackStrengthScale(0) < 1)
			return InteractionResultHolder.fail(stack);

		if (level instanceof ServerLevel serverLevel) {
			WeaponFiringContext context = createFiringContext(stack, player, hand).build();
			WeaponProjectile projectile = throwProjectile(serverLevel, context, stack);

			if (projectile != null) {
				player.awardStat(Stats.ITEM_USED.get(this));

				int cooldown = getTicksBetweenThrows(stack);

				if (cooldown > 1)
					player.getCooldowns().addCooldown(this, cooldown);
			}
		}

		return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
	}

	@Nullable
	public WeaponProjectile throwProjectile(ServerLevel level, WeaponFiringContext context, ItemStack stack) {
		WeaponProjectile projectile = findAndConsumeAmmo(level, context, stack);

		if (projectile == null || !(projectile.asEntity() instanceof Entity projectileEntity))
			return null;

		projectile.fromEyePos().shootingStraight(context.velocity(), context.inaccuracy());

		if (level.addFreshEntity(projectileEntity)) {
			onProjectileThrown(level, context, projectile);
			doThrowEffects(level, context, projectile);
		}

		return projectile;
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		float impactDamage = getImpactDamage(stack);

		if (impactDamage > 0)
			tooltip.add(1, LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.RANGED_DAMAGE, LocaleUtil.ItemDescriptionType.ITEM_DAMAGE, LocaleUtil.numToComponent(impactDamage)));

		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.THROWN_WEAPON, LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.THROWN_WEAPON_RATE, LocaleUtil.ItemDescriptionType.NEUTRAL, Component.literal(NumberUtil.roundToNthDecimalPlace(20 / (float)getTicksBetweenThrows(stack), 2))));
	}
}
