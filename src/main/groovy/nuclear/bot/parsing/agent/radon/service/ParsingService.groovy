package nuclear.bot.parsing.agent.radon.service

import lombok.RequiredArgsConstructor
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
    @Value('${application.xPath}')
    private String xPath
    Logger logger = Logger.getLogger(getClass().name)
    private final PageParser pageParser
    private final KafkaTemplate<String, AgentMessage> kafkaTemplate

    @Autowired
    ParsingService(PageParser pageParser, KafkaTemplate<String, AgentMessage> kafkaTemplate) {
        this.pageParser = pageParser
        this.kafkaTemplate = kafkaTemplate
    }

    @Scheduled(fixedRate = 10_000)
    void parse() {
        def values = pageParser.getContent(url, xPath)
        logger.info "Parsed values ${values}"
    }

    void sendMessage(List<String> values) {

    }
}
