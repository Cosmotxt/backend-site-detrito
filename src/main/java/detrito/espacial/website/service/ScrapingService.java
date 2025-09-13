package detrito.espacial.website.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import detrito.espacial.website.DTO.ScrapedDataDTO;

@Service
public class ScrapingService {
    public List<ScrapedDataDTO> scrapeData(String url) throws IOException{
            List<ScrapedDataDTO> data = new ArrayList<>();
            Document document = Jsoup.connect(url).get();
            Elements productCards = document.select("div.card-thumb");
            
            for(Element product : productCards) {
                String productUrl = product.select("a.card-thumb__image-v2").first().attr("abs:href");
                String productImage = product.select("img[id^='image-']").first().attr("src");
                String productTitle = product.select(".card-thumb__title-ellipsis").text();
                String productPrice = product.select("div.card-thumb__prices-v2").first().text();

                data.add(new ScrapedDataDTO(productImage, productTitle, productPrice, productUrl));
            }
            return data;
    }
}
