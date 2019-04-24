package net.tslat.aoa3.item.food;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nullable;
import java.util.List;

public class LunaGlobe extends BasicFood {
	public LunaGlobe() {
		super("LunaGlobe", "luna_globe", 0, 0);
		setAlwaysEdible();
		BlockRegister.cropLunaGlobes.setCrop(this);
	}

	@Override
	protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
		super.onFoodEaten(stack, world, player);

		if (!world.isRemote) {
			player.addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, 120, 0, true, false));
		}
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(StringUtil.getLocaleString("item.LunaGlobe.desc.1"));
	}
}
