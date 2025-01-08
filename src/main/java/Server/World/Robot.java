package Server.World;

import Server.ClientHandler;

import java.io.IOException;
import java.util.Random;

import static Server.MultiServerRunner.config;

public class Robot {

    private String name;
    private Position position;
    private Direction direction = Direction.NORTH;
    private int shots;
    private int shield;
    private String status;
    private String status1 = "Normal";
    private String robotMake;
    private int visibility = getVisibility();
    private int bulletTravel = getBulletTravel();
    private ClientHandler robotHandler;
    private int maxShot;
    private int maxShield;

    public Robot(String name) throws IOException {

        this.name = name;

        Random random = new Random();
        int placement_x = random.nextInt(config("Height") + 1) - (config("Width"));
        int placement_y = random.nextInt((config("Height")*2) + 1) - (config("Width")*2);
        this.position = new Position(placement_x, placement_y);
    }

    public void setRobotHandler(ClientHandler robotHandler) {
        this.robotHandler = robotHandler;
    }

    public ClientHandler getRobotHandler() {
        return robotHandler;
    }

    public int getBulletTravel(){
        return bulletTravel;
    }
    public void setBulletTravel(int value){
        this.bulletTravel = value;
    }
    public void setVisibility(int value){
        this.visibility = value;
    }
    public int getVisibility(){
        return visibility;
    }
    public void setRobotMake(String robotMake) {
        this.robotMake = robotMake;
    }
    public String getRobotMake(){
        return robotMake;
    }

    public void setDirection(Direction direction){
        this.direction = direction;
    }

    public void setPosition(Position position){
        this.position = position;
    }

    public  void setName(String name){
        this.name = name;
    }

    public Direction getDirection(){
        return direction;
    }

    public Position getPosition(){
        return position;
    }

    public String getName(){
        return  name;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }

    public int getShots() {
        return shots;
    }

    public void setShots(int shots){
        this.shots = shots;
    }
    public int getShield() {
        return shield;
    }
    public void setShield(int shield) {
        this.shield = shield;
    }
    public String getStatus1(){
        return status1;
    }
    public void setStatus1(String status){
        this.status1 = status;
    }

    public int getMaxShots() {
        return maxShot;
    }

    public void setMaxShot(int maxShot) {
        this.maxShot = maxShot;
    }

    public int getMaxShield() {
        return maxShield;
    }

    public void setMaxShield(int maxShield) {
        this.maxShield = maxShield;
    }
}
