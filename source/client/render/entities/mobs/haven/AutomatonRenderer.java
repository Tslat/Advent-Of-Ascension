package net.tslat.aoa3.client.render.entities.mobs.haven;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.model.entities.mobs.haven.ModelAutomaton;
import net.tslat.aoa3.entity.mobs.haven.EntityAutomaton;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class AutomatonRenderer extends RenderLiving<EntityAutomaton> {
	private final ResourceLocation blueAutomaton = new ResourceLocation("aoa3", "textures/entities/mobs/haven/blue_automaton.png");
	private final ResourceLocation greenAutomaton = new ResourceLocation("aoa3", "textures/entities/mobs/haven/green_automaton.png");
	private final ResourceLocation purpleAutomaton = new ResourceLocation("aoa3", "textures/entities/mobs/haven/purple_automaton.png");
	private final ResourceLocation redAutomaton = new ResourceLocation("aoa3", "textures/entities/mobs/haven/red_automaton.png");
	private final ResourceLocation yellowAutomaton = new ResourceLocation("aoa3", "textures/entities/mobs/haven/yellow_automaton.png");

	public AutomatonRenderer(RenderManager renderManager) {
		super(renderManager, new ModelAutomaton(), EntityAutomaton.entityWidth / 3);
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityAutomaton entity) {
		switch (entity.getColour()) {
			case 0:
				return blueAutomaton;
			case 1:
				return redAutomaton;
			case 2:
				return greenAutomaton;
			case 3:
				return purpleAutomaton;
			case 4:
			default:
				return yellowAutomaton;
		}
	}
}