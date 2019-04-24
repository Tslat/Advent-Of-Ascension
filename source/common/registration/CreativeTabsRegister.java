package net.tslat.aoa3.common.registration;

import net.minecraft.creativetab.CreativeTabs;
import net.tslat.aoa3.client.gui.creativetabs.*;

public class CreativeTabsRegister {
	public static final CreativeTabs miscTab = new MiscTab(CreativeTabs.getNextID(), "AdventMiscTab");
	public static final CreativeTabs foodTab = new FoodTab(CreativeTabs.getNextID(), "AdventFoodTab");
	public static final CreativeTabs toolsTab = new ToolsTab(CreativeTabs.getNextID(), "AdventToolsTab");
	public static final CreativeTabs armourTab = new ArmourTab(CreativeTabs.getNextID(), "AdventArmourTab");
	public static final CreativeTabs swordsTab = new SwordsTab(CreativeTabs.getNextID(), "AdventSwordsTab");
	public static final CreativeTabs greatbladesTab = new GreatbladesTab(CreativeTabs.getNextID(), "AdventGreatbladesTab");
	public static final CreativeTabs maulsTab = new MaulsTab(CreativeTabs.getNextID(), "AdventMaulsTab");
	public static final CreativeTabs gunsTab = new GunsTab(CreativeTabs.getNextID(), "AdventGunsTab");
	public static final CreativeTabs cannonsTab = new CannonsTab(CreativeTabs.getNextID(), "AdventCannonsTab");
	public static final CreativeTabs shotgunsTab = new ShotgunsTab(CreativeTabs.getNextID(), "AdventShotgunsTab");
	public static final CreativeTabs snipersTab = new SnipersTab(CreativeTabs.getNextID(), "AdventSnipersTab");
	public static final CreativeTabs blastersTab = new BlastersTab(CreativeTabs.getNextID(), "AdventBlastersTab");
	public static final CreativeTabs archergunsTab = new ArchergunsTab(CreativeTabs.getNextID(), "AdventArchergunsTab");
	public static final CreativeTabs bowsTab = new BowsTab(CreativeTabs.getNextID(), "AdventBowsTab");
	public static final CreativeTabs thrownWeaponsTab = new ThrownWeaponsTab(CreativeTabs.getNextID(), "AdventThrownWeaponsTab");
	public static final CreativeTabs stavesTab = new StavesTab(CreativeTabs.getNextID(), "AdventStavesTab");
	public static final CreativeTabs vulcanesTab = new VulcanesTab(CreativeTabs.getNextID(), "AdventVulcanesTab");
	public static final CreativeTabs ammoTab = new AmmoTab(CreativeTabs.getNextID(), "AdventAmmoTab");
	public static final CreativeTabs minionSlabsTab = new MinionSlabsTab(CreativeTabs.getNextID(), "AdventMinionSlabsTab");
	public static final CreativeTabs generationBlocksTab = new GenerationBlocksTab(CreativeTabs.getNextID(), "AdventGenerationBlocksTab");
	public static final CreativeTabs decorationBlocksTab = new DecorationBlocksTab(CreativeTabs.getNextID(), "AdventDecorationBlocksTab");
	public static final CreativeTabs functionalBlocksTab = new FunctionalBlocksTab(CreativeTabs.getNextID(), "AdventFunctionalBlocksTab");
	public static final CreativeTabs bannersTab = new BannersTab(CreativeTabs.getNextID(), "AdventBannersTab");
	public static final CreativeTabs statuesTab = new StatuesTab(CreativeTabs.getNextID(), "AdventStatuesTab");
	public static final CreativeTabs totemsTab = new TotemsTab(CreativeTabs.getNextID(), "AdventTotemsTab");
}
