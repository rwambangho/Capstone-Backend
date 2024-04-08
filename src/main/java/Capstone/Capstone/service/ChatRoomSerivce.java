package Capstone.Capstone.service;

import Capstone.Capstone.dto.UserDto;
import Capstone.Capstone.entity.ChatRoom;

public interface ChatRoomSerivce {
   ChatRoom createChatRoom(UserDto user1, UserDto user2);

   Long getChatRoom(String userId1, String userId2);

   boolean hasChatRoom(String userId1, String userId2);


}
