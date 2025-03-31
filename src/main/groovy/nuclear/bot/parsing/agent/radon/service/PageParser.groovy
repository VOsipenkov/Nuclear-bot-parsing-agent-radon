package nuclear.bot.parsing.agent.radon.service

import nuclear.bot.parsing.agent.radon.core.dto.AgentMessage
import org.jsoup.Jsoup
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import us.codecraft.xsoup.Xsoup

import java.time.LocalDateTime
import java.util.logging.Logger

@Service
class PageParser {
    Logger logger = Logger.getLogger(getClass().name)
    private final String2AgentMessageMapper string2AgentMessageMapper;

    @Autowired
    PageParser(String2AgentMessageMapper string2AgentMessageMapper) {
        this.string2AgentMessageMapper = string2AgentMessageMapper
    }

    List<AgentMessage> getContent(String url) {
        def document = Jsoup.connect(url).get()
        def contentText = document.selectXpath("//pre[contains(@style, 'display:none')]").text()

        // Ищем все вхождения паттерна [name] => значение
        def matcher = contentText =~ /\[name\]\s*=>\s*(.*)/

        // Собираем найденные значения в список и возвращаем
        def values = matcher
                .collect { it[1].trim() }
                .collect { string2AgentMessageMapper.map(it) }
        return values as List<AgentMessage>;
    }
}
