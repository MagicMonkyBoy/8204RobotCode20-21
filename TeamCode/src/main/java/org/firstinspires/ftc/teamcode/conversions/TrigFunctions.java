package org.firstinspires.ftc.teamcode.conversions;

import java.math.*;

public class TrigFunctions {

    public TrigFunctions() {

    }

    public double distance(double x1, double y1, double x2, double y2) {        //returns distance in cm from point 1 to point 2
        double dx = x2 - x1;
        double dy = y2 - y1;
        return Math.sqrt((dx*dx) + (dy*dy));
    }

    public double angle(double x1, double y1, double x2, double y2) {           //returns angle of the initial side(x axis) to the terminal side(where the robot is facing)
        double dx = x2 - x1;
        double dy = y2 - y1;
        return Math.atan2(dy, dx);
    }

    public double degreeToRadian(double angleDegree) {                          //converts degrees to radians
        return (angleDegree*Math.PI/180);
    }
    public double radianToDegree(double angleRadian) {                          //converts radians to degrees
        return (angleRadian*180/Math.PI);
    }

}
