package net.tslat.aoa3.item.food;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nullable;
import java.util.List;

public class YetiFingernails extends BasicFood {
	public YetiFingernails() {
		super("YetiFingernails", "yeti_fingernails", 1, 0.1f);
		setAlwaysEdible();
		setCreativeTab(null);
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 100;
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World world, EntityLivingBase entity) {
		if (!world.isRemote && entity instanceof EntityPlayer)
			entity.sendMessage(StringUtil.getLocale("message.feedback.yetiFingernails.eat"));

		return super.onItemUseFinish(stack, world, entity);
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(StringUtil.getLocaleString("item.YetiFingernails.desc.1"));
	}
}
