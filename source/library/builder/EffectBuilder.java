package net.tslat.aoa3.library.builder;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;

public class EffectBuilder {
	private final MobEffect effect;
	private final int duration;

	private int level = 1;
	private boolean ambient = false;
	private boolean showParticles = true;
	private boolean showIcon = true;

	public EffectBuilder(MobEffect effect) {
		this(effect, -1);
	}

	public EffectBuilder(MobEffect effect, int duration) {
		this.effect = effect;
		this.duration = duration;
	}

	public EffectBuilder isAmbient() {
		ambient = true;

		return this;
	}

	public EffectBuilder level(int level) {
		this.level = level;

		return this;
	}

	public EffectBuilder hideParticles() {
		showParticles = false;

		return this;
	}

	public EffectBuilder hideEffectIcon() {
		showIcon = false;

		return this;
	}

	public MobEffect getEffect() {
		return this.effect;
	}

	public MobEffectInstance build() {
		return new MobEffectInstance(effect, duration, level - 1, ambient, showParticles, showIcon);
	}
}
