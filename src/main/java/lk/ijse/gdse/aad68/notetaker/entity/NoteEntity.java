package lk.ijse.gdse.aad68.notetaker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "notes")
@Entity
public class NoteEntity implements Serializable {
    @Id
    private String noteId;
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private UserEntity user;//methana define karapu name( @OneToMany(mappedBy = "user")) mehema dai
    private String noteTitle;
    private String noteDesc;
    private String priorityLevel;
    private String createDate;
}
