package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.conversions.MecanumMapping;
import org.firstinspires.ftc.teamcode.robottypes.BasicMecanum;
import org.firstinspires.ftc.teamcode.teleopfunctions.Telopbbot;

@TeleOp(name="kinda IMU", group="MecanumBot")
public class TeleOPNoIMU extends OpMode {

    double currentAngle;
    double lastAngle;
    double initialAngle;
    Orientation angles;

    Telopbbot bot = new Telopbbot(this);
    MecanumMapping map = new MecanumMapping();

    private final static double POWER = .5;

    @Override
    public void init() {
        bot.init();
        angles = bot.imu.getAngularOrientation();
        initialAngle = angles.firstAngle;
        currentAngle = angles.firstAngle - initialAngle;
    }



    @Override
    public void loop() {
        double yStick = -gamepad1.left_stick_y * POWER;
        double xStick = gamepad1.left_stick_x * POWER;

        double heading = Math.atan2(yStick, xStick) - Math.PI/2;
        if (heading < 0) {
            heading += (2*Math.PI);
        }
        if (xStick == 0 && yStick == 0) {
            heading = 0;
        }
        double power = Math.sqrt((Math.abs(xStick)*Math.abs(xStick)) + (Math.abs(yStick))*Math.abs(yStick)); // a^2 + b^2 = c^2
        if (power > 1) {
            power = 1;
        }

        angles = bot.imu.getAngularOrientation();
        currentAngle = angles.firstAngle - initialAngle;


        telemetry.addData("Heading ", Math.toDegrees(heading));
        telemetry.addData("Angle ", currentAngle*(180/Math.PI));
        telemetry.addData("Power   ", power);
        telemetry.addData("Y Stick ", yStick);
        telemetry.addData("X Stick ", xStick);

        telemetry.update();

        if (gamepad1.left_bumper) {
            bot.fLMotor.setPower(POWER);
            bot.fRMotor.setPower(POWER);
            bot.bLMotor.setPower(POWER);
            bot.bRMotor.setPower(POWER);
        }
        else if (gamepad1.right_bumper) {
            bot.fLMotor.setPower(-POWER);
            bot.fRMotor.setPower(-POWER);
            bot.bLMotor.setPower(-POWER);
            bot.bRMotor.setPower(-POWER);
        }
        else {
            bot.fLMotor.setPower(0);
            bot.fRMotor.setPower(0);
            bot.bLMotor.setPower(0);
            bot.bRMotor.setPower(0);
        }

        bot.fLMotor.setPower(map.flbrAngleMap(heading) * -power);
        bot.fRMotor.setPower(map.frblAngleMap(heading) * power);
        bot.bLMotor.setPower(map.frblAngleMap(heading) * -power);
        bot.bRMotor.setPower(map.flbrAngleMap(heading) * power);



        lastAngle = currentAngle;
    }
}
