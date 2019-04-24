package net.tslat.aoa3.item.weapon.staff;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.item.misc.RuneItem;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.StringUtil;

import java.util.HashMap;
import java.util.List;

public class Lightshine extends BaseStaff {
	private static HashMap<RuneItem, Integer> runes = new HashMap<RuneItem, Integer>();

	static {
		runes.put(ItemRegister.runePower, 3);
		runes.put(ItemRegister.runeLife, 3);
		runes.put(ItemRegister.runeDistortion, 2);
	}

	public Lightshine(SoundEvent sound, int durability) {
		super(sound, durability);
		setUnlocalizedName("Lightshine");
		setRegistryName("aoa3:lightshine");
	}

	@Override
	public Object checkPreconditions(EntityLivingBase caster, ItemStack staff) {
		List<EntityLivingBase> list = caster.world.getEntitiesWithinAABB(EntityLivingBase.class, caster.getEntityBoundingBox().grow(10), EntityUtil::isHostileMob);

		if (!list.isEmpty())
			return list;

		return null;
	}

	@Override
	public HashMap<RuneItem, Integer> getRunes() {
		return runes;
	}

	@Override
	public void cast(World world, ItemStack staff, EntityLivingBase caster, Object args) {
		for (EntityLivingBase e : (List<EntityLivingBase>)args) {
			EntityUtil.dealMagicDamage(null, caster, e, 1.0f, true);
		}

		EntityUtil.healEntity(caster, Math.min(15, ((List<EntityLivingBase>)args).size()));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("item.Lightshine.desc.1", TextFormatting.DARK_GREEN));
		tooltip.add(StringUtil.getColourLocaleString("item.Lightshine.desc.2", TextFormatting.DARK_GREEN));
		super.addInformation(stack, world, tooltip, flag);
	}
}
