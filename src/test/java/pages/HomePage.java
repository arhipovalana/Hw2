package pages;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class HomePage {

    private BaseFunction baseFunc;

    private final By HOME_PAGE_ARTICLES = By.xpath("//article[contains(@class, 'headline')]");
    private final By HOME_PAGE_ARTICLE_TITLES = By.xpath(".//*[contains(@class, 'headline__title')]");
    private final By HOME_PAGE_ARTICLE_COMMENTS = By.xpath(".//a[@class = 'comment-count text-red-ribbon']");

    public HomePage(BaseFunction baseFunc) {
        this.baseFunc = baseFunc;
    }

    private List<WebElement> getArticleList() {
        List<WebElement> articleBlockList = baseFunc.getElementList(HOME_PAGE_ARTICLES);
        Assertions.assertFalse(articleBlockList.isEmpty(), "There is no any article on Home Page");
        return articleBlockList;
    }
//    // первый вариант метода для заголовка
//    public String getTitleText(Integer articleIndex) {
//    //    if (!getArticleList().isEmpty()) ; // тут нужна другая проверка - есть ли элемент в элементе
//        return getArticleList().get(articleIndex).findElement(HOME_PAGE_ARTICLE_TITLES).getText().trim();
//    }

    // второй вариант метода для заголовка
    public String getTitleText(Integer articleIndex) {
        if (getArticleList().size()>articleIndex){
            WebElement article = getArticleList().get(articleIndex); // тут получила элемент номер Х
            List<WebElement> titleList = article.findElements(HOME_PAGE_ARTICLE_TITLES); // тут ищу элемент в элементе Х
            if (!titleList.isEmpty()){
//                return article.getText().trim();
                return titleList.get(0).getText().trim();
            } else {
                return null;
//                return String.valueOf(article = null);
            }
        } else {
            return null;
        }
    }
//    // первый вариант метода для коментов
//    public Integer getCommentCount(Integer articleIndex) {
//        //    if (!getArticleList().isEmpty()) ; // тут нужна другая проверка - есть ли элемент в элементе
//        String commentCount = baseFunc.removeBrackets(getArticleList().get(articleIndex).findElement(HOME_PAGE_ARTICLE_COMMENTS).getText());
//        return baseFunc.changeStringToInteger(commentCount);
//    }

//    // второй вариант метода для коментов
    public Integer getCommentCount(Integer articleIndex) {
        if (getArticleList().size()>articleIndex) {
            WebElement article = getArticleList().get(articleIndex); // тут получила элемент номер Х
            List<WebElement> commentList = article.findElements(HOME_PAGE_ARTICLE_COMMENTS); // тут ищу элемент в элементе Х
            if (!commentList.isEmpty()) {
                String commentCount = baseFunc.removeBrackets(commentList.get(0).getText());
                return baseFunc.changeStringToInteger(commentCount);
            } else {
                Integer commentCount = 0;
                return commentCount;
            }
        } else {
            return null;
        }
    }

    public void openArticlePage(Integer articleIndex) {
        WebElement element = getArticleList().get(articleIndex).findElement(HOME_PAGE_ARTICLE_TITLES);
        baseFunc.clickOnElement(element);
    }

}