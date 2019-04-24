package net.tslat.aoa3.item.weapon.archergun;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.entity.projectiles.gun.BaseBullet;
import net.tslat.aoa3.item.weapon.gun.BaseGun;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

public class SpectralArchergun extends BaseArchergun {
	public SpectralArchergun(double dmg, SoundEvent sound, int durability, int fireDelayTicks, float recoil) {
		super(dmg, sound, durability, fireDelayTicks, recoil);
		setUnlocalizedName("SpectralArchergun");
		setRegistryName("aoa3:spectral_archergun");
	}

	public BaseBullet findAndConsumeAmmo(EntityPlayer player, BaseGun gun, EnumHand hand) {
		return super.findAndConsumeAmmo(player, gun, hand,itemRand.nextInt(5) == 0);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("items.description.ammo.noConsume.80", TextFormatting.DARK_GREEN));
		super.addInformation(stack, world, tooltip, flag);
	}
}
