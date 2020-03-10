package net.tslat.aoa3.item.tool.axe;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.capabilities.handlers.AdventMiscStackSerializeableCapability;
import net.tslat.aoa3.capabilities.interfaces.CapabilityBaseMiscStackSerializable;
import net.tslat.aoa3.capabilities.providers.AdventMiscStackSerializeableProvider;
import net.tslat.aoa3.common.registration.MaterialsRegister;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.player.PlayerDataManager;
import net.tslat.aoa3.utils.player.PlayerUtil;

import javax.annotation.Nullable;
import java.util.List;

public class EnergisticAxe extends BaseAxe {
	public EnergisticAxe() {
		super("EnergisticAxe", "energistic_axe", MaterialsRegister.TOOL_ENERGISTIC);
	}

	@Override
	public float getDestroySpeed(ItemStack stack, IBlockState state) {
		float speed = super.getDestroySpeed(stack, state);

		if (speed != 1.0f && hasEnergy(stack))
			return speed * 3f;

		return speed;
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, IBlockState state, BlockPos pos, EntityLivingBase entity) {
		CapabilityBaseMiscStackSerializable cap = getCapability(stack);

		if (cap != null && super.getDestroySpeed(stack, state) != 1.0f)
			cap.setValue(Math.max(0, cap.getValue() - 5));

		return super.onBlockDestroyed(stack, world, state, pos, entity);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		if (!world.isRemote) {
			CapabilityBaseMiscStackSerializable cap = getCapability(player.getHeldItem(hand));

			if (cap != null) {
				PlayerDataManager plData = PlayerUtil.getAdventPlayer(player);
				float storeAmount = MathHelper.clamp(2000f - cap.getValue(), 0, Math.min(20, plData.stats().getResourceValue(Enums.Resources.ENERGY)));

				cap.setValue(cap.getValue() + storeAmount);
				plData.stats().consumeResource(Enums.Resources.ENERGY, storeAmount, true);

				return ActionResult.newResult(EnumActionResult.SUCCESS, player.getHeldItem(hand));
			}
		}

		return super.onItemRightClick(world, player, hand);
	}

	private boolean hasEnergy(ItemStack stack) {
		CapabilityBaseMiscStackSerializable cap = getCapability(stack);

		if (cap != null)
			return cap.getValue() >= 5;

		return false;
	}

	@Nullable
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt) {
		return new AdventMiscStackSerializeableProvider();
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
			getCapability(stack).setValue(Math.min(2000, nbt.getFloat("AdventMiscStackCapability")));

		super.readNBTShareTag(stack, nbt);
	}

	public static CapabilityBaseMiscStackSerializable getCapability(ItemStack stack) {
		return stack.getCapability(AdventMiscStackSerializeableProvider.MISC_STACK, null);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.EnergisticAxe.desc.1", Enums.ItemDescriptionType.POSITIVE));
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.EnergisticAxe.desc.2", Enums.ItemDescriptionType.POSITIVE));

		AdventMiscStackSerializeableCapability cap = (AdventMiscStackSerializeableCapability)stack.getCapability(AdventMiscStackSerializeableProvider.MISC_STACK, null);

		if (cap != null)
			tooltip.add(ItemUtil.getFormattedDescriptionText("items.description.energisticTool", Enums.ItemDescriptionType.NEUTRAL, Integer.toString((int)cap.getValue())));
	}
}
