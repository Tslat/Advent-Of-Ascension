package net.tslat.aoa3.item.food;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nullable;
import java.util.List;

public class Lunarade extends BasicFood {
	public Lunarade() {
		super("Lunarade", "lunarade", 0, 0.2f);
		setAlwaysEdible();
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.DRINK;
	}

	@Override
	protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
		super.onFoodEaten(stack, world, player);

		if (!world.isRemote) {
			player.removePotionEffect(MobEffects.BLINDNESS);
			player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 40, 1, true, false));
			player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 40, 0, true, false));
			ItemUtil.givePlayerItemOrDrop(player, new ItemStack(ItemRegister.lunaradeMug));
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(StringUtil.getLocaleString("item.Lunarade.desc.1"));
	}
}
