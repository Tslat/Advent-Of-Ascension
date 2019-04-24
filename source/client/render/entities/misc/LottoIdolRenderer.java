package net.tslat.aoa3.client.render.entities.misc;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.model.entities.misc.ModelLottoIdol;
import net.tslat.aoa3.entity.misc.EntityLottoIdol;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class LottoIdolRenderer extends RenderLiving<EntityLottoIdol> {
	private final ResourceLocation texture;

	public LottoIdolRenderer(RenderManager renderManager, final ResourceLocation resource) {
		super(renderManager, new ModelLottoIdol(), EntityLottoIdol.entityWidth / 3);
		texture = resource;
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityLottoIdol entity) {
		return texture;
	}
}