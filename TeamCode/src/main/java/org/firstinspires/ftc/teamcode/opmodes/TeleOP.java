package org.firstinspires.ftc.teamcode.opmodes;

import android.graphics.drawable.GradientDrawable;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.conversions.MecanumMapping;
import org.firstinspires.ftc.teamcode.robottypes.BasicMecanum;
import org.firstinspires.ftc.teamcode.teleopfunctions.Telopbbot;

@TeleOp(name="With IMU", group="MecanumBot")
public class TeleOP extends OpMode {
    Telopbbot bot = new Telopbbot(this);
    MecanumMapping map = new MecanumMapping();
    Orientation angles;

    private final static double POWER = .5;

    @Override
    public void init() {

        bot.init();
    }

    @Override
    public void loop() {
        double yStick = -gamepad1.left_stick_y * POWER;
        double xStick = gamepad1.left_stick_x * POWER;
        double theta = Math.atan2(yStick, xStick) - Math.PI/2;
        if (theta < 0) {
            theta += (2*Math.PI);
        }
        if (xStick == 0 && yStick == 0) {
            theta = 0;
        }
        double power = Math.sqrt((Math.abs(xStick)*Math.abs(xStick)) + (Math.abs(yStick))*Math.abs(yStick)); // a^2 + b^2 = c^2
        if (power > 1) {
            power = 1;
        }
        angles = bot.imu.getAngularOrientation();
        double alpha = angles.secondAngle;
        double fAngle = theta - alpha;

        telemetry.addData("Theta ", Math.toDegrees(theta));
        telemetry.addData("Alpha ", Math.toDegrees(alpha));
        telemetry.addData("Final ", Math.toDegrees(fAngle));
        telemetry.addData("Power   ", power);
        telemetry.addData("Y Stick ", yStick);
        telemetry.addData("X Stick ", xStick);
        telemetry.update();

        bot.fLMotor.setPower(map.flbrAngleMap(fAngle) * -power);
        bot.fRMotor.setPower(map.frblAngleMap(fAngle) * power);
        bot.bLMotor.setPower(map.frblAngleMap(fAngle) * -power);
        bot.bRMotor.setPower(map.flbrAngleMap(fAngle) * power);

    }
}
