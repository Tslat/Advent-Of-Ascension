package net.tslat.aoa3.content.item.weapon.gun;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.Tags;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.item.AoAEnchantments;
import net.tslat.aoa3.content.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.content.entity.projectile.gun.SeedDartEntity;
import net.tslat.aoa3.util.ItemUtil;
import org.jetbrains.annotations.Nullable;


public class DartGun extends BaseGun {
	double dmg;
	int firingDelay;

	public DartGun(float dmg, int durability, int firingDelayTicks, float recoil) {
		super(dmg, durability, firingDelayTicks, recoil);
		this.dmg = dmg;
		this.firingDelay = firingDelayTicks;
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_GUN_BLOWPIPE_SHOOT.get();
	}

	@Override
	public boolean isFullAutomatic() {
		return false;
	}

	@Override
	public Item getAmmoItem() {
		return Items.WHEAT_SEEDS;
	}

	@Nullable
	@Override
	public BaseBullet findAndConsumeAmmo(LivingEntity shooter, ItemStack gunStack, InteractionHand hand) {
		if (shooter.getType() != EntityType.PLAYER || ItemUtil.findItemByTag((Player)shooter, Tags.Items.SEEDS, !shooter.level().isClientSide(), 1 + gunStack.getEnchantmentLevel(AoAEnchantments.GREED.get())))
			return createProjectileEntity(shooter, gunStack, hand);

		return null;
	}

	@Override
	public BaseBullet createProjectileEntity(LivingEntity shooter, ItemStack gunStack, InteractionHand hand) {
		return new SeedDartEntity(shooter, this, hand, 120, 0);
	}

	@Override
	protected void doFiringEffects(ServerLevel level, LivingEntity shooter, BaseBullet bullet, ItemStack stack, InteractionHand hand) {
		doFiringSound(shooter, bullet, stack, hand);
	}
}
