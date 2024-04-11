package Capstone.Capstone.controller;


import Capstone.Capstone.service.UserService;

import Capstone.Capstone.dto.CreateRoomDto;
import Capstone.Capstone.dto.UserDto;
import Capstone.Capstone.entity.ChatRoom;
import Capstone.Capstone.service.ChatRoomSerivce;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class ChatRoomController {

    private final UserService userService;
    private final ChatRoomSerivce chatRoomSerivce;
    @Autowired
    public ChatRoomController(UserService userService,ChatRoomSerivce chatRoomSerivce) {
        this.userService = userService;
        this.chatRoomSerivce=chatRoomSerivce;
    }


    @PostMapping("/Chat")
    public ResponseEntity<Long> CreateChatRoom (@RequestBody CreateRoomDto createRoomDto){
        log.info("{},{}",createRoomDto.getUserId1(),createRoomDto.getUserId2());
        UserDto user1=UserDto.convertToDto(userService.getUserById(createRoomDto.getUserId1()));
        log.info("user1={}",user1);
        UserDto user2=UserDto.convertToDto(userService.getUserById(createRoomDto.getUserId2()));
        log.info("user2={}",user2);
        Long chatRoomId;
        if(chatRoomSerivce.hasChatRoom(user1.getId(),user2.getId())) {
           chatRoomId=chatRoomSerivce.getChatRoom(user1.getId(),user2.getId());
            return new ResponseEntity<>(chatRoomId, HttpStatus.OK);
        }
        else {
            ChatRoom chatRoom = chatRoomSerivce.createChatRoom(user1, user2);
            chatRoomId=chatRoom.getId();
            return new ResponseEntity<>(chatRoomId, HttpStatus.OK);
        }

    }
    @GetMapping("/getChatRoomNumber")
    public ResponseEntity<Long> GetChatRoomNumber(@RequestParam String userId1,@RequestParam String userId2){
        return new ResponseEntity<>(chatRoomSerivce.getChatRoom(userId1,userId2),HttpStatus.OK);
    }

}
