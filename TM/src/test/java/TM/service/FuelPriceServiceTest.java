package TM.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import TM.model.FuelPrice;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FuelPriceServiceTest {

  private final FuelPriceService fuelPriceService = new FuelPriceService();

  @Test
  void getFuelPriceWhenDataExists() {
    String html =
        """
            <html>
              <body>
                <table>
                  <tr>
                    <td>România</td><td>other</td><td>6.50</td><td>other</td><td>7,.0</td>
                  </tr>
                </table>
              </body>
            </html>
            """;

    Document document = Jsoup.parse(html);

    FuelPrice result = fuelPriceService.getFuelPriceFromHtml(document);

    assertEquals("6.50", result.getPetrolPrice());
  }

  @Test
  void getFuelPriceWhenNoDataExists() {
    String html =
        """
                <html>
                  <body>
                    <table>
                      <tr>
                        <td>Any Country name here</td><td>other</td><td>6.50</td><td>other</td><td>7,.0</td>
                      </tr>
                    </table>
                  </body>
                </html>
                """;

    Document document = Jsoup.parse(html);

    RuntimeException exception =
        assertThrows(RuntimeException.class, () -> fuelPriceService.getFuelPriceFromHtml(document));

    assertEquals("Romania not found in fuel price table.", exception.getMessage());
  }
}
