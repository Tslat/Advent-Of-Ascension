package net.tslat.aoa3.item.weapon.staff;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.entity.projectiles.staff.BaseEnergyShot;
import net.tslat.aoa3.entity.projectiles.staff.EntityMoonlightFall;
import net.tslat.aoa3.item.misc.RuneItem;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.StringUtil;

import java.util.HashMap;
import java.util.List;

public class MoonlightStaff extends BaseStaff {
	private static HashMap<RuneItem, Integer> runes = new HashMap<RuneItem, Integer>();

	static {
		runes.put(ItemRegister.runeCompass, 1);
		runes.put(ItemRegister.runeLunar, 2);
		runes.put(ItemRegister.runeKinetic, 2);
	}

	public MoonlightStaff(SoundEvent sound, int durability) {
		super(sound, durability);
		setUnlocalizedName("MoonlightStaff");
		setRegistryName("aoa3:moonlight_staff");
	}

	@Override
	public HashMap<RuneItem, Integer> getRunes() {
		return runes;
	}

	@Override
	public Object checkPreconditions(EntityLivingBase caster, ItemStack staff) {
		BlockPos trace = null;

		if (caster instanceof EntityPlayer)
			trace = EntityUtil.getBlockAimingAt((EntityPlayer)caster, 70);

		return trace;
	}

	@Override
	public void cast(World world, ItemStack staff, EntityLivingBase caster, Object args) {
		BlockPos pos = (BlockPos)args;

		world.spawnEntity(new EntityMoonlightFall(caster, this, pos.getX(), pos.getY() + 30, pos.getZ(), 3f));
	}

	@Override
	public void doBlockImpact(BaseEnergyShot shot, BlockPos block, EntityLivingBase caster) {
		caster.world.createExplosion(caster, shot.posX, shot.posY, shot.posZ, 1.5f, false);

		for (EntityLivingBase e : caster.world.getEntitiesWithinAABB(EntityLivingBase.class, caster.getEntityBoundingBox().grow(10), EntityUtil::isHostileMob)) {
			e.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 100, 2, true, true));
		}
	}

	@Override
	public void doEntityImpact(BaseEnergyShot shot, Entity target, EntityLivingBase caster) {
		caster.world.createExplosion(caster, shot.posX, shot.posY, shot.posZ, 1.5f, false);

		for (EntityLivingBase e : caster.world.getEntitiesWithinAABB(EntityLivingBase.class, caster.getEntityBoundingBox().grow(10), EntityUtil::isHostileMob)) {
			e.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 100, 2, true, true));
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("item.MoonlightStaff.desc.1", TextFormatting.DARK_GREEN));
		super.addInformation(stack, world, tooltip, flag);
	}
}
