package net.tslat.aoa3.item.misc;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class SimpleItem extends Item {
	private List<String> tooltipInfo;

	public SimpleItem(String name, String registryName) {
		setTranslationKey(name);
		setRegistryName("aoa3:" + registryName);
		setCreativeTab(CreativeTabsRegister.miscTab);
	}

	public SimpleItem unstackable() {
		setMaxStackSize(1);

		return this;
	}

	public SimpleItem isInContainer(Item item) {
		setContainerItem(item);

		return this;
	}

	public SimpleItem withTooltip(String tooltipLangKey) {
		if (tooltipInfo == null)
			tooltipInfo = new ArrayList<String>(1);

		tooltipInfo.add(tooltipLangKey);

		return this;
	}

	public SimpleItem hidden() {
		setCreativeTab(null);

		return this;
	}

	public SimpleItem damageable(int maxDamage) {
		setMaxDamage(maxDamage);

		return this;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (tooltipInfo != null) {
			for (String s : tooltipInfo) {
				tooltip.add(StringUtil.getLocaleString(s));
			}
		}
	}
}
