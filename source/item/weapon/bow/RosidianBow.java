package net.tslat.aoa3.item.weapon.bow;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.entity.minions.EntityRosid;
import net.tslat.aoa3.entity.projectiles.arrow.EntityHollyArrow;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

public class RosidianBow extends BaseBow {
	public RosidianBow(double damage, float drawSpeedMultiplier, int durability) {
		super(damage, drawSpeedMultiplier, durability);
		setUnlocalizedName("RosidianBow");
		setRegistryName("aoa3:rosidian_bow");
	}

	@Override
	public void doImpactEffect(EntityHollyArrow arrow, Entity target, Entity shooter) {
		if (target instanceof EntityLivingBase && itemRand.nextInt(4) == 0) {
			EntityRosid rosid = new EntityRosid(shooter.world);

			if (shooter instanceof EntityPlayer)
				rosid.setTamedBy((EntityPlayer)shooter);

			rosid.setPosition(target.posX, target.posY, target.posZ);
			shooter.world.spawnEntity(rosid);
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("item.RosidianBow.desc.1", TextFormatting.DARK_GREEN));
		super.addInformation(stack, world, tooltip, flag);
	}
}
