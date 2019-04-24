package net.tslat.aoa3.client.render.entities.mobs.overworld;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.entity.mobs.overworld.EntityClown;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class ClownRenderer extends RenderBiped<EntityClown> {
	private final ResourceLocation texture;

	public ClownRenderer(RenderManager renderManager, final ResourceLocation resource) {
		super(renderManager, new ModelZombie(), EntityClown.entityWidth / 3);
		texture = resource;
	}

	@Override
	protected void renderLayers(EntityClown entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scaleIn) {
		for (LayerRenderer<EntityClown> layer : this.layerRenderers) {
			boolean flag = this.setBrightness(entitylivingbaseIn, partialTicks, layer.shouldCombineTextures());

			if (layer.getClass().isAssignableFrom(LayerHeldItem.class)) {
				renderWeaponCorrectly(layer, entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scaleIn);
			}
			else {
				layer.doRenderLayer(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scaleIn);
			}

			if (flag)
				this.unsetBrightness();
		}
	}

	private void renderWeaponCorrectly(LayerRenderer<EntityClown> layer, EntityClown entity, float limbWing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scaleIn) {
		boolean flag = entity.getPrimaryHand() == EnumHandSide.RIGHT;
		ItemStack itemstack = flag ? entity.getHeldItemOffhand() : entity.getHeldItemMainhand();
		ItemStack itemstack1 = flag ? entity.getHeldItemMainhand() : entity.getHeldItemOffhand();

		if (!itemstack.isEmpty() || !itemstack1.isEmpty()) {
			GlStateManager.pushMatrix();

			if (this.getMainModel().isChild) {
				GlStateManager.translate(0.0F, 0.75F, 0.0F);
				GlStateManager.scale(0.5F, 0.5F, 0.5F);
			}

			this.renderHeldItem(entity, itemstack1, ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, EnumHandSide.RIGHT);
			this.renderHeldItem(entity, itemstack, ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND, EnumHandSide.LEFT);
			GlStateManager.popMatrix();
		}
	}

	private void renderHeldItem(EntityLivingBase entity, ItemStack heldStack, ItemCameraTransforms.TransformType transform, EnumHandSide hand) {
		if (!heldStack.isEmpty()) {
			GlStateManager.pushMatrix();

			if (entity.isSneaking())
				GlStateManager.translate(0.0F, 0.2F, 0.0F);

			this.translateToHand(hand);
			GlStateManager.rotate(-45f, 1.0F, 0.0F, 0.0F);
			GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);

			boolean flag = hand == EnumHandSide.LEFT;

			GlStateManager.translate((float)(flag ? -1 : 1) / 16.0F, 0.125F, -0.625F);
			Minecraft.getMinecraft().getItemRenderer().renderItemSide(entity, heldStack, transform, flag);
			GlStateManager.popMatrix();
		}
	}

	private void translateToHand(EnumHandSide hand) {
		((ModelBiped)this.getMainModel()).postRenderArm(0.0625F, hand);
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityClown entity) {
		return texture;
	}
}