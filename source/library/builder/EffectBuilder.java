package net.tslat.aoa3.library.builder;

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;

public class EffectBuilder {
	private final Effect effect;
	private final int duration;

	private int level = 1;
	private boolean ambient = false;
	private boolean showParticles = true;
	private boolean showIcon = true;

	public EffectBuilder(Effect effect) {
		this(effect, -1);
	}

	public EffectBuilder(Effect effect, int duration) {
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

	public Effect getEffect() {
		return this.effect;
	}

	public EffectInstance build() {
		return new EffectInstance(effect, duration, level - 1, ambient, showParticles, showIcon);
	}
}
