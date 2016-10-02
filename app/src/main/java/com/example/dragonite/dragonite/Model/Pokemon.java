package com.example.dragonite.dragonite.Model;

import android.graphics.Bitmap;
import android.media.Image;

import java.util.ArrayList;

/**
 * Created by Kathy on 14/09/2016.
 */
public class Pokemon {
    public int id;
    public String name;
    public int weight;
    public int height;
    public byte[] mainIcon;
    public byte[] back_default;
    public byte[] front_shiny;
    public byte[] back_shiny;
    public int speed;
    public int special_defense;
    public int special_attack;
    public int defense;
    public int attack;
    public int hp;

    public String moves;
    public String types;
    public String abilities;

    public String evolutionChain;

    public String locations;

    public Pokemon() {
    }

    public Pokemon(int id, String name, int weight, int height, byte[] mainIcon, int speed, int special_defense, int special_attack, int defense, int attack, int hp) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.height = height;

        this.mainIcon = mainIcon;
        this.speed = speed;
        this.special_defense = special_defense;
        this.special_attack = special_attack;
        this.defense = defense;
        this.attack = attack;
        this.hp = hp;
    }

    public byte[] getIcon() {
        return mainIcon;
    }

    public void setIcon(byte[] icon) {
        this.mainIcon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }


    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public byte[] getBack_default() {
        return back_default;
    }

    public void setBack_default(byte[] back_default) {
        this.back_default = back_default;
    }

    public byte[] getFront_shiny() {
        return front_shiny;
    }

    public void setFront_shiny(byte[] front_shiny) {
        this.front_shiny = front_shiny;
    }

    public byte[] getBack_shiny() {
        return back_shiny;
    }

    public void setBack_shiny(byte[] back_shiny) {
        this.back_shiny = back_shiny;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpecial_defense() {
        return special_defense;
    }

    public void setSpecial_defense(int special_defense) {
        this.special_defense = special_defense;
    }

    public int getSpecial_attack() {
        return special_attack;
    }

    public void setSpecial_attack(int special_attack) {
        this.special_attack = special_attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public String getAbilities() {
        return abilities;
    }

    public void setAbilities(String abilities) {
        this.abilities = abilities;
    }

    public String getMoves() {
        return moves;
    }

    public void setMoves(String moves) {
        this.moves = moves;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getEvolutionChain() {
        return evolutionChain;
    }

    public void setEvolutionChain(String evolutionChain) {
        this.evolutionChain = evolutionChain;
    }

    public String getLocations() {
        return locations;
    }

    public void setLocations(String locations) {
        this.locations = locations;
    }
}
