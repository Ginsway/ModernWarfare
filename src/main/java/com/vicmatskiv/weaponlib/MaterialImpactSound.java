package com.vicmatskiv.weaponlib;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.vicmatskiv.weaponlib.compatibility.CompatibleSound;

public class MaterialImpactSound {
    private List<CompatibleSound> sounds = new ArrayList<>();
    private float volume;
    private Random rand = new Random();
    
    public MaterialImpactSound(float volume) {
        this.volume = volume;
    }
    
    public void addSound(CompatibleSound sound) {
        sounds.add(sound);
    }
    
    public CompatibleSound getSound() {
        int soundIndex = rand.nextInt(sounds.size());
//        System.out.println("Playing sound " + soundIndex + " out of " + sounds.size());
        return sounds.get(soundIndex);
    }
    
    public float getVolume() {
        return volume;
    }
}