package ru.dvfu.agregator.controller;

import net.minidev.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.dvfu.agregator.model.Hub;
import ru.dvfu.agregator.model.Reader;
import ru.dvfu.agregator.service.HubService;
import ru.dvfu.agregator.service.ReaderService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by anton on 21.03.17.
 */
@EnableWebMvc
@RestController
@RequestMapping("/api/hubs")
public class HubController {
    @Resource
    private HubService hubService;
    @Resource
    private ReaderService readerService;

    @RequestMapping(method = RequestMethod.GET)
    public List<JSONObject> getAllHubs() {
        List<JSONObject> jsonObjects = new ArrayList<>();
        for (Hub hub : hubService.getAllHubs()) {
            JSONObject articleObject = new JSONObject();
            articleObject.put("name", hub.getName());
            articleObject.put("description", hub.getDescription());
            articleObject.put("subscribers", hub.getSubscribers());

            jsonObjects.add(articleObject);
        }
        return jsonObjects;
    }

    @RequestMapping(value = "/reader/{name}", method = RequestMethod.GET)
    public List<JSONObject> getreaderHubs(@PathVariable("name") String name) {
        List<JSONObject> jsonObjects = new ArrayList<>();
        Reader reader = readerService.getReaderByName(name);
        for (Hub hub : hubService.getAllHubs()) {
            JSONObject articleObject = new JSONObject();
            articleObject.put("name", hub.getName());
            articleObject.put("description", hub.getDescription());
            articleObject.put("subscribers", hub.getSubscribers());
            articleObject.put("subscribed", reader.getHubs().contains(hub));

            jsonObjects.add(articleObject);
        }
        return jsonObjects;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Hub addHub(@RequestParam("name") String name, @RequestParam("description") String description) {
        return hubService.addHub(name, description);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Hub editHub(@RequestParam("name") String name, @RequestParam("description") String description) {
        return hubService.editHub(name, description);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteHub(@RequestParam("name") String name) {
        hubService.deleteHub(name);
    }


    @RequestMapping(value = "/reader", method = RequestMethod.PUT)
    public boolean addSubscription(@RequestParam("reader") String readerName, @RequestParam("hub") String hubName) {
        Hub hub = hubService.getHubByName(hubName);
        hub.setSubscribers(hub.getSubscribers() + 1);
        Reader reader = readerService.getReaderByName(readerName);
        boolean b = reader.getHubs().add(hub);
        readerService.save(reader);
        return b;
    }

    @RequestMapping(value = "/reader", method = RequestMethod.DELETE)
    public void removeSubscription(@RequestParam("reader") String readerName, @RequestParam("hub") String hubName) {
        Hub hub = hubService.getHubByName(hubName);
        hub.setSubscribers(hub.getSubscribers() - 1);
        Reader reader = readerService.getReaderByName(readerName);
        reader.getHubs().remove(hub);
        readerService.save(reader);
    }


}
