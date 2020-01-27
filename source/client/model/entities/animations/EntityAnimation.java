package net.tslat.aoa3.client.model.entities.animations;

import com.google.gson.*;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.JsonUtils;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.entity.base.AnimatableEntity;
import net.tslat.aoa3.utils.ModUtil;
import org.apache.logging.log4j.Level;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;
import java.util.*;

@SideOnly(Side.CLIENT)
public class EntityAnimation {
	public static final Gson GSON = (new GsonBuilder()).registerTypeHierarchyAdapter(EntityAnimation.Builder.class, (JsonDeserializer<Builder>)(json, type, context) -> Builder.deserialize(JsonUtils.getJsonObject(json, "animations"), context)).create();

	private final float animationLength;
	private final ArrayList<BoneAnimation> animations;
	private final boolean loop;

	protected EntityAnimation(float animationLength, ArrayList<BoneAnimation> animations, boolean loop) {
		this.animationLength = animationLength;
		this.animations = animations;
		this.loop = loop;
	}

	protected EntityAnimation(int animationLength, ArrayList<BoneAnimation> animations) {
		this(animationLength, animations, false);
	}

	protected void animate(AnimatableEntity entity, int animationTicks, float partialTicks) {
		if (entity.getCurrentAnimationTicks() < 0 && loop)
			entity.resetAnimation();

		if (animations == null || animationTicks > animationLength) {
			if (loop && animationTicks > animationLength) {
				entity.resetAnimation();
			}
			else {
				entity.finishAnimation();
			}

			return;
		}

		for (BoneAnimation animation : animations) {
			animation.rotate(animationTicks, partialTicks);
		}
	}

	private static class BoneAnimation {
		private final ModelRenderer bone;
		private final float[] keyframes;
		private final float[] keyframeRotations;

		private BoneAnimation(ModelRenderer bone, float[] keyFrames, float[] keyframeRotations) {
			this.bone = bone;
			this.keyframes = keyFrames;
			this.keyframeRotations = keyframeRotations;
		}

		protected void rotate(int animationTicks, float partialTicks) {
			for (int i = 0; i < keyframes.length; i++) {
				if (keyframes[i] >= animationTicks + partialTicks) {
					float framePercentComplete = (animationTicks + partialTicks - keyframes[i - 1]) / (keyframes[i] - keyframes[i - 1]);

					bone.rotateAngleX = keyframeRotations[(i - 1) * 3] + ((keyframeRotations[i * 3] - keyframeRotations[(i - 1) * 3]) * framePercentComplete);
					bone.rotateAngleY = keyframeRotations[(i - 1) * 3 + 1] + ((keyframeRotations[i * 3 + 1] - keyframeRotations[(i - 1) * 3 + 1]) * framePercentComplete);
					bone.rotateAngleZ = keyframeRotations[(i - 1) * 3 + 2] + ((keyframeRotations[i * 3 + 2] - keyframeRotations[(i - 1) * 3 + 2]) * framePercentComplete);

					return;
				}
			}
		}
	}

	public static class Builder {
		private final HashSet<HashMap<String, Object>> semiParsedElementsMap;

		Builder(@Nonnull HashSet<HashMap<String, Object>> partiallyDigestedMap) {
			this.semiParsedElementsMap = partiallyDigestedMap;
		}

