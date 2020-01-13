package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import java.util.concurrent.TimeUnit;


// Declares name on driver station

@TeleOp(name="teleOpNiki", group="Linear Opmode")

public class TeleOpNiki extends LinearOpMode {

    // Map hardware to here.
    NikiHardware robot = new NikiHardware();
    double          clawOffset      = 0;                       // Servo mid position
    final double    CLAW_SPEED      = 0.01;                   // sets rate to move servo


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

            // These variables control arm movement
            double armUp = 0.5;
            double armDown = -0.5;



            // These are controls for the driver of the chassis (Game Pad 1 user)


            if (gamepad1.dpad_up)           // Accelerate
            {
                for (double i = 0.5; i < 1; i = i + 0.01)
                {
                    robot.leftDrive.setPower(i);
                    robot.rightDrive.setPower(i);
                }
            }

            else if (gamepad1.dpad_down)    // Decelerate
            {
                for (double j = 1; j >= 0.5 ; j = j - 0.01)
                {
                    robot.leftDrive.setPower(j);
                    robot.rightDrive.setPower(j);
                }
            }

            else                            // Stop
            {
                    robot.leftDrive.setPower(0);
                    robot.rightDrive.setPower(0);
            }



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


            if (gamepad2.x)
            {
                robot.arm.setPower(armUp);
            }

            else if (gamepad2.b)
            {
                robot.arm.setPower(armDown);
            }

            else
            {
                robot.arm.setPower(0);
            }



            // Use game pad buttons 'y' and 'a' to open and close the claw
            clawOffset = Range.clip(clawOffset, -0.5, 0.5);
            if (gamepad2.y)
            {
                clawOffset += CLAW_SPEED;
                robot.claw.setPosition(robot.neutral + clawOffset);
            }

            else if (gamepad2.a)
            {
                clawOffset -= CLAW_SPEED;
                robot.claw.setPosition(robot.neutral + clawOffset);
            }



            if (gamepad2.dpad_up)
            {
                clawOffset += CLAW_SPEED;
                robot.lIntake.setPosition(robot.neutral + clawOffset);

            }

            else if (gamepad2.dpad_down)
            {
                clawOffset += CLAW_SPEED;
                robot.rIntake.setPosition(robot.neutral + clawOffset);
            }




            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)");
        }
    }
}
