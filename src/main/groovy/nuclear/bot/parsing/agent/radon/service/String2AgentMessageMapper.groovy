package nuclear.bot.parsing.agent.radon.service

import nuclear.bot.parsing.agent.radon.core.dto.AgentMessage
import org.springframework.stereotype.Service

import java.time.OffsetDateTime

@Service
class String2AgentMessageMapper {

    private final String PARSER_AGENT_NAME = "www.radon.ru"

    def map(String value){
        def agentMessage = new AgentMessage()
        agentMessage.message=value
        agentMessage.parserAgentName = PARSER_AGENT_NAME
        agentMessage.messageDateTime = OffsetDateTime.now().toString()
        return agentMessage
    }


}
