package com.farcr.savageandravage.common;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.world.server.ServerWorld;

public class EffectBaby extends Effect {

    public EffectBaby() {
        super(EffectType.NEUTRAL, 12380152);
    }

    @Override
    public void performEffect(LivingEntity entity, int amplifier) {
        boolean canBaby = false;
        //TODO add case for booflo
        if(entity instanceof SlimeEntity && ((SlimeEntity)entity).getSlimeSize()>1){
            canBaby = true;
        }
        else if (!entity.isChild()) {
            canBaby = true;
            if (!(entity instanceof AgeableEntity || entity instanceof CreeperEntity || entity instanceof ZombieEntity)) canBaby = false;
        }
        if(canBaby && entity.getRNG().nextInt(3)==0){
            if(entity.isServerWorld()) ((ServerWorld) entity.world).spawnParticle(ParticleTypes.TOTEM_OF_UNDYING, entity.getPosXRandom(0.3D), entity.getPosYRandom(), entity.getPosZRandom(0.3D), 1, 0.3D, 0.3D, 0.3D, 0.2D);
        }
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return true;
    }

}

