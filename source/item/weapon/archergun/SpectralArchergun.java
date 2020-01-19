package net.tslat.aoa3.item.weapon.archergun;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.entity.projectiles.gun.BaseBullet;
import net.tslat.aoa3.entity.projectiles.gun.EntityHollyArrowShot;
import net.tslat.aoa3.item.weapon.gun.BaseGun;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

public class SpectralArchergun extends BaseArchergun {
	public SpectralArchergun(double dmg, int durability, int fireDelayTicks, float recoil) {
		super(dmg, durability, fireDelayTicks, recoil);
		setTranslationKey("SpectralArchergun");
		setRegistryName("aoa3:spectral_archergun");
	}

	public BaseBullet findAndConsumeAmmo(EntityPlayer player, BaseGun gun, EnumHand hand) {
		EntityHollyArrowShot arrow = new EntityHollyArrowShot(player, gun, hand,120, 0);

		arrow.motionY += 0.25f;

		return arrow;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.SpectralArchergun.desc.1", Enums.ItemDescriptionType.POSITIVE));
		tooltip.add(1, StringUtil.getColourLocaleStringWithArguments("items.description.damage.arrows", TextFormatting.DARK_RED, Double.toString(getDamage())));
		tooltip.add(StringUtil.getLocaleStringWithArguments("items.description.gun.speed", Double.toString((2000 / firingDelay) / (double)100)));
	}
}
