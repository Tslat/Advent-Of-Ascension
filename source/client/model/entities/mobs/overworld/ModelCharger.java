package net.tslat.aoa3.client.model.entities.mobs.overworld;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.tslat.aoa3.advent.AdventOfAscension;

import javax.annotation.Nullable;

public class ModelCharger extends ModelBase {
	private final boolean isGhost;

	private ModelRenderer root;
	private ModelRenderer head;
	private ModelRenderer bone;
	private ModelRenderer bone2;
	private ModelRenderer rightleg;
	private ModelRenderer leftleg;

	public static ResourceLocation getChargerTexture(ModelCharger model, @Nullable ResourceLocation backupTexture) {
		if (AdventOfAscension.instance().getCurrentHoliday() == AdventOfAscension.Holiday.HALLOWEEN) {
			if (model.isGhost) {
				if (AdventOfAscension.rand.nextBoolean()) {
					return new ResourceLocation("aoa3", "textures/entities/mobs/overworld/charger_ghost.png");
				}
				else {
					return new ResourceLocation("aoa3", "textures/entities/mobs/overworld/charger_bloody_ghost.png");
				}
			}
			else {
				return new ResourceLocation("aoa3", "textures/entities/mobs/overworld/charger_zombie.png");
			}
		}

		return backupTexture == null ? new ResourceLocation("aoa3", "textures/entities/mobs/overworld/charger.png") : backupTexture;
	}

