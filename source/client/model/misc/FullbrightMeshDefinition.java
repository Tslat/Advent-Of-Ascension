package net.tslat.aoa3.client.model.misc;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.MeshDefinition;

public class FullbrightMeshDefinition extends MeshDefinition {
	private final FullbrightPartDefinition root = new FullbrightPartDefinition(ImmutableList.of(), PartPose.ZERO);

	@Override
	public FullbrightPartDefinition getRoot() {
		return this.root;
	}
}
