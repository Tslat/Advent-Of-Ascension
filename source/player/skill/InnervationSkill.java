package net.tslat.aoa3.player.skill;

import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.FlyingEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.IMob;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.PlayerUtil;

import static net.tslat.aoa3.player.AoAPlayerEventListener.ListenerType.OUTGOING_ATTACK_AFTER;

public class InnervationSkill extends AoASkill.Instance {
	private static final ListenerType[] LISTENERS = new ListenerType[] {OUTGOING_ATTACK_AFTER, ListenerType.ENTITY_KILL, };

	private final Int2ObjectOpenHashMap<Pair<Long, Float>> attackTracker = new Int2ObjectOpenHashMap<Pair<Long, Float>>();

	public InnervationSkill(ServerPlayerDataManager plData, JsonObject jsonData) {
		super(AoASkills.INNERVATION.get(), plData, jsonData);
	}

	public InnervationSkill(CompoundNBT nbtData) {
		super(AoASkills.INNERVATION.get(), nbtData);
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public void handlePostOutgoingAttack(LivingDamageEvent ev) {
		LivingEntity target = ev.getEntityLiving();

		attackTracker.compute(target.getId(), (id, value) -> {
			if (value == null)
				return Pair.of(target.level.getGameTime(), ev.getAmount());

			return Pair.of(target.level.getGameTime(), Math.min(target.getMaxHealth() * 1.5f, ev.getAmount() + value.getSecond()));
		});
	}

	@Override
	public void handleEntityKill(LivingDeathEvent ev) {
		LivingEntity target = ev.getEntityLiving();
		Pair<Long, Float> attackEntry = attackTracker.get(target.getId());
		float damageDealt = attackEntry == null ? 5f : attackEntry.getSecond();

		if (attackEntry != null)
			attackTracker.remove(ev.getEntityLiving().getId());

		if (canGainXp(true))
			adjustXp(getKillXpForEntity(target, damageDealt), false, false);

		if (attackTracker.size() > 10)
			purgeTracker(target.level.getGameTime());
	}

	private void purgeTracker(long currentTime) {
		getAttackEntries().removeIf(entry -> entry.getValue().getFirst() < currentTime - 1200);
	}

	public Int2ObjectMap.FastEntrySet<Pair<Long, Float>> getAttackEntries() {
		return attackTracker.int2ObjectEntrySet();
	}

	public boolean hasAttackedEntity(Entity entity) {
		return attackTracker.containsKey(entity.getId());
	}

	protected float getKillXpForEntity(LivingEntity target, float damageDealt) {
		float xp = PlayerUtil.getTimeBasedXpForLevel(getLevel(true), (int)((Math.min(target.getMaxHealth() * 1.5f, damageDealt) / 20f) * 20));
		double armour = EntityUtil.safelyGetAttributeValue(target, Attributes.ARMOR);
		double toughness = armour > 0 ? EntityUtil.safelyGetAttributeValue(target, Attributes.ARMOR_TOUGHNESS) : 0;
		double speed = EntityUtil.safelyGetAttributeValue(target, Attributes.MOVEMENT_SPEED);

		xp *= 1 + (Math.pow(armour / 30, 2) + Math.pow(toughness / 30, 3));

		if (target.getPersistentData().contains("spawned_by_spawner"))
			xp *= 0.3f;

		if (target instanceof FlyingEntity || (target instanceof MobEntity && ((MobEntity)target).getNavigation() instanceof SwimmerPathNavigator))
			xp *= 1.1f;

		if (speed > 0.3f)
			xp *= 1.1f;

		if (!(target instanceof IMob))
			xp *= 0.5f;

		return xp;
	}
}
