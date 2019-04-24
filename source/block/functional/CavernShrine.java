package net.nevermine.block.functional;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.container.AncientBossesContainer;
import net.nevermine.container.PlayerContainer;
import net.nevermine.izer.Blockizer;
import net.nevermine.izer.Itemizer;

import static net.nevermine.container.PlayerContainer.Deities.*;
import static net.nevermine.container.PlayerContainer.Skills.*;

public class CavernShrine extends Block {
	private static Block.SoundType rck = Block.soundTypeStone;

	public CavernShrine(final Material p_i45394_1_) {
		super(p_i45394_1_);
		setCreativeTab(Itemizer.TablesTab);
		setHardness(-1.0f);
		setResistance(999999999f);
		setSoundType(CavernShrine.rck);
	}

	public boolean onBlockActivated(final World w, final int x, final int y, final int z, final EntityPlayer p, final int var6, final float var7, final float var8, final float var9) {
		if (!w.isRemote && !p.isSneaking()) {
			PlayerContainer cont = PlayerContainer.getProperties(p);

			if (w.getBlock(x, y, z) == Blockizer.SelyanShrine) {
				if (cont.getLevel(Augury) > 70 && cont.getLevel(Infusion) > 70) {
					if (cont.getTribute(Selyan) == 200) {
						if (x < -20 && z < -20) {
							p.setPositionAndUpdate(0.0, 17.0, 0.0);
						}
						else {
							p.setPositionAndUpdate(-60.0, 18.0, -65.0);

							AncientBossesContainer.trySpawnConiferon(w);
						}
					}
					else {
						p.addChatMessage(StringUtil.getLocale("message.feedback.ancientCavernRequirement.selyan.tribute"));
					}
				}
				else {
					p.addChatMessage(StringUtil.getLocale("message.feedback.ancientCavernRequirement.selyan.levels"));
				}
			}
			else if (w.getBlock(x, y, z) == Blockizer.LuxonShrine) {
				if (cont.getLevel(Augury) > 70 && cont.getLevel(Runation) > 70) {
					if (cont.getTribute(Luxon) == 200) {
						if (x > 60 && z < -20) {
							p.setPositionAndUpdate(0.0, 17.0, 0.0);
						}
						else {
							p.setPositionAndUpdate(80.0, 18.0, -70.0);

							AncientBossesContainer.trySpawnHoron(w);
						}
					}
					else {
						p.addChatMessage(StringUtil.getLocale("message.feedback.ancientCavernRequirement.luxon.tribute"));
					}
				}
				else {
					p.addChatMessage(StringUtil.getLocale("message.feedback.ancientCavernRequirement.luxon.levels"));
				}
			}
			else if (w.getBlock(x, y, z) == Blockizer.ErebonShrine) {
				if (cont.getLevel(Augury) > 70 && cont.getLevel(Hunter) > 70) {
					if (cont.getTribute(Erebon) == 200) {
						if (x < -20 && z > 60) {
							p.setPositionAndUpdate(0.0, 17.0, 0.0);
						}
						else {
							p.setPositionAndUpdate(-70.0, 18.0, 80.0);

							AncientBossesContainer.trySpawnPenumbra(w);
						}
					}
					else {
						p.addChatMessage(StringUtil.getLocale("message.feedback.ancientCavernRequirement.erebon.tribute"));
					}
				}
				else {
					p.addChatMessage(StringUtil.getLocale("message.feedback.ancientCavernRequirement.erebon.levels"));
				}
			}
			else if (w.getBlock(x, y, z) == Blockizer.PlutonShrine) {
				if (cont.getLevel(Augury) > 70 && cont.getLevel(Foraging) > 70) {
					if (cont.getTribute(Pluton) == 200) {
						if (x > 60 && z > 60) {
							p.setPositionAndUpdate(0.0, 17.0, 0.0);
						}
						else {
							p.setPositionAndUpdate(80.0, 18.0, 80.0);

							AncientBossesContainer.trySpawnGoldorth(w);
						}
					}
					else {
						p.addChatMessage(StringUtil.getLocale("message.feedback.ancientCavernRequirement.pluton.tribute"));
					}
				}
				else {
					p.addChatMessage(StringUtil.getLocale("message.feedback.ancientCavernRequirement.pluton.levels"));
				}
			}
		}
		return true;
	}

	public Block setSoundType(final Block.SoundType name) {
		return setStepSound(name);
	}
}
