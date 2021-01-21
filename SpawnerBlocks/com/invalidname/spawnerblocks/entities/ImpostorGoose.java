package com.invalidname.spawnerblocks.entities;

import org.newdawn.slick.opengl.Texture;

import com.invalidname.spawnerblocks.SpawnerBlocksMain;

import dangerzone.TextureMapper;
import dangerzone.Utils;
import dangerzone.World;
import dangerzone.entities.Entity;
import dangerzone.entities.Goose;
import dangerzone.entities.Gosling;
import dangerzone.entities.Temperament;
import dangerzone.items.Items;

public class ImpostorGoose extends Impostor {

	public ImpostorGoose(World w) {
		super(w);
		maxrenderdist = 120; //in blocks
		this.height = 1.25f;
		this.width = 0.75f;
		uniquename = "SpawnerBlocks:Impostor Goose";
		moveSpeed = 0.10f;
		setMaxHealth(10.0f);
		setHealth(10.0f);
		setDefense(1.2f);
		setAttackDamage(4.0f);
		searchDistance = 16f;
		attackRange = 1f;
		movefrequency = 55;
		setExperience(28);
		canSwim = true;
		swimoffset = -0.75f;
		setCanDespawn(false);
		takesFallDamage = false;
		setOnGround(true);
		enableFollowHeldFood(12);
		enableBreeding(10);
		temperament = Temperament.PASSIVE;
		enable_hostile = true;
		tower_defense_enable = true;
		
		start_recover = 2;
	}
	
	
	public String getLivingSound(){
		return "DangerZone:goose_living";
	}
	
	public String getHurtSound(){
		return "DangerZone:goose_hit";
	}
	
	public String getDeathSound(){
		return getImpostorDeathSound("DangerZone:goose_death");
	}
	
	
	
	public boolean isFoodItem(int foodid){
		if(foodid == Items.corn.itemID)return true;
		return false;
	}
	
	public boolean isMate(Entity e){
		if(e instanceof Goose)return true;
		if(e instanceof ImpostorGoose)return true;
		return false;
	}
	
	public void doDeathDrops(){
		if(world.rand.nextInt(10)==1)Utils.doDropRand(world, 0, SpawnerBlocksMain.trophyimpostor.itemID, 1f, dimension, posx, posy, posz);
		Utils.doDropRand(world, 0, Items.goosemeat.itemID, 1f, dimension, posx, posy, posz);
		int i=1+world.rand.nextInt(4);
		for(int j=0;j<i;j++){
			Utils.doDropRand(world, 0, Items.feather.itemID, 1.5f, dimension, posx, posy, posz);
		}
		//easter egg!
		if(world.rand.nextInt(1000) == 42){
			for(int j=0;j<1000;j++){
				Utils.doDropRand(world, 0, Items.feather.itemID, 3.5f, dimension, posx, posy, posz);
			}
		}
	}
	
	public void update(float deltaT){
		if(this.world.isServer){
			if(world.rand.nextInt(5000) == 1){
				Utils.doDropRand(world, 0, Items.egggosling.itemID, 0.1f, dimension, posx, posy, posz);
			}
		}
		super.update(deltaT);
	}
	
	public boolean isSuitableTarget(Entity e) {
		
		if(isIgnorable(e))return false;
		if(e instanceof Impostor)return false;
		if(e instanceof Goose)return false;
		if(e instanceof Gosling)return false;
		
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
			texture = TextureMapper.getTexture("res/skins/"+ "Goosetexture.png");	//this is not fast, so we keep our own pointer!
		}
		return texture;
	}
}
