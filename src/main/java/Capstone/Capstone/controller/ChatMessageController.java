package Capstone.Capstone.controller;


import Capstone.Capstone.dto.ChatMessageDto;
import Capstone.Capstone.dto.UserDto;
import Capstone.Capstone.entity.ChatMessage;
import Capstone.Capstone.service.ChatMessageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@RestController
@Tag(name="ChatMessage", description = "ChatMessage API입니다")
public class ChatMessageController {





    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ChatMessageService chatMessageService;
    @Autowired
    public ChatMessageController(SimpMessagingTemplate simpMessagingTemplate, ChatMessageService chatMessageService) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.chatMessageService = chatMessageService;
    }


    @MessageMapping("/chat/{roomId}")
    public void sendMessage(@Payload String message,@DestinationVariable Long roomId){
        log.info("message={}",message);
        log.info("roomId={}",roomId);
        ChatMessageDto chatMessageDto=new ChatMessageDto();
        UserDto userDto=new UserDto();
        String text =message;
        JSONObject obj = new JSONObject(text);
        String sender=obj.getString("sender");
        String realMessage=obj.getString("message");

        userDto.setId(sender);
        chatMessageDto.setUserDto(userDto);
        chatMessageDto.setRoomId(roomId);
        chatMessageDto.setMessage(realMessage);
        chatMessageDto.setId(0L);
        log.info("chatMessageDto={},{},{},{}",chatMessageDto.getId(),chatMessageDto.getRoomId(),chatMessageDto.getUserDto(),chatMessageDto.getMessage());
        ChatMessage chatMessage=chatMessageService.convertToEntity(chatMessageDto);
        log.info("chatMessage={},{},{},{}",chatMessage.getId(),chatMessage.getChatRoom(),chatMessage.getUser(),chatMessage.getMessage());

        chatMessageService.createChat(chatMessage);


        simpMessagingTemplate.convertAndSend("/topic/chat/"+roomId,message);

    }


    @GetMapping("/chat/history/{roomId}")
    public ResponseEntity<List<ChatMessageDto>> getChatHistory(@PathVariable Long roomId) {
        List<ChatMessage> messages = chatMessageService.getMessagesByRoomId(roomId);
        // Convert messages to DTOs
        List<ChatMessageDto> messageDtos = messages.stream()
                .map(chatMessageService::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(messageDtos);
    }








}


