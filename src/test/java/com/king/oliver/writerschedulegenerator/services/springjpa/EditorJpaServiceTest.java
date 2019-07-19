package com.king.oliver.writerschedulegenerator.services.springjpa;

import com.king.oliver.writerschedulegenerator.model.Editor;
import com.king.oliver.writerschedulegenerator.repositories.EditorRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EditorJpaServiceTest {

    @Mock
    EditorRepository editorRepository;

    @InjectMocks
    EditorJpaService editorJpaService;

    private Editor editor;

    @Before
    public void setUp() throws Exception {
        editor = new Editor("Test Editor");
        editor.setId(1L);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findAll() {
        Set<Editor> editors = new HashSet<>();
        editors.add(new Editor("Editor 1"));
        editors.add(new Editor("Editor 2"));

        when(editorRepository.findAll()).thenReturn(editors);

        Set<Editor> returnedEditors = editorJpaService.findAll();

        assertNotNull(returnedEditors);
        assertEquals(editors.size(), returnedEditors.size());
    }

    @Test
    public void findById() {
        when(editorRepository.findById(anyLong())).thenReturn(Optional.of(editor));
        Editor returnEditor = editorJpaService.findById(1L);
        assertNotNull(returnEditor);
    }

    @Test
    public void save() {
        when(editorRepository.save(editor)).thenReturn(editor);
        Editor returnEditor = editorJpaService.save(editor);
        assertNotNull(returnEditor);
        verify(editorRepository).save(any());
    }

    @Test
    public void delete() {
        editorJpaService.delete(editor);
        verify(editorRepository, times(1)).delete(any());
    }

    @Test
    public void deleteById() {
        editorJpaService.deleteById(1L);
        verify(editorRepository, times(1)).deleteById(anyLong());
    }

    @Test
    public void findByName() {
        when(editorRepository.findByName(anyString())).thenReturn(editor);
        Editor returnEditor = editorJpaService.findByName("Test Editor");
        assertEquals("Test Editor", returnEditor.getName());
        verify(editorRepository).findByName(any());
    }
}