package nuclear.bot.parsing.agent.radon.service

import nuclear.bot.parsing.agent.radon.core.dto.AgentMessage
import org.springframework.stereotype.Service

import java.time.LocalDateTime

@Service
class String2AgentMessageMapper {

    private final String PARSER_AGENT_NAME = "don.ru/online-map/"

    def map(String value){
        def agentMessage = new AgentMessage()
        agentMessage.message=value
        agentMessage.parserAgentName = PARSER_AGENT_NAME
        agentMessage.messageDateTime = LocalDateTime.now()
        return agentMessage
    }


}
