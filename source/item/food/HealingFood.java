package net.tslat.aoa3.item.food;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nullable;
import java.util.List;

public class HealingFood extends ItemFood {
	public final float healAmount;

	public HealingFood(String name, String registryName, int hunger, float saturation, float healing) {
		super(hunger, saturation, false);
		setUnlocalizedName(name);
		setRegistryName("aoa3:" + registryName);
		this.healAmount = healing;
		setCreativeTab(CreativeTabsRegister.foodTab);
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World world, EntityLivingBase entity) {
		float percentHunger = 100;

		if (entity instanceof EntityPlayer)
			percentHunger = Math.min((20 - ((EntityPlayer)entity).getFoodStats().getFoodLevel()) / (float)this.getHealAmount(stack), 1);

		ItemStack returnStack = super.onItemUseFinish(stack, world, entity);

		if (entity instanceof EntityPlayer)
			EntityUtil.healEntity(entity, healAmount * percentHunger);

		return returnStack;
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(StringUtil.getLocaleString("items.description.healingFood.desc.1"));
		tooltip.add(StringUtil.getLocaleStringWithArguments("items.description.healingFood.desc.2", Float.toString(healAmount)));
	}
}
