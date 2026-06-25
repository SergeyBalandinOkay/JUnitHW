package data;

public enum NamePage {

    Доставка("Доставка"),
    Акции("Акции"),
    Скидки("Скидки");

    public final String selectionName;

    NamePage(String selectionName) {
        this.selectionName = selectionName;
    }
}