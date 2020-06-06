package net.tslat.aoa3.hooks.thaumcraft;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.utils.ConfigurationUtil;
import org.apache.logging.log4j.Level;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.AspectRegistryEvent;

public class ThaumcraftCompat {
	public static void preInit() {
		MinecraftForge.EVENT_BUS.register(new ThaumcraftCompat());
	}

	@SubscribeEvent
	public void registerAspects(AspectRegistryEvent ev) {
		AdventOfAscension.logOptionalMessage("Registering Thaumcraft Aspects..");

		if (ConfigurationUtil.IntegrationsConfig.thaumcraft.defaultAspects.length <= 0)
			return;

		for (String entry : ConfigurationUtil.IntegrationsConfig.thaumcraft.defaultAspects) {
			String[] entrySplit = entry.split("; ");
			String itemId = entrySplit[0];

			if (!itemId.contains(":"))
				itemId = "aoa3:" + itemId;

			String itemName = itemId.split(":")[1];
			Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation("aoa3", itemName));

			if (item == null) {
				AdventOfAscension.logMessage(Level.WARN, "Unable to find item for Thaumcraft Aspects: " + itemId + ", skipping.");

				continue;
			}
			else if (entrySplit[1].length() <= 1) {
				AdventOfAscension.logMessage(Level.WARN, "No aspects entered for Thaumcraft integration for item: " + itemId + ", skipping.");

				continue;
			}

			AspectList aspects = new AspectList();

			for (String aspectEntry : entrySplit[1].split(" ")) {
				if (!aspectEntry.contains(":")) {
					AdventOfAscension.logMessage(Level.WARN, "Invalid formatting for aspect list for Thaumcraft integration: " + aspectEntry);

					continue;
				}

				String[] aspectSplit = aspectEntry.split(":");
				Aspect aspect = Aspect.getAspect(aspectSplit[0]);

				if (aspectSplit.length < 2) {
					AdventOfAscension.logMessage(Level.WARN, "No amount value entered for aspect for Thaumcraft integration: " + aspectEntry);

					continue;
				}

				int amount = 0;

				try {
					amount = Integer.parseInt(aspectSplit[1]);

					if (amount <= 0)
						continue;
				}
				catch (NumberFormatException ex) {
					AdventOfAscension.logMessage(Level.WARN, "Value is not valid integer value for aspect: " + aspectEntry);

					continue;
				}

				if (aspect == null) {
					AdventOfAscension.logMessage(Level.WARN, "Invalid Aspect name for Thaumcraft Integration: " + aspectSplit[0]);

					continue;
				}

				aspects.add(aspect, amount);
			}

			if (aspects.size() <= 0) {
				AdventOfAscension.logMessage(Level.WARN, "No valid aspects entered for Thaumcraft integration for item: " + itemId + ", skipping.");

				continue;
			}

			if (ConfigurationUtil.MainConfig.doVerboseDebugging) {
				StringBuilder builder = new StringBuilder("Registered \"");

				builder.append(new ItemStack(item).getDisplayName());
				builder.append("\", Aspects: ");

				for (Aspect aspect : aspects.getAspects()) {
					builder.append(aspect.getName());
					builder.append(":");
					builder.append(aspects.getAmount(aspect));
					builder.append(" ");
				}

				AdventOfAscension.logOptionalMessage(builder.toString());
			}

			ev.register.registerObjectTag(new ItemStack(item), aspects);
		}
	}
}
