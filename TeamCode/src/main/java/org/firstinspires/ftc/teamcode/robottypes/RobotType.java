package org.firstinspires.ftc.teamcode.robottypes;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public abstract class RobotType {

    private static RobotType currentRobotType = null;       //This is set to null, therefore must be defined in robot initiation
    protected double x, y, angleOffset, width, length;
    protected Telemetry telemetry;

    public RobotType (double x, double y, double angleOffset, double width, double length) {
        this.x = x;                         //Center of field is (0, 0), Red alliance side is quadrant 1 and 4
        this.y = y;                         //Each floor tile is 60cm by 60cm and there are 6 tiles by 6 tiles on the field.
        this.angleOffset = angleOffset;     //Unit is degrees for humans and radians for calculations, point (0, 0) to (180, 0) is 0 degrees, (0, 0) to (0, 180) is 90 degrees
        this.width = width;                 //width of robot in cm
        this.length = length;               //length of robot in cm
    }

    public abstract void init(HardwareMap hardwareMap, Telemetry telemetry);

    public abstract void moveTo(double x, double y, double targetPower);
    public abstract void turnTo(double x, double y, double targetPower);
    public abstract void turnTo(double angle, double targetPower);
    public abstract void turn(double angle, double targetPower);

    public static void setRobotType(RobotType robotType) {
        currentRobotType = robotType;
    }
    public static RobotType getRobotType() {
        return currentRobotType;
    }
}
