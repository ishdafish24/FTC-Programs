/* Copyright (c) 2017 FIRST. All rights reserved.
        *
        * Redistribution and use in source and binary forms, with or without modification,
        * are permitted (subject to the limitations in the disclaimer below) provided that
        * the following conditions are met:
        *
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
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
        * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
        * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
        */

        package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;


// Declares name on driver station

@Autonomous(name="autonomousRight", group="Pushbot")
public class AutonomousParkRight extends LinearOpMode {

    /* Declare OpMode members. */
    PegasusHardware robot = new PegasusHardware();
    private ElapsedTime     runtime = new ElapsedTime();


    static final double     FORWARD_SPEED = 0.4;
    static final double     TURN_SPEED    = 0.4;

    @Override
    public void runOpMode() {

        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");
        telemetry.update();

        waitForStart();
        runtime.reset();

            robot.leftDrive.setPower(-FORWARD_SPEED);
            robot.rightDrive.setPower(FORWARD_SPEED);
        while (opModeIsActive() && (runtime.seconds() < 2))
        {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        runtime.reset();
        // Spin right for 2 seconds
        robot.leftDrive.setPower(TURN_SPEED);
        robot.rightDrive.setPower(TURN_SPEED);
        while (opModeIsActive() && (runtime.seconds() < 2))
        {
            telemetry.addData("Path", "Leg 2: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();

        }

        runtime.reset();
            robot.leftDrive.setPower(-FORWARD_SPEED);
            robot.rightDrive.setPower(FORWARD_SPEED);
        while (opModeIsActive() && (runtime.seconds() < 5.0))
        {
            telemetry.addData("Path", "Leg 3: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        runtime.reset();

        robot.leftDrive.setPower(0);
        robot.rightDrive.setPower(0);
        while (opModeIsActive() && (runtime.seconds() < 20.0))
        {
            telemetry.addData("Path", "Leg 3: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        sleep(20000000);

        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);
    }
}
