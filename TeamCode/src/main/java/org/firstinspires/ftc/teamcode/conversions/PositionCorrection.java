package org.firstinspires.ftc.teamcode.conversions;

public class PositionCorrection {

    public PositionCorrection() {

    }

    public double powerCorrectionStraight(double targetPower, double deltaLeft, double deltaRight) {

        if (deltaLeft > deltaRight) {
            return targetPower * (Math.abs(deltaLeft - deltaRight) / deltaLeft);
        }
        else if (deltaRight < deltaLeft) {
            return targetPower * (Math.abs(deltaRight - deltaLeft) / deltaRight);
        }
        else {
            return targetPower;
        }
    }

}
