package net.nevermine.mob.render.haven;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.entity.haven.EntityAutomaton;
import net.nevermine.mob.model.haven.modelAutomaton;

@SideOnly(Side.CLIENT)
public class RenderAutomaton extends RenderLiving {
	private final ResourceLocation EntityTexture1;
	private final ResourceLocation EntityTexture2;
	private final ResourceLocation EntityTexture3;
	private final ResourceLocation EntityTexture4;
	private final ResourceLocation EntityTexture5;
	protected modelAutomaton model;
	private int color;

	public RenderAutomaton(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		EntityTexture1 = new ResourceLocation("nevermine:textures/mobs/automaton_1.png");
		EntityTexture2 = new ResourceLocation("nevermine:textures/mobs/automaton_2.png");
		EntityTexture3 = new ResourceLocation("nevermine:textures/mobs/automaton_3.png");
		EntityTexture4 = new ResourceLocation("nevermine:textures/mobs/automaton_4.png");
		EntityTexture5 = new ResourceLocation("nevermine:textures/mobs/automaton_5.png");
		model = (modelAutomaton)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		final EntityAutomaton thisentity = (EntityAutomaton)entity;
		switch (thisentity.getClrType()) {
			case 1: {
				return EntityTexture1;
			}
			case 2: {
				return EntityTexture2;
			}
			case 3: {
				return EntityTexture3;
			}
			case 4: {
				return EntityTexture4;
			}
			case 5: {
				return EntityTexture5;
			}
			default: {
				return EntityTexture1;
			}
		}
	}
}
