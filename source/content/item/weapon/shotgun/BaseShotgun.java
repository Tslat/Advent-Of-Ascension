package net.tslat.aoa3.content.item.weapon.shotgun;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.enchantment.FormEnchantment;
import net.tslat.aoa3.content.enchantment.ShellEnchantment;
import net.tslat.aoa3.content.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.content.entity.projectile.gun.LimoniteBulletEntity;
import net.tslat.aoa3.content.entity.projectile.gun.MetalSlugEntity;
import net.tslat.aoa3.content.item.weapon.gun.BaseGun;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.smartbrainlib.util.RandomUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BaseShotgun extends BaseGun {
	protected final int pelletCount;
	protected final float knockbackFactor;

	public BaseShotgun(final float dmg, final int pellets, final int durability, final int fireDelayTicks, final float knockbackFactor, final float recoil) {
		super(dmg, durability, fireDelayTicks, recoil);

		this.pelletCount = pellets;
		this.knockbackFactor = knockbackFactor;
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_GUN_SHOTGUN_MEDIUM_FIRE_LONG.get();
	}

	@Override
	public Item getAmmoItem() {
		return AoAItems.SPREADSHOT.get();
	}

	@Override
	public boolean isFullAutomatic() {
		return false;
	}

	public int getPelletCount() {
		return this.pelletCount;
	}

	@Override
	protected boolean fireGun(ServerLevel level, LivingEntity shooter, ItemStack stack, InteractionHand hand) {
		BaseBullet bullet = findAndConsumeAmmo(shooter, stack, hand);

		if (bullet == null)
			return false;

		int pellets = getPelletCount();
		float spreadFactor = getSpreadFactor(shooter, stack, pellets);

		for (int i = 0; i < pellets; i++) {
			BaseBullet pellet = new LimoniteBulletEntity(shooter, this, hand, 4, 1.0f, 0, RandomUtil.randomValueUpTo(0.5f) * spreadFactor, RandomUtil.randomValueUpTo(0.5f) * spreadFactor, RandomUtil.randomValueUpTo(0.5f) * spreadFactor);

			level.addFreshEntity(pellet);
		}

		doFiringEffects(level, shooter, bullet, stack, hand);

		return true;
	}

	protected float getSpreadFactor(LivingEntity shooter, ItemStack stack, int pellets) {
		return FormEnchantment.modifySpread(stack, 0.1f * pellets);
	}

	@Override
	public BaseBullet createProjectileEntity(LivingEntity shooter, ItemStack gunStack, InteractionHand hand) {
		return new MetalSlugEntity(shooter, this, hand, 4, 0);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		super.appendHoverText(stack, world, tooltip, flag);

		tooltip.set(1, LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.SHOTGUN_DAMAGE, LocaleUtil.ItemDescriptionType.ITEM_DAMAGE, Component.literal(NumberUtil.roundToNthDecimalPlace(ShellEnchantment.applyDamageBonus(stack, getDamage()), 2)), LocaleUtil.numToComponent(pelletCount)));
	}
}
