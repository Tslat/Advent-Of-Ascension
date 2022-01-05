package net.tslat.aoa3.object.item.weapon.gun;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.object.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.object.entity.projectile.gun.ShoeShotEntity;
import net.tslat.aoa3.util.AdvancementUtil;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.List;

public class ShoeFlinger extends BaseGun {
	double dmg;
	int firingDelay;

	public ShoeFlinger(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(AoAItemGroups.GUNS, dmg, durability, firingDelayTicks, recoil);

		this.dmg = dmg;
		this.firingDelay = firingDelayTicks;
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_FLINGER_FIRE.get();
	}

	@Override
	public Item getAmmoItem() {
		return Items.LEATHER_BOOTS;
	}

	@Override
	public BaseBullet createProjectileEntity(LivingEntity shooter, ItemStack gunStack, Hand hand) {
		return new ShoeShotEntity(shooter, this, hand, 120, 0);
	}

	@Override
	protected void doImpactEffect(Entity target, LivingEntity shooter, BaseBullet bullet, float bulletDmgMultiplier) {
		if (target instanceof LivingEntity) {
			LivingEntity livingTarget = (LivingEntity)target;

			DamageUtil.doScaledKnockback(livingTarget, shooter, 1.35f, shooter.getX() - target.getX(), shooter.getZ() - target.getZ());

			if (shooter instanceof ServerPlayerEntity && livingTarget.getHealth() == 0 && !target.canChangeDimensions())
				AdvancementUtil.completeAdvancement((ServerPlayerEntity)shooter, new ResourceLocation(AdventOfAscension.MOD_ID, "overworld/la_chancla"), "shoe_flinger_boss_kill");

			if (!livingTarget.hasItemInSlot(EquipmentSlotType.FEET))
				livingTarget.setItemSlot(EquipmentSlotType.FEET, new ItemStack(Items.LEATHER_BOOTS));
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.UNIQUE, 1));

		super.appendHoverText(stack, world, tooltip, flag);
	}
}
