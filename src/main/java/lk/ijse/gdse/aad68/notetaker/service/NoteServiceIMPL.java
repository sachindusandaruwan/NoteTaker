package lk.ijse.gdse.aad68.notetaker.service;

import jakarta.transaction.Transactional;
import lk.ijse.gdse.aad68.notetaker.dao.NoteDao;
import lk.ijse.gdse.aad68.notetaker.dto.impl.NoteDTO;
import lk.ijse.gdse.aad68.notetaker.entity.NoteEntity;
import lk.ijse.gdse.aad68.notetaker.util.AppUtil;
import lk.ijse.gdse.aad68.notetaker.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public  class NoteServiceIMPL implements NoteService {
    @Autowired
    private NoteDao noteDao;
    @Autowired
    private Mapping mapping;

    @Override
    public String saveNote(NoteDTO noteDTO) {
        noteDTO.setNoteId(AppUtil.createNoteId());
        var noteEntity = mapping.convertToEntity(noteDTO);
        noteDao.save(noteEntity);
        return "Saved successfully in Service layer";
    }

    @Override
    public boolean updateNote(String noteId, NoteDTO incomeNoteDTO) {

       Optional<NoteEntity> tempNoteEntityById = noteDao.findById(noteId);
       if(!tempNoteEntityById.isPresent()){
           return false;
       }else {
           tempNoteEntityById.get().setNoteDesc(incomeNoteDTO.getNoteDesc());
           tempNoteEntityById.get().setNoteTitle(incomeNoteDTO.getNoteTitle());
           tempNoteEntityById.get().setCreateDate(incomeNoteDTO.getCreateDate());
           tempNoteEntityById.get().setPriorityLevel(incomeNoteDTO.getPriorityLevel());
       }
       return true;
    }

    @Override
    public boolean deleteNote(String noteId) {
//        System.out.println(noteId);
//        noteDao.deleteById(noteId);
//        System.out.println("delete successfully!!!");

        if (noteDao.existsById(noteId)) {
            noteDao.deleteById(noteId);
            return true;
        }else {
            return false;
        }

    }

    @Override
    public NoteDTO getSelectedNote(String noteId) {

//        NoteEntity selectNote = noteDao.getReferenceById(noteId);
//        return mapping.convertToDTO(selectNote);

        return mapping.convertToDTO(noteDao.getReferenceById(noteId));

    }



    @Override
    public List<NoteDTO> getAllNotes() {
//        List<NoteEntity> getAllNotes=noteDao.findAll();
//        List<NoteDTO> noteDTOS=mapping.convertToDTO(getAllNotes);
//        return noteDTOS;

       return mapping.convertToDTO(noteDao.findAll());
    }
}
