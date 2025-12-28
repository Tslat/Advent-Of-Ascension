package net.tslat.aoa3.player.skill;

import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.event.EntityEvents;
import net.tslat.aoa3.event.dynamic.DynamicEventSubscriber;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.util.AttributeUtil;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.PlayerUtil;

import java.util.List;

public class InnervationSkill extends AoASkill.Instance {
	private final List<DynamicEventSubscriber<?>> eventSubscribers = List.of(
			afterAttacking(serverOnly(this::handleAfterAttacking)),
			onEntityKill(serverOnly(this::handleEntityKill)));

	private final Int2ObjectOpenHashMap<Pair<Long, Float>> attackTracker = new Int2ObjectOpenHashMap<Pair<Long, Float>>();

	public InnervationSkill(ServerPlayerDataManager plData, JsonObject jsonData) {
		super(AoASkills.INNERVATION.get(), plData, jsonData);
	}

	public InnervationSkill(CompoundTag nbtData) {
		super(AoASkills.INNERVATION.get(), nbtData);
	}

	@Override
	public List<DynamicEventSubscriber<?>> getEventSubscribers() {
		return this.eventSubscribers;
	}

	private void handleAfterAttacking(LivingDamageEvent.Post ev) {
		LivingEntity target = ev.getEntity();

		this.attackTracker.compute(target.getId(), (id, value) -> {
			if (value == null)
				return Pair.of(target.level().getGameTime(), ev.getNewDamage());

			return Pair.of(target.level().getGameTime(), Math.min(target.getMaxHealth() * 1.5f, ev.getNewDamage() + value.getSecond()));
		});
	}

	private void handleEntityKill(LivingDeathEvent ev) {
		LivingEntity target = ev.getEntity();
		Pair<Long, Float> attackEntry = this.attackTracker.get(target.getId());
		float damageDealt = attackEntry == null ? 5f : attackEntry.getSecond();

		if (attackEntry != null)
			this.attackTracker.remove(ev.getEntity().getId());

		if (canGainXp(true))
			PlayerUtil.giveXpToPlayer((ServerPlayer)getPlayer(), type(), getKillXpForEntity(target, damageDealt), false);

		if (this.attackTracker.size() > 10)
			purgeTracker(target.level().getGameTime());
	}

	private void purgeTracker(long currentTime) {
		getAttackEntries().removeIf(entry -> entry.getValue().getFirst() < currentTime - 1200);
	}

	public Int2ObjectMap.FastEntrySet<Pair<Long, Float>> getAttackEntries() {
		return this.attackTracker.int2ObjectEntrySet();
	}

	public boolean hasAttackedEntity(Entity entity) {
		return this.attackTracker.containsKey(entity.getId());
	}

	protected float getKillXpForEntity(LivingEntity target, float damageDealt) {
		float xp = PlayerUtil.getTimeBasedXpForLevel(getLevel(true), (int)((Math.min(target.getMaxHealth() * 1.5f, damageDealt) / 16f) * 20));
		double armour = AttributeUtil.getAttributeValue(target, Attributes.ARMOR);
		double toughness = armour > 0 ? AttributeUtil.getAttributeValue(target, Attributes.ARMOR_TOUGHNESS) : 0;
		double speed = AttributeUtil.getAttributeValue(target, Attributes.MOVEMENT_SPEED);

		xp *= 1 + (float)(Mth.square(armour / 30) + Math.pow(toughness / 15, 1.5d));

		if (target.getPersistentData().contains(EntityEvents.SPAWNED_BY_SPAWNER_TAG))
			xp *= 0.25f;

		if (target instanceof FlyingMob || (target instanceof Mob mob && mob.getNavigation() instanceof WaterBoundPathNavigation))
			xp *= 1.1f;

		if (speed > 0.3f)
			xp *= 1.1f;

		if (!EntityUtil.isHostileMob(target))
			xp *= 0.5f;

		if (target.getType().is(Tags.EntityTypes.BOSSES))
			xp *= 1.3f;

		return xp;
	}
}
