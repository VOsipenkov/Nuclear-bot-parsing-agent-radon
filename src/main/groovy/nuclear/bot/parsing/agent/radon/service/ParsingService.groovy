package nuclear.bot.parsing.agent.radon.service

import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
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

    @Autowired
    ParsingService(PageParser pageParser) {
        this.pageParser = pageParser
    }

    @Scheduled(fixedRate = 10000)
    void parse() {
        def values = pageParser.getContent(url, xPath)
        logger.info "Parsed values ${values}"
    }
}
