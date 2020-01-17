package net.tslat.aoa3.client.model.entities.animations;

import com.google.gson.JsonParseException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.entity.Entity;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.utils.ConfigurationUtil;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.Level;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.*;
import java.util.Collections;
import java.util.Iterator;

@SideOnly(Side.CLIENT)
public final class AnimationManager {
	public static void registerAnimations() {
		if (!ConfigurationUtil.MainConfig.customEntityAnimationsEnabled)
			return;
		// TODO Fix when implementing
		AdventOfAscension.logMessage(Level.INFO, "Importing Animation Files...");
		int fileCount = 0;

		try {
			URL animationsURL = AdventOfAscension.class.getResource("/assets/aoa3/models/animations");

			if (animationsURL != null) {
				URI animationsURI = animationsURL.toURI();
				Path path;

				if ("file".equals(animationsURI.getScheme())) {
					path = Paths.get(animationsURI);
				}
				else {
					FileSystem fs = FileSystems.newFileSystem(animationsURI, Collections.emptyMap());
					path = fs.getPath("/assets/aoa3/models/animations");
				}

				Iterator<Path> animationFiles = Files.walk(path).iterator();

				while (animationFiles.hasNext()) {
					Path animationPath = animationFiles.next();

					if ("json".equals(FilenameUtils.getExtension(animationPath.toString()))) {
						BufferedReader reader = null;

						try {
							reader = Files.newBufferedReader(animationPath);
							EntityAnimation.Builder builder = JsonUtils.fromJson(EntityAnimation.GSON, reader, EntityAnimation.Builder.class);

							if (builder != null) {
								String entityName = FilenameUtils.removeExtension(path.relativize(animationPath).toString());
								EntityEntry entityEntry = ForgeRegistries.ENTITIES.getValue(new ResourceLocation("aoa3", entityName));

								if (entityEntry != null) {
									Render<? extends Entity> renderer = Minecraft.getMinecraft().getRenderManager().entityRenderMap.get(entityEntry.getEntityClass());

									if (renderer instanceof RenderLivingBase) {
										boolean alwaysChargers = ConfigurationUtil.MainConfig.funOptions.alwaysChargers;
										ConfigurationUtil.MainConfig.funOptions.alwaysChargers = false;

										ModelBase model = ((RenderLivingBase)renderer).getMainModel();

										ConfigurationUtil.MainConfig.funOptions.alwaysChargers = alwaysChargers;

										if (model instanceof ModelAnimatable) {
											builder.build((ModelAnimatable)model);
										}
										else {
											AdventOfAscension.logMessage(Level.WARN, "Entity model is not of type ModelAnimatable. Unable to continue with animations support for this entity: " + entityName);
										}
									}
									else {
										AdventOfAscension.logMessage(Level.WARN, "EntityRenderer is not of valid type: " + renderer.getClass().toString());
									}
								}
								else {
									AdventOfAscension.logMessage(Level.WARN, "Unable to find entity entry for entity: " + entityName);
								}
							}
							else {
								throw new JsonParseException("Entity animation builder failed to build");
							}

							fileCount++;
						}
						catch (JsonParseException ex) {
							AdventOfAscension.logMessage(Level.WARN, "Malformed JSON in animation file, skipping");
							ex.printStackTrace();
						}
						catch (IOException ex) {
							AdventOfAscension.logMessage(Level.ERROR, "Encountered a file error while parsing animations, skipping");
							ex.printStackTrace();
						}
						finally {
							IOUtils.closeQuietly(reader);
						}
					}
				}
			}
			else {
				AdventOfAscension.logMessage(Level.WARN, "Unable to find animations resource path, dropping animations import task");
			}
		}
		catch (Exception e) {
			AdventOfAscension.logMessage(Level.ERROR, "Critical error while attempting to import animation files, dropping animations entirely.");
			e.printStackTrace();
		}

		AdventOfAscension.logOptionalMessage("Loaded animations for " + fileCount + " entities.");
	}
}
