package news;

public enum Category {
    City("Город"), Business("Финансы"), Culture("Культура"), Science("Наука");

    private String name;

    Category(String name){
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}