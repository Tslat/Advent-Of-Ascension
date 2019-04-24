package net.nevermine.rendering;

import net.nevermine.common.nevermine;
import net.nevermine.resource.creation.creationRenderer;
import net.nevermine.resource.energy.energyRenderer;
import net.nevermine.resource.rage.rageRenderer;
import net.nevermine.resource.revenge.revengeRenderer;
import net.nevermine.resource.soulpower.soulPowerRenderer;
import net.nevermine.resource.tribute.tributeRenderer;
import net.nevermine.skill.anima.animaRenderer;
import net.nevermine.skill.augury.auguryRenderer;
import net.nevermine.skill.butchery.butcheryRenderer;
import net.nevermine.skill.creation.creationSkillRenderer;
import net.nevermine.skill.expedition.expeditionRenderer;
import net.nevermine.skill.extraction.extractionRenderer;
import net.nevermine.skill.foraging.foragingRenderer;
import net.nevermine.skill.hauling.haulingRenderer;
import net.nevermine.skill.hermetism.hermetismRenderer;
import net.nevermine.skill.hunter.hunterRenderer;
import net.nevermine.skill.infusion.infusionRenderer;
import net.nevermine.skill.innervation.innervationRenderer;
import net.nevermine.skill.logging.loggingRenderer;
import net.nevermine.skill.robbery.robberyRenderer;
import net.nevermine.skill.runation.runationRenderer;
import net.nevermine.skill.vulcanism.vulcanismRenderer;

public class SkillRenders {
	public static void init() {
		nevermine.addSpecialEventBus(new energyRenderer());
		nevermine.addSpecialEventBus(new soulPowerRenderer());
		nevermine.addSpecialEventBus(new creationRenderer());
		nevermine.addSpecialEventBus(new rageRenderer());
		nevermine.addSpecialEventBus(new hunterRenderer());
		nevermine.addSpecialEventBus(new foragingRenderer());
		nevermine.addSpecialEventBus(new auguryRenderer());
		nevermine.addSpecialEventBus(new infusionRenderer());
		nevermine.addSpecialEventBus(new robberyRenderer());
		nevermine.addSpecialEventBus(new runationRenderer());
		nevermine.addSpecialEventBus(new creationSkillRenderer());
		nevermine.addSpecialEventBus(new innervationRenderer());
		nevermine.addSpecialEventBus(new animaRenderer());
		nevermine.addSpecialEventBus(new extractionRenderer());
		nevermine.addSpecialEventBus(new vulcanismRenderer());
		nevermine.addSpecialEventBus(new expeditionRenderer());
		nevermine.addSpecialEventBus(new tributeRenderer());
		nevermine.addSpecialEventBus(new loggingRenderer());
		nevermine.addSpecialEventBus(new hermetismRenderer());
		nevermine.addSpecialEventBus(new butcheryRenderer());
		nevermine.addSpecialEventBus(new revengeRenderer());
		nevermine.addSpecialEventBus(new haulingRenderer());
	}
}
