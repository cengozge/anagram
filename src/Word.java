public class Word {
    private String name;
    private Integer index;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIndex() {
        return this.index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Word(String name, Integer index) {
        this.name = name;
        this.index = index;
    }
}