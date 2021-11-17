package net.tslat.aoa3.client.model.entity.mob.overworld;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.tslat.aoa3.util.HolidayUtil;
import net.tslat.aoa3.util.RandomUtil;

import javax.annotation.Nullable;

public class ChargerModel extends EntityModel<MobEntity> {
	private final boolean isGhost;

	private ModelRenderer root;
	private ModelRenderer head;
	private ModelRenderer bone;
	private ModelRenderer bone2;
	private ModelRenderer rightleg;
	private ModelRenderer leftleg;

	public static ResourceLocation getChargerTexture(ChargerModel model, @Nullable ResourceLocation backupTexture) {
		if (HolidayUtil.isHalloween()) {
			if (model.isGhost) {
				if (RandomUtil.fiftyFifty()) {
					return new ResourceLocation("aoa3", "textures/entity/mobs/overworld/charger_ghost.png");
				}
				else {
					return new ResourceLocation("aoa3", "textures/entity/mobs/overworld/charger_bloody_ghost.png");
				}
			}
			else {
				return new ResourceLocation("aoa3", "textures/entity/mobs/overworld/charger_zombie.png");
			}
		}

		return backupTexture == null ? new ResourceLocation("aoa3", "textures/entity/mobs/overworld/charger.png") : backupTexture;
	}

	private void doGhostChargerModel() {
		texWidth = 64;
		texHeight = 32;

		root = new ModelRenderer(this);
		root.setPos(0.0F, 24.0F, 0.0F);

		head = new ModelRenderer(this);
		head.setPos(0.0F, -14.0F, 0.0F);
		root.addChild(head);
		head.texOffs(37, 0).addBox(-2.0F, -7.0F, -6.0F, 4, 1, 2, 0.0F, true);
		head.texOffs(3, 20).addBox(-5.0F, -8.0F, -3.0F, 1, 6, 6, 0.0F, true);
		head.texOffs(37, 0).addBox(-3.0F, -6.0F, -6.0F, 6, 2, 2, 0.0F, true);
		head.texOffs(37, 0).addBox(-2.0F, -6.0F, -7.0F, 4, 2, 1, 0.0F, true);
		head.texOffs(37, 0).addBox(-2.0F, -4.0F, -6.0F, 4, 1, 2, 0.0F, true);
		head.texOffs(38, 7).addBox(-3.0F, -10.0F, -3.0F, 6, 1, 6, 0.0F, true);
		head.texOffs(2, 20).addBox(4.0F, -8.0F, -3.0F, 1, 6, 6, 0.0F, false);
		head.texOffs(45, 16).addBox(-3.0F, -8.0F, 4.0F, 6, 6, 1, 0.0F, true);
		head.texOffs(0, 0).addBox(-4.0F, -9.0F, -4.0F, 8, 8, 8, 0.0F, true);
		head.texOffs(9, 24).addBox(-2.0F, -4.0F, -7.0F, 4, 7, 0, 0.0F, false);
		head.texOffs(4, 23).addBox(-3.0F, -2.0F, 5.0F, 6, 8, 0, 0.0F, false);
		head.texOffs(0, 24).addBox(-2.999F, -4.0F, -6.0F, 1, 8, 0, 0.0F, false);
		head.texOffs(0, 24).addBox(3.001F, -2.0F, 4.0F, 1, 7, 0, 0.0F, false);
		head.texOffs(0, 24).addBox(4.001F, -2.0F, 3.0F, 1, 7, 0, 0.0F, false);
		head.texOffs(0, 24).addBox(4.001F, -2.0F, -3.0F, 1, 7, 0, 0.0F, false);
		head.texOffs(0, 24).addBox(-4.999F, -2.0F, 3.0F, 1, 8, 0, 0.0F, false);
		head.texOffs(0, 24).addBox(-4.999F, -2.0F, -3.0F, 1, 8, 0, 0.0F, false);
		head.texOffs(0, 24).addBox(3.001F, -1.0F, -4.0F, 1, 5, 0, 0.0F, false);
		head.texOffs(0, 24).addBox(2.001F, -4.0F, -6.0F, 1, 8, 0, 0.0F, false);
		head.texOffs(0, 24).addBox(-3.999F, -2.0F, 4.0F, 1, 7, 0, 0.0F, false);
		head.texOffs(0, 24).addBox(-3.999F, -1.0F, -4.0F, 1, 7, 0, 0.0F, false);
		head.texOffs(0, 22).addBox(-2.999F, -4.0F, -6.0F, 0, 8, 2, 0.0F, false);
		head.texOffs(0, 22).addBox(-3.999F, -1.0F, -4.0F, 0, 6, 1, 0.0F, false);
		head.texOffs(5, 18).addBox(-5.0F, -2.0F, -3.0F, 0, 8, 6, 0.0F, false);
		head.texOffs(0, 18).addBox(5.001F, -2.0F, -3.0F, 0, 8, 6, 0.0F, true);
		head.texOffs(0, 22).addBox(3.001F, -4.0F, -6.0F, 0, 8, 2, 0.0F, false);
		head.texOffs(0, 23).addBox(2.001F, -4.0F, -7.0F, 0, 8, 1, 0.0F, false);
		head.texOffs(0, 23).addBox(-3.999F, -2.0F, 3.0F, 0, 7, 1, 0.0F, false);
		head.texOffs(0, 23).addBox(4.001F, -1.0F, -4.0F, 0, 5, 1, 0.0F, false);
		head.texOffs(0, 23).addBox(-2.999F, -2.0F, 4.0F, 0, 8, 1, 0.0F, false);
		head.texOffs(0, 23).addBox(3.001F, -2.0F, 4.0F, 0, 8, 1, 0.0F, false);
		head.texOffs(0, 23).addBox(-1.999F, -4.0F, -7.0F, 0, 8, 1, 0.0F, false);
		head.texOffs(0, 23).addBox(4.001F, -2.0F, 3.0F, 0, 7, 1, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setPos(0.0F, 14.0F, 0.0F);
		head.addChild(bone2);
		setRotation(bone2, 0.0F, 0.0F, -0.7854F);
		bone2.texOffs(0, 26).addBox(15.2635F, -13.435F, -6.0F, 1, 1, 2, 0.0F, false);
		bone2.texOffs(0, 26).addBox(12.435F, -16.2635F, -6.0F, 1, 1, 2, 0.0F, false);
		bone2.texOffs(0, 26).addBox(12.0208F, -16.2635F, -6.0F, 1, 1, 2, 0.0F, false);
		bone2.texOffs(0, 26).addBox(15.2635F, -13.0208F, -6.0F, 1, 1, 2, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setPos(0.0F, 14.0F, 0.0F);
		head.addChild(bone);
		setRotation(bone, -0.7854F, 0.0F, 0.0F);
		bone.texOffs(0, 25).addBox(-2.0F, -14.8492F, -19.0919F, 4, 5, 2, 0.0F, false);
		bone.texOffs(0, 26).addBox(-1.999F, -10.1924F, -19.092F, 4, 1, 1, 0.0F, false);

		rightleg = new ModelRenderer(this);
		rightleg.setPos(3.0F, -15.0F, 0.0F);
		root.addChild(rightleg);
		rightleg.texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4, 3, 4, 0.0F, false);
		rightleg.texOffs(18, 24).addBox(-2.0F, 13.0F, -5.0F, 1, 2, 6, 0.0F, true);
		rightleg.texOffs(33, 16).addBox(-1.0F, 3.0F, -1.0F, 2, 12, 2, 0.0F, true);
		rightleg.texOffs(18, 24).addBox(1.0F, 13.0F, -5.0F, 1, 2, 6, 0.0F, true);

		leftleg = new ModelRenderer(this);
		leftleg.setPos(-3.0F, -15.0F, 0.0F);
		root.addChild(leftleg);
		leftleg.texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4, 3, 4, 0.0F, true);
		leftleg.texOffs(18, 24).addBox(-2.0F, 13.0F, -5.0F, 1, 2, 6, 0.0F, true);
		leftleg.texOffs(33, 16).addBox(-1.0F, 3.0F, -1.0F, 2, 12, 2, 0.0F, false);
		leftleg.texOffs(18, 24).addBox(1.0F, 13.0F, -5.0F, 1, 2, 6, 0.0F, true);
	}

