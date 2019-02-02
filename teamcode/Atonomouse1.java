

package org.firstinspires.ftc.TeamCode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import java.util.List;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class Atonomouse extends LinearOpMode {

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
        initVuforia();

        if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
            initTfod();
        } else {
            telemetry.addData("Sorry!", "This device is not compatible with TFOD");
        }

        telemetry.addData(">", "Press Play to start tracking");
        telemetry.update();
        waitForStart();

        if (opModeIsActive()) {
            if (tfod != null) {
                tfod.activate();
            }

            while (opModeIsActive()) {
                if (tfod != null) {
                    // getUpdatedRecognitions() will return null if no new information is available since
                    // the last time that call was made.
                    List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                    if (updatedRecognitions != null) {
                        telemetry.addData("# Object Detected", updatedRecognitions.size());
                        if (updatedRecognitions.size() == 3) {
                            int goldMineralX = -1;
                            int silverMineral1X = -1;
                            int silverMineral2X = -1;
                            for (Recognition recognition : updatedRecognitions) {
                                if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                                    goldMineralX = (int) recognition.getLeft();
                                } else if (silverMineral1X == -1) {
                                    silverMineral1X = (int) recognition.getLeft();
                                } else {
                                    silverMineral2X = (int) recognition.getLeft();
                                }
                            }
                            if (goldMineralX != -1 && silverMineral1X != -1 && silverMineral2X != -1) {
                                if (goldMineralX < silverMineral1X && goldMineralX < silverMineral2X) {
                                    telemetry.addData("Gold Mineral Position", "Left");
                                } else if (goldMineralX > silverMineral1X && goldMineralX > silverMineral2X) {
                                    telemetry.addData("Gold Mineral Position", "Right");
                                } else {
                                    telemetry.addData("Gold Mineral Position", "Center");
                                }
                            }
                        }
                        telemetry.update();
                    }
                }
            }
        }

        if (tfod != null) {
            tfod.shutdown();
        }
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
        // %%&& ENCODERS BROKEN &&%% \\
        moveLift(.5, -18.65, 5000);
        //ArmMotor.setPower(-.9);
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
        rightMotor.setPower(-1);

        sleep(2250);

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


