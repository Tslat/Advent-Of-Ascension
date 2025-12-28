package net.tslat.aoa3.content.item.weapon.shotgun;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.networking.AoANetworking;
import net.tslat.aoa3.common.networking.packets.GunRecoilPacket;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.common.registration.item.AoADataComponents;
import net.tslat.aoa3.common.registration.item.AoAEnchantments;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.entity.projectile.base.PhysicalWeaponProjectile;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.content.entity.projectile.base.WeaponProjectile;
import net.tslat.aoa3.content.item.datacomponent.ShotgunStats;
import net.tslat.aoa3.content.item.weapon.gun.AoAGun;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.tme.api.util.RandomUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AoAShotgun extends AoAGun {
	public AoAShotgun(Item.Properties properties) {
		super(properties);
	}

	public ShotgunStats shotgunStats() {
		return shotgunStats(getDefaultInstance());
	}

	public ShotgunStats shotgunStats(ItemStack stack) {
		return stack.get(AoADataComponents.SHOTGUN_STATS.get());
	}

	public int getPelletCount(ItemStack stack) {
		return shotgunStats(stack).pellets();
	}

	public float getPelletSpread(ItemStack stack) {
		return shotgunStats(stack).pelletSpread();
	}

	protected float getSpreadFactor(Level level, @Nullable Entity shooter, ItemStack stack, int pellets) {
		float baseSpread = getPelletSpread(stack);

		return level instanceof ServerLevel serverLevel ? AoAEnchantments.modifyPelletSpread(serverLevel, stack, baseSpread) : baseSpread;
	}

	@Override
	public WeaponFiringContext.Builder createFiringContext(ItemStack stack, @Nullable Entity shooter, InteractionHand hand) {
		return super.createFiringContext(stack, shooter, hand).multiplyDamage(1 / (float)getPelletCount(stack)).lifespan(4);
	}

	@Override
	public WeaponProjectile createProjectileEntity(Level level, WeaponFiringContext context) {
		return new PhysicalWeaponProjectile(AoAProjectiles.METAL_SLUG.get(), level, context);
	}

	@Nullable
	@Override
	protected WeaponProjectile fireGun(ServerLevel level, WeaponFiringContext context) {
		WeaponProjectile projectile = findAndConsumeAmmo(level, context);

		if (projectile == null || !(projectile.asEntity() instanceof Entity bullet))
			return null;

		projectile.fromArmPos().shootingAtTarget(context.velocity(), context.inaccuracy());

		if (level.addFreshEntity(bullet)) {
			ItemStack weaponStack = context.weaponStack();
			int pellets = getPelletCount(weaponStack);
			float spreadFactor = getSpreadFactor(level, projectile.getShooter(), weaponStack, pellets);

			for (int i = 0; i < pellets - 1; i++) {
				fireAdditionalPellet(level, context, weaponStack, pellets, spreadFactor);
			}

			onGunFire(level, context, projectile);
			doFiringEffects(level, context, projectile);
		}

		return projectile;
	}

	protected void fireAdditionalPellet(ServerLevel level, WeaponFiringContext context, ItemStack weaponStack, int pellets, float spreadFactor) {
		WeaponProjectile projectile2 = createProjectileEntity(level, context);

		projectile2.fromArmPos().shootingAtTarget(context.velocity(), context.inaccuracy()).offsetVelocity(new Vec3(RandomUtil.scaledGaussianValue(0.5f * spreadFactor),
																													RandomUtil.scaledGaussianValue(0.5f * spreadFactor),
																													RandomUtil.scaledGaussianValue(0.5f * spreadFactor)));

		if (projectile2.asEntity() instanceof Entity bullet2)
			level.addFreshEntity(bullet2);
	}

	@Override
	public void doRecoil(ServerPlayer player, WeaponFiringContext context, WeaponProjectile projectile) {
		ItemStack stack = context.weaponStack();
		float pellets = getPelletCount(stack);
		float recoilAmount = AoAEnchantments.modifyRecoil(player.serverLevel(), stack, getRecoilForShot(stack, player) * 2 / pellets);

		AoANetworking.sendToPlayer(player, new GunRecoilPacket(context.weaponHand() == InteractionHand.OFF_HAND ? recoilAmount * 1.25f : recoilAmount));
	}

	@Override
	public ItemStack getDefaultCreativeAmmo(@Nullable Player player, ItemStack weapon) {
		ItemStack defaultAmmo = super.getDefaultCreativeAmmo(player, weapon);

		return defaultAmmo.is(AoAItems.LIMONITE_BULLET) ? defaultAmmo : AoAItems.SPREADSHOT.toStack();
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		super.appendHoverText(stack, context, tooltip, flag);

		float dmg = getGunDamage(stack);

		if (dmg > 0)
			tooltip.set(1, LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.SHOTGUN_DAMAGE, LocaleUtil.ItemDescriptionType.ITEM_DAMAGE, Component.literal(NumberUtil.roundToNthDecimalPlace(dmg, 2)), LocaleUtil.numToComponent(getPelletCount(stack))));
	}
}
