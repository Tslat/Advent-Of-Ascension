package net.tslat.aoa3.client.model.entity;

import net.minecraft.entity.Entity;
import net.tslat.aoa3.client.model.BaseGeoModel;
import software.bernie.geckolib3.core.IAnimatable;

public class EntityGeoModel<T extends Entity & IAnimatable> extends BaseGeoModel<T> {
	public EntityGeoModel(String assetSubpath) {
		super(assetSubpath);
	}

	public EntityGeoModel(String modelSubpath, String textureSubpath) {
		super(modelSubpath, textureSubpath);
	}

	public EntityGeoModel(String modelSubpath, String textureSubpath, String animationSubpath) {
		super(modelSubpath, textureSubpath, animationSubpath);
	}

	@Override
	protected String subtype() {
		return "entity";
	}
}