	public ChargerModel() {
		if (HolidayUtil.isHalloween() && RandomUtil.fiftyFifty()) {
			doGhostChargerModel();
			isGhost = true;

			return;
		}

		isGhost = false;

		texWidth = 64;
		texHeight = 32;

		root = new ModelRenderer(this);
		root.setPos(0.0F, 24.0F, 0.0F);

		head = new ModelRenderer(this);
		head.setPos(0.0F, -14.0F, 0.0F);
		root.addChild(head);
		head.texOffs(37, 0).addBox(-2.0F, -7.0F, -6.0F, 4, 1, 2, 0.0F, true);
		head.texOffs(16, 4).addBox(-5.0F, -8.0F, -3.0F, 1, 6, 6, 0.0F, true);
		head.texOffs(37, 0).addBox(-3.0F, -6.0F, -6.0F, 6, 2, 2, 0.0F, true);
		head.texOffs(37, 0).addBox(-2.0F, -6.0F, -7.0F, 4, 2, 1, 0.0F, true);
		head.texOffs(37, 0).addBox(-2.0F, -4.0F, -6.0F, 4, 1, 2, 0.0F, true);
		head.texOffs(38, 7).addBox(-3.0F, -10.0F, -3.0F, 6, 1, 6, 0.0F, true);
		head.texOffs(16, 4).addBox(4.0F, -8.0F, -3.0F, 1, 6, 6, 0.0F, true);
		head.texOffs(45, 16).addBox(-3.0F, -8.0F, 4.0F, 6, 6, 1, 0.0F, true);
		head.texOffs(0, 0).addBox(-4.0F, -9.0F, -4.0F, 8, 8, 8, 0.0F, true);

		rightleg = new ModelRenderer(this);
		rightleg.setPos(3.0F, -15.0F, 0.0F);
		root.addChild(rightleg);
		rightleg.texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4, 3, 4, 0.0F, true);
		rightleg.texOffs(18, 24).addBox(-2.0F, 13.0F, -5.0F, 1, 2, 6, 0.0F, true);
		rightleg.texOffs(33, 16).addBox(-1.0F, 3.0F, -1.0F, 2, 12, 2, 0.0F, true);
		rightleg.texOffs(18, 24).addBox(1.0F, 13.0F, -5.0F, 1, 2, 6, 0.0F, true);

		leftleg = new ModelRenderer(this);
		leftleg.setPos(-3.0F, -15.0F, 0.0F);
		root.addChild(leftleg);
		leftleg.texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4, 3, 4, 0.0F, true);
		leftleg.texOffs(18, 24).addBox(-2.0F, 13.0F, -5.0F, 1, 2, 6, 0.0F, true);
		leftleg.texOffs(33, 16).addBox(-1.0F, 3.0F, -1.0F, 2, 12, 2, 0.0F, true);
		leftleg.texOffs(18, 24).addBox(1.0F, 13.0F, -5.0F, 1, 2, 6, 0.0F, true);
	}

	@Override
	public void setupAnim(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		rightleg.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leftleg.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}

	@Override
	public void renderToBuffer(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		root.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}
}
