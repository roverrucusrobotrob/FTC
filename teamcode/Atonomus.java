

package org.firstinspires.ftc.TeamCode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import static java.lang.Thread.*;

@Autonomous
public class Atonomus extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftMotor = null;
    private DcMotor rightMotor = null;
    private DcMotor ArmMotor = null;
    private Servo servom = null;
    private Servo dropper = null;

    //@Override
    /*public void init() {
        leftMotor  = hardwareMap.get(DcMotor.class, "Left_Motor");
        rightMotor = hardwareMap.get(DcMotor.class, "Right_Motor");
        ArmMotor = hardwareMap.get(DcMotor.class, "Arm_Motor");
        }*/
    @Override
    public void runOpMode() throws InterruptedException {
        leftMotor = hardwareMap.get(DcMotor.class, "Left_Motor");
        rightMotor = hardwareMap.get(DcMotor.class, "Right_Motor");
        ArmMotor = hardwareMap.get(DcMotor.class, "Arm_Motor");
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        servom = hardwareMap.servo.get("latch");

        telemetry.addData("Mode", "waiting");
        telemetry.update();

        // wait for start button.

        //waitForStart();

        telemetry.addData("Mode", "running");
        telemetry.update();

        // set both motors to 25% power.

        ArmMotor.setPower(-.5);

        // wait for 800 milliseconds.
        sleep(800);

        // set motor power to zero to stop motors.

        servom.setPosition(1);
        servom.setPosition(0);

        sleep(1500);

        leftMotor.setPower(-1);
        rightMotor.setPower(-1);

        sleep(1000);

        leftMotor.setPower(-1);
        rightMotor.setPower(1);

        sleep(1000);

        leftMotor.setPower(0);
        rightMotor.setPower(0);

        boolean forward = false;
        boolean left = false;
        boolean right = false;
        boolean img = false;

        for (int i=0; i==2; i++) {

            if (img) {
                rightMotor.setPower(-.5);
                leftMotor.setPower(-.5);
                sleep(1000);
                rightMotor.setPower(0);
                leftMotor.setPower(0);
            } else if (!img) {
                rightMotor.setPower(-.5);
                leftMotor.setPower(.5);
                sleep(1000);
                rightMotor.setPower(0);
                leftMotor.setPower(0);
            } else if (!img){
                rightMotor.setPower(.5);
                leftMotor.setPower(-.5);
                sleep(2000);
                rightMotor.setPower(0);
                leftMotor.setPower(0);
            }
            else if (!img) {
                rightMotor.setPower(-.5);
                leftMotor.setPower(.5);
                sleep(1000);
                rightMotor.setPower(0);
                leftMotor.setPower(0);
            }
            if(i==2){
                img=true;
            }
        }
    }
}