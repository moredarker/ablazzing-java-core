package homework_3_expert;

public class Human {
    private int age;
    private String name;
    private int weight;

    private Human() {
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    void info() {
        System.out.println(new StringBuilder()
                .append(getName())
                .append(" - возраст ")
                .append(getAge())
                .append(", вес ")
                .append(getWeight()));
    }

    static class HumanBuilder {
        private Human human;

        private HumanBuilder() {
            this.human = new Human();
        }

        public HumanBuilder age(int age) {
            this.human.setAge(age);
            return this;
        }

        public HumanBuilder name(String name) {
            this.human.setName(name);
            return this;
        }

        public HumanBuilder weight(int weight) {
            this.human.setWeight(weight);
            return this;
        }

        public Human build() {
            return human;
        }

    }

    static HumanBuilder builder() {
        return new HumanBuilder();
    }
}
