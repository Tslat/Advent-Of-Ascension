package net.tslat.aoa3.content.item.weapon.sniper;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.networking.AoANetworking;
import net.tslat.aoa3.common.networking.packets.GunRecoilPacket;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.common.registration.item.AoADataComponents;
import net.tslat.aoa3.common.registration.item.AoAEnchantments;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.entity.projectile.base.PhysicalWeaponProjectile;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.content.entity.projectile.base.WeaponProjectile;
import net.tslat.aoa3.content.item.weapon.gun.AoAGun;
import net.tslat.aoa3.library.object.extension.MutableFloat;
import net.tslat.aoa3.util.AdvancementUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.object.RayTrace;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AoASniper extends AoAGun {
	public static final ResourceLocation BASIC_SCOPE = AdventOfAscension.id("textures/gui/overlay/scope/basic.png");
	public static final ResourceLocation CLASSIC_SCOPE = AdventOfAscension.id("textures/gui/overlay/scope/classic.png");
	public static final ResourceLocation MOA_SCOPE = AdventOfAscension.id("textures/gui/overlay/scope/moa.png");
	public static final ResourceLocation AERIAL_SCOPE = AdventOfAscension.id("textures/gui/overlay/scope/aerial.png");

	public AoASniper(Item.Properties properties) {
		super(properties);
	}

	public ResourceLocation getScopeTexture(ItemStack stack) {
		return stack.get(AoADataComponents.SNIPER_SCOPE).texture();
	}

	public static boolean isScoped(Player player) {
		return player.isCrouching() && player.onGround() && player.getMainHandItem().getItem() instanceof AoASniper;
	}

	@Override
	public WeaponFiringContext.Builder createFiringContext(ItemStack stack, @Nullable Entity shooter, InteractionHand hand) {
		boolean noscope = !(shooter instanceof LivingEntity livingShooter) || !livingShooter.onGround() || !livingShooter.isShiftKeyDown();

		return super.createFiringContext(stack, shooter, hand).velocity(20).degreesInaccuracy(noscope ? 50 : 2);
	}

	@Override
	public WeaponProjectile createProjectileEntity(Level level, WeaponFiringContext context) {
		return new PhysicalWeaponProjectile(AoAProjectiles.SNIPER_SLUG.get(), level, context);
	}

	@Override
	public void doRecoil(ServerPlayer player, WeaponFiringContext context, WeaponProjectile projectile) {
		float recoilAmount = AoAEnchantments.modifyRecoil(player.serverLevel(), context.weaponStack(), getRecoilForShot(context.weaponStack(), player) * 0.25f);

		if (!player.isShiftKeyDown() || !player.onGround())
			recoilAmount *= 3.5f;

		AoANetworking.sendToPlayer(player, new GunRecoilPacket(context.weaponHand() == InteractionHand.OFF_HAND ? recoilAmount * 1.25f : recoilAmount));
	}

	@Override
	protected void modifyImpactDamage(ServerLevel level, WeaponProjectile projectile, RayTrace<?> rayTrace, Entity hitEntity, DamageSource source, MutableFloat damage) {
		damage.multiply(0.5f * (projectile.asEntity().tickCount <= 0 ? 0.5f : 1));

		super.modifyImpactDamage(level, projectile, rayTrace, hitEntity, source, damage);
	}

	@Override
	protected void onDamageEntity(Level level, WeaponProjectile projectile, RayTrace<?> rayTrace, Entity hitEntity, float damage) {
		if (!hitEntity.isAlive() && hitEntity instanceof Phantom && projectile.asEntity().getOwner() instanceof ServerPlayer pl)
			AdvancementUtil.grantCriterion(pl, AdventOfAscension.id("completionist/skeet"), "phantom_sniper_kill");
	}

	@Override
	public ItemStack getDefaultCreativeAmmo(@Nullable Player player, ItemStack weapon) {
		ItemStack defaultAmmo = super.getDefaultCreativeAmmo(player, weapon);

		return defaultAmmo.is(AoAItems.LIMONITE_BULLET) ? defaultAmmo : AoAItems.METAL_SLUG.toStack();
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		super.appendHoverText(stack, context, tooltip, flag);

		tooltip.add(2, LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.SNIPER_CROUCH, LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO));
	}
}
