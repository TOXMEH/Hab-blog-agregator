package ru.dvfu.agregator.service;

import org.springframework.stereotype.Service;
import ru.dvfu.agregator.model.Writer;
import ru.dvfu.agregator.repository.WriterRepository;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * Created by anton on 20.03.17.
 */
@Service
public class WriterService {
    @Resource
    private WriterRepository writerRepository;

    public boolean authorizeWriter(String login, String password) {
        Writer writer = writerRepository.getWriterByName(login);
        return writer != null && Objects.equals(writer.getPassword(), password);
    }

    public Writer getWriterByName(String name) {
        return writerRepository.getWriterByName(name);
    }

    public void addWriter(String name, String password) {
        Writer writer = new Writer(name, password);
        writerRepository.save(writer);
    }
}
