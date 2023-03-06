package net.tslat.aoa3.content.entity.brain.task;

import com.google.common.collect.ImmutableMap;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.brain.memory.MemoryModuleStatus;
import net.minecraft.entity.ai.brain.memory.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.Task;
import net.minecraft.item.*;
import net.minecraft.util.Hand;
import net.minecraft.world.Difficulty;
import net.minecraft.world.server.ServerWorld;
import net.tslat.aoa3.common.registration.AoAWeapons;
import net.tslat.aoa3.content.item.weapon.gun.BaseGun;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.RandomUtil;

public class CounterTargetWeaponTask extends Task<MobEntity> {
	private final int minSwitchTime;
	private final int maxSwitchTime;

	private long nextSwitchTime = 0;

	public CounterTargetWeaponTask() {
		this(10, 20);
	}

	public CounterTargetWeaponTask(int minSwitchTime, int maxSwitchTime) {
		super(ImmutableMap.of(MemoryModuleType.ATTACK_TARGET, MemoryModuleStatus.VALUE_PRESENT));

		this.minSwitchTime = minSwitchTime;
		this.maxSwitchTime = maxSwitchTime;
	}

	@Override
	protected boolean checkExtraStartConditions(ServerWorld pLevel, MobEntity owner) {
		LivingEntity target = owner.getBrain().getMemory(MemoryModuleType.ATTACK_TARGET).get();

		if (!target.isAlive())
			return false;

		return nextSwitchTime == 0 || nextSwitchTime < owner.level.getGameTime();
	}

	@Override
	protected void start(ServerWorld pLevel, MobEntity owner, long pGameTime) {
		LivingEntity target = owner.getBrain().getMemory(MemoryModuleType.ATTACK_TARGET).get();

		switch (determineThreat(owner, target)) {
			case MELEE:
				handleMeleeThreat(owner, target);
				break;
			case RANGED:
				handleRangedThreat(owner, target);
				break;
			case SHIELD:
				handleShieldThreat(owner, target);
				break;
			case NONE:
				handleUnknownThreat(owner, target);
				break;
			default:
		}

		nextSwitchTime = RandomUtil.randomNumberBetween(minSwitchTime, maxSwitchTime);
	}

	protected ThreatType determineThreat(LivingEntity owner, LivingEntity target) {
		ItemStack mainHandItem = target.getItemInHand(Hand.MAIN_HAND);

		if (mainHandItem.getItem() instanceof ShieldItem || target.getItemInHand(Hand.OFF_HAND).getItem() instanceof ShieldItem)
			return ThreatType.SHIELD;

		if (mainHandItem.getItem() instanceof SwordItem || EntityUtil.safelyGetAttributeValue(target, Attributes.ATTACK_DAMAGE) > 5) {
			if (!(owner.getItemInHand(Hand.MAIN_HAND).getItem() instanceof SwordItem))
				return ThreatType.MELEE;

			return ThreatType.NONE;
		}

		if (mainHandItem.getItem() instanceof BowItem || mainHandItem.getItem() instanceof BaseGun || mainHandItem.getItem() instanceof CrossbowItem) {
			if (!(owner.getItemInHand(Hand.OFF_HAND).getItem() instanceof ShieldItem))
				return ThreatType.RANGED;

			return ThreatType.NONE;
		}

		return ThreatType.NONE;
	}

	protected void handleMeleeThreat(LivingEntity owner, LivingEntity target) {
		owner.setItemInHand(Hand.MAIN_HAND, new ItemStack(AoAWeapons.HARVESTER_SWORD.get()));
		owner.setItemInHand(Hand.OFF_HAND, ItemStack.EMPTY);
	}

	protected void handleRangedThreat(LivingEntity owner, LivingEntity target) {
		owner.setItemInHand(Hand.MAIN_HAND, new ItemStack(AoAWeapons.HARVESTER_SWORD.get()));
		owner.setItemInHand(Hand.OFF_HAND, new ItemStack(Items.SHIELD));
	}

	protected void handleShieldThreat(LivingEntity owner, LivingEntity target) {
		owner.setItemInHand(Hand.MAIN_HAND, new ItemStack(AoAWeapons.VULCAMMER_MAUL.get()));
		owner.setItemInHand(Hand.OFF_HAND, new ItemStack(Items.SHIELD));
	}

	protected void handleUnknownThreat(LivingEntity owner, LivingEntity target) {
		if (owner.level.getDifficulty() == Difficulty.HARD)
			owner.setItemInHand(Hand.MAIN_HAND, new ItemStack(AoAWeapons.GODS_GREATBLADE.get()));
	}

	private enum ThreatType {
		NONE,
		MELEE,
		RANGED,
		SHIELD
	}
}
