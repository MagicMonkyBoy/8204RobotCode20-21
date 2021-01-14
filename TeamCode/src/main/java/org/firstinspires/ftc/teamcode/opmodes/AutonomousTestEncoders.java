package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.robottypes.BasicMecanum;
import org.firstinspires.ftc.teamcode.robottypes.RobotType;


@Autonomous(name="MecanumBot: Drive cool math", group="MecanumBot")

public class AutonomousTestEncoders extends LinearOpMode {

    RobotType robot;

    @Override
    public void runOpMode() {
        robot = new BasicMecanum(0, 0, 0, 18, 18);
        robot.init(hardwareMap, telemetry);

        robot.moveTo(4, 0, .25);

        waitForStart();
    }
}