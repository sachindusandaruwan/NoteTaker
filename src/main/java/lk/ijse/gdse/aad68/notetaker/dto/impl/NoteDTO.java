package lk.ijse.gdse.aad68.notetaker.dto.impl;

import lk.ijse.gdse.aad68.notetaker.custom.NoteResponse;
import lk.ijse.gdse.aad68.notetaker.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NoteDTO implements SuperDTO, NoteResponse {
    private String noteId;
    private String noteTitle;
    private String noteDesc;
    private String priorityLevel;
    private String createDate;
    private String userId;
}
