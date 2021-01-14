package org.firstinspires.ftc.teamcode.robottypes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class BasicMecanum extends RobotType {

    public DcMotor fLMotor, fRMotor, bLMotor, bRMotor;          // f = front, b = back, L = left, R = Right
    public DcMotor fEnocder, lEncoder, rEncoder, bEncoder;
    private Telemetry telemetry;                                // these are unique to the child class and are not abstract

    static final double     COUNTS_PER_MOTOR_REV    = 28 ;    // eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 1.0 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 3.54331 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);

    public BasicMecanum(double x, double y, double angleOffset, double width, double length) {
        super(x, y, angleOffset, width, length);
    }

    @Override
    public void init(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;

        telemetry.addData("Status", "Initializing motors");
        telemetry.update();
        fLMotor = hardwareMap.get(DcMotor.class, "fLMotor");
        fRMotor = hardwareMap.get(DcMotor.class, "fRMotor");
        bLMotor = hardwareMap.get(DcMotor.class, "bLMotor");
        bRMotor = hardwareMap.get(DcMotor.class, "bRMotor");

        fLMotor.setPower(0);
        fRMotor.setPower(0);
        bLMotor.setPower(0);
        bRMotor.setPower(0);

        telemetry.addData("Status", "Resetting Encoders");
        telemetry.update();
        fLMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fRMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bLMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bRMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        fLMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fRMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bLMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bRMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    @Override
    public void moveTo(double x, double y, double targetPower) {
        fLMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        int targetX = (int)(x * COUNTS_PER_INCH);
        //int targetY = (int)(y * COUNTS_PER_INCH);

        fLMotor.setTargetPosition(targetX);

        fLMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        fLMotor.setPower(Math.abs(targetPower));

        while (fLMotor.isBusy()) {
            telemetry.addData("Status ", "Waiting on encoder");
        }

        fLMotor.setPower(0);

        fLMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addData("Status ", "Done!");
    }

    @Override
    public void turnTo(double x, double y, double targetPower) {

    }

    @Override
    public void turnTo(double angle, double targetPower) {

    }

    @Override
    public void turn(double angle, double targetPower) {

    }
}
