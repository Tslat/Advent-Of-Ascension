package net.nevermine.block.generation.structure;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.boss.primordialfive.EntityKajaros;
import net.nevermine.izer.Blockizer;
import net.nevermine.izer.Itemizer;

import java.util.List;

public class PrimordialShrine extends Block {
	@SideOnly(Side.CLIENT)
	private IIcon top;
	@SideOnly(Side.CLIENT)
	private IIcon side;
	@SideOnly(Side.CLIENT)
	private IIcon bottom;

	public PrimordialShrine(final Material p_i45394_1_) {
		super(p_i45394_1_);
		setCreativeTab(Itemizer.GenerationTab);
		setHardness(-1.0f);
		setResistance(999999999f);
		setLightLevel(0.0f);
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(final int par1, final int par2) {
		return (par1 == 1) ? top : ((par1 == 0) ? top : ((par2 == 2 && par1 == 2) ? side : ((par2 == 3 && par1 == 5) ? side : ((par2 == 0 && par1 == 3) ? side : ((par2 == 1 && par1 == 4) ? side : blockIcon)))));
	}

	public boolean onBlockActivated(final World w, final int x, final int y, final int z, final EntityPlayer p, final int var6, final float var7, final float var8, final float var9) {
		if (!w.isRemote && !p.isSneaking()
				&& w.getBlock(x - 1, y + 1, z + 4) == Blockizer.LampDustopian
				&& w.getBlock(x - 1, y + 1, z - 4) == Blockizer.LampDustopian
				&& w.getBlock(x - 3, y + 1, z + 5) == Blockizer.LampDustopian
				&& w.getBlock(x - 3, y + 1, z - 5) == Blockizer.LampDustopian
				&& w.getBlock(x + 1, y + 3, z - 3) == Blockizer.LampDustopian
				&& w.getBlock(x + 1, y + 3, z + 3) == Blockizer.LampDustopian
				&& w.getBlock(x + 1, y + 5, z - 1) == Blockizer.LampDustopian
				&& w.getBlock(x + 1, y + 5, z + 1) == Blockizer.LampDustopian) {
			if (w.difficultySetting == EnumDifficulty.PEACEFUL) {
				p.addChatMessage(StringUtil.getLocale("message.feedback.spawnBoss.fail"));
				return false;
			}

			final EntityKajaros var10 = new EntityKajaros(w);
			var10.setLocationAndAngles((double)x, (double)(y + 3), (double)z, 0.0f, 0.0f);
			w.spawnEntityInWorld(var10);

			w.setBlock(x - 1, y + 1, z - 4, Blockizer.DustopianLampOff);
			w.setBlock(x - 3, y + 1, z + 5, Blockizer.DustopianLampOff);
			w.setBlock(x - 3, y + 1, z - 5, Blockizer.DustopianLampOff);
			w.setBlock(x + 1, y + 3, z - 3, Blockizer.DustopianLampOff);
			w.setBlock(x + 1, y + 3, z + 3, Blockizer.DustopianLampOff);
			w.setBlock(x + 1, y + 5, z - 1, Blockizer.DustopianLampOff);
			w.setBlock(x + 1, y + 5, z + 1, Blockizer.DustopianLampOff);

			IChatComponent msg = StringUtil.getLocaleWithArguments("message.mob.primordialFive.spawn", p.getDisplayName());

			for (final EntityPlayer e : (List<EntityPlayer>)p.worldObj.getEntitiesWithinAABB(EntityPlayer.class, p.boundingBox.expand(50.0, 50.0, 50.0))) {
				e.addChatMessage(msg);
			}
		}
		return true;
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(final IIconRegister icon) {
		top = icon.registerIcon("nevermine:ritualShrine_top");
		side = icon.registerIcon("nevermine:ritualShrine_side");
		bottom = icon.registerIcon("nevermine:ritualShrine_bottom");
		blockIcon = icon.registerIcon("nevermine:ritualShrine_side");
	}
}
