package net.tslat.aoa3.client.model.armor;

import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.tslat.aoa3.advent.AdventOfAscension;

import java.util.function.Function;

public class HelmOfTheAlchemistModel extends SkillHelmetModel {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(AdventOfAscension.id("helm_of_the_alchemist"), "main");

	public HelmOfTheAlchemistModel(ModelPart meshDefRoot) {
		super(meshDefRoot);
	}

	public static Function<EntityModelSet, HelmOfTheAlchemistModel> modelFactory() {
		return modelSet -> new HelmOfTheAlchemistModel(modelSet.bakeLayer(LAYER_LOCATION));
	}

	public static LayerDefinition createLayerDefinition() {
		return LayerDefinition.create(new MeshDefinition(), 64, 64);
	}
}