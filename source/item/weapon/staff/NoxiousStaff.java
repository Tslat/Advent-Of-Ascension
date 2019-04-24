package net.tslat.aoa3.item.weapon.staff;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.entity.projectiles.staff.BaseEnergyShot;
import net.tslat.aoa3.entity.projectiles.staff.EntityNoxiousShot;
import net.tslat.aoa3.item.misc.RuneItem;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.StringUtil;

import java.util.HashMap;
import java.util.List;

public class NoxiousStaff extends BaseStaff {
	private static HashMap<RuneItem, Integer> runes = new HashMap<RuneItem, Integer>();

	static {
		runes.put(ItemRegister.runeWind, 2);
		runes.put(ItemRegister.runePoison, 2);
		runes.put(ItemRegister.runeStorm, 2);
	}

	public NoxiousStaff(SoundEvent sound, int durability) {
		super(sound, durability);
		setUnlocalizedName("NoxiousStaff");
		setRegistryName("aoa3:noxious_staff");
	}

	@Override
	public HashMap<RuneItem, Integer> getRunes() {
		return runes;
	}

	@Override
	public void cast(World world, ItemStack staff, EntityLivingBase caster, Object args) {
		world.spawnEntity(new EntityNoxiousShot(caster, this, 60, 0, 0, 0));
		world.spawnEntity(new EntityNoxiousShot(caster, this, 60, 0.075f, 0.075f, 0));
		world.spawnEntity(new EntityNoxiousShot(caster, this, 60, -0.075f, 0, 0.075f));
		world.spawnEntity(new EntityNoxiousShot(caster, this, 60, 0, -0.075f, -0.075f));
		world.spawnEntity(new EntityNoxiousShot(caster, this, 60, -0.075f, 0.075f, -0.075f));
		world.spawnEntity(new EntityNoxiousShot(caster, this, 60, -0.075f, -0.075f, 0.075f));
	}

	@Override
	public void doEntityImpact(BaseEnergyShot shot, Entity target, EntityLivingBase shooter) {
		if (EntityUtil.dealMagicDamage(shot, shooter, target, getDmg(), false)) {
			if (target instanceof EntityLivingBase)
				((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.POISON, 100, 2, true, true));
		}
	}

	@Override
	public float getDmg() {
		return 3.5f;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("item.NoxiousStaff.desc.1", TextFormatting.DARK_GREEN));
		super.addInformation(stack, world, tooltip, flag);
	}
}
