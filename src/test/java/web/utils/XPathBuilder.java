package web.utils;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

/**
 * Класс описывает методы-шаблоны, которые возвращают XPath элементы
 */
public class XPathBuilder {
    public static SelenideElement element(String tag, String attribute, String param) {
        return $x("//" + tag + "[@" + attribute + "='" + param + "']");
    }

    public static SelenideElement elementText(String tag, String attribute, String param) {
        return $x("//" + tag + "[" + attribute + "() ='" + param + "']");
    }

    public static SelenideElement containsElement(String tag, String attribute, String name) {
        return $x("//" + tag + "[contains(" + attribute + ",'" + name + "')]");
    }
    public static SelenideElement containsElementInclude(String tag, String attribute, String name,String include) {
        return $x("//" + tag + "[contains(" + attribute + ",'" + name + "')]"+include);
    }
    public static SelenideElement numberElement(String tag, String attribute, String param, String number) {
        return $x("//" + tag + "[@" + attribute + "='" + param + "']"+"["+number+"]");
    }
    public static SelenideElement parentElement(String tag, String attribute, String param, String parent) {
        return $x("//" + tag + "[@" + attribute + "='" + param + "']"+parent);
    }
}
