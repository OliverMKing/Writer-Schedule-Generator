package com.king.oliver.writerschedulegenerator.bootstrap;

import com.king.oliver.writerschedulegenerator.services.WriterService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final WriterService writerService;

    public DataLoader(WriterService writerService) {
        this.writerService = writerService;
    }

    @Override
    public void run(String... args) throws Exception {

        int dataSize = writerService.findAll().size();
        if (dataSize == 0) {
            loadData();
        }
    }

    private void loadData() {
    }
}
