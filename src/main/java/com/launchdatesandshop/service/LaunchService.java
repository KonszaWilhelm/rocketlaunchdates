package com.launchdatesandshop.service;

import com.launchdatesandshop.entities.Launch;

import java.util.List;


public interface LaunchService {

    List<Launch> getAllLaunches();

    void createLaunch(Launch launch);

    Launch getLaunchById(long id);

    Launch updateLaunch(Launch launch);

    void deleteLaunch(long idLaunch);
}
