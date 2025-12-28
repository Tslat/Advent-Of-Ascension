package net.tslat.aoa3.content.entity.projectile.gun;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.Tags;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.base.PhysicalWeaponProjectile;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.util.AdvancementUtil;

public class ShoeShotEntity extends PhysicalWeaponProjectile {
	public ShoeShotEntity(EntityType<? extends PhysicalWeaponProjectile> entityType, Level level) {
		super(entityType, level);
	}

	public ShoeShotEntity(EntityType<? extends PhysicalWeaponProjectile> entityType, Level level, WeaponFiringContext context) {
		super(entityType, level, context);
	}

	public ShoeShotEntity(Level level, WeaponFiringContext context) {
		super(AoAProjectiles.SHOE_SHOT.get(), level, context);
	}

	@Override
	public double getDefaultGravity() {
		return 0.13f;
	}

	@Override
	protected void doEntityImpact(EntityHitResult rayTrace, Entity hitEntity) {
		if (hitEntity instanceof LivingEntity target) {
			Vec3 angle = getDeltaMovement().normalize();

			target.knockback(1.35f, angle.x, angle.z);

			if (getOwner() instanceof ServerPlayer pl && target.isDeadOrDying() && target.getType().is(Tags.EntityTypes.BOSSES))
				AdvancementUtil.grantCriterion(pl, AdventOfAscension.id("overworld/la_chancla"), "shoe_flinger_boss_kill");

			if (!target.hasItemInSlot(EquipmentSlot.FEET)) {
				target.setItemSlot(EquipmentSlot.FEET, new ItemStack(Items.LEATHER_BOOTS));
				discard();
			}
		}
	}

	@Override
	protected void doBlockImpactFx(BlockHitResult rayTrace, BlockState block) {}
}