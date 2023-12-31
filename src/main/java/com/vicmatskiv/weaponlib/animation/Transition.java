package com.vicmatskiv.weaponlib.animation;

import java.util.function.Consumer;

import com.vicmatskiv.weaponlib.Part;
import com.vicmatskiv.weaponlib.compatibility.CompatibleSound;

import net.minecraft.util.SoundEvent;

public class Transition<Context> {

    private static final Consumer<?> ANCHORED_POSITION = c -> {};

    @SuppressWarnings("unchecked")
    public static <T> Consumer<T> anchoredPosition() {
        return (Consumer<T>) ANCHORED_POSITION;
    }

	private Consumer<Context> itemPositioning;
	private long duration;
	private long pause;
	private Part attachedTo;
	private boolean animated;

	private CompatibleSound evt;
	
	public Transition(Consumer<Context> itemPositioning, Part attachedTo, boolean animated) {
        this(itemPositioning, 0, 0);
        this.animated = animated;
    }

	public Transition(Consumer<Context> itemPositioning, long duration) {
	    this(itemPositioning, duration, 0);
	}

	public Transition(Consumer<Context> itemPositioning, long duration, long pause) {
        this(itemPositioning, duration, pause, null);
    }
	
	public void setSound(CompatibleSound event) {
		this.evt = event;
	}

	public Transition(Consumer<Context> itemPositioning, long duration, long pause, Part attachedTo) {
		this.itemPositioning = itemPositioning;
		this.duration = duration;
		this.pause = pause;
		this.attachedTo = attachedTo;
	}

	public Consumer<Context> getItemPositioning() {
		return itemPositioning;
	}
	
	public void setDuration(long newDuration) {
		this.duration = newDuration;
	}

	public long getDuration() {
		return duration;
	}
	
	public CompatibleSound getSound() {
		return this.evt;
	}

	public long getPause() {
		return pause;
	}

	public Part getAttachedTo() {
	    return attachedTo;
	}

	public boolean isAnimated() {
        return animated;
    }
}
