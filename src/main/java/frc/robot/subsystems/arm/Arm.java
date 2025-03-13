// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.arm;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase {
  private double currentAngle = 0;
  public double[] angles = {35, 35, };
  public double pivotSpeed;

  public Arm() {}

  public void setAngle(double a){
    currentAngle = a;
  }

  public double getAngle(){
    return currentAngle;
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
