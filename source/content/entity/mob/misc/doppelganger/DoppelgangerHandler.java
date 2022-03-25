package net.tslat.aoa3.content.entity.mob.misc.doppelganger;

public class DoppelgangerHandler {
	/*private static final ImmutableList<SensorType<? extends Sensor<? super DoppelgangerEntity>>> SENSORS = ImmutableList.of(SensorType.NEAREST_PLAYERS, SensorType.HURT_BY, SensorType.NEAREST_LIVING_ENTITIES);
	private static final ImmutableList<MemoryModuleType<?>> MEMORIES = ImmutableList.of(MemoryModuleType.WALK_TARGET, MemoryModuleType.LOOK_TARGET, MemoryModuleType.VISIBLE_LIVING_ENTITIES, MemoryModuleType.NEAREST_PLAYERS, MemoryModuleType.NEAREST_VISIBLE_PLAYER, MemoryModuleType.NEAREST_VISIBLE_TARGETABLE_PLAYER, MemoryModuleType.HURT_BY, MemoryModuleType.HURT_BY_ENTITY, MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE, MemoryModuleType.ATTACK_TARGET, MemoryModuleType.PATH, MemoryModuleType.ATTACK_COOLING_DOWN);

	private final DoppelgangerEntity doppelganger;
	private final Brain<DoppelgangerEntity> brain;

	public DoppelgangerHandler(DoppelgangerEntity doppelganger) {
		this.doppelganger = doppelganger;
		this.brain = this.doppelganger.getBrain();
	}

	public static Brain.BrainCodec<DoppelgangerEntity> getCodecHandler() {
		return Brain.provider(MEMORIES, SENSORS);
	}

	public static Brain<DoppelgangerEntity> prepBrain(DoppelgangerEntity doppelganger, Dynamic<?> codec) {
		Brain<DoppelgangerEntity> brain = doppelganger.brainProvider().makeBrain(codec);

		brain.addActivity(Activity.CORE, 0, getCoreActivities());
		brain.addActivity(Activity.IDLE, 10, getIdleActivities());
		brain.addActivityAndRemoveMemoryWhenStopped(Activity.FIGHT, 10, getFightActivities(), MemoryModuleType.ATTACK_TARGET);

		brain.setCoreActivities(ImmutableSet.of(Activity.CORE));
		brain.setDefaultActivity(Activity.IDLE);
		brain.useDefaultActivity();

		return brain;
	}

	private static ImmutableList<? extends Task<? super DoppelgangerEntity>> getCoreActivities() {
		return ImmutableList.of(
				new LookTask(60, 240),
				new WalkToTargetTask());
	}

	private static ImmutableList<? extends Task<? super DoppelgangerEntity>> getIdleActivities() {
		return ImmutableList.of(
				new FirstShuffledTask<DoppelgangerEntity>(ImmutableList.of(
						Pair.of(new ForgetAttackTargetTask<>(doppelganger -> doppelganger.getBrain().getMemory(MemoryModuleType.NEAREST_PLAYERS).orElse(ImmutableList.of()).stream().filter(EntityPredicates.ATTACK_ALLOWED).findFirst()), 1),
						Pair.of(new LookAtEntityTask(EntityType.PLAYER, 8), 1),
						Pair.of(new LookAtEntityTask(8), 1),
						Pair.of(new DummyTask(30, 60), 1))),
				new FirstShuffledTask<DoppelgangerEntity>(ImmutableList.of(
						Pair.of(new WalkRandomlyTask(1f), 1),
						Pair.of(new DummyTask(30, 60), 1))));
	}

	private static ImmutableList<? extends Task<? super DoppelgangerEntity>> getFightActivities() {
		return ImmutableList.of(
				new AttackTargetTask(6),
				new MoveToTargetTask(1.3f),
				new FindNewAttackTargetTask<>(target -> !target.isAlive() || target instanceof PlayerEntity && ((PlayerEntity)target).isCreative()));
	}

	public boolean hasSensor(SensorType<?> sensorType) {
		return SENSORS.stream().anyMatch(sensor -> sensor == sensorType);
	}

	public boolean hasMemory(MemoryModuleType<?> memoryModule) {
		return MEMORIES.stream().anyMatch(memory -> memory == memoryModule);
	}

	public void tick() {
		doppelganger.level.getProfiler().push("doppelgangerBrain");
		brain.tick((ServerWorld)doppelganger.level, doppelganger);
		brain.setActiveActivityToFirstValid(ImmutableList.of(Activity.FIGHT, Activity.IDLE));
		doppelganger.setAggressive(getCurrentTarget() != null);
		doppelganger.level.getProfiler().pop();
	}

	@Nullable
	public LivingEntity getCurrentTarget() {
		return brain.getMemory(MemoryModuleType.ATTACK_TARGET).orElse(null);
	}*/
}
