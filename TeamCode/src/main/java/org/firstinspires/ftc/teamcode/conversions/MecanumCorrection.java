package org.firstinspires.ftc.teamcode.conversions;

public class MecanumCorrection {

    public MecanumCorrection() {

    }

    public int wheelCorrectionFL(double targetAngle, double actualAngle) {
        double deltaAngle = targetAngle - actualAngle;
        if (deltaAngle > 0) {


            if (targetAngle >= 0 || targetAngle <= 45) {
                return 0;
            }
            else if (targetAngle >= 45 || targetAngle <= 90) {
                return 0;
            }
            else if (targetAngle >= 90 || targetAngle <= 135) {
                return 0;
            }
            else if (targetAngle >= 135 || targetAngle <= 180) {
                return 0;
            }
            else if (targetAngle >= 180 || targetAngle <= 225) {
                return 0;
            }
            else if (targetAngle >= 225 || targetAngle <= 270) {
                return 0;
            }
            else if (targetAngle >= 270 || targetAngle <= 315) {
                return 0;
            }
            else if (targetAngle >= 315 || targetAngle <= 360) {
                return 0;
            }
            
        } else if (deltaAngle < 0) {

        }
        return 0;
    }

}
