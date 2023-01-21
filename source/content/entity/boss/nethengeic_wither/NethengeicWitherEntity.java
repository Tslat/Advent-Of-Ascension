package net.tslat.aoa3.content.entity.boss.nethengeic_wither;

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.content.entity.boss.AoABoss;
import net.tslat.smartbrainlib.api.core.sensor.ExtendedSensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.HurtBySensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.NearbyPlayersSensor;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.RawAnimation;

import java.util.List;

public class NethengeicWitherEntity extends AoABoss {
	private static final EntityDataAccessor<Integer> LEFT_HEAD_STATE = SynchedEntityData.defineId(NethengeicWitherEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> CENTER_HEAD_STATE = SynchedEntityData.defineId(NethengeicWitherEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> RIGHT_HEAD_STATE = SynchedEntityData.defineId(NethengeicWitherEntity.class, EntityDataSerializers.INT);
	private static final int DEFAULT_STATE = 0;
	private static final int FIREBALL_STATE = 1;
	private static final int FLAMETHROWER_STATE = 2;

	private static final RawAnimation FIREBALL_LEFT = RawAnimation.begin().thenPlay("attack.fireball.left");
	private static final RawAnimation FIREBALL_RIGHT = RawAnimation.begin().thenPlay("attack.fireball.right");
	private static final RawAnimation FIREBALL_CENTER = RawAnimation.begin().thenPlay("attack.fireball.center");
	private static final RawAnimation FIRE_AURA = RawAnimation.begin().thenPlay("misc.fire_aura");

	public NethengeicWitherEntity(EntityType<? extends Monster> entityType, Level level) {
		super(entityType, level);
	}

	@Override
	protected void addSwingData(Int2ObjectOpenHashMap<SwingData> states) {
		states.put(0, new SwingData(0, 0, RawAnimation.begin()));
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
		return 3;
	}

	@Nullable
	@Override
	protected SoundEvent getMusic() {
		return null;
	}

	@Override
	public List<ExtendedSensor<AoABoss>> getSensors() {
		return ObjectArrayList.of(
				new NearbyPlayersSensor<>(),
				new HurtBySensor<>()
		);
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
	}
}