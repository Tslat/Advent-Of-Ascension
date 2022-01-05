package net.tslat.aoa3.object.item.weapon.sniper;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.GunRecoilPacket;
import net.tslat.aoa3.common.registration.AoAEnchantments;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.object.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.object.entity.projectile.gun.SniperSlugEntity;
import net.tslat.aoa3.object.item.weapon.gun.BaseGun;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.List;

public abstract class BaseSniper extends BaseGun {
	protected static final ResourceLocation SCOPE_1 = new ResourceLocation(AdventOfAscension.MOD_ID, "textures/gui/overlay/scope/scope1.png");
	protected static final ResourceLocation SCOPE_2 = new ResourceLocation(AdventOfAscension.MOD_ID, "textures/gui/overlay/scope/scope2.png");
	protected static final ResourceLocation SCOPE_3 = new ResourceLocation(AdventOfAscension.MOD_ID, "textures/gui/overlay/scope/scope3.png");
	protected static final ResourceLocation SCOPE_4 = new ResourceLocation(AdventOfAscension.MOD_ID, "textures/gui/overlay/scope/scope4.png");

	public BaseSniper(double dmg, int durability, int fireDelayTicks, float recoil) {
		super(AoAItemGroups.SNIPERS, dmg, durability, fireDelayTicks, recoil);
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
	protected boolean fireGun(LivingEntity shooter, ItemStack stack, Hand hand) {
		BaseBullet bullet = findAndConsumeAmmo(shooter, stack, hand);

		if (bullet == null)
			return false;

		if (!shooter.onGround || !shooter.isShiftKeyDown())
			bullet.shootFromRotation(shooter, shooter.xRot, shooter.yRot, 0.0f, 20.0f, 50.0f);

		shooter.level.addFreshEntity(bullet);

		if (!shooter.level.isClientSide())
			doFiringEffects(shooter, bullet, stack, hand);

		return true;
	}

	@Override
	public void doRecoil(ServerPlayerEntity player, ItemStack stack, Hand hand) {
		int control = EnchantmentHelper.getItemEnchantmentLevel(AoAEnchantments.CONTROL.get(), stack);
		float recoilAmount = getRecoilForShot(stack, player) * 0.5f * (1 - control * 0.15f);

		if (!player.isShiftKeyDown() || !player.onGround)
			recoilAmount *= 3.5f;

		AoAPackets.messagePlayer(player, new GunRecoilPacket(recoilAmount, getFiringDelay()));
	}

	@Override
	public void doImpactDamage(Entity target, LivingEntity shooter, BaseBullet bullet, float bulletDmgMultiplier) {
		super.doImpactDamage(target, shooter, bullet, bullet.getAge() <= 0 ? bulletDmgMultiplier * 0.5f : bulletDmgMultiplier);
	}

	@Override
	public BaseBullet createProjectileEntity(LivingEntity shooter, ItemStack gunStack, Hand hand) {
		return new SniperSlugEntity(shooter, this, 0);
	}

	public ResourceLocation getScopeTexture(ItemStack stack) {
		return SCOPE_1;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		super.appendHoverText(stack, world, tooltip, flag);

		tooltip.add(2, LocaleUtil.getFormattedItemDescriptionText("items.description.sniper.use", LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO));
	}
}
