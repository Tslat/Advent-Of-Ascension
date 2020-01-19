package net.tslat.aoa3.client.render.entities.misc;

import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.RenderEntityItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BossItemRenderer extends RenderEntityItem {
	public BossItemRenderer(RenderManager renderManager, RenderItem renderItem) {
		super(renderManager, renderItem);
	}
}
