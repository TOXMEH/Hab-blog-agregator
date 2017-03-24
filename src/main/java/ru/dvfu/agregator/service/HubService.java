package ru.dvfu.agregator.service;

import org.springframework.stereotype.Service;
import ru.dvfu.agregator.model.Hub;
import ru.dvfu.agregator.repository.HubRepository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by nesud on 02.12.2016.
 */
@Service
public class HubService {
    @Resource
    HubRepository hubRepository;

    public Hub getHubByName(String name) {
        return hubRepository.getHubByName(name);
    }

    public void save(Hub hub) {
        hubRepository.save(hub);
    }

    public List<Hub> getAllHubs() {
        return (List<Hub>) hubRepository.findAll();
    }

    public Hub addHub(String name, String description) {
        Hub hub = new Hub(name, description);
        return hubRepository.save(hub);
    }

    public Hub editHub(String name, String description) {
        Hub hub = hubRepository.getHubByName(name);
        hub.setDescription(description);
        hubRepository.save(hub);
        return hub;
    }

    public void deleteHub(String name) {
        Hub hub = hubRepository.getHubByName(name);
        hubRepository.delete(hub);
    }
}
