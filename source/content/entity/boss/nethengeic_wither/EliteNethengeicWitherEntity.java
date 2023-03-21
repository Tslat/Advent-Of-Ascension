package net.tslat.aoa3.content.entity.boss.nethengeic_wither;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.content.entity.boss.AoABoss;
import net.tslat.smartbrainlib.api.core.sensor.ExtendedSensor;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.core.animation.AnimatableManager;

import java.util.List;

public class EliteNethengeicWitherEntity extends AoABoss {
	public EliteNethengeicWitherEntity(EntityType<? extends AoABoss> entityType, Level level) {
		super(entityType, level);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
		return 0;
	}

	@Nullable
	@Override
	protected SoundEvent getMusic() {
		return null;
	}

	@Override
	public List<ExtendedSensor<AoABoss>> getSensors() {
		return List.of();
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		super.registerControllers(controllers);
	}
}