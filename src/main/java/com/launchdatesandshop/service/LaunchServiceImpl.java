package com.launchdatesandshop.service;

import com.launchdatesandshop.entities.Launch;
import com.launchdatesandshop.exception.ResourceNotFoundException;
import com.launchdatesandshop.repositories.LaunchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LaunchServiceImpl implements LaunchService {

    @Autowired
    private LaunchRepository launchRepository;

    @Override
    public List<Launch> getAllLaunches() {

        return launchRepository.findAll();
    }

    @Override
    public void createLaunch(Launch launch) {

        this.launchRepository.save(launch);
    }

    @Override
    public Launch getLaunchById(long id) {
        Optional<Launch> launchDb = launchRepository.findById(id);
        if (launchDb.isPresent()) {
            return launchDb.get();
        } else {
            throw new RuntimeException("Launch id not found: " + id);
        }
    }

    @Override
    public Launch updateLaunch(Launch launch) {

        Optional<Launch> launchDb = launchRepository.findById(launch.getIdLaunch());
        if (launchDb.isPresent()) {
            // getting product obj from Optional
            Launch launchUpdate = launchDb.get();
//            launchUpdate.setIdProduct(launch.getIdProduct());
            launchUpdate.setMissionName(launch.getMissionName());
            launchUpdate.setLaunchServiceProvider(launch.getLaunchServiceProvider());
            launchUpdate.setRocketType(launch.getRocketType());
//            launchUpdate.setLaunchDate(launch.getLaunchDate());
//            launchUpdate.setTMinus(launch.getTMinus());
            //saving the entity
            launchRepository.save(launchUpdate);
            return launchUpdate;
        } else {
            throw new ResourceNotFoundException("Record not found with id: " + launch.getIdLaunch());
        }

    }

    @Override
    public void deleteLaunch(long idLaunch) {
        Optional<Launch> launchDb = this.launchRepository.findById(idLaunch);
        if (launchDb.isPresent()){
            this.launchRepository.delete(launchDb.get());
        }else{
            throw new ResourceNotFoundException("Launch not found with id: " + idLaunch);
        }

    }
}
