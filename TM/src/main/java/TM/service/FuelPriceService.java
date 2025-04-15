package TM.service;

import TM.model.FuelPrice;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class FuelPriceService {

    public FuelPrice getFuelPrice() {

        try {
            Document document = Jsoup.connect("https://www.cargopedia.ro/preturi-carburanti-europa").get();
            Elements rows = document.select("table tr");

            for (Element row : rows) {
                Elements cells = row.select("td");

                if (cells.size() >= 3) {
                    String country = cells.get(0).text();

                    if (country.equalsIgnoreCase("România")) {
                        String petrol = cells.get(2).text().replace(",", ".");
                        String diesel = cells.get(4).text().replace(",", ".");
                        return new FuelPrice(petrol, diesel);
                    }
                }
            }

            throw new RuntimeException("Romania not found in fuel price table.");

        } catch (IOException e) {
            throw new RuntimeException("No data found.");
        }
    }
}
