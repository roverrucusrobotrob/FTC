

package org.firstinspires.ftc.teamcode;

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
    private double ticksPerRevolution = 1440;
    private double diameter = 2.5;
    private double gearReduction = .5;
    private double ticksPerInch = (ticksPerRevolution * gearReduction) / diameter * 3.1415;
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

    public void imagerec(){

    }
    /////////////////////////////////////////////////////////////////////////////////////////////

    public void moveLift(double power,double inches, long time) throws InterruptedException {
        ArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        ArmMotor.setTargetPosition((int) (ticksPerInch * inches));
        ArmMotor.setPower(power);
        if (inches >= 12) {
            ArmMotor.setPower(0);
        }

    }
    @Override
    public void runOpMode() throws InterruptedException {
        leftMotor = hardwareMap.get(DcMotor.class, "Left_Motor");
        rightMotor = hardwareMap.get(DcMotor.class, "Right_Motor");
        ArmMotor = hardwareMap.get(DcMotor.class, "Arm_Motor");
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        servom = hardwareMap.servo.get("latch");
        ArmMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        ArmMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addData("Mode", "waiting");
        telemetry.update();

        // wait for start button.

        //waitForStart();

        telemetry.addData("Mode", "running");
        telemetry.update();

        // set both motors to 25% power.
        moveLift(.5, 12, 5);
        ArmMotor.setPower(-.5);
        //or naw
        // wait for 800 milliseconds.
        sleep(800);

        // set motor power to zero to stop motors. We Forgot this so um yeah
        ArmMotor.setPower(0);

        //Wait some milli-sss u catchin’ my vibes
        sleep(800);

        //set servo from 1 to 0
        servom.setPosition(1);
        servom.setPosition(0);

        sleep(1500);

        leftMotor.setPower(-1);
        rightMotor.setPower(-1);

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
                if(img){
                    rightMotor.setPower(.5);
                    leftMotor.setPower(.5);
                    sleep(1000);
                    rightMotor.setPower(0);
                    leftMotor.setPower(0);
                    sleep(800);
                    dropper.setPosition(0);
                    sleep(500);
                    dropper.setPosition(1);
                }
                if(!img){
                    rightMotor.setPower(-.5);
                    leftMotor.setPower(.5);
                    sleep(1000);
                    rightMotor.setPower(0);
                    leftMotor.setPower(0);
                }
                else if(!img){
                    rightMotor.setPower(.5);
                    leftMotor.setPower(-.5);
                    sleep(2000);
                    rightMotor.setPower(0);
                    leftMotor.setPower(0);
                }
            }
            if (!img) {
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
            else if (!img){
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


