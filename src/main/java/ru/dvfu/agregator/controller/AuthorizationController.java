package ru.dvfu.agregator.controller;

import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.dvfu.agregator.model.Admin;
import ru.dvfu.agregator.model.Reader;
import ru.dvfu.agregator.model.Writer;
import ru.dvfu.agregator.service.AdminService;
import ru.dvfu.agregator.service.ReaderService;
import ru.dvfu.agregator.service.WriterService;

import javax.annotation.Resource;
import java.util.Objects;

//import ru.dvfu.agregator.config.SpringConfiguration;

/**
 * Created by Anton Nesudimov on 12.11.2016.
 */
@EnableWebMvc
@RestController
@RequestMapping("/api/auth")
public class AuthorizationController {

    //    private SpringConfiguration springConfiguration;
    @Resource
    private ReaderService readerService;
    @Resource
    private WriterService writerService;
    @Resource
    private AdminService adminService;

//    @ApiOperation(value = "registrate new user", notes = "", tags = {"Authorization"})
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "no collisions"),
//            @ApiResponse(code = 409, message = "some collision occurred")})
//    @RequestMapping(method = RequestMethod.POST)
//    public ResponseEntity<String> registrateUser(@RequestParam("login") String login, @RequestParam("password") String password) {
//        boolean registred = authorizationService.register(login, password);
//        if (registred) {
//            return ResponseEntity.status(HttpStatus.OK).body(null);
//        } else {
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
//        }
//    }


    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<JSONObject> registrate(@RequestParam("name") String name, @RequestParam("password") String password, @RequestParam("role") String role) {
        Reader reader = readerService.getReaderByName(name);
        Writer writer = writerService.getWriterByName(name);
        Admin admin = adminService.getAdminByName(name);
        if ((reader != null) || (writer != null) || (admin != null)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

        if (Objects.equals(role, "reader")) {
            readerService.addReader(name, password);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else if (Objects.equals(role, "writer")) {
            writerService.addWriter(name, password);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<JSONObject> authorize(@RequestParam("name") String name, @RequestParam("password") String password) {
        if (readerService == null) {
            System.out.println("WOW");
        }
        if (readerService.authorizeReader(name, password)) {
            JSONObject entity = new JSONObject();
            entity.put("redirect", "reader");
            return ResponseEntity.status(HttpStatus.OK).body(entity);
        } else if (writerService.authorizeWriter(name, password)) {
            JSONObject entity = new JSONObject();
            entity.put("redirect", "writer");
            return ResponseEntity.status(HttpStatus.OK).body(entity);
        } else if (adminService.authorizeAdmin(name, password)) {
            JSONObject entity = new JSONObject();
            entity.put("redirect", "admin");
            return ResponseEntity.status(HttpStatus.OK).body(entity);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}
