package com.king.oliver.writerschedulegenerator.bootstrap;

import com.king.oliver.writerschedulegenerator.model.Editor;
import com.king.oliver.writerschedulegenerator.model.Writer;
import com.king.oliver.writerschedulegenerator.services.EditorService;
import com.king.oliver.writerschedulegenerator.services.WriterService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final WriterService writerService;
    private final EditorService editorService;

    public DataLoader(WriterService writerService, EditorService editorService) {
        this.writerService = writerService;
        this.editorService = editorService;
    }

    @Override
    public void run(String... args) throws Exception {

        int dataSize = writerService.findAll().size();
        if (dataSize == 0) {
            loadData();
        }
    }

    private void loadData() {
        Writer caleb = new Writer("Caleb Gedemer", "https://www.pokebeach.com/author/caleb-gedemer");
        writerService.save(caleb);

        Writer grant = new Writer("Grant Manley", "https://www.pokebeach.com/author/grant-manley");
        writerService.save(grant);

        Writer stephane = new Writer("Stephane Ivanoff", "https://www.pokebeach.com/forums/members/lubyllule.117481/");
        writerService.save(stephane);

        Editor oliver = new Editor("Oliver King");
        editorService.save(oliver);

        Editor sam = new Editor("Sam VerNooy");
        editorService.save(sam);
    }
}
