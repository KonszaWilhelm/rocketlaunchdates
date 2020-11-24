package com.launchdatesandshop.entities;

import javax.persistence.*;

@Entity
@Table(name = "launches")
public class Launch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_launch")
    private Long idLaunch;

    @Column(name = "mission_name")
    private String missionName;

    @Column(name = "launch_service_provider")
    private String launchServiceProvider;

    @Column(name = "rocket_type")
    private String rocketType;

    @Column(name = "launch_date")
    private String launchDate;

    public Long getIdLaunch() {
        return idLaunch;
    }

    public void setIdLaunch(Long idAdmin) {
        this.idLaunch = idAdmin;
    }

    public String getMissionName() {
        return missionName;
    }

    public void setMissionName(String firstName) {
        this.missionName = firstName;
    }

    public String getLaunchServiceProvider() {
        return launchServiceProvider;
    }

    public void setLaunchServiceProvider(String lastName) {
        this.launchServiceProvider = lastName;
    }

    public String getRocketType() {
        return rocketType;
    }

    public void setRocketType(String rocketType) {
        this.rocketType = rocketType;
    }

    public String getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(String launchDate) {
        this.launchDate = launchDate;
    }

    @Override
    public String toString() {
        return "Launch{" +
                "idLaunch=" + idLaunch +
                ", missionName='" + missionName + '\'' +
                ", launchServiceProvider='" + launchServiceProvider + '\'' +
                ", rocketType='" + rocketType + '\'' +
                ", launchDate=" + launchDate +

                '}';
    }
}
