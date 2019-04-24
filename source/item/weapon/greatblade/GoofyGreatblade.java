package net.tslat.aoa3.item.weapon.greatblade;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.item.weapon.LongReachWeapon;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

public class GoofyGreatblade extends BaseGreatblade implements AdventWeapon, LongReachWeapon {
	public GoofyGreatblade(double dmg, double speed, int durability) {
		super(dmg, speed, durability);
		setUnlocalizedName("GoofyGreatblade");
		setRegistryName("aoa3:goofy_greatblade");
	}

	@Override
	public void attackEntity(ItemStack stack, Entity target, EntityLivingBase attacker, float dmg) {
		if (itemRand.nextInt(4) == 0) {
			if (attacker instanceof  EntityPlayer)
				attacker.world.playSound(null, attacker.posX, attacker.posY, attacker.posZ, SoundsRegister.greatbladeGoofy, SoundCategory.PLAYERS, 1.0f, 1.0f);

			stack.damageItem(1, attacker);
		}
		else {
			super.attackEntity(stack, target, attacker, dmg);
		}
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(StringUtil.getColourLocaleString("item.GoofyGreatblade.desc.1", TextFormatting.DARK_GREEN));
	}
}
