package net.tslat.aoa3.content.entity.boss.king_bambambam;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.content.entity.boss.AoABoss;
import org.jetbrains.annotations.Nullable;

public class KingBamBamBamEntity extends AoABoss {
	public KingBamBamBamEntity(EntityType<? extends AoABoss> entityType, Level level) {
		super(entityType, level);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
		return 2.15625f;
	}

	@Nullable
	@Override
	public SoundEvent getMusic() {
		return null;
	}
}
