package net.tslat.aoa3.item.weapon.staff;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.item.misc.RuneItem;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.PredicateUtil;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;

public class ShowStaff extends BaseStaff {
	public ShowStaff(int durability) {
		super(durability);
		setTranslationKey("ShowStaff");
		setRegistryName("aoa3:show_staff");
	}

	@Nullable
	@Override
	public SoundEvent getCastingSound() {
		return SoundsRegister.staffShow;
	}

	@Override
	protected void populateRunes(HashMap<RuneItem, Integer> runes) {
		runes.put(ItemRegister.runeCompass, 3);
		runes.put(ItemRegister.runePower, 3);
	}

	@Override
	public Object checkPreconditions(EntityLivingBase caster, ItemStack staff) {
		List<EntityLivingBase> list = caster.world.getEntitiesWithinAABB(EntityLivingBase.class, caster.getEntityBoundingBox().grow(30), PredicateUtil.IS_HOSTILE_MOB);

		if (!list.isEmpty())
			return list;

		return null;
	}

	@Override
	public void cast(World world, ItemStack staff, EntityLivingBase caster, Object args) {
		for (EntityLivingBase entity : (List<EntityLivingBase>)args) {
			entity.setFire(5);
			entity.addPotionEffect(new PotionEffect(MobEffects.GLOWING, 100, 0, true, true));
			world.spawnEntity(new EntityFireworkRocket(world, entity.posX, entity.getEntityBoundingBox().maxY, entity.posZ, makeFireworksStack()));
		}
	}

	private ItemStack makeFireworksStack() {
		ItemStack fireworks = new ItemStack(Items.FIREWORKS, 1);
		NBTTagCompound explosionTag = new NBTTagCompound();
		NBTTagCompound fireworksTag = new NBTTagCompound();
		NBTTagCompound finalTag = new NBTTagCompound();
		NBTTagCompound wrapperTag = new NBTTagCompound();
		NBTTagList finalTagList = new NBTTagList();

		explosionTag.setBoolean("Trail", true);
		explosionTag.setIntArray("Colors", new int[] {0});
		explosionTag.setByte("Type", (byte)4);
		fireworksTag.setTag("Explosion", explosionTag);
		finalTagList.appendTag(fireworksTag);
		finalTag.setTag("Explosions", finalTagList);
		finalTag.setByte("Flight", (byte)3);
		wrapperTag.setTag("Fireworks", finalTag);
		fireworks.setTagCompound(wrapperTag);

		return fireworks;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.ShowStaff.desc.1", Enums.ItemDescriptionType.POSITIVE));
		super.addInformation(stack, world, tooltip, flag);
	}
}
