package detrito.espacial.website.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import detrito.espacial.website.DTO.ScrapedDataDTO;
import detrito.espacial.website.service.ScrapingService;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "https://detritoespacial.com"})
public class ScrapingController {
    private ScrapingService scrapingService;

    public ScrapingController(ScrapingService scrapingService) {
        this.scrapingService = scrapingService;
    }

    @GetMapping("/scrape")
    public List<ScrapedDataDTO> scrapeWebsite(@RequestParam String url) throws IOException {
        return scrapingService.scrapeData(url);
    }
}
