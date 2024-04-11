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

import java.util.ArrayList;
import java.util.List;

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

        UserDto user1=UserDto.convertToDto(userService.getUserById(createRoomDto.getUserId1()));
        UserDto user2=UserDto.convertToDto(userService.getUserById(createRoomDto.getUserId2()));
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

    @GetMapping("/getAllChatRoomNumber")
    public ResponseEntity<List<String>>GetAllChatRoomNumber(@RequestParam String userId){
        List<Long>chatRooms=chatRoomSerivce.getAllChatRoom(userId);
        List<String> allOtherUsers = new ArrayList<>();

        for (Long chatRoomId : chatRooms) {
           String user = chatRoomSerivce.getUserInChatRoom(userId, chatRoomId);
            allOtherUsers.add(user);
        }
        return new ResponseEntity<>(allOtherUsers,HttpStatus.OK);
    }



}
