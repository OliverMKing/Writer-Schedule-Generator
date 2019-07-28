package com.king.oliver.writerschedulegenerator.bootstrap;

import com.king.oliver.writerschedulegenerator.model.Editor;
import com.king.oliver.writerschedulegenerator.model.Schedule;
import com.king.oliver.writerschedulegenerator.model.Slot;
import com.king.oliver.writerschedulegenerator.model.Writer;
import com.king.oliver.writerschedulegenerator.services.EditorService;
import com.king.oliver.writerschedulegenerator.services.ScheduleService;
import com.king.oliver.writerschedulegenerator.services.SlotService;
import com.king.oliver.writerschedulegenerator.services.WriterService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;

@Component
public class DataLoader implements CommandLineRunner {

    private final WriterService writerService;
    private final EditorService editorService;
    private final ScheduleService scheduleService;

    public DataLoader(WriterService writerService, EditorService editorService, ScheduleService scheduleService) {
        this.writerService = writerService;
        this.editorService = editorService;
        this.scheduleService = scheduleService;
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

        LocalDate now = LocalDate.now();
        Schedule schedule = new Schedule();
        schedule.setName("January");
        schedule.addSlot(new Slot(now, caleb, oliver));
        schedule.addSlot(new Slot(now, grant, sam));
        schedule.addSlot(new Slot(now, stephane, oliver));
        scheduleService.save(schedule);
    }
}
