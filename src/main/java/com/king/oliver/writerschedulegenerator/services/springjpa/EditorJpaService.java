package com.king.oliver.writerschedulegenerator.services.springjpa;

import com.king.oliver.writerschedulegenerator.model.Editor;
import com.king.oliver.writerschedulegenerator.repositories.EditorRepository;
import com.king.oliver.writerschedulegenerator.services.EditorService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class EditorJpaService implements EditorService {

    private final EditorRepository editorRepository;

    public EditorJpaService(EditorRepository editorRepository) {
        this.editorRepository = editorRepository;
    }

    @Override
    public Set<Editor> findAll() {

        HashSet<Editor> editors = new HashSet<>();
        editorRepository.findAll().forEach(editors::add);
        return editors;
    }

    @Override
    public Editor findById(Long aLong) {
        return editorRepository.findById(aLong).orElse(null);
    }

    @Override
    public Editor save(Editor object) {
        return editorRepository.save(object);
    }

    @Override
    public void delete(Editor object) {
        editorRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        editorRepository.deleteById(aLong);
    }
}
