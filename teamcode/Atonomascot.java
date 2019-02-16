
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

@Autonomous
public class Atonomascot extends LinearOpMode {

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
    private DcMotor clawMotor = null;
    private Servo extendymcboi = null;
    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
    private static final String LABEL_SILVER_MINERAL = "Silver Mineral";
    private static final String VUFORIA_KEY = "ATqh6ID/////AAABmSp+cac+nElJlqbw4zOQwMhXrKa3JOmoRJkBzGVI4j79O609HvRnB8eo+gm0kFUNlBO/wps9ungwpcL6P8TbvN/6F3QGSKmR7BOuFb5bL1SRg/GxGFab2Qn18T3c/lwPJOLjokahChuNzB3kEWfjz2V+vH30nGyqHJ5LEmaiUZUpBZGTXTwOEIwzrZD9vvtnQsq8xbbdG12CwZhBKzyEsGQfleZkPuALiqFTwVjQF8uNC9+kZC3JZ+itePJ2dpBlGUTo3Eoim/+5r3D2CQ06pIQasa+cZAnagDDg18QCSF9oyY3DmTR3akC5lbW6Bt8z2n8dIc0Me1uVZHVbP3zhIpQ3KK14xF4vDRv2Gqre7FR0";
    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;

    //@Override
/*public void init() {
    leftMotor  = hardwareMap.get(DcMotor.class, "Left_Motor");
    rightMotor = hardwareMap.get(DcMotor.class, "Right_Motor");
    ArmMotor = hardwareMap.get(DcMotor.class, "Arm_Motor");
    }*/
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
        clawMotor = hardwareMap.get(DcMotor.class, "Claw_Motor");
        extendymcboi = hardwareMap.get(Servo.class, "Claw_Servo");
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        servom = hardwareMap.servo.get("latch");
        ArmMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        ArmMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addData("Mode", "waiting");
        telemetry.update();

        // wait for start button.

        waitForStart();

        telemetry.addData("Mode", "running");
        telemetry.update();

        // set both motors to 25% power.
        // %%&& ENCODERS BROKEN &&%% \\
        //moveLift(.5, -18.65, 5000);
        ArmMotor.setPower(.9);
        //or naw
        // wait for 800 milliseconds.
        sleep(1300);


        // set motor power to zero to stop motors. We Forgot this so um yeah
        ArmMotor.setPower(0);

        //Wait some milli-sss u catchin’ my vibes
        sleep(100);

        //set servo from 1 to 0
        servom.setPosition(1);
        servom.setPosition(0);

        sleep(1500);

        leftMotor.setPower(-1);
        rightMotor.setPower(1);

        sleep(2250);

        leftMotor.setPower(0);
        rightMotor.setPower(0);

        leftMotor.setPower(1);
        rightMotor.setPower(1);

        sleep(250);

        leftMotor.setPower(0);
        rightMotor.setPower(0);

        sleep(500);

        extendymcboi.setPosition(.45);
        extendymcboi.setPosition(.05);

        sleep(5000);

        clawMotor.setPower(1);
        sleep(500);
        clawMotor.setPower(-1);
        sleep(200);
        clawMotor.setPower(1);
        sleep(300);
        clawMotor.setPower(-1);
        sleep(200);
        clawMotor.setPower(1);
        sleep(100);
        clawMotor.setPower(-1);
        sleep(100);
        clawMotor.setPower(1);
        sleep(100);
        clawMotor.setPower(-1);
        sleep(100);
        clawMotor.setPower(1);
        sleep(100);
        clawMotor.setPower(-1);
        sleep(100);
        clawMotor.setPower(1);
        sleep(100);
        clawMotor.setPower(-1);
        sleep(100);
        clawMotor.setPower(0);
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
    private void initVuforia() {
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = CameraDirection.BACK;

        vuforia = ClassFactory.getInstance().createVuforia(parameters);

    }

    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL);
    }
}
