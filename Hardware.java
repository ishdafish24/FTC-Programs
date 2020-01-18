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

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * This is NOT an opmode.
 *
 * This class can be used to define all the specific hardware for a single robot.
 *
 * This hardware class assumes the following device names have been configured on the robot:
 * Note:  All names are lower case and some have single spaces between words.
 *
 * IGNORE BELOW
 *
 * Motor channel:  Left  drive motor:        "ld"
 * Motor channel:  Right drive motor:        "rd"
 * Motor channel:  Manipulator drive motor:  "arm"
 */

// add intake and output when decided what to do


public class PegasusHardware {

    /* Public OpMode members. */
    public DcMotor  leftDrive  = null;
    public DcMotor  rightDrive = null;
    public Servo arm = null;
    public Servo claw = null;
    public DcMotor rIntake =null;
    public DcMotor lIntake = null;


    public static final double neutral = 0.5;

    /* local OpMode members. */
    HardwareMap hwMapN =  null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public PegasusHardware(){}

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap nhwMap) {
        // Save reference to Hardware map
        hwMapN = nhwMap;

        // Define and Initialize Motors (This is what they'll be called in the driver station)
        leftDrive  = hwMapN.get(DcMotor.class,"ld");
        rightDrive = hwMapN.get(DcMotor.class, "rd");
        rIntake = hwMapN.get(DcMotor.class, "rIntake");
        lIntake = hwMapN.get(DcMotor.class, "lIntake");

        // sets direction
        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);

        // Set all motors to zero power
        rightDrive.setPower(0);
        leftDrive.setPower(0);
        rIntake.setPower(0);
        lIntake.setPower(0);

        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rIntake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lIntake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        // Define and initialize installed servos.
        claw = hwMapN.get(Servo.class, "claw");
        arm = hwMapN.get(Servo.class, "arm");

    }
}
