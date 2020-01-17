package net.tslat.aoa3.client.render.entities.mobs.nether;

import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.entity.mobs.nether.EntityFakePigman;

@SideOnly(Side.CLIENT)
public class FakePigmanRenderer extends RenderBiped<EntityFakePigman> {
    private final ResourceLocation tetxure;

    public FakePigmanRenderer(RenderManager renderManager, ResourceLocation texture) {
        super(renderManager, new ModelZombie(), 0.5f);
        this.tetxure = texture;
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityFakePigman entity) {
        return tetxure;
    }
}
