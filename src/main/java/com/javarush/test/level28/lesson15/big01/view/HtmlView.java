package com.javarush.test.level28.lesson15.big01.view;

import com.javarush.test.level28.lesson15.big01.Controller;
import com.javarush.test.level28.lesson15.big01.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class HtmlView implements View {
    private Controller controller;
    private final String filePath = "./src/" + this.getClass().getPackage().getName().replace(".", "/") + "/vacancies.html";
    @Override
    public void update(List<Vacancy> vacancies) {
        updateFile(getUpdatedFileContent(vacancies));
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod() {
        controller.onCitySelect("Odessa");
    }

    private String getUpdatedFileContent(List<Vacancy> vacancies) {
        Document doc;
        try {
            doc = getDocument();
        } catch (IOException e) {
            e.printStackTrace();
            return "Some exception occurred";
        }

        Element templateElem = doc.select("[class~=template]").first();

        Element pattern = templateElem.clone();
        pattern.removeAttr("style");
        pattern.removeClass("template");

        doc.select("tr[class=vacancy]").remove();

        for (Vacancy vacancy : vacancies) {
            Element newVacancyElement = pattern.clone();

            newVacancyElement.select("[class=city]").first().text(vacancy.getCity());
            newVacancyElement.select("[class=companyName]").first().text(vacancy.getCompanyName());
            newVacancyElement.select("[class=salary]").first().text(vacancy.getSalary());
            newVacancyElement.select("a").first().text(vacancy.getTitle()).attr("href" , vacancy.getUrl());

            templateElem.before(newVacancyElement.outerHtml());
        }
        return doc.html();
    }

    private void updateFile(String newContent) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
            bw.write(newContent);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected Document getDocument() throws IOException {
        return Jsoup.parse(new File(filePath), "UTF-8");
    }
}
