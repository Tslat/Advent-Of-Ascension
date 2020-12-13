package net.tslat.aoa3.entity.minion;

import net.minecraft.block.EnderChestBlock;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.ChestContainer;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;

import javax.annotation.Nullable;

public class EnderCarrierEntity extends AoAMinion {
	public EnderCarrierEntity(EntityType<? extends TameableEntity> entityType, final World world){
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
		return 1.21875f;
	}

	@Override
	protected double getBaseMoveSpeed() {
		return 0.3346d;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 100;
	}

	@Override
	protected boolean isHostile() {
		return false;
	}

	@Override
	public boolean processInteract(PlayerEntity player, Hand hand) {
		if (getOwnerId() != null && getOwnerId().equals(player.getUniqueID())) {
			(player).openContainer(new SimpleNamedContainerProvider((id, pl, inventory) -> ChestContainer.createGeneric9X3(id, pl, (player).getInventoryEnderChest()), EnderChestBlock.CONTAINER_NAME));

			return true;
		}

		return super.processInteract(player, hand);
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_DYREHORN_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_DYREHORN_HURT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_DYREHORN_DEATH.get();
	}
}
