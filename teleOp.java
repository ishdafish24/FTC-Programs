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

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Setup a variable for each drive wheel to save power level for telemetry
            double armUp = 0.5;
            double armDown = -0.5;



            // POV Mode uses left stick to go forward, and right stick to turn.
            // - This uses basic math to combine motions and is easier to drive straight.

            double drive =  gamepad1.right_stick_x;
            double turn  =  gamepad1.left_stick_y;


            // Send calculated power to wheels
            if (gamepad1.left_trigger == 1f) {
                robot.leftDrive.setPower(1);
                robot.rightDrive.setPower(-1);
            }

            else if (gamepad1.right_trigger == 1f) {
                robot.leftDrive.setPower(-1);
                robot.rightDrive.setPower(1);
            }

            else {
                robot.leftDrive.setPower(0);
                robot.rightDrive.setPower(0);
            }

            if (gamepad1.left_stick_x == 1) {     //turn right
                robot.leftDrive.setPower(-1);
                robot.rightDrive.setPower(-1);
            }

            else if (gamepad1.left_stick_x == -1) {     //turn left
                robot.leftDrive.setPower(1);
                robot.rightDrive.setPower(1);
            }

            else {
                robot.leftDrive.setPower(0);
                robot.rightDrive.setPower(0);
            }


          /*  if ()
            else if (gamepad1.b){
                robot.leftDrive.setPower(1);
                robot.rightDrive.setPower(1);
            }
            else if (gamepad1.a) {
                robot.leftDrive.setPower(-1);     //can be deleted..this section that is commented
                robot.rightDrive.setPower(-1);
            }
            else{
                robot.leftDrive.setPower(0);
                robot.rightDrive.setPower(0);
            }
          */




            if (gamepad2.x) {
                robot.arm.setPower(armUp);
            }

            else if (gamepad2.b) {
                robot.arm.setPower(armDown);
            }

            else{
                robot.arm.setPower(0);
            }



            // Use game pad buttons 'y' and 'a' to open and close the claw
            if (gamepad2.y)
                clawOffset += CLAW_SPEED;
            else if (gamepad2.a)
                clawOffset -= CLAW_SPEED;

            // Move both servos to new position.  Assume servos are mirror image of each other.
            clawOffset = Range.clip(clawOffset, -0.5, 0.5);
            robot.claw.setPosition(robot.neutral + clawOffset);



            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)");
        }
    }
}
