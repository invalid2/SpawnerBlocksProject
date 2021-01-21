package com.invalidname.spawnerblocks.entities;

import org.newdawn.slick.opengl.Texture;

import com.invalidname.spawnerblocks.SpawnerBlocksMain;

import dangerzone.TextureMapper;
import dangerzone.Utils;
import dangerzone.World;
import dangerzone.entities.Anteater;
import dangerzone.entities.Entity;
import dangerzone.entities.Temperament;
import dangerzone.items.Items;

public class ImpostorAnteater extends Impostor {

	public ImpostorAnteater(World w) {
		super(w);
		maxrenderdist = 130; //in blocks
		this.height = 1.25f;
		this.width = 0.95f;
		uniquename = "SpawnerBlocks:Impostor Anteater";
		moveSpeed = 0.25f;
		setMaxHealth(80.0f);
		setHealth(80.0f);
		setDefense(3.5f);
		setAttackDamage(12.0f);
		searchDistance = 16f;
		attackRange = 3f;
		movefrequency = 45;
		setExperience(120);
		canSwim = true;
		setCanDespawn(false);
		enableDroppedFood(12);
		temperament = Temperament.PASSIVE;
		enable_hostile = true;
		tower_defense_enable = true;
	}
	
	public boolean isFoodItem(int foodid){
		if(foodid == Items.deadbug.itemID)return true;
		return false;
	}
	
	public String getLivingSound() {
		return null;
	}
	
	public String getHurtSound() {
		return "DangerZone:anteater_hit";
	}
	
	public String getDeathSound() {
		return getImpostorDeathSound(null);
	}
	
	public void doDeathDrops(){		
		if(world.rand.nextInt(10)==1)Utils.doDropRand(world, 0, SpawnerBlocksMain.trophyimpostoranteater.itemID, 1f, dimension, posx, posy, posz);
		super.doDeathDrops();
	}
	
	public boolean isSuitableTarget(Entity e) {
		
		if(isIgnorable(e))return false;
		if(e instanceof Impostor)return false;
		if(e instanceof Anteater)return false;
		
		if(CanProbablySeeEntity(e) && target_entity == null && !this.isInPOV(e) && !isRecovering) {
			this.temperament = Temperament.HOSTILE;
			target_entity = e;
			return true;
		}
		if(target_entity != null && this.getDistanceFromEntity(target_entity) < this.searchDistance) {
			this.temperament = Temperament.HOSTILE;
			return true;
		}
		if(target_entity != null && this.getDistanceFromEntity(target_entity) > this.searchDistance) {
			this.temperament = Temperament.PASSIVE;
			target_entity = null;
			return false;
		}
		return false;
	}
	
	public Texture getTexture() {
		if(texture == null){
			//ENTITIES MUST USE TEXTUREMAPPER.GETTEXTURE()!!!!
			texture = TextureMapper.getTexture("res/skins/"+ "Anteatertexture.png");	//this is not fast, so we keep our own pointer!
		}
		return texture;
	}
}
