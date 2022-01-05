package net.tslat.aoa3.client.model.entity.animal;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.tslat.aoa3.client.model.misc.FullbrightModelRenderer;

import java.util.Arrays;

public class ShinySquidModel extends SegmentedModel<MobEntity> {
	protected final ModelRenderer body;
	protected final ModelRenderer[] tentacles = new ModelRenderer[8];
	protected final ImmutableList<ModelRenderer> parts;

	public ShinySquidModel() {
		this.body = new FullbrightModelRenderer(this, 0, 0);
		this.body.addBox(-6, -8, -6, 12, 16, 12);
		this.body.y += 8;

		for(int i = 0; i < this.tentacles.length; ++i) {
			this.tentacles[i] = new FullbrightModelRenderer(this, 48, 0);
			double rotPos = (double)i * Math.PI * 2 / (double)this.tentacles.length;

			this.tentacles[i].addBox(-1, 0, -1, 2, 18, 2);

			this.tentacles[i].x = (float)Math.cos(rotPos) * 5;
			this.tentacles[i].z = (float)Math.sin(rotPos) * 5;
			this.tentacles[i].y = 15;
			rotPos = (double)i * Math.PI * -2 / (double)this.tentacles.length + (Math.PI / 2d);
			this.tentacles[i].yRot = (float)rotPos;
		}

		ImmutableList.Builder<ModelRenderer> builder = ImmutableList.builder();
		
		builder.add(this.body);
		builder.addAll(Arrays.asList(this.tentacles));
		
		this.parts = builder.build();
	}

	@Override
	public void setupAnim(MobEntity squid, float pLimbSwing, float pLimbSwingAmount, float age, float pNetHeadYaw, float pHeadPitch) {
		for(ModelRenderer modelrenderer : this.tentacles) {
			modelrenderer.xRot = age;
		}
	}

	@Override
	public Iterable<ModelRenderer> parts() {
		return this.parts;
	}
}
