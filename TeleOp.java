package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;



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

        // Wait for t-he game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Setup a variable for each drive wheel to save power level for telemetry
            double leftPower;
            double rightPower;
            double armUp = 0.5;
            double armDown = -0.5;



            // POV Mode uses left stick to go forward, and right stick to turn.
            // - This uses basic math to combine motions and is easier to drive straight.

            double drive =  gamepad1.right_stick_x;
            double turn  =  gamepad1.left_stick_y;

            leftPower    = Range.clip(drive*0.725667 - turn, -1.0, 1.0);
            rightPower   = Range.clip(drive*0.725667 + turn, -1.0, 1.0);

            // Send calculated power to wheels
            robot.leftDrive.setPower(leftPower);
            robot.rightDrive.setPower(rightPower);

            if (gamepad1.x) {
                robot.arm.setPower(armUp);
            }

            else if (gamepad1.b) {
                robot.arm.setPower(armDown);
            }

            else{
                robot.arm.setPower(0);
            }

            // Use game pad buttons 'y' and 'a' to open and close the claw
            if (gamepad1.y)
                clawOffset += CLAW_SPEED;
            else if (gamepad1.a)
                clawOffset -= CLAW_SPEED;

            // Move both servos to new position.  Assume servos are mirror image of each other.
            clawOffset = Range.clip(clawOffset, -0.5, 0.5);
            robot.claw.setPosition(robot.neutral + clawOffset);



            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
        }
    }
}
