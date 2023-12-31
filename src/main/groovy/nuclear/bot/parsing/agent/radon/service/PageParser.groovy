package nuclear.bot.parsing.agent.radon.service

import nuclear.bot.parsing.agent.radon.core.dto.AgentMessage
import org.jsoup.Jsoup
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import us.codecraft.xsoup.Xsoup

import java.util.logging.Logger

@Service
class PageParser {
    Logger logger = Logger.getLogger(getClass().name)
    private final String2AgentMessageMapper string2AgentMessageMapper;

    @Autowired
    PageParser(String2AgentMessageMapper string2AgentMessageMapper) {
        this.string2AgentMessageMapper = string2AgentMessageMapper
    }

    List<AgentMessage> getContent(String url, String xPath) {
        def document = Jsoup.connect(url).get()
        def xsoup = Xsoup.compile(xPath).evaluate(document).get()
        def textBlock = Arrays.stream(xsoup.split("\n\t\t"))
                .filter(value -> value.startsWith "var iconText")
                .findFirst()
                .map(v -> v.replaceAll "var iconText = \\[", "")
                .map(v -> v.replaceAll "\\\\'", "")
                .map(v -> v.replaceAll "',", "")
                .map(v -> v.replaceAll "'", "")
                .map(v -> v.replaceAll "];", "")
        if (textBlock.isPresent()) {
            def values = Arrays
                    .stream(textBlock.get().split("<div.class=placemarkName>"))
                    .map(v -> v.replaceAll "</div> ", "")
                    .filter(v -> v.trim().length() != 0)
                    .toList()
            logger.info "Parsed values ${values.size()}"
            return values.stream()
                    .map(v -> string2AgentMessageMapper.map v)
                    .toList() as List<AgentMessage>
        }
        logger.info("There is empty parsed result")
        return Collections.EMPTY_LIST
    }
}
