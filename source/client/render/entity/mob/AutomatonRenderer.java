package net.tslat.aoa3.client.render.entity.mob;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.client.model.entity.mob.haven.AutomatonModel;
import net.tslat.aoa3.client.render.entity.AoAMobRenderer;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.mob.haven.AutomatonEntity;

public class AutomatonRenderer extends AoAMobRenderer {
	private static final ResourceLocation blueAutomaton = new ResourceLocation("aoa3", "textures/entities/mobs/haven/blue_automaton.png");
	private static final ResourceLocation greenAutomaton = new ResourceLocation("aoa3", "textures/entities/mobs/haven/green_automaton.png");
	private static final ResourceLocation purpleAutomaton = new ResourceLocation("aoa3", "textures/entities/mobs/haven/purple_automaton.png");
	private static final ResourceLocation redAutomaton = new ResourceLocation("aoa3", "textures/entities/mobs/haven/red_automaton.png");
	private static final ResourceLocation yellowAutomaton = new ResourceLocation("aoa3", "textures/entities/mobs/haven/yellow_automaton.png");

	public AutomatonRenderer(EntityRendererManager renderManager) {
		super(renderManager, new AutomatonModel(), AoAEntities.Mobs.AUTOMATON.get().getWidth() / 3f, 1f, blueAutomaton);
	}

	@Override
	public ResourceLocation getEntityTexture(MobEntity entity) {
		if (entity instanceof AutomatonEntity) {
			switch (((AutomatonEntity)entity).getColour()) {
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

		return blueAutomaton;
	}
}