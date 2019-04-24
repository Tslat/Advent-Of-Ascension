package net.nevermine.block.generation.structure;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.container.PlayerContainer;
import net.nevermine.izer.Itemizer;

import static net.nevermine.container.PlayerContainer.Deities.Pluton;

public class PureGoldAccumulator extends Block {
	@SideOnly(Side.CLIENT)
	private IIcon top;
	@SideOnly(Side.CLIENT)
	private IIcon side;
	@SideOnly(Side.CLIENT)
	private IIcon bottom;

	public PureGoldAccumulator(final Material p_i45394_1_) {
		super(p_i45394_1_);
		setCreativeTab(Itemizer.GenerationTab);
		setHardness(-1.0f);
		setResistance(999999999f);
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(final int par1, final int par2) {
		return (par1 == 1) ? top : ((par1 == 0) ? top : ((par2 == 2 && par1 == 2) ? side : ((par2 == 3 && par1 == 5) ? side : ((par2 == 0 && par1 == 3) ? side : ((par2 == 1 && par1 == 4) ? side : blockIcon)))));
	}

	public boolean onBlockActivated(final World w, final int x, final int y, final int z, final EntityPlayer p, final int var6, final float var7, final float var8, final float var9) {
		if (!w.isRemote && !p.isSneaking() && p.inventory.consumeInventoryItem(Itemizer.PureGold)) {
			PlayerContainer cont = PlayerContainer.getProperties(p);

			cont.adjustTribute(Pluton, 20);

			if (cont.getTribute(Pluton) == 200)
				p.addChatMessage(StringUtil.getLocaleWithArguments("message.feedback.immortallisProgression.pureGoldEnd", p.getDisplayName()));
		}
		return true;
	}

	public Block setName(final String name) {
		setBlockName(name);
		GameRegistry.registerBlock(this, name);
		return this;
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(final IIconRegister icon) {
		top = icon.registerIcon("nevermine:goldAccumulator_top");
		side = icon.registerIcon("nevermine:goldAccumulator_side");
		bottom = icon.registerIcon("nevermine:goldAccumulator_top");
		blockIcon = icon.registerIcon("nevermine:goldAccumulator_side");
	}
}
