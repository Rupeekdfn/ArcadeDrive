/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.Timer;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class Robot extends TimedRobot {
  private DifferentialDrive m_myrobotDrive;
  private Joystick m_leftStick;
  private Joystick m_rightStick;
  private static final int leftfrontDeviceID = 1; 
  private static final int leftrearDeviceID = 2; 
  private static final int rightfrontDeviceID = 3; 
  private static final int rightrearDeviceID = 4;
  private CANSparkMax m_leftfrontMotor;
  private CANSparkMax m_rightfrontMotor;
  private CANSparkMax m_leftrearMotor;
  private CANSparkMax m_rightrearMotor;
  private final Timer m_timer = new Timer();
  private final Joystick m_stick = new Joystick(0);

  @Override
  public void robotInit() {
  /**
   * SPARK MAX controllers are intialized over CAN by constructing a CANSparkMax object
   * 
   * The CAN ID, which can be configured using the SPARK MAX Client, is passed as the
   * first parameter
   * 
   * The motor type is passed as the second parameter. Motor type can either be:
   *  com.revrobotics.CANSparkLowLevel.MotorType.kBrushless
   *  com.revrobotics.CANSparkLowLevel.MotorType.kBrushed
   * 
   * The example below initializes four brushless motors with CAN IDs 1 and 2. Change
   * these parameters to match your setup
   */
    m_leftfrontMotor = new CANSparkMax(leftfrontDeviceID, MotorType.kBrushless);
    m_leftrearMotor = new CANSparkMax(leftrearDeviceID, MotorType.kBrushless);
    m_rightfrontMotor = new CANSparkMax(rightfrontDeviceID, MotorType.kBrushless);
    m_rightrearMotor = new CANSparkMax(rightrearDeviceID, MotorType.kBrushless);
    MotorControllerGroup left = new MotorControllerGroup(m_leftfrontMotor, m_leftrearMotor);
    MotorControllerGroup right = new MotorControllerGroup(m_rightfrontMotor, m_rightrearMotor);
    m_myrobotDrive = new DifferentialDrive(left, right);
    m_leftfrontMotor.restoreFactoryDefaults();
    m_leftrearMotor.restoreFactoryDefaults();
    m_rightrearMotor.restoreFactoryDefaults();
    m_rightfrontMotor.restoreFactoryDefaults();
    
  }
  
@Override
  public void autonomousInit() {
    m_timer.restart();
  }
  @Override
  public void teleopPeriodic() {
    // Drive with arcade drive.
    // That means that the Y axis drives forward
    // and backward, and the X turns left and right.
    m_myrobotDrive.arcadeDrive(-m_stick.getY(), -m_stick.getX());
  }
@Override
 public void autonomousPeriodic() {
    // Drive for 10 seconds
    if (m_timer.get() < 10.0) {
      // Drive forwards half speed, make sure to turn input squaring off
      m_myrobotDrive.arcadeDrive(0.5, 0.0, false);
    }
    else if (m_timer.get() < 15) {
       m_myrobotDrive.arcadeDrive(0.0, 0.5, false);
    } else {
      m_myrobotDrive.stopMotor(); // stop robot
    }
  }
}
