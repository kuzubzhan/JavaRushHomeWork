package com.javarush.test.level28.lesson15.big01.model;

import com.javarush.test.level28.lesson15.big01.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HHStrategy implements Strategy {
    private static final String URL_FORMAT = "http://hh.ua/search/vacancy?text=java+%s&page=%d";

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> vacancyList = new ArrayList<>();
        try {
            int page = 0;
            while (true) {
                Document doc = getDocument(searchString, page++);
                if (doc == null)
                    break;
                Elements elements = doc.select("[data-qa=vacancy-serp__vacancy]");
                if (elements.size() == 0)
                    break;
                for (Element currentElement : elements) {
                    Element titleElem = currentElement.select("[data-qa=vacancy-serp__vacancy-title]").first();
                    String title = titleElem.text();

                    Element salaryElem = currentElement.select("[data-qa=vacancy-serp__vacancy-compensation]").first();
                    String salary = "";
                    if (salaryElem != null) {
                        salary = salaryElem.text();
                    }

                    Element cityElem = currentElement.select("[data-qa=vacancy-serp__vacancy-address]").first();
                    String city = cityElem.text();

                    Element companyNameElem = currentElement.select("[data-qa=vacancy-serp__vacancy-employer]").first();
                    String companyName = companyNameElem.text();

                    String siteName = "http://hh.ua/";

                    Element urlElem = currentElement.select("[href]").first();
                    String url = urlElem.attr("abs:href");

                    Vacancy vacancy = new Vacancy();
                    vacancy.setTitle(title);
                    vacancy.setSalary(salary);
                    vacancy.setCity(city);
                    vacancy.setCompanyName(companyName);
                    vacancy.setSiteName(siteName);
                    vacancy.setUrl(url);

                    vacancyList.add(vacancy);
                }
            }
        } catch (IOException e) {
        }
        return vacancyList;
    }

    protected Document getDocument(String searchString, int page) throws IOException {
        String url = String.format(URL_FORMAT, searchString, page);
        return Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows NT 6.1; rv:51.0) Gecko/20100101 Firefox/51.0")
                .referrer("none")
                .get();
    }
}
