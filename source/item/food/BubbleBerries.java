package net.tslat.aoa3.item.food;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nullable;
import java.util.List;

public class BubbleBerries extends BasicFood {
	public BubbleBerries() {
		super("BubbleBerries", "bubble_berries", 0, 0);
		setAlwaysEdible();
		BlockRegister.BUBBLE_BERRY_CROP.setCrop(this);
	}

	@Override
	protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
		super.onFoodEaten(stack, world, player);

		if (!world.isRemote)
			player.setAir(Math.min(300, player.getAir() + 50));
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 18;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(StringUtil.getLocaleString("item.BubbleBerries.desc.1"));
	}
}
