package Server.World;

import Server.MultiServerRunner;
public class RobotCharacteristics {

    private String robotMake;
    private int shield;
    private int shots;
    private  int maxShield;
    private int bulletTravel;
    private int visibility;
    private int maxShots;

    public RobotCharacteristics(String robotMake, int maxShield, int maxShots){
        this.robotMake = robotMake;
        this.shield = maxShield;
        this.shots = maxShots;
        setChars(robotMake);
    }

    public int getBulletTravel() {
        return bulletTravel;
    }
    public int getVisibility() {
        return visibility;
    }
    public String getRobotMake(){
        return robotMake;
    }
    public int getShield(){
        return shield;
    }

    public int getShots(){
        return shots;
    }
    public int getMaxShield() {
        return maxShield;
    }
    private void setChars(String robotMake) {

        int defaultMaxShield = MultiServerRunner.robotConfig(robotMake, "maxShield");
        int defaultMaxShot = MultiServerRunner.robotConfig(robotMake, "maxShots");
        bulletTravel = MultiServerRunner.robotConfig(robotMake, "maxBulletTravel");
        visibility = MultiServerRunner.robotConfig(robotMake, "maxVisibility");
        maxShield = defaultMaxShield;
        maxShots = defaultMaxShot;

        switch (robotMake.toLowerCase()) {

            case ("sniper"), ("assassin"), ("tank"), ("spy"), ("striker"), ("commander"), ("mabena"):
                if (shield > defaultMaxShield) {
                    shield = defaultMaxShield;
                }
                if (shots > defaultMaxShot) {
                    shots = defaultMaxShot;
                }
                break;

            default:
                this.robotMake = "mabena";
                this.shield = MultiServerRunner.robotConfig(robotMake, "maxShield");
                this.shots = MultiServerRunner.robotConfig(robotMake, "maxShots");
                this.visibility = MultiServerRunner.robotConfig(robotMake, "maxVisibility");
                this.bulletTravel = MultiServerRunner.robotConfig(robotMake, "maxBulletTravel");
            }

    }

    public int getMaxShots() {
        return maxShots;
    }
}
