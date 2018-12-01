package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gyroscope;

@TeleOp
public class practicet extends OpMode {
    
    //call variables here, like DcMotor, Servo, ColorSensor, etc
    private DcMotor ArmMotor;
    private DcMotor RightMotor;
    private DcMotor LeftMotor;
    private Gyroscope imu;

    @Override
    public void init() {
        //string is what the names should be in the phone
        imu = hardwareMap.get(Gyroscope.class, "imu");
        ArmMotor = hardwareMap.get(DcMotor.class, "Arm_Motor");
        RightMotor = hardwareMap.get(DcMotor.class, "Right_Motor");
        LeftMotor = hardwareMap.get(DcMotor.class, "Left_Motor");
    }

    @Override
    public void loop() {
        //bind the motors to a part of the controller
        ArmMotor.setPower(gamepad1.left_trigger);
        RightMotor.setPower(gamepad1.left_stick_x);
        LeftMotor.setPower(gamepad1.left_stick_y);
        ArmMotor.setPower(gamepad2.left_trigger);
        RightMotor.setPower(gamepad2.left_stick_x);
        LeftMotor.setPower(gamepad2.left_stick_y);
    }
    }
