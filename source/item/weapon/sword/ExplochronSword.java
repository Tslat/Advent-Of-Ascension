package net.tslat.aoa3.item.weapon.sword;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

public class ExplochronSword extends BaseSword implements AdventWeapon {
	public ExplochronSword(final ToolMaterial material, Float dmg, Double speed) {
		super(material, dmg, speed);
		setUnlocalizedName("ExplochronSword");
		setRegistryName("aoa3:explochron_sword");
	}

	@Override
	public boolean onLeftClickEntity(final ItemStack stack, final EntityPlayer player, final Entity target) {
		if (player.world.isRemote || !(target instanceof EntityLivingBase))
			return false;

		if (player.getCooledAttackStrength(0.0f) > 0.75f) {
			player.world.createExplosion(player, target.posX, target.posY, target.posZ, 2.0f, false);
			stack.damageItem(1, player);
		}

		return false;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(StringUtil.getColourLocaleString("item.ExplochronSword.desc.1", TextFormatting.DARK_GREEN));
	}
}
