package lk.ijse.gdse.aad68.notetaker.util;

import lk.ijse.gdse.aad68.notetaker.dto.impl.NoteDTO;
import lk.ijse.gdse.aad68.notetaker.dto.impl.UserDTO;
import lk.ijse.gdse.aad68.notetaker.entity.NoteEntity;
import lk.ijse.gdse.aad68.notetaker.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;

    //matters of NoteEntity and DTO
    public NoteDTO convertToDTO(NoteEntity note) {
        return modelMapper.map(note, NoteDTO.class);
    }
    public NoteEntity convertToEntity(NoteDTO dto) {
        return modelMapper.map(dto, NoteEntity.class);
    }
    public List<NoteDTO> convertToDTO(List<NoteEntity> notes) {
        return modelMapper.map(notes, List.class);
    }

    //user matters mapping
    public UserEntity convertToEntity(UserDTO dto) {
        return modelMapper.map(dto, UserEntity.class);
    }
    public UserDTO convertToDTO(UserEntity dto) {
        return modelMapper.map(dto, UserDTO.class);
    }

    public List<UserDTO> convertToDto(List<UserEntity> userEntities) {
        return modelMapper.map(userEntities, List.class);
    }

    public UserDTO convertToUserDTO(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserDTO.class);
    }

    public List<UserDTO> convertToUserDTOS(List<UserEntity> users) {
        return modelMapper.map(users, new TypeToken<List<UserDTO>>() {}.getType());
    }
}

