package net.tslat.aoa3.client.model.entity;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.tslat.aoa3.client.model.BaseGeoModel;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class EntityGeoModel<T extends Entity & IAnimatable> extends BaseGeoModel<T> {
	private final boolean turnsHead;

	public EntityGeoModel(String assetSubpath) {
		this(assetSubpath, false);
	}

	public EntityGeoModel(String assetSubpath, boolean turnsHead) {
		super(assetSubpath);

		this.turnsHead = turnsHead;
	}

	@Override
	protected String subtype() {
		return "entity";
	}

	@Override
	public EntityGeoModel<T> withModel(String modelPath) {
		return (EntityGeoModel<T>)super.withModel(modelPath);
	}

	@Override
	public EntityGeoModel<T> withAnimations(String modelPath) {
		return (EntityGeoModel<T>)super.withAnimations(modelPath);
	}

	@Override
	public void setLivingAnimations(T entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
		super.setLivingAnimations(entity, uniqueID, customPredicate);

		if (!this.turnsHead)
			return;

		IBone head = getAnimationProcessor().getBone("head");

		EntityModelData entityData = (EntityModelData)customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
		head.setRotationX(entityData.headPitch * Mth.DEG_TO_RAD);
		head.setRotationY(entityData.netHeadYaw * Mth.DEG_TO_RAD);
	}
}
