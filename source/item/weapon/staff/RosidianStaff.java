package net.tslat.aoa3.item.weapon.staff;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.entity.minions.EntityRosid;
import net.tslat.aoa3.entity.projectiles.staff.BaseEnergyShot;
import net.tslat.aoa3.entity.projectiles.staff.EntityRosidianShot;
import net.tslat.aoa3.item.misc.RuneItem;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.StringUtil;

import java.util.HashMap;
import java.util.List;

public class RosidianStaff extends BaseStaff {
	private static HashMap<RuneItem, Integer> runes = new HashMap<RuneItem, Integer>();

	static {
		runes.put(ItemRegister.runeWind, 3);
		runes.put(ItemRegister.runeLife, 2);
	}

	public RosidianStaff(SoundEvent sound, int durability) {
		super(sound, durability);
		setUnlocalizedName("RosidianStaff");
		setRegistryName("aoa3:rosidian_staff");
	}

	@Override
	public HashMap<RuneItem, Integer> getRunes() {
		return runes;
	}

	@Override
	public void cast(World world, ItemStack staff, EntityLivingBase caster, Object args) {
		world.spawnEntity(new EntityRosidianShot(caster, this, 60));
	}

	@Override
	public void doEntityImpact(BaseEnergyShot shot, Entity target, EntityLivingBase shooter) {
		if (EntityUtil.dealMagicDamage(shot, shooter, target, getDmg(), false)) {
			if (itemRand.nextInt(5) == 0) {
				EntityRosid rosid = new EntityRosid(shooter.world);

				if (shooter instanceof EntityPlayer)
					rosid.setTamedBy((EntityPlayer)shooter);

				rosid.setPosition(target.posX, target.posY, target.posZ);
				shooter.world.spawnEntity(rosid);
			}
		}
	}

	@Override
	public float getDmg() {
		return 8;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("item.RosidianStaff.desc.1", TextFormatting.DARK_GREEN));
		super.addInformation(stack, world, tooltip, flag);
	}
}
