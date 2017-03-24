package ru.dvfu.agregator.service;

import org.springframework.stereotype.Service;
import ru.dvfu.agregator.model.Reader;
import ru.dvfu.agregator.repository.ReaderRepository;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * Created by anton on 20.03.17.
 */
@Service
public class ReaderService {
    @Resource
    private ReaderRepository readerRepository;

    public boolean authorizeReader(String login, String password) {
        Reader reader = readerRepository.getReaderByName(login);
        return reader != null && Objects.equals(reader.getPassword(), password);
    }

    public Reader getReaderByName(String name) {
        return readerRepository.getReaderByName(name);
    }

    public void addReader(String name, String password) {
        Reader reader = new Reader(name, password);
        readerRepository.save(reader);
    }

    public Reader save(Reader reader) {
        return readerRepository.save(reader);
    }
}
