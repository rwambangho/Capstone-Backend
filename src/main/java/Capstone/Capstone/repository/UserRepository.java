package Capstone.Capstone.repository;

import Capstone.Capstone.dto.UserDto;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepository {

    private Map<String, UserDto> userMap = new HashMap<>();

    public void save(UserDto userDTO) {
        userMap.put(userDTO.getId(), userDTO);
    }



    public UserDto findById(String Id) {
        return userMap.get(Id);
    }

    public void updatePassword(String userId, String newPassword) {
        userMap.computeIfPresent(userId, (key, userDTO) -> {
            userDTO.setPassword(newPassword);
            return userDTO;
        });
    }
}
