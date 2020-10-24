package com.launchdatesandshop.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    private LocalDateTime launchDate;

    @Column(name = "t_minus")
    private Long tMinus;

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

    @Override
    public String toString() {
        return "Launch{" +
                "idLaunch=" + idLaunch +
                ", missionName='" + missionName + '\'' +
                ", launchServiceProvider='" + launchServiceProvider + '\'' +
                ", rocketType='" + rocketType + '\'' +
                ", launchDate=" + launchDate +
                ", tMinus=" + tMinus +
                '}';
    }
}