	private void doGhostChargerModel() {
		textureWidth = 64;
		textureHeight = 32;

		root = new ModelRenderer(this);
		root.setRotationPoint(0.0F, 24.0F, 0.0F);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -14.0F, 0.0F);
		root.addChild(head);
		head.cubeList.add(new ModelBox(head, 37, 0, -2.0F, -7.0F, -6.0F, 4, 1, 2, 0.0F, true));
		head.cubeList.add(new ModelBox(head, 3, 20, -5.0F, -8.0F, -3.0F, 1, 6, 6, 0.0F, true));
		head.cubeList.add(new ModelBox(head, 37, 0, -3.0F, -6.0F, -6.0F, 6, 2, 2, 0.0F, true));
		head.cubeList.add(new ModelBox(head, 37, 0, -2.0F, -6.0F, -7.0F, 4, 2, 1, 0.0F, true));
		head.cubeList.add(new ModelBox(head, 37, 0, -2.0F, -4.0F, -6.0F, 4, 1, 2, 0.0F, true));
		head.cubeList.add(new ModelBox(head, 38, 7, -3.0F, -10.0F, -3.0F, 6, 1, 6, 0.0F, true));
		head.cubeList.add(new ModelBox(head, 2, 20, 4.0F, -8.0F, -3.0F, 1, 6, 6, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 45, 16, -3.0F, -8.0F, 4.0F, 6, 6, 1, 0.0F, true));
		head.cubeList.add(new ModelBox(head, 0, 0, -4.0F, -9.0F, -4.0F, 8, 8, 8, 0.0F, true));
		head.cubeList.add(new ModelBox(head, 9, 24, -2.0F, -4.0F, -7.0F, 4, 7, 0, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 4, 23, -3.0F, -2.0F, 5.0F, 6, 8, 0, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 0, 24, -2.999F, -4.0F, -6.0F, 1, 8, 0, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 0, 24, 3.001F, -2.0F, 4.0F, 1, 7, 0, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 0, 24, 4.001F, -2.0F, 3.0F, 1, 7, 0, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 0, 24, 4.001F, -2.0F, -3.0F, 1, 7, 0, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 0, 24, -4.999F, -2.0F, 3.0F, 1, 8, 0, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 0, 24, -4.999F, -2.0F, -3.0F, 1, 8, 0, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 0, 24, 3.001F, -1.0F, -4.0F, 1, 5, 0, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 0, 24, 2.001F, -4.0F, -6.0F, 1, 8, 0, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 0, 24, -3.999F, -2.0F, 4.0F, 1, 7, 0, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 0, 24, -3.999F, -1.0F, -4.0F, 1, 7, 0, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 0, 22, -2.999F, -4.0F, -6.0F, 0, 8, 2, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 0, 22, -3.999F, -1.0F, -4.0F, 0, 6, 1, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 5, 18, -5.0F, -2.0F, -3.0F, 0, 8, 6, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 0, 18, 5.001F, -2.0F, -3.0F, 0, 8, 6, 0.0F, true));
		head.cubeList.add(new ModelBox(head, 0, 22, 3.001F, -4.0F, -6.0F, 0, 8, 2, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 0, 23, 2.001F, -4.0F, -7.0F, 0, 8, 1, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 0, 23, -3.999F, -2.0F, 3.0F, 0, 7, 1, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 0, 23, 4.001F, -1.0F, -4.0F, 0, 5, 1, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 0, 23, -2.999F, -2.0F, 4.0F, 0, 8, 1, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 0, 23, 3.001F, -2.0F, 4.0F, 0, 8, 1, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 0, 23, -1.999F, -4.0F, -7.0F, 0, 8, 1, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 0, 23, 4.001F, -2.0F, 3.0F, 0, 7, 1, 0.0F, false));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.0F, 14.0F, 0.0F);
		head.addChild(bone2);
		setRotation(bone2, 0.0F, 0.0F, -0.7854F);
		bone2.cubeList.add(new ModelBox(bone2, 0, 26, 15.2635F, -13.435F, -6.0F, 1, 1, 2, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 0, 26, 12.435F, -16.2635F, -6.0F, 1, 1, 2, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 0, 26, 12.0208F, -16.2635F, -6.0F, 1, 1, 2, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 0, 26, 15.2635F, -13.0208F, -6.0F, 1, 1, 2, 0.0F, false));

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 14.0F, 0.0F);
		head.addChild(bone);
		setRotation(bone, -0.7854F, 0.0F, 0.0F);
		bone.cubeList.add(new ModelBox(bone, 0, 26, -2.0F, -14.8492F, -19.0919F, 4, 5, 2, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 26, -1.999F, -10.1924F, -19.092F, 4, 1, 1, 0.0F, false));

		rightleg = new ModelRenderer(this);
		rightleg.setRotationPoint(3.0F, -15.0F, 0.0F);
		root.addChild(rightleg);
		rightleg.cubeList.add(new ModelBox(rightleg, 0, 16, -2.0F, 0.0F, -2.0F, 4, 3, 4, 0.0F, false));
		rightleg.cubeList.add(new ModelBox(rightleg, 18, 24, -2.0F, 13.0F, -5.0F, 1, 2, 6, 0.0F, true));
		rightleg.cubeList.add(new ModelBox(rightleg, 33, 16, -1.0F, 3.0F, -1.0F, 2, 12, 2, 0.0F, true));
		rightleg.cubeList.add(new ModelBox(rightleg, 18, 24, 1.0F, 13.0F, -5.0F, 1, 2, 6, 0.0F, true));

		leftleg = new ModelRenderer(this);
		leftleg.setRotationPoint(-3.0F, -15.0F, 0.0F);
		root.addChild(leftleg);
		leftleg.cubeList.add(new ModelBox(leftleg, 0, 16, -2.0F, 0.0F, -2.0F, 4, 3, 4, 0.0F, true));
		leftleg.cubeList.add(new ModelBox(leftleg, 18, 24, -2.0F, 13.0F, -5.0F, 1, 2, 6, 0.0F, true));
		leftleg.cubeList.add(new ModelBox(leftleg, 33, 16, -1.0F, 3.0F, -1.0F, 2, 12, 2, 0.0F, false));
		leftleg.cubeList.add(new ModelBox(leftleg, 18, 24, 1.0F, 13.0F, -5.0F, 1, 2, 6, 0.0F, true));
	}

	public ModelCharger() {
		if (AdventOfAscension.instance().getCurrentHoliday() == AdventOfAscension.Holiday.HALLOWEEN && AdventOfAscension.rand.nextBoolean()) {
			doGhostChargerModel();
			isGhost = true;

			return;
		}

		isGhost = false;

		textureWidth = 64;
		textureHeight = 32;

		root = new ModelRenderer(this);
		root.setRotationPoint(0.0F, 24.0F, 0.0F);


		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -14.0F, 0.0F);
		root.addChild(head);
		head.cubeList.add(new ModelBox(head, 37, 0, -2.0F, -7.0F, -6.0F, 4, 1, 2, 0.0F, true));
		head.cubeList.add(new ModelBox(head, 16, 4, -5.0F, -8.0F, -3.0F, 1, 6, 6, 0.0F, true));
		head.cubeList.add(new ModelBox(head, 37, 0, -3.0F, -6.0F, -6.0F, 6, 2, 2, 0.0F, true));
		head.cubeList.add(new ModelBox(head, 37, 0, -2.0F, -6.0F, -7.0F, 4, 2, 1, 0.0F, true));
		head.cubeList.add(new ModelBox(head, 37, 0, -2.0F, -4.0F, -6.0F, 4, 1, 2, 0.0F, true));
		head.cubeList.add(new ModelBox(head, 38, 7, -3.0F, -10.0F, -3.0F, 6, 1, 6, 0.0F, true));
		head.cubeList.add(new ModelBox(head, 16, 4, 4.0F, -8.0F, -3.0F, 1, 6, 6, 0.0F, true));
		head.cubeList.add(new ModelBox(head, 45, 16, -3.0F, -8.0F, 4.0F, 6, 6, 1, 0.0F, true));
		head.cubeList.add(new ModelBox(head, 0, 0, -4.0F, -9.0F, -4.0F, 8, 8, 8, 0.0F, true));

		rightleg = new ModelRenderer(this);
		rightleg.setRotationPoint(3.0F, -15.0F, 0.0F);
		root.addChild(rightleg);
		rightleg.cubeList.add(new ModelBox(rightleg, 0, 16, -2.0F, 0.0F, -2.0F, 4, 3, 4, 0.0F, true));
		rightleg.cubeList.add(new ModelBox(rightleg, 18, 24, -2.0F, 13.0F, -5.0F, 1, 2, 6, 0.0F, true));
		rightleg.cubeList.add(new ModelBox(rightleg, 33, 16, -1.0F, 3.0F, -1.0F, 2, 12, 2, 0.0F, true));
		rightleg.cubeList.add(new ModelBox(rightleg, 18, 24, 1.0F, 13.0F, -5.0F, 1, 2, 6, 0.0F, true));

		leftleg = new ModelRenderer(this);
		leftleg.setRotationPoint(-3.0F, -15.0F, 0.0F);
		root.addChild(leftleg);
		leftleg.cubeList.add(new ModelBox(leftleg, 0, 16, -2.0F, 0.0F, -2.0F, 4, 3, 4, 0.0F, true));
		leftleg.cubeList.add(new ModelBox(leftleg, 18, 24, -2.0F, 13.0F, -5.0F, 1, 2, 6, 0.0F, true));
		leftleg.cubeList.add(new ModelBox(leftleg, 33, 16, -1.0F, 3.0F, -1.0F, 2, 12, 2, 0.0F, true));
		leftleg.cubeList.add(new ModelBox(leftleg, 18, 24, 1.0F, 13.0F, -5.0F, 1, 2, 6, 0.0F, true));
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		root.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		rightleg.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		leftleg.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}
