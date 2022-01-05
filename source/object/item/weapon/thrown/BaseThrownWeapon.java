package net.tslat.aoa3.object.item.weapon.thrown;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.object.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.object.item.weapon.gun.BaseGun;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.NumberUtil;

import javax.annotation.Nullable;
import java.util.List;

public abstract class BaseThrownWeapon extends BaseGun {
	double dmg;
	int firingDelay;

	public BaseThrownWeapon(double dmg, int fireDelayTicks) {
		super(new Item.Properties().tab(AoAItemGroups.THROWN_WEAPONS).stacksTo(64), dmg, fireDelayTicks, 0);

		this.dmg = dmg;
		this.firingDelay = fireDelayTicks;
	}

	@Override
	public boolean isEnchantable(ItemStack stack) {
		return false;
	}

	@Override
	public boolean isFullAutomatic() {
		return false;
	}

	@Override
	public Item getAmmoItem() {
		return this;
	}

	@Override
	public float getRecoilForShot(ItemStack stack, LivingEntity shooter) {
		return 0;
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return SoundEvents.SPLASH_POTION_THROW;
	}

	@Override
	public void doImpactDamage(Entity target, LivingEntity shooter, BaseBullet bullet, float bulletDmgMultiplier) {
		if (target != null && dmg > 0.0f && DamageUtil.dealRangedDamage(target, shooter, bullet, (float)dmg * bulletDmgMultiplier))
			doImpactEffect(target, shooter, bullet, bulletDmgMultiplier);
	}

	@Override
	public BaseBullet findAndConsumeAmmo(LivingEntity shooter, ItemStack weaponStack, Hand hand) {
		if (weaponStack.isEmpty())
			return null;

		if (!shooter.level.isClientSide() && shooter instanceof PlayerEntity && !((PlayerEntity)shooter).isCreative())
			weaponStack.shrink(1);

		return createProjectileEntity(shooter, weaponStack, hand);
	}

	@Override
	protected void doFiringEffects(LivingEntity shooter, BaseBullet bullet, ItemStack stack, Hand hand) {
		doFiringSound(shooter, bullet, stack, hand);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		if (dmg > 0.0f)
			tooltip.add(1, LocaleUtil.getFormattedItemDescriptionText("items.description.damage.ranged", LocaleUtil.ItemDescriptionType.ITEM_DAMAGE, LocaleUtil.numToComponent(dmg)));

		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.thrownWeapon", LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.throwable.speed", LocaleUtil.ItemDescriptionType.NEUTRAL, new StringTextComponent(NumberUtil.roundToNthDecimalPlace(20 / (float)firingDelay, 2))));
	}

	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot, ItemStack stack) {
		return HashMultimap.<Attribute, AttributeModifier>create();
	}
}
