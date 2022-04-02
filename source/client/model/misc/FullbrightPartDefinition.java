package net.tslat.aoa3.client.model.misc;

import com.google.common.collect.ImmutableList;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDefinition;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.PartDefinition;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FullbrightPartDefinition extends PartDefinition {
	public FullbrightPartDefinition(List<CubeDefinition> pCubes, PartPose pPartPose) {
		super(pCubes, pPartPose);
	}

	@Override
	public PartDefinition addOrReplaceChild(String pName, CubeListBuilder pCubes, PartPose pPartPose) {
		PartDefinition newPart = new FullbrightPartDefinition(pCubes.getCubes(), pPartPose);
		PartDefinition replacedPart = this.children.put(pName, newPart);

		if (replacedPart != null)
			newPart.children.putAll(replacedPart.children);

		return newPart;
	}

	@Override
	public ModelPart bake(int pTexWidth, int pTexHeight) {
		Object2ObjectArrayMap<String, ModelPart> partMap = this.children.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().bake(pTexWidth, pTexHeight), (part1, part2) -> part1, Object2ObjectArrayMap::new));
		List<ModelPart.Cube> cubes = this.cubes.stream().map(cubeDef -> cubeDef.bake(pTexWidth, pTexHeight)).collect(ImmutableList.toImmutableList());
		ModelPart modelPart = new FullbrightModelPart(cubes, partMap);

		modelPart.loadPose(this.partPose);

		return modelPart;
	}
}
