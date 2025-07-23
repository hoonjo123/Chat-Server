package joney.chatserver.chat.repository;

import joney.chatserver.chat.domain.ChatParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatParticipantRepository extends JpaRepository<ChatParticipant, Long> {
}
