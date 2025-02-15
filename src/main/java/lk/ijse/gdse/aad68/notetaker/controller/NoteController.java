package lk.ijse.gdse.aad68.notetaker.controller;

import lk.ijse.gdse.aad68.notetaker.custom.NoteResponse;
import lk.ijse.gdse.aad68.notetaker.exception.DataPersistFailedException;
import lk.ijse.gdse.aad68.notetaker.exception.NoteNotFound;
import lk.ijse.gdse.aad68.notetaker.service.NoteService;
import lk.ijse.gdse.aad68.notetaker.dto.impl.NoteDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/notes")
@RequiredArgsConstructor
public class NoteController {
    @Autowired
    private final NoteService noteService;
   //Todo: CRUD of the note


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createNote(@RequestBody NoteDTO note) {
        if (note == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            try {
                noteService.saveNote(note);
                return new ResponseEntity<>(HttpStatus.CREATED);
            } catch (DataPersistFailedException e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

/*   public ResponseEntity<String> createNote(@RequestBody NoteDTO note) {

        //Todo: Handle with Service
        var saveData = noteService.saveNote(note);
       return ResponseEntity.ok(saveData);
    }*/


    @GetMapping(value = "allnotes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<NoteDTO> getAllNotes(){
        return noteService.getAllNotes();
    }
    @GetMapping(value = "/{noteId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public NoteResponse getNote(@PathVariable ("noteId") String noteId)  {
        return noteService.getSelectedNote(noteId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(value = "/{noteId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateNote(@PathVariable ("noteId") String noteId, @RequestBody NoteDTO note) {
        try {
            if(note == null && (noteId != null||noteId.isEmpty())) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            noteService.updateNote(noteId, note);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (NoteNotFound e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value ="/{noteId}" )
    public ResponseEntity<Void> deleteNote(@PathVariable ("noteId") String noteId) {
        try {
            noteService.deleteNote(noteId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);


        } catch (NoteNotFound e) {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

//        return noteService.deleteNote(noteId) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

//    @GetMapping(value = "health")
//    public String healthCheck() {
//        return "Note Taker is Running...";
//    }

}
