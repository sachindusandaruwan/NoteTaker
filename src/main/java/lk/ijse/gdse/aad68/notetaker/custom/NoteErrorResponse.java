package lk.ijse.gdse.aad68.notetaker.custom;

import java.io.Serializable;

public class NoteErrorResponse implements NoteResponse, Serializable {
    private int errorCode;
    private String errorMessage;
}
