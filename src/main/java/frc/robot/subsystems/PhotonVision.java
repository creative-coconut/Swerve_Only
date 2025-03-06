// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PhotonVision extends SubsystemBase {
  
  public PhotonCamera backCam;
  public PhotonCamera frontCam;

  //Height in Meters, Pitch in Radians
  public double frontCamHeight;
  public double frontCamPitch;
  
  /** Creates a new Photonvision. */
  public PhotonVision(NetworkTableInstance nt) {
    frontCam = new PhotonCamera(nt, "Front Camera");
    backCam = new PhotonCamera(nt, "Back Camera");
    frontCam.setPipelineIndex(0);
  }
  // AprilTags
  public double[] getTagData(int id) {
    var result = backCam.getLatestResult();
    boolean hasTargets = result.hasTargets();
    if (hasTargets) {
      PhotonTrackedTarget target = result.getBestTarget();
      if (target.getFiducialId() == id) {
        double[] pack = {target.getBestCameraToTarget().getX(), 
        target.getBestCameraToTarget().getZ(),
        target.getBestCameraToTarget().getZ()};
        return pack;
      }
      else {
        double[] mt = {0};
        return mt;
    }
    }
    else {
      double[] mt = {0};
      return mt;
    }
  }


  public Transform3d getTargetValues() {
    var result = backCam.getLatestResult();
    // boolean hasTargets = result.hasTargets();

    PhotonTrackedTarget target = result.getBestTarget();
    Transform3d targetVal = target.getBestCameraToTarget();
    return targetVal;
  }

  public boolean isObj() {
    var result = frontCam.getLatestResult();
    boolean hasTargets = result.hasTargets();
    if (hasTargets) {
      return true;
    } else {
      return false;
    }
  }

  public double[] getObjData() {
    double data[] = {objYaw(), objPitch(), objSkew()};
    return data;
  }

  public double objYaw() {
    var result = frontCam.getLatestResult();
    PhotonTrackedTarget target = result.getBestTarget();
    return target.getYaw();
  }

  public double objPitch() {
    var result = frontCam.getLatestResult();
    PhotonTrackedTarget target = result.getBestTarget();
    return target.getPitch();
  }

  public double objSkew() {
    var result = frontCam.getLatestResult();
    PhotonTrackedTarget target = result.getBestTarget();
    return target.getSkew();
  }

  
  public boolean isTarget(int targetID) {
    var result = backCam.getLatestResult();
    boolean hasTargets = result.hasTargets();
    if (hasTargets) {
      PhotonTrackedTarget target = result.getBestTarget();
      if (targetID == 0) {
        return true;
      } else {
        if (targetID == target.getFiducialId()) {
          return true;
        } else {
          return false;
        }
      }
    } else {
      return false;
    }
  }

}
