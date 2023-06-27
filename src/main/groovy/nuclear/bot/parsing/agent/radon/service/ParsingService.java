package nuclear.bot.parsing.agent.radon.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ParsingService {

    @Value("${application.parseSiteUrl}")
    private String url;
    @Value("${application.xPath}")
    private String xPath;

    private final PageParser pageParser;

    @Scheduled(fixedRate = 10000)
    public void parse() {
        List<String> values = pageParser.getContent(url, xPath);
        log.info("Parsed values {}", values);
    }
}
