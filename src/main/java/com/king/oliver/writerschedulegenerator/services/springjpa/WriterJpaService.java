package com.king.oliver.writerschedulegenerator.services.springjpa;

import com.king.oliver.writerschedulegenerator.model.Writer;
import com.king.oliver.writerschedulegenerator.repositories.WriterRepository;
import com.king.oliver.writerschedulegenerator.services.WriterService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class WriterJpaService implements WriterService {

    private final WriterRepository writerRepository;

    public WriterJpaService(WriterRepository writerRepository) {
        this.writerRepository = writerRepository;
    }

    @Override
    public Set<Writer> findAll() {

        Set<Writer> writers = new HashSet<>();
        writerRepository.findAll().forEach(writers::add);
        return writers;
    }

    @Override
    public Writer findById(Long aLong) {
        return writerRepository.findById(aLong).orElse(null);
    }

    @Override
    public Writer save(Writer object) {
        return writerRepository.save(object);
    }

    @Override
    public void delete(Writer object) {
        writerRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        writerRepository.deleteById(aLong);
    }

    @Override
    public Writer findByName(String name) {
        return writerRepository.findByName(name);
    }
}
