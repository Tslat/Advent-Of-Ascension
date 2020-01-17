package net.tslat.aoa3.item.tool.misc;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumAction;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.capabilities.interfaces.CapabilityBaseMiscStackSerializable;
import net.tslat.aoa3.capabilities.providers.AdventMiscStackSerializeableProvider;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.utils.StringUtil;
import net.tslat.aoa3.utils.player.PlayerUtil;

import javax.annotation.Nullable;
import java.util.List;

public class ExpFlask extends Item {
	public ExpFlask() {
		setTranslationKey("ExpFlask");
		setRegistryName("aoa3:exp_flask");
		setCreativeTab(CreativeTabsRegister.toolsTab);
		setMaxStackSize(1);
		addPropertyOverride(new ResourceLocation("filled"), new IItemPropertyGetter() {
			@Override
			@SideOnly(Side.CLIENT)
			public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
				CapabilityBaseMiscStackSerializable cap = getCapability(stack);

				return cap == null || cap.getValue() <= 0 ? 0 : 1;
			}
		});
	}

	@Override
	public boolean isEnchantable(ItemStack stack) {
		return false;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.DRINK;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 100;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);

		if (!world.isRemote) {
			CapabilityBaseMiscStackSerializable cap = getCapability(stack);

			if (cap == null || cap.getValue() <= 0)
				return ActionResult.newResult(EnumActionResult.FAIL, stack);

			player.setActiveHand(hand);
		}

		return ActionResult.newResult(EnumActionResult.PASS, stack);
	}

	@Override
	public void onUsingTick(ItemStack stack, EntityLivingBase entity, int count) {
		if (!entity.world.isRemote && entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)entity;
			CapabilityBaseMiscStackSerializable cap = getCapability(stack);

			if (cap.getValue() > 0) {
				int xpChange = (int)Math.min(1 + ((int)(player.experienceLevel / 15f)), cap.getValue());

				player.addExperience(xpChange);
				cap.setValue(cap.getValue() - xpChange);

				if (cap.getValue() == 0)
					player.resetActiveHand();
			}
		}
	}

	@Nullable
	@Override
	public NBTTagCompound getNBTShareTag(ItemStack stack) {
		NBTTagCompound tag = super.getNBTShareTag(stack);

		if (tag == null)
			tag = new NBTTagCompound();

		tag.setFloat("AdventMiscStackCapability", getCapability(stack).getValue());

		return tag;
	}

	@Override
	public void readNBTShareTag(ItemStack stack, @Nullable NBTTagCompound nbt) {
		if (nbt != null && nbt.hasKey("AdventMiscStackCapability"))
			getCapability(stack).setValue(nbt.getFloat("AdventMiscStackCapability"));

		super.readNBTShareTag(stack, nbt);
	}

	@Nullable
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt) {
		return new AdventMiscStackSerializeableProvider();
	}

	public static CapabilityBaseMiscStackSerializable getCapability(ItemStack stack) {
		return stack.getCapability(AdventMiscStackSerializeableProvider.MISC_STACK, null);
	}

	public static void addExp(EntityPlayerMP player, int slot, ItemStack stack, int xp) {
		CapabilityBaseMiscStackSerializable cap = getCapability(stack);

		if (cap != null) {
			cap.setValue(cap.getValue() + xp);
		}
	}

	public static void setExp(ItemStack stack, int xp) {
		CapabilityBaseMiscStackSerializable cap = getCapability(stack);

		if (cap != null)
			cap.setValue(xp);
	}

	public static boolean consumeExp(ItemStack stack, int xp) {
		CapabilityBaseMiscStackSerializable cap = getCapability(stack);

		if (cap != null && cap.getValue() >= xp) {
			cap.setValue(cap.getValue() - xp);

			return true;
		}

		return false;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag) {
		int storedValue = (int)getCapability(stack).getValue();

		if (storedValue > 0)
			tooltip.add(StringUtil.getColourLocaleStringWithArguments("item.ExpFlask.desc.1", TextFormatting.GOLD, StringUtil.floorAndAppendSuffix(storedValue, true) + (storedValue >= 7 ? " (" + PlayerUtil.getPlayerLevelFromExp(storedValue) + ")" : "")));

		tooltip.add(StringUtil.getLocaleString("item.ExpFlask.desc.2"));
		tooltip.add(StringUtil.getLocaleString("item.ExpFlask.desc.3"));
	}
}