		public void build(ModelAnimatable baseModel) {
			String animationName = null;
			float animationLength = 0;
			ArrayList<BoneAnimation> boneAnimations = new ArrayList<BoneAnimation>();
			boolean loop = false;

			for (HashMap<String, Object> animationMap : semiParsedElementsMap) {
				animationLoop:
				for (Map.Entry<String, Object> entry : animationMap.entrySet()) {
					switch (entry.getKey()) {
						case "name":
							animationName = (String)entry.getValue();
							break;
						case "animation_length":
							animationLength = (Float)entry.getValue();
							break;
						case "loop":
							loop = true;
							break;
						case "bones":
							for (Map.Entry<String, JsonElement> boneEntry : (Set<Map.Entry<String, JsonElement>>)entry.getValue()) {
								try {
									Field boneField = ReflectionHelper.findField(baseModel.getClass(), boneEntry.getKey());
									Object boneObject;

									if (ModelRenderer.class.isAssignableFrom((boneObject = boneField.get(baseModel)).getClass())) {
										ModelRenderer bone = (ModelRenderer)boneObject;
										JsonObject boneDetails = boneEntry.getValue().getAsJsonObject();
										float[] keyframes;
										float[] keyframeRotations;

										if (!boneDetails.has("rotation"))
											throw new JsonSyntaxException("No rotation information for animated bone section. Skipping animation");

										JsonElement boneRotationObject = boneDetails.get("rotation");

										try {
											Set<Map.Entry<String, JsonElement>> rotationDetails;

											if (boneRotationObject.isJsonObject()) {
												rotationDetails = boneRotationObject.getAsJsonObject().entrySet();
												keyframes = ModUtil.initFixedArray((float)rotationDetails.size());
												keyframeRotations = ModUtil.initFixedArray((float)rotationDetails.size() * 3);
												int i = 0;

												for (Map.Entry<String, JsonElement> rotationDetail : rotationDetails) {
													JsonArray keyFrameRotationArray = rotationDetail.getValue().getAsJsonArray();

													keyframes[i] = Float.parseFloat(rotationDetail.getKey()) * 20f;
													keyframeRotations[i * 3] = (float)Math.toRadians(keyFrameRotationArray.get(0).getAsJsonPrimitive().getAsFloat());
													keyframeRotations[i * 3 + 1] = (float)Math.toRadians(keyFrameRotationArray.get(1).getAsJsonPrimitive().getAsFloat());
													keyframeRotations[i * 3 + 2] = (float)Math.toRadians(keyFrameRotationArray.get(2).getAsJsonPrimitive().getAsFloat());

													i++;
												}
											}
											else {
												keyframes = new float[] {0};
												JsonArray boneRotationArray = boneRotationObject.getAsJsonArray();
												keyframeRotations = ModUtil.initFixedArray(boneRotationArray.size());

												for (int i = 0; i < boneRotationArray.size(); i++) {
													keyframeRotations[i] = boneRotationArray.get(i).getAsJsonPrimitive().getAsFloat();
												}
											}
										}
										catch (IndexOutOfBoundsException ex) {
											AdventOfAscension.logMessage(Level.WARN, "Oddly malformed rotation instruction in animation file for " + baseModel.getClass().getName() + ": " + animationName);

											continue animationLoop;
										}

										boneAnimations.add(new BoneAnimation(bone, keyframes, keyframeRotations));
									}
									else {
										AdventOfAscension.logMessage(Level.WARN, "Model field " + boneEntry.getKey() + " is not a valid ModelRenderer field, unable to parse animation details");

										continue animationLoop;
									}
								}
								catch (IllegalAccessException ex) {
									AdventOfAscension.logMessage(Level.WARN, "Bone model field has invalid access modifier: " + baseModel.getClass().toString() + "#" + boneEntry.getKey());

									continue animationLoop;
								}
								catch (JsonSyntaxException ex) {
									AdventOfAscension.logMessage(Level.WARN, "Malformed JSON for animation file");
									ex.printStackTrace();

									continue animationLoop;
								}
								catch (NumberFormatException ex) {
									AdventOfAscension.logMessage(Level.WARN, "Invalid number format for timing or rotation for entity animation");
									ex.printStackTrace();

									continue animationLoop;
								}
								catch (IllegalStateException ex) {
									AdventOfAscension.logMessage(Level.WARN, "JSON Object received in an unexpected format");
									ex.printStackTrace();

									continue animationLoop;
								}
							}
							break;
					}
				}

				if (!boneAnimations.isEmpty() && animationLength > 0)
					baseModel.addAnimation(animationName, new EntityAnimation(animationLength, boneAnimations, loop));
			}
		}

		public static EntityAnimation.Builder deserialize(JsonObject json, JsonDeserializationContext context) {
			Set<Map.Entry<String, JsonElement>> jsonEntries = json.get("animations").getAsJsonObject().entrySet();
			HashSet<HashMap<String, Object>> elementsMap = new HashSet<HashMap<String, Object>>(jsonEntries.size());

			for (Map.Entry<String, JsonElement> entry : jsonEntries) {
				String name = entry.getKey();
				JsonObject object = entry.getValue().getAsJsonObject();
				HashMap<String, Object> animationMap = new HashMap<String, Object>();

				if (!object.has("animation_length"))
					throw new JsonSyntaxException("Missing animation length property from animation JSON, skipping");

				if (!object.has("bones"))
					throw new JsonSyntaxException("Missing bones list from animation JSON, skipping");

				animationMap.put("name", name);
				animationMap.put("animation_length", JsonUtils.getFloat(object, "animation_length") * 20);
				animationMap.put("bones", object.get("bones").getAsJsonObject().entrySet());

				if (object.has("loop") && object.get("loop").getAsBoolean())
					animationMap.put("loop", true);

				elementsMap.add(animationMap);
			}

			return new Builder(elementsMap);
		}
	}
}
