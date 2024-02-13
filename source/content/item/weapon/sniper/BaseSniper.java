package net.tslat.aoa3.content.item.weapon.sniper;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.networking.AoANetworking;
import net.tslat.aoa3.common.networking.packets.GunRecoilPacket;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.enchantment.ControlEnchantment;
import net.tslat.aoa3.content.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.content.entity.projectile.gun.SniperSlugEntity;
import net.tslat.aoa3.content.item.weapon.gun.BaseGun;
import net.tslat.aoa3.util.AdvancementUtil;
import net.tslat.aoa3.util.LocaleUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class BaseSniper extends BaseGun {
	protected static final ResourceLocation SCOPE_1 = new ResourceLocation(AdventOfAscension.MOD_ID, "textures/gui/overlay/scope/scope1.png");
	protected static final ResourceLocation SCOPE_2 = new ResourceLocation(AdventOfAscension.MOD_ID, "textures/gui/overlay/scope/scope2.png");
	protected static final ResourceLocation SCOPE_3 = new ResourceLocation(AdventOfAscension.MOD_ID, "textures/gui/overlay/scope/scope3.png");
	protected static final ResourceLocation SCOPE_4 = new ResourceLocation(AdventOfAscension.MOD_ID, "textures/gui/overlay/scope/scope4.png");

	public BaseSniper(float dmg, int durability, int fireDelayTicks, float recoil) {
		super(dmg, durability, fireDelayTicks, recoil);
	}

	@Override
	public Item getAmmoItem() {
		return AoAItems.METAL_SLUG.get();
	}

	@Override
	public boolean isFullAutomatic() {
		return false;
	}

	@Override
	protected boolean fireGun(ServerLevel level, LivingEntity shooter, ItemStack stack, InteractionHand hand) {
		BaseBullet bullet = findAndConsumeAmmo(shooter, stack, hand);

		if (bullet == null)
			return false;

		if (!shooter.onGround() || !shooter.isShiftKeyDown())
			bullet.shootFromRotation(shooter, shooter.getXRot(), shooter.getYRot(), 0.0f, 20.0f, 50.0f);

		shooter.level().addFreshEntity(bullet);
		doFiringEffects(level, shooter, bullet, stack, hand);

		return true;
	}

	@Override
	public void doRecoil(ServerPlayer player, ItemStack stack, InteractionHand hand) {
		float recoilAmount = ControlEnchantment.modifyRecoil(stack, getRecoilForShot(stack, player) * 0.25f);

		if (!player.isShiftKeyDown() || !player.onGround())
			recoilAmount *= 3.5f;

		AoANetworking.sendToPlayer(player, new GunRecoilPacket(recoilAmount, getFiringDelay()));
	}

	@Override
	public void doImpactDamage(Entity target, LivingEntity shooter, BaseBullet bullet, Vec3 impactPosition, float bulletDmgMultiplier) {
		super.doImpactDamage(target, shooter, bullet, impactPosition, bullet.getAge() <= 0 ? bulletDmgMultiplier * 0.5f : bulletDmgMultiplier);

		if (!target.isAlive() && target instanceof Phantom && shooter instanceof ServerPlayer pl)
			AdvancementUtil.completeAdvancement(pl, AdventOfAscension.id("completionist/skeet"), "phantom_sniper_kill");
	}

	@Override
	public BaseBullet createProjectileEntity(LivingEntity shooter, ItemStack gunStack, InteractionHand hand) {
		return new SniperSlugEntity(shooter, this, 0);
	}

	public ResourceLocation getScopeTexture(ItemStack stack) {
		return SCOPE_1;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		super.appendHoverText(stack, world, tooltip, flag);

		tooltip.add(2, LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.SNIPER_CROUCH, LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO));
	}
}
