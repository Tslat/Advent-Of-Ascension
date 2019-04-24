package net.tslat.aoa3.item.weapon.sword;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.entity.minions.EntityRosid;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

public class RosidianSword extends BaseSword implements AdventWeapon {
	public RosidianSword(final ToolMaterial material, Float dmg, Double speed) {
		super(material, dmg, speed);
		setUnlocalizedName("RosidianSword");
		setRegistryName("aoa3:rosidian_sword");
	}

	@Override
	public boolean onLeftClickEntity(final ItemStack stack, final EntityPlayer player, final Entity target) {
		if (!player.world.isRemote && ItemUtil.checkCooledItemProc(player, 0.1f)) {
			EntityRosid rosid = new EntityRosid(player.world);

			rosid.setTamedBy(player);
			rosid.setPosition(target.posX, target.posY, target.posZ);
			player.world.spawnEntity(rosid);
		}

		return false;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(StringUtil.getColourLocaleString("item.RosidianSword.desc.1", TextFormatting.DARK_GREEN));
	}
}
