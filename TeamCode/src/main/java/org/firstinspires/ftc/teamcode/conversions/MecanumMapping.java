package org.firstinspires.ftc.teamcode.conversions;

public class MecanumMapping {

    public double flbrAngleMap(double heading) {
        if (heading >= 0 && heading < Math.PI/2) {
            return Math.cos(2*heading);
        }
        else if (heading >= Math.PI/2 && heading <= Math.PI) {
            return -1d;
        }
        else if (heading > Math.PI && heading < (3*Math.PI)/2) {
            return -Math.cos(2*heading);
        }
        else if ((heading >= (3*Math.PI)/2 && heading <= Math.PI*2) || (heading <= 0) && (heading >= -Math.PI/2)) {
            return 1d;
        }
        return 0d;
    }

    public double frblAngleMap(double heading) {
        if (heading >= 0 && heading < Math.PI/2) {
            return 1d;
        }
        else if (heading >= Math.PI/2 && heading <= Math.PI) {
            return -Math.cos(2*heading);
        }
        else if (heading > Math.PI && heading < (3*Math.PI)/2) {
            return -1d;
        }
        else if ((heading >= (3*Math.PI)/2 && heading <= Math.PI*2) || (heading <= 0) && (heading >= -Math.PI/2)) {
            return Math.cos(2*heading);
        }
        return 0d;
    }


}
