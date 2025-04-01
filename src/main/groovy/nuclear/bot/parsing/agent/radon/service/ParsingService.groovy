package nuclear.bot.parsing.agent.radon.service

import nuclear.bot.parsing.agent.radon.core.dto.AgentMessage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

import java.util.logging.Logger

@Service
class ParsingService {

    @Value('${application.parseSiteUrl}')
    private String url
    @Value('${spring.kafka.template.default-topic}')
    private String topicName
    Logger logger = Logger.getLogger(getClass().name)
    private final PageParser pageParser
    private final KafkaTemplate<String, AgentMessage> kafkaTemplate

    @Autowired
    ParsingService(PageParser pageParser, KafkaTemplate<String, AgentMessage> kafkaTemplate) {
        this.pageParser = pageParser
        this.kafkaTemplate = kafkaTemplate
    }

    @Scheduled(fixedRate = 60_000)
    void parse() {
        def values = pageParser.getContent(url)
        logger.info "Parsed values"
        sendMessage(values)
    }

    void sendMessage(List<AgentMessage> messages) {
        messages.each { message -> kafkaTemplate.send(topicName, message) }
        logger.info "Send ${messages?.size()} messages"
    }
}
