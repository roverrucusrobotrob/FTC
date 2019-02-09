/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp
public class DriverTeleOP extends OpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private DcMotor ArmMotor = null;
    private DcMotor clawMotor = null;
    private Servo latch = null;
    private Servo extendymcboi = null;
    private double extendy = 0.5;
    private double tfjf = 0;
    private double jz = 0;
    private double dz = 0;
    @Override
    public void init() {
        leftDrive  = hardwareMap.get(DcMotor.class, "Left_Motor");
        rightDrive = hardwareMap.get(DcMotor.class, "Right_Motor");
        ArmMotor = hardwareMap.get(DcMotor.class, "Arm_Motor");
        clawMotor = hardwareMap.get(DcMotor.class, "Claw_Motor");
        latch = hardwareMap.get(Servo.class, "latch");
        extendymcboi = hardwareMap.get(Servo.class, "Claw_Servo");

        ArmMotor.setDirection(DcMotor.Direction.REVERSE);
        clawMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
        @Override
        public void loop(){
            double leftPower;
            double rightPower;
            double clawPower;
            double armPower;
            double drive = -gamepad1.left_stick_y;
            double turn  =  gamepad1.right_stick_x;
            double extend = gamepad1.left_trigger;
            double dextend = gamepad1.right_trigger;
            if(gamepad1.y) {
                latch.setPosition(0);
            } else if (gamepad1.a) {
                latch.setPosition(1);
            }
            armPower     = Range.clip(extend - dextend, -1.0, 1.0);
            clawPower    = Range.clip(jz - dz, -0175, 0175);
            leftPower    = Range.clip(drive + turn, -1.0, 1.0);
            rightPower   = Range.clip(drive - turn, -1.0, 1.0);
            //extendy      = Range.clip(extendy+(gamepad2.right_stick_y / 10000), .27, .79);
            telemetry.addData("it be happening", gamepad2.right_stick_y);
            telemetry.addData("it be happeningv2", extendy);
            if (gamepad1.left_bumper){tfjf = 1;}
            else if (gamepad1.right_bumper){tfjf = -1;}
            else {tfjf = 0;}
            if (gamepad1.dpad_right){jz = 1;}
            else {jz = 0;}
            if (gamepad1.dpad_left) {dz = 1;}
            else {dz = 0;}
            extendy += clawPower / 1000;
            clawMotor.setPower(armPower);
            ArmMotor.setPower(tfjf);
            leftDrive.setPower(leftPower);
            rightDrive.setPower(rightPower);
            extendymcboi.setPosition(extendy);
    }
}
