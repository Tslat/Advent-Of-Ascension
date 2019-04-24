package net.nevermine.izer.equipment;

import net.minecraft.item.Item;
import net.nevermine.item.soul.*;

public class Tabletizer {
	public static Item TabletGills;
	public static Item TabletCombat;
	public static Item TabletAgility;
	public static Item TabletFireResistance;
	public static Item TabletFuryMining;
	public static Item TabletGravity;
	public static Item TabletNightVision;
	public static Item TabletRegeneration;
	public static Item TabletResistance;
	public static Item TabletSpeed;
	public static Item TabletStrength;
	public static Item TabletInvisibility;
	public static Item TabletDivine;
	public static Item TabletHealth;
	public static Item TabletEnergy;
	public static Item TabletCreation;
	public static Item TabletVitality;
	public static Item TabletAirhop;
	public static Item TabletPeace;
	public static Item TabletDebuff;
	public static Item TabletHunger;

	static {
		Tabletizer.TabletGills = new TabletGills(12000, 5).setUnlocalizedName("TabletGills").setTextureName("nevermine:tabletGills");
		Tabletizer.TabletCombat = new TabletCombat(20000, 85).setUnlocalizedName("TabletCombat").setTextureName("nevermine:tabletCombat");
		Tabletizer.TabletAgility = new TabletAgility(20000, 75).setUnlocalizedName("TabletAgility").setTextureName("nevermine:tabletAgility");
		Tabletizer.TabletFireResistance = new TabletFireResistance(18000, 20).setUnlocalizedName("TabletFireResistance").setTextureName("nevermine:tabletFireResistance");
		Tabletizer.TabletFuryMining = new TabletFuryMining(20000, 30).setUnlocalizedName("TabletFuryMining").setTextureName("nevermine:tabletFuryMining");
		Tabletizer.TabletGravity = new TabletGravity(16000, 10).setUnlocalizedName("TabletGravity").setTextureName("nevermine:tabletGravity");
		Tabletizer.TabletNightVision = new TabletNightVision(16000, 1).setUnlocalizedName("TabletNightVision").setTextureName("nevermine:tabletNightVision");
		Tabletizer.TabletRegeneration = new TabletRegeneration(20000, 45).setUnlocalizedName("TabletRegeneration").setTextureName("nevermine:tabletRegeneration");
		Tabletizer.TabletResistance = new TabletResistance(18000, 35).setUnlocalizedName("TabletResistance").setTextureName("nevermine:tabletResistance");
		Tabletizer.TabletSpeed = new TabletSpeed(14000, 25).setUnlocalizedName("TabletSpeed").setTextureName("nevermine:tabletSpeed");
		Tabletizer.TabletStrength = new TabletStrength(20000, 50).setUnlocalizedName("TabletStrength").setTextureName("nevermine:tabletStrength");
		Tabletizer.TabletInvisibility = new TabletInvisibility(10000, 1).setUnlocalizedName("TabletInvisibility").setTextureName("nevermine:tabletInvisibility");
		Tabletizer.TabletDivine = new TabletDivine(12000, 80).setUnlocalizedName("TabletDivine").setTextureName("nevermine:tabletDivine");
		Tabletizer.TabletHealth = new TabletHealth(10000, 28).setUnlocalizedName("TabletHealth").setTextureName("nevermine:tabletHealth");
		Tabletizer.TabletEnergy = new TabletEnergy(6000, 18).setUnlocalizedName("TabletEnergy").setTextureName("nevermine:tabletEnergy");
		Tabletizer.TabletCreation = new TabletCreation(18000, 38).setUnlocalizedName("TabletCreation").setTextureName("nevermine:tabletCreation");
		Tabletizer.TabletVitality = new TabletVitality(2000, 48).setUnlocalizedName("TabletVitality").setTextureName("nevermine:tabletVitality");
		Tabletizer.TabletAirhop = new TabletAirhop(2000, 7).setUnlocalizedName("TabletAirhop").setTextureName("nevermine:tabletAirhop");
		Tabletizer.TabletPeace = new TabletPeace(20000, 53).setUnlocalizedName("TabletPeace").setTextureName("nevermine:tabletPeace");
		Tabletizer.TabletDebuff = new TabletDebuff(12000, 13).setUnlocalizedName("TabletDebuff").setTextureName("nevermine:tabletDebuff");
		Tabletizer.TabletHunger = new TabletHunger(18000, 58).setUnlocalizedName("TabletHunger").setTextureName("nevermine:tabletHunger");
	}
}
