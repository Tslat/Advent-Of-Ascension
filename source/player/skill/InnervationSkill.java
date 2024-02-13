package net.tslat.aoa3.player.skill;

import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.PlayerUtil;

public class InnervationSkill extends AoASkill.Instance {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.OUTGOING_ATTACK_AFTER, ListenerType.ENTITY_KILL, };

	private final Int2ObjectOpenHashMap<Pair<Long, Float>> attackTracker = new Int2ObjectOpenHashMap<Pair<Long, Float>>();

	public InnervationSkill(ServerPlayerDataManager plData, JsonObject jsonData) {
		super(AoASkills.INNERVATION.get(), plData, jsonData);
	}

	public InnervationSkill(CompoundTag nbtData) {
		super(AoASkills.INNERVATION.get(), nbtData);
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public void handlePostOutgoingAttack(LivingDamageEvent ev) {
		LivingEntity target = ev.getEntity();

		attackTracker.compute(target.getId(), (id, value) -> {
			if (value == null)
				return Pair.of(target.level().getGameTime(), ev.getAmount());

			return Pair.of(target.level().getGameTime(), Math.min(target.getMaxHealth() * 1.5f, ev.getAmount() + value.getSecond()));
		});
	}

	@Override
	public void handleEntityKill(LivingDeathEvent ev) {
		LivingEntity target = ev.getEntity();
		Pair<Long, Float> attackEntry = attackTracker.get(target.getId());
		float damageDealt = attackEntry == null ? 5f : attackEntry.getSecond();

		if (attackEntry != null)
			attackTracker.remove(ev.getEntity().getId());

		if (canGainXp(true))
			PlayerUtil.giveXpToPlayer((ServerPlayer)getPlayer(), type(), getKillXpForEntity(target, damageDealt), false);

		if (attackTracker.size() > 10)
			purgeTracker(target.level().getGameTime());
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
		float xp = PlayerUtil.getTimeBasedXpForLevel(getLevel(true), (int)((Math.min(target.getMaxHealth() * 1.5f, damageDealt) / 16f) * 20));
		double armour = EntityUtil.safelyGetAttributeValue(target, Attributes.ARMOR);
		double toughness = armour > 0 ? EntityUtil.safelyGetAttributeValue(target, Attributes.ARMOR_TOUGHNESS) : 0;
		double speed = EntityUtil.safelyGetAttributeValue(target, Attributes.MOVEMENT_SPEED);

		xp *= 1 + (Math.pow(armour / 30, 2) + Math.pow(toughness / 15, 1.5d));

		if (target.getPersistentData().contains("spawned_by_spawner"))
			xp *= 0.25f;

		if (target instanceof FlyingMob || (target instanceof Mob mob && mob.getNavigation() instanceof WaterBoundPathNavigation))
			xp *= 1.1f;

		if (speed > 0.3f)
			xp *= 1.1f;

		if (!EntityUtil.isHostileMob(target))
			xp *= 0.5f;

		if (!target.canChangeDimensions())
			xp *= 1.3f;

		return xp;
	}
}
