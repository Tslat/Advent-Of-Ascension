package net.tslat.aoa3.item.weapon.sword;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

public class CreepifiedSword extends BaseSword implements AdventWeapon {
	public CreepifiedSword(final ToolMaterial material, Float dmg, Double speed) {
		super(material, dmg, speed);
		setUnlocalizedName("CreepifiedSword");
		setRegistryName("aoa3:creepified_sword");
	}

	@Override
	public boolean onLeftClickEntity(final ItemStack stack, final EntityPlayer player, final Entity target) {
		if (player.world.isRemote || !(target instanceof EntityLivingBase))
			return false;

		if (itemRand.nextInt(10) == 0 && player.getCooledAttackStrength(0.0f) > 0.75f) {
			final EntityCreeper creeper = new EntityCreeper(target.world);

			creeper.setLocationAndAngles(target.posX, target.posY, target.posZ, itemRand.nextFloat() * 360.0f, 0.0f);
			target.world.spawnEntity(creeper);
		}

		return false;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(StringUtil.getColourLocaleString("item.CreepifiedSword.desc.1", TextFormatting.DARK_GREEN));
	}
}
