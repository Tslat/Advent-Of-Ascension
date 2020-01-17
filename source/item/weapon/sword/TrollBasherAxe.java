package net.tslat.aoa3.item.weapon.sword;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

public class TrollBasherAxe extends BaseSword implements AdventWeapon {
	final ToolMaterial material;

	public TrollBasherAxe(final ToolMaterial material, final double speed) {
		super(material, speed);
		this.material = material;
		setTranslationKey("TrollBasherAxe");
		setRegistryName("aoa3:troll_basher_axe");
		setHarvestLevel("axe", 3);
	}

	@Override
	public float getDestroySpeed(ItemStack stack, IBlockState state) {
		Material material = state.getMaterial();

		return material != Material.WOOD && material != Material.PLANTS && material != Material.VINE ? super.getDestroySpeed(stack, state) : 4.0f;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(StringUtil.getColourLocaleString("item.TrollBasherAxe.desc.1", TextFormatting.DARK_GREEN));
	}
}
