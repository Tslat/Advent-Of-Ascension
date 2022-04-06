package net.tslat.aoa3.client.model.armor;

import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.tslat.aoa3.advent.AdventOfAscension;

import java.util.function.Function;

public class HelmOfTheTinkererModel extends SkillHelmetModel {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(AdventOfAscension.id("helm_of_the_tinkerer"), "main");

	public HelmOfTheTinkererModel(ModelPart meshDefRoot) {
		super(meshDefRoot);
	}

	public static Function<EntityModelSet, HelmOfTheTinkererModel> modelFactory() {
		return modelSet -> new HelmOfTheTinkererModel(modelSet.bakeLayer(LAYER_LOCATION));
	}

	public static LayerDefinition createLayerDefinition() {
		return LayerDefinition.create(new MeshDefinition(), 64, 64);
	}
}