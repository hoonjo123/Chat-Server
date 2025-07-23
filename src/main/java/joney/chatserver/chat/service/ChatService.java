package joney.chatserver.chat.service;

import jakarta.transaction.Transactional;
import joney.chatserver.chat.repository.ChatMessageRepository;
import joney.chatserver.chat.repository.ChatParticipantRepository;
import joney.chatserver.chat.repository.ChatRoomRepository;
import joney.chatserver.chat.repository.ReadStatusRepository;
import joney.chatserver.member.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Transactional
@Service
public class ChatService {
    private final ChatRoomRepository chatRoomRepository;
    private final ChatParticipantRepository chatParticipantRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final ReadStatusRepository readStatusRepository;
    private final MemberRepository memberRepository;
}
