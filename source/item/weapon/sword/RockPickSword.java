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

public class RockPickSword extends BaseSword implements AdventWeapon {
	final ToolMaterial material;

	public RockPickSword(final ToolMaterial material, Float dmg, Double speed) {
		super(material, dmg, speed);
		this.material = material;
		setUnlocalizedName("RockPickSword");
		setRegistryName("aoa3:rock_pick_sword");
		setHarvestLevel("pickaxe", 3);
	}

	@Override
	public float getDestroySpeed(ItemStack stack, IBlockState state) {
		Material material = state.getMaterial();
		return material != Material.IRON && material != Material.ANVIL && material != Material.ROCK ? super.getDestroySpeed(stack, state) : 4.0f;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(StringUtil.getColourLocaleString("item.RockPickSword.desc.1", TextFormatting.DARK_GREEN));
	}
}
