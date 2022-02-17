// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.LEDSubsystem;
import frc.robot.commands.LEDCommand;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  // The robot's subsystems and commands are defined here...
  private final LEDCommand autonPatternCommand;
  private final LEDCommand idlePatternCommand;
  private final LEDCommand intakePatternCommand;
  private final LEDCommand shooterPatternCommand;
  private final LEDCommand endgamePatternCommand;
  private final LEDCommand easyPatternCommand;
  private final LEDCommand onEveryOtherCommand;
  private final LEDCommand SolidLEDsCommand;

  // Driver Controller
  private final XboxController driverController = new XboxController(0);
  // Codriver Controller
  private final XboxController codriverController = new XboxController(1);

  // Left Joystick
  public static final int XB_AXIS_LEFT_X = 0;
  public static final int XB_AXIS_LEFT_Y = 1;
  // Triggers
  public static final int XB_AXIS_LT = 2;
  public static final int XB_AXIS_RT = 3;
  // Right Joystick
  public static final int XB_AXIS_RIGHT_X = 4;
  public static final int XB_AXIS_RIGHT_Y = 5;

  // Buttons
  public static final int XB_A = 1;
  public static final int XB_B = 2;
  public static final int XB_X = 3;
  public static final int XB_Y = 4;
  public static final int XB_LB = 5;
  public static final int XB_RB = 6;
  public static final int XB_BACK = 7;
  public static final int XB_START = 8;
  public static final int XB_LEFTSTICK_BUTTON = 9;
  public static final int XB_RIGHTSTICK_BUTTON = 10;
 

  // CODRIVER CONTROLLER BINDS
  JoystickButton intakeButton = new JoystickButton(codriverController, XB_LB);
  JoystickButton reverseIntakeButton = new JoystickButton(codriverController, XB_B);

  private LEDSubsystem ledSubsystem;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   * 
   * @param intakePatternCommand
   */
  public RobotContainer() {
    ledSubsystem = new LEDSubsystem(0);

    autonPatternCommand = new LEDCommand(ledSubsystem, LEDCommand.LEDPatterns.EveryOther);
    endgamePatternCommand = new LEDCommand(ledSubsystem, LEDCommand.LEDPatterns.EveryOther);
    idlePatternCommand = new LEDCommand(ledSubsystem, LEDCommand.LEDPatterns.IdlePattern);
    intakePatternCommand = new LEDCommand(ledSubsystem, LEDCommand.LEDPatterns.IntakePattern);
    shooterPatternCommand = new LEDCommand(ledSubsystem, LEDCommand.LEDPatterns.ShooterPattern);
    easyPatternCommand = new LEDCommand(ledSubsystem, LEDCommand.LEDPatterns.Easy);
    onEveryOtherCommand = new LEDCommand(ledSubsystem, LEDCommand.LEDPatterns.OnEveryOther);
    SolidLEDsCommand = new LEDCommand(ledSubsystem, LEDCommand.LEDPatterns.SolidLEDs);
 
    
  }
  // Configure the button bindings

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
   * it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  public void beginTeleopRunCommands() {
    JoystickButton launchButton = new JoystickButton(driverController, XB_LB);
    JoystickButton rotateArmButton = new JoystickButton(driverController, XB_Y);
    JoystickButton rotateArmRevButton = new JoystickButton(driverController, XB_A);
    JoystickButton endgameSensorCloseButton = new JoystickButton(driverController, XB_X);
    JoystickButton rotateUntilTouchingButton = new JoystickButton(driverController, XB_B);
    JoystickButton endgameComplete = new JoystickButton(driverController, XB_START);
    
    launchButton.whileActiveOnce(SolidLEDsCommand);
    rotateArmButton.whileActiveOnce(autonPatternCommand);
    rotateArmRevButton.whileActiveOnce(intakePatternCommand);
    endgameSensorCloseButton.whileActiveOnce(shooterPatternCommand);
    rotateUntilTouchingButton.whileActiveOnce(onEveryOtherCommand);
    endgameComplete.whileActiveOnce(endgamePatternCommand);
    CommandScheduler scheduler = CommandScheduler.getInstance();

    scheduler.setDefaultCommand(ledSubsystem, idlePatternCommand);

  }

}
