package org.firstinspires.ftc.teamcode.teleopfunctions;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Telopbbot {

    public DcMotor fLMotor, fRMotor, bLMotor, bRMotor;
    private Telemetry telemetry;
    private OpMode mode;
    private HardwareMap hardwareMap;
    public BNO055IMU imu;

    public Telopbbot(OpMode mode) {
        this.mode = mode;


    }

    public void init() {
        telemetry = mode.telemetry;
        hardwareMap = mode.hardwareMap;

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.mode                = BNO055IMU.SensorMode.IMU;
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.loggingEnabled      = false;

        imu = hardwareMap.get(BNO055IMU.class, "imu");     //imu of the expansion hub
        imu.initialize(parameters);

        telemetry.addData("Status", "Calibrating Gyro");
        telemetry.update();

        while (!imu.isGyroCalibrated())
        {
            telemetry.addData("Status", "Calibrating Gyro");
            telemetry.update();
        }

        telemetry.addData("Status", "Initializing motors");
        telemetry.update();

        fLMotor = hardwareMap.get(DcMotor.class, "fLMotor");
        fRMotor = hardwareMap.get(DcMotor.class, "fRMotor");
        bLMotor = hardwareMap.get(DcMotor.class, "bLMotor");
        bRMotor = hardwareMap.get(DcMotor.class, "bRMotor");
        telemetry.addData("Status", "Init Complete");
        telemetry.update();
    }
}
