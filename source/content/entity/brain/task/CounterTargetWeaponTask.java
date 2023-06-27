package net.tslat.aoa3.content.entity.brain.task;

import com.google.common.collect.ImmutableMap;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.item.*;
import net.tslat.aoa3.common.registration.item.AoAWeapons;
import net.tslat.aoa3.content.item.weapon.gun.BaseGun;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.smartbrainlib.util.RandomUtil;

public class CounterTargetWeaponTask extends Behavior<Mob> {
	private final int minSwitchTime;
	private final int maxSwitchTime;

	private long nextSwitchTime = 0;

	public CounterTargetWeaponTask() {
		this(10, 20);
	}

	public CounterTargetWeaponTask(int minSwitchTime, int maxSwitchTime) {
		super(ImmutableMap.of(MemoryModuleType.ATTACK_TARGET, MemoryStatus.VALUE_PRESENT));

		this.minSwitchTime = minSwitchTime;
		this.maxSwitchTime = maxSwitchTime;
	}

	@Override
	protected boolean checkExtraStartConditions(ServerLevel pLevel, Mob owner) {
		LivingEntity target = owner.getBrain().getMemory(MemoryModuleType.ATTACK_TARGET).get();

		if (!target.isAlive())
			return false;

		return nextSwitchTime == 0 || nextSwitchTime < owner.level().getGameTime();
	}

	@Override
	protected void start(ServerLevel pLevel, Mob owner, long pGameTime) {
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
			default:
		}

		nextSwitchTime = RandomUtil.randomNumberBetween(minSwitchTime, maxSwitchTime);
	}

	protected ThreatType determineThreat(LivingEntity owner, LivingEntity target) {
		ItemStack mainHandItem = target.getItemInHand(InteractionHand.MAIN_HAND);

		if (mainHandItem.getItem() instanceof ShieldItem || target.getItemInHand(InteractionHand.OFF_HAND).getItem() instanceof ShieldItem)
			return ThreatType.SHIELD;

		if (mainHandItem.getItem() instanceof SwordItem || EntityUtil.safelyGetAttributeValue(target, Attributes.ATTACK_DAMAGE) > 5) {
			if (!(owner.getItemInHand(InteractionHand.MAIN_HAND).getItem() instanceof SwordItem))
				return ThreatType.MELEE;

			return ThreatType.NONE;
		}

		if (mainHandItem.getItem() instanceof BowItem || mainHandItem.getItem() instanceof BaseGun) {
			if (!(owner.getItemInHand(InteractionHand.OFF_HAND).getItem() instanceof ShieldItem))
				return ThreatType.RANGED;

			return ThreatType.NONE;
		}

		return ThreatType.NONE;
	}

	protected void handleMeleeThreat(LivingEntity owner, LivingEntity target) {
		owner.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(AoAWeapons.HARVESTER_SWORD.get()));
		owner.setItemInHand(InteractionHand.OFF_HAND, ItemStack.EMPTY);
	}

	protected void handleRangedThreat(LivingEntity owner, LivingEntity target) {
		owner.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(AoAWeapons.HARVESTER_SWORD.get()));
		owner.setItemInHand(InteractionHand.OFF_HAND, new ItemStack(Items.SHIELD));
	}

	protected void handleShieldThreat(LivingEntity owner, LivingEntity target) {
		owner.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(AoAWeapons.HORIZON_MAUL.get()));
		owner.setItemInHand(InteractionHand.OFF_HAND, new ItemStack(Items.SHIELD));
	}

	private enum ThreatType {
		NONE,
		MELEE,
		RANGED,
		SHIELD
	}
}
