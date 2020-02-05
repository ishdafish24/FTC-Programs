package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


// Declares name on driver station

@TeleOp(name="teleOpNiki", group="Linear Opmode")

public class TeleOpPegasus extends LinearOpMode {

    // Map hardware to here.
    PegasusHardware robot = new PegasusHardware();
    double          clawOffset      = 0;                        // Servo mid position
    double          intakeOffset      = 0.15;
    final double    CLAW_SPEED      = 0.05;                   // sets rate to move servo
    final double    INTAKE_SPEED    = 0.01;



    @Override
    public void runOpMode() {

        telemetry.addData("Status", "Ready to Go!");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).

        robot.init(hardwareMap);
        ElapsedTime runtime = new ElapsedTime();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {


            // These are controls for the driver of the chassis (Game Pad 1 user)


            if (gamepad1.left_trigger == 1f)        //Go forward
            {
                robot.leftDrive.setPower(1);
                robot.rightDrive.setPower(-1);
            }

            else if (gamepad1.right_trigger == 1f)  //Go back
            {
                robot.leftDrive.setPower(-1);
                robot.rightDrive.setPower(1);
            }

            else                                    // Stop
            {
                robot.leftDrive.setPower(0);
                robot.rightDrive.setPower(0);
            }

            if (gamepad1.left_stick_x == 1)         //turn right
            {
                robot.leftDrive.setPower(-1);
                robot.rightDrive.setPower(-1);
            }

            else if (gamepad1.left_stick_x == -1)   //turn left
            {
                robot.leftDrive.setPower(1);
                robot.rightDrive.setPower(1);
            }

            else                                    // Stop
            {
                robot.leftDrive.setPower(0);
                robot.rightDrive.setPower(0);
            }


            // These are controls for the arm and intake operator (Game Pad 2 user)


            //Joystick for arm
            if (gamepad2.left_stick_y == -1)
            {
                robot.armZ.setPower(-0.5);
            }

            else if (gamepad2.left_stick_y == 1)
            {
                robot.armZ.setPower(0.5);
            }

            else
            {
                robot.armZ.setPower(0);
            }





            if (gamepad2.right_stick_y == -1)
            {
                robot.armXY.setPower(0.5);
            }

            else if (gamepad2.right_stick_y == 1)
            {
                robot.armXY.setPower(-0.5);
            }

            else
            {
                robot.armXY.setPower(0);
            }




            // Use game pad buttons 'a' and 'x' to open and close the claw
            clawOffset = Range.clip(clawOffset, -0.5, 0.5);
            if (gamepad2.b)
            {
                clawOffset += CLAW_SPEED;
                robot.claw.setPosition(robot.neutral + clawOffset);
            }

            if (gamepad2.a)
            {
                clawOffset -= CLAW_SPEED;
                robot.claw.setPosition(robot.neutral + clawOffset);
            }




            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)");
        }
    }
}
