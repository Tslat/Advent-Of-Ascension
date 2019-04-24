package net.nevermine.block.generation.structure;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.nevermine.container.PlayerContainer;
import net.nevermine.izer.Itemizer;

import static net.nevermine.container.PlayerContainer.Skills.Runation;

public class RuneShrine extends Block {
	public RuneShrine(final Material p_i45394_1_) {
		super(p_i45394_1_);
		setCreativeTab(Itemizer.GenerationTab);
		setHardness(1.0f);
		setResistance(5.0f);
	}

	public boolean onBlockActivated(final World w, final int x, final int y, final int z, final EntityPlayer p, final int var6, final float var7, final float var8, final float var9) {
		if (w.isRemote || p.isSneaking())
			return true;

		PlayerContainer cont = PlayerContainer.getProperties(p);
		int lvl = cont.getLevel(Runation);
		Block bl = w.getBlock(x + 3, y + 2, z + 3);
		boolean success = false;

		switch (bl.getUnlocalizedName()) {
			case "tile.runePostFire":
				if (lvl >= 8 && p.inventory.consumeInventoryItem(Itemizer.Rune)) {
					p.dropItem(Itemizer.FireRune, lvl < 55 ? 1 : 2);
					cont.addExperience(7.0f, Runation);
					success = true;
				}
				break;
			case "tile.runePostWater":
				if (lvl >= 15 && p.inventory.consumeInventoryItem(Itemizer.Rune)) {
					p.dropItem(Itemizer.WaterRune, lvl < 65 ? 1 : 2);
					cont.addExperience(10.0f, Runation);
					success = true;
				}
				break;
			case "tile.runePostPoison":
				if (lvl >= 22 && p.inventory.consumeInventoryItem(Itemizer.Rune)) {
					p.dropItem(Itemizer.PoisonRune, lvl < 75 ? 1 : 2);
					cont.addExperience(14.0f, Runation);
					success = true;
				}
				break;
			case "tile.runePostWither":
				if (lvl >= 30 && p.inventory.consumeInventoryItem(Itemizer.Rune)) {
					p.dropItem(Itemizer.WitherRune, lvl < 85 ? 1 : 2);
					cont.addExperience(15.0f, Runation);
					success = true;
				}
				break;
			case "tile.runePostLunar":
				if (lvl >= 32 && p.inventory.consumeInventoryItem(Itemizer.RuneCharged)) {
					p.dropItem(Itemizer.LunarRune, lvl < 90 ? 1 : 2);
					cont.addExperience(20.0f, Runation);
					success = true;
				}
				break;
			case "tile.runePostEnergy":
				if (lvl >= 36 && p.inventory.consumeInventoryItem(Itemizer.Rune)) {
					p.dropItem(Itemizer.EnergyRune, lvl < 95 ? 1 : 2);
					cont.addExperience(22.0f, Runation);
					success = true;
				}
				break;
			case "tile.runePostStrike":
				if (lvl >= 42 && p.inventory.consumeInventoryItem(Itemizer.Rune)) {
					p.dropItem(Itemizer.StrikeRune, 1);
					cont.addExperience(24.0f, Runation);
					success = true;
				}
				break;
			case "tile.runePostStorm":
				if (lvl >= 49 && p.inventory.consumeInventoryItem(Itemizer.RuneCharged)) {
					p.dropItem(Itemizer.StormRune, 1);
					cont.addExperience(30.0f, Runation);
					success = true;
				}
				break;
			case "tile.runePostKinetic":
				if (lvl >= 54 && p.inventory.consumeInventoryItem(Itemizer.RuneCharged)) {
					p.dropItem(Itemizer.KineticRune, 1);
					cont.addExperience(32.0f, Runation);
					success = true;
				}
				break;
			case "tile.runePostPower":
				if (lvl >= 59 && p.inventory.consumeInventoryItem(Itemizer.Rune)) {
					p.dropItem(Itemizer.PowerRune, 1);
					cont.addExperience(40.0f, Runation);
					success = true;
				}
				break;
			case "tile.runePostCompass":
				if (lvl >= 63 && p.inventory.consumeInventoryItem(Itemizer.RuneCharged)) {
					p.dropItem(Itemizer.CompassRune, 1);
					cont.addExperience(45.0f, Runation);
					success = true;
				}
				break;
			case "tile.runePostDistortion":
				if (lvl >= 65 && p.inventory.consumeInventoryItem(Itemizer.RuneCharged)) {
					p.dropItem(Itemizer.DistortionRune, 1);
					cont.addExperience(50.0f, Runation);
					success = true;
				}
				break;
			case "tile.runePostLife":
				if (lvl >= 72 && p.inventory.consumeInventoryItem(Itemizer.RuneCharged)) {
					p.dropItem(Itemizer.LifeRune, 1);
					cont.addExperience(56.0f, Runation);
					success = true;
				}
				break;
			default:
				if (p.inventory.consumeInventoryItem(Itemizer.Rune)) {
					p.dropItem(Itemizer.WindRune, lvl < 40 ? 1 : 2);
					cont.addExperience(2.0f, Runation);
					success = true;
				}
				break;
		}

		if (success) {
			w.playSoundAtEntity(p, "nevermine:RuneMake", 1.85f, 1.0f);

			if (p instanceof EntityPlayerMP)
				((EntityPlayerMP)p).sendContainerToPlayer(p.inventoryContainer);
		}

		return true;
	}
}
