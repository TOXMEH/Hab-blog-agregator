package ru.dvfu.agregator.service;

import org.springframework.stereotype.Service;
import ru.dvfu.agregator.model.Hub;
import ru.dvfu.agregator.repository.HubRepository;

/**
 * Created by nesud on 02.12.2016.
 */
@Service
public class HubService {
    HubRepository hubRepository;

    public Hub getHubByName(String name) {
        return hubRepository.getHubByName(name);
    }
}
