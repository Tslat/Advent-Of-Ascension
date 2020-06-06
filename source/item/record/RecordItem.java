package net.tslat.aoa3.item.record;

import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;

import java.util.Map;
import java.util.function.Supplier;

public class RecordItem extends ItemRecord {
	private final Supplier<SoundEvent> music;

	public RecordItem(String name, String registryName, Supplier<SoundEvent> music) {
		super(name, null);

		setTranslationKey(name);
		setRegistryName("aoa3:" + registryName);
		setCreativeTab(CreativeTabsRegister.MISC);
		setTranslationKey("record");

		this.music = music;
	}

	public void applySound() {
		Map<SoundEvent, ItemRecord> recordsMap = ObfuscationReflectionHelper.getPrivateValue(ItemRecord.class, null, "field_150928_b");

		recordsMap.remove(null);
		recordsMap.put(music.get(), this);
	}

	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		return super.getItemStackDisplayName(stack);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public SoundEvent getSound() {
		return music.get();
	}
}
