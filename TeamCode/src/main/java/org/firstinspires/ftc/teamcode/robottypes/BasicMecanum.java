package org.firstinspires.ftc.teamcode.robottypes;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CompassSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class BasicMecanum extends RobotType {

    public DcMotor fLMotor, fRMotor, bLMotor, bRMotor;          // f = front, b = back, L = left, R = Right
    public DcMotor lEncoder, rEncoder, bEncoder;
    private Telemetry telemetry;                        // these are unique to the child class and are not abstract
    private LinearOpMode mode;
    private BNO055IMU imu;

    static final double     COUNTS_PER_MOTOR_REV    = 1120 ;    // eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 1.0 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 3.54331 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);

    public BasicMecanum(double x, double y, double angleOffset, double width, double length) {
        super(x, y, angleOffset, width, length);
    }

    @Override
    public void init(LinearOpMode mode) {
        this.mode = mode;
        HardwareMap hardwareMap = mode.hardwareMap;
        this.telemetry = mode.telemetry;

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.mode                = BNO055IMU.SensorMode.IMU;
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.loggingEnabled      = false;

        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);

        telemetry.addData("Status", "Calibrating Gyro");
        telemetry.update();

        while (!mode.isStopRequested() && !imu.isGyroCalibrated())
        {
            mode.sleep(50);
            mode.idle();
        }

        telemetry.addData("Status", "Initializing motors");
        telemetry.update();
        fLMotor = hardwareMap.get(DcMotor.class, "fLMotor");
        fRMotor = hardwareMap.get(DcMotor.class, "fRMotor");
        bLMotor = hardwareMap.get(DcMotor.class, "bLMotor");
        bRMotor = hardwareMap.get(DcMotor.class, "bRMotor");
        lEncoder = hardwareMap.get(DcMotor.class, "lEncoder");
        rEncoder = hardwareMap.get(DcMotor.class, "rEncoder");
        bEncoder = hardwareMap.get(DcMotor.class, "bEncoder");

        fLMotor.setPower(0);
        fRMotor.setPower(0);
        bLMotor.setPower(0);
        bRMotor.setPower(0);
        lEncoder.setPower(0);
        rEncoder.setPower(0);
        bEncoder.setPower(0);

        telemetry.addData("Status", "Resetting Encoders");
        telemetry.update();
        fLMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fRMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bLMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bRMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lEncoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rEncoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bEncoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        fLMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fRMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bLMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bRMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lEncoder.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rEncoder.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bEncoder.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addData("Status", "Init Complete");
        telemetry.update();
    }

    @Override
    public void moveTo(double x, double y, double targetPower) {
        int targetXPos = (int)(x * COUNTS_PER_INCH) + ((lEncoder.getCurrentPosition() + rEncoder.getCurrentPosition())/2);
        int targetYPos = (int)(x * COUNTS_PER_INCH) + bEncoder.getCurrentPosition();
        Orientation angles;

        while (mode.opModeIsActive()) {
            angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.XYX, AngleUnit.DEGREES);
            telemetry.addData("X ", angles.firstAngle);
            telemetry.addData("Y ", angles.secondAngle);
            telemetry.addData("Z ", angles.thirdAngle);
            telemetry.update();
        }
        fLMotor.setPower(0);

        telemetry.addData("Status ", "Done");
        telemetry.update();

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
