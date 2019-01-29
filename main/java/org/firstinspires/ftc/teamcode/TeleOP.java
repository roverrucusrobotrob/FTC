package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.util.Range;

@TeleOp
public class TeleOP extends OpMode {
    
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
        boolean boi = true;
        //bind the motors to a part of the controller
        double leftPower;
        double rightPower;
        double drive = -gamepad1.left_stick_x;
        double turn  =  gamepad1.right_stick_y;
        double extend = gamepad1.left_trigger;
        leftPower    = Range.clip(drive + turn, -1.0, 1.0) ;
        rightPower   = Range.clip(drive - turn, -1.0, 1.0) ;
        ArmMotor.setPower(extend);
        LeftMotor.setPower(leftPower);
        RightMotor.setPower(rightPower);
    }
}
