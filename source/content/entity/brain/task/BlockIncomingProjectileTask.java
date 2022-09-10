package net.tslat.aoa3.content.entity.brain.task;

import com.google.common.collect.ImmutableMap;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShieldItem;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.smartbrainlib.registry.SBLMemoryTypes;

import javax.annotation.Nullable;
import java.util.List;

public class BlockIncomingProjectileTask extends Behavior<Mob> {
	private final boolean equipShieldIfUnequipped;

	private InteractionHand hand = null;
	private boolean equipShield = false;
	private long equipTime;

	public BlockIncomingProjectileTask(boolean equipShieldIfUnequipped) {
		super(ImmutableMap.of(SBLMemoryTypes.INCOMING_PROJECTILES.get(), MemoryStatus.VALUE_PRESENT));

		this.equipShieldIfUnequipped = equipShieldIfUnequipped;
	}

	@Override
	protected boolean checkExtraStartConditions(ServerLevel pLevel, Mob owner) {
		Item mainHandItem = owner.getItemInHand(InteractionHand.MAIN_HAND).getItem();
		Item offHandItem = owner.getItemInHand(InteractionHand.OFF_HAND).getItem();
		hand = InteractionHand.MAIN_HAND;

		Projectile projectile = owner.getBrain().getMemory(SBLMemoryTypes.INCOMING_PROJECTILES.get()).get().get(0);

		if (!owner.hasLineOfSight(projectile))
			return false;

		if (mainHandItem instanceof ShieldItem)
			return true;

		hand = InteractionHand.OFF_HAND;

		if (offHandItem instanceof ShieldItem)
			return true;

		if (offHandItem == Items.AIR && equipShieldIfUnequipped) {
			equipShield = true;

			return true;
		}

		return false;
	}

	@Override
	protected void start(ServerLevel pLevel, Mob owner, long pGameTime) {
		if (equipShield) {
			owner.setItemInHand(InteractionHand.OFF_HAND, new ItemStack(Items.SHIELD));

			equipShield = false;
		}

		equipTime = pGameTime;
		owner.startUsingItem(hand);

		Entity faceTarget = getEntityFocus(owner);

		//if (faceTarget != null)
		//	owner.getBrain().setMemory(MemoryModuleType.LOOK_TARGET, new EntityPosWrapper(faceTarget, true));
	}

	@Override
	protected void tick(ServerLevel level, Mob owner, long gameTime) {
		//Entity faceTarget = getEntityFocus(owner);

		//if (faceTarget != null)
		//	owner.getBrain().setMemory(MemoryModuleType.LOOK_TARGET, new EntityPosWrapper(faceTarget, true));

		if (gameTime - equipTime <= 10)
			return;

		if (!owner.getBrain().getMemory(SBLMemoryTypes.INCOMING_PROJECTILES.get()).isPresent())
			doStop(level, owner, gameTime);
	}

	@Override
	protected boolean canStillUse(ServerLevel pLevel, Mob owner, long gameTime) {
		return gameTime - equipTime <= 10 || owner.getBrain().getMemory(SBLMemoryTypes.INCOMING_PROJECTILES.get()).isPresent();
	}

	@Override
	protected void stop(ServerLevel pLevel, Mob owner, long pGameTime) {
		owner.stopUsingItem();
	}

	@Nullable
	private Entity getEntityFocus(Mob owner) {
		Brain<?> brain = owner.getBrain();

		if (!brain.hasMemoryValue(SBLMemoryTypes.INCOMING_PROJECTILES.get()))
			return null;

		List<Projectile> projectiles = brain.getMemory(SBLMemoryTypes.INCOMING_PROJECTILES.get()).get();

		if (projectiles.isEmpty())
			return null;

		Projectile projectile = projectiles.get(0);
		Entity target = PlayerUtil.getPlayerOrOwnerIfApplicable(projectile);

		return target != null ? target : projectile;
	}
}
