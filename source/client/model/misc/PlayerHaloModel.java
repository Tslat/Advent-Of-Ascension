package net.tslat.aoa3.client.model.misc;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.player.Player;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.model.misc.fullbright.FullbrightCubes;

public class PlayerHaloModel extends EntityModel<Player> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(AdventOfAscension.id("player_halo"), "main");

	public final ModelPart root;

	public PlayerHaloModel(ModelPart meshDefRoot) {
		this.root = meshDefRoot.getChild("root");
	}

	public static LayerDefinition createLayerDefinition() {
		MeshDefinition meshDefinition = new MeshDefinition();
		PartDefinition partDefinition = meshDefinition.getRoot();

		partDefinition.addOrReplaceChild("root", FullbrightCubes.create()
				.texOffs(0, 1).addGlowBox(-1.0F, -12.5F, -3.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 1).addGlowBox(-1.0F, -12.5F, 2.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 1).addGlowBox(0.5F, -12.5F, -2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 1).addGlowBox(0.5F, -12.5F, 1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 1).addGlowBox(1.0F, -12.5F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 1).addGlowBox(1.0F, -12.5F, 1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 1).addGlowBox(1.5F, -12.5F, -1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 1).addGlowBox(1.5F, -12.5F, 0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 1).addGlowBox(2.0F, -12.5F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 1).addGlowBox(-1.5F, -12.5F, -2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 1).addGlowBox(-1.5F, -12.5F, 1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 1).addGlowBox(-2.0F, -12.5F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 1).addGlowBox(-2.0F, -12.5F, 1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 1).addGlowBox(-2.5F, -12.5F, -1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 1).addGlowBox(-2.5F, -12.5F, 0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 1).addGlowBox(-3.0F, -12.5F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshDefinition, 16, 16);
	}

	@Override
	public void setupAnim(Player pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {}

	@Override
	public void renderToBuffer(PoseStack pPoseStack, VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, float pRed, float pGreen, float pBlue, float pAlpha) {
		this.root.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
	}
}
