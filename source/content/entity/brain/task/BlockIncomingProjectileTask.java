package net.tslat.aoa3.content.entity.brain.task;

import com.google.common.collect.ImmutableMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.memory.MemoryModuleStatus;
import net.minecraft.entity.ai.brain.task.Task;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ShieldItem;
import net.minecraft.util.Hand;
import net.minecraft.world.server.ServerWorld;
import net.tslat.aoa3.common.registration.AoABrainMemories;
import net.tslat.aoa3.util.PlayerUtil;

import javax.annotation.Nullable;
import java.util.List;

public class BlockIncomingProjectileTask extends Task<MobEntity> {
	private final boolean equipShieldIfUnequipped;

	private Hand hand = null;
	private boolean equipShield = false;
	private long equipTime;

	public BlockIncomingProjectileTask(boolean equipShieldIfUnequipped) {
		super(ImmutableMap.of(AoABrainMemories.INCOMING_PROJECTILES.get(), MemoryModuleStatus.VALUE_PRESENT));

		this.equipShieldIfUnequipped = equipShieldIfUnequipped;
	}

	@Override
	protected boolean checkExtraStartConditions(ServerWorld pLevel, MobEntity owner) {
		Item mainHandItem = owner.getItemInHand(Hand.MAIN_HAND).getItem();
		Item offHandItem = owner.getItemInHand(Hand.OFF_HAND).getItem();
		hand = Hand.MAIN_HAND;

		ProjectileEntity projectile = owner.getBrain().getMemory(AoABrainMemories.INCOMING_PROJECTILES.get()).get().get(0);

		if (!owner.canSee(projectile))
			return false;

		if (mainHandItem instanceof ShieldItem)
			return true;

		hand = Hand.OFF_HAND;

		if (offHandItem instanceof ShieldItem)
			return true;

		if (offHandItem == Items.AIR && equipShieldIfUnequipped) {
			equipShield = true;

			return true;
		}

		return false;
	}

	@Override
	protected void start(ServerWorld pLevel, MobEntity owner, long pGameTime) {
		if (equipShield) {
			owner.setItemInHand(Hand.OFF_HAND, new ItemStack(Items.SHIELD));

			equipShield = false;
		}

		equipTime = pGameTime;
		owner.startUsingItem(hand);

		Entity faceTarget = getEntityFocus(owner);

		//if (faceTarget != null)
		//	owner.getBrain().setMemory(MemoryModuleType.LOOK_TARGET, new EntityPosWrapper(faceTarget, true));
	}

	@Override
	protected void tick(ServerWorld level, MobEntity owner, long gameTime) {
		//Entity faceTarget = getEntityFocus(owner);

		//if (faceTarget != null)
		//	owner.getBrain().setMemory(MemoryModuleType.LOOK_TARGET, new EntityPosWrapper(faceTarget, true));

		if (gameTime - equipTime <= 10)
			return;

		if (!owner.getBrain().getMemory(AoABrainMemories.INCOMING_PROJECTILES.get()).isPresent())
			doStop(level, owner, gameTime);
	}

	@Override
	protected boolean canStillUse(ServerWorld pLevel, MobEntity owner, long gameTime) {
		return gameTime - equipTime <= 10 || owner.getBrain().getMemory(AoABrainMemories.INCOMING_PROJECTILES.get()).isPresent();
	}

	@Override
	protected void stop(ServerWorld pLevel, MobEntity owner, long pGameTime) {
		owner.stopUsingItem();
	}

	@Nullable
	private Entity getEntityFocus(MobEntity owner) {
		Brain<?> brain = owner.getBrain();

		if (!brain.hasMemoryValue(AoABrainMemories.INCOMING_PROJECTILES.get()))
			return null;

		List<ProjectileEntity> projectiles = brain.getMemory(AoABrainMemories.INCOMING_PROJECTILES.get()).get();

		if (projectiles.isEmpty())
			return null;

		ProjectileEntity projectile = projectiles.get(0);
		Entity target = PlayerUtil.getPlayerOrOwnerIfApplicable(projectile);

		return target != null ? target : projectile;
	}
}
